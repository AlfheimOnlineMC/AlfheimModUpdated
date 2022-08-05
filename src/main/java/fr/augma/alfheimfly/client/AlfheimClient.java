package fr.augma.alfheimfly.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import com.mojang.realmsclient.gui.ChatFormatting;
import fr.augma.alfheimfly.AlfheimFly;
import fr.augma.alfheimfly.items.AlfheimItemSword;
import fr.augma.alfheimfly.packet.ToggleFlyPacket;
import fr.augma.alfheimfly.utils.RuneUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.apache.commons.io.IOUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import fr.augma.alfheimfly.client.gui.GuiIngameMenuCustom;
import fr.augma.alfheimfly.client.gui.overlay.AlfheimInGameGui;
import fr.augma.alfheimfly.client.player.layer.LayerCaitSithTail;
import fr.augma.alfheimfly.client.player.layer.LayerEar;
import fr.augma.alfheimfly.client.player.layer.LayerWings;
import fr.augma.alfheimfly.common.AlfheimCommon;
import fr.augma.alfheimfly.config.ModConfig;
import fr.augma.alfheimfly.utils.AlfheimRef;
import fr.augma.alfheimfly.utils.handlers.RenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class AlfheimClient extends AlfheimCommon {

	private static final ResourceLocation[] wings = new ResourceLocation[18];
	public static KeyBinding keyWings = new KeyBinding("Activate wings", Keyboard.KEY_P, "Alfheim Online");

	private static final Comparator<String> comparator = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return I18n.translateToLocal("attribute.name.alfheim." + o1).compareTo(I18n.translateToLocal("attribute.name.alfheim." + o2));
		}
	};
	
	@Override
	public void preinit(File configfile) {
		Display.setTitle("Alfheim Online 1.12.2 - " + Minecraft.getMinecraft().getSession().getUsername());
		this.setWindowIcon();
		this.loadWings();
		OBJLoader.INSTANCE.addDomain(AlfheimRef.MODID);
		MinecraftForge.EVENT_BUS.register(this);
		RenderHandler.registerEntityRenders();
		RenderHandler.registerArmorRenderer();
		ClientRegistry.registerKeyBinding(keyWings);
	}

	@Override
	public void init() {
		RenderPlayer render;
		render = Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default");
		render.addLayer(new LayerEar(render));
		render.addLayer(new LayerCaitSithTail());
		render.addLayer(new LayerWings());
		
		render = Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim");
		render.addLayer(new LayerEar(render));
		render.addLayer(new LayerCaitSithTail());
		render.addLayer(new LayerWings());
	}
	
	@SubscribeEvent
	public void onWorldLoad(FMLNetworkEvent.ClientConnectedToServerEvent event) {
		Minecraft.getMinecraft().ingameGUI = new AlfheimInGameGui(Minecraft.getMinecraft());
	}
	
	@SubscribeEvent
	public void onGui(GuiOpenEvent e) {
		if(e.getGui() instanceof GuiIngameMenu) {
			e.setGui(new GuiIngameMenuCustom());
		}
	}

	@SubscribeEvent
	public void onToolTip(ItemTooltipEvent e) {
		if(!(e.getItemStack().getItem() instanceof ItemSword) && !(e.getItemStack().getItem() instanceof ItemArmor)) {
			e.getToolTip().add("");
			e.getToolTip().add(ChatFormatting.BLUE + "Alfheim Online MMORPG");
			return;
		}

		e.getToolTip().clear();
		this.displayStats(e, Keyboard.isKeyDown(Keyboard.KEY_LSHIFT));
		e.getToolTip().add(ChatFormatting.BLUE + "Alfheim Online MMORPG");
	}

	@SubscribeEvent
	public void onKeyPressed(InputEvent.KeyInputEvent event) {
		if(keyWings.isPressed()) {
			AlfheimFly.network.sendToServer(new ToggleFlyPacket());
		}
	}

	private void displayStats(ItemTooltipEvent e, boolean rune) {
		ItemStack item = e.getItemStack();
		if(item.getItem() instanceof AlfheimItemSword) {
			e.getToolTip().add(item.getDisplayName() + " [" + (item.hasTagCompound() ? item.getTagCompound().getInteger("level") : 0) + "]");
		} else {
			e.getToolTip().add(item.getDisplayName());
		}
		e.getToolTip().add("");

		EntityEquipmentSlot type = EntityEquipmentSlot.MAINHAND;

		if(e.getItemStack().getItem() instanceof ItemArmor) {
			type = ((ItemArmor) e.getItemStack().getItem()).armorType;
		}

		if(item.getAttributeModifiers(type).size() <= 0) return;

		if(rune) {
			this.display(e,"tooltip.header.equipment", getWeaponStats(e, type));
			e.getToolTip().add("");
			this.display(e,"tooltip.header.rune", getRuneStats(e, type));
			e.getToolTip().add("");
		} else {
			LinkedHashMap<String, Double> stats = new LinkedHashMap<>();

			for(Map.Entry<String, AttributeModifier> att : item.getAttributeModifiers(type).entries()) {
				double d0 = att.getValue().getAmount();

				if(e.getEntityPlayer() != null) {
					if(att.getValue().getID().equals(UUID.fromString("cb3f55d3-645c-4f38-a497-9c13a33db5cf"))) {
						d0 += e.getEntityPlayer().getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
					} else if (att.getValue().getID().equals(UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca3"))) {
						d0 += e.getEntityPlayer().getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue();
					}
				}

				if(d0 <= 0D) continue;

				if(stats.containsKey(att.getKey())) {
					stats.put(att.getKey(), stats.get(att.getKey()) + att.getValue().getAmount());
				} else {
					stats.put(att.getKey(), d0);
				}
			}

			stats = stats.entrySet().stream().sorted(Map.Entry.comparingByKey(comparator)).collect(Collectors.toMap(
					Map.Entry::getKey,
					Map.Entry::getValue,
					(oldValue, newValue) -> oldValue, LinkedHashMap::new));

			this.display(e, "tooltip.header.all", stats);
			e.getToolTip().add(ChatFormatting.DARK_GRAY + I18n.translateToLocalFormatted("tooltip.hold", Keyboard.getKeyName(Keyboard.KEY_LSHIFT)));
			e.getToolTip().add("");
		}
	}

	private HashMap<String, Double> getRuneStats(ItemTooltipEvent e, EntityEquipmentSlot type) {
		LinkedHashMap<String, Double> stats = new LinkedHashMap<>();

		for(Map.Entry<String, AttributeModifier> att : e.getItemStack().getAttributeModifiers(type).entries()) {
			if(!att.getValue().getName().contains("rune")) continue;

			if(att.getValue().getAmount() <= 0D) continue;

			if(stats.containsKey(att.getKey())) {
				stats.put(att.getKey(), stats.get(att.getKey()) + att.getValue().getAmount());
			} else {
				double d0 = att.getValue().getAmount();
				if(e.getEntityPlayer() != null) {
					if(att.getValue().getID().equals(UUID.fromString("cb3f55d3-645c-4f38-a497-9c13a33db5cf"))) {
						d0 += e.getEntityPlayer().getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
					} else if (att.getValue().getID().equals(UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca3"))) {
						d0 += e.getEntityPlayer().getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue();
					}
				}
				stats.put(att.getKey(), d0);
			}
		}

		stats = stats.entrySet().stream().sorted(Map.Entry.comparingByKey(comparator)).collect(Collectors.toMap(
				Map.Entry::getKey,
				Map.Entry::getValue,
				(oldValue, newValue) -> oldValue, LinkedHashMap::new));

		return stats;
	}

	private HashMap<String, Double> getWeaponStats(ItemTooltipEvent e, EntityEquipmentSlot type) {
		LinkedHashMap<String, Double> stats = new LinkedHashMap<>();
		for(Map.Entry<String, AttributeModifier> att : e.getItemStack().getAttributeModifiers(type).entries()) {
			if(att.getValue().getName().contains("rune")) continue;

			if(att.getValue().getAmount() <= 0D) continue;

			if(stats.containsKey(att.getKey())) {
				stats.put(att.getKey(), stats.get(att.getKey()) + att.getValue().getAmount());
			} else {
				double d0 = att.getValue().getAmount();
				if(e.getEntityPlayer() != null) {
					if(att.getValue().getID().equals(UUID.fromString("cb3f55d3-645c-4f38-a497-9c13a33db5cf"))) {
						d0 += e.getEntityPlayer().getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
					} else if (att.getValue().getID().equals(UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca3"))) {
						d0 += e.getEntityPlayer().getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue();
					}
				}
				stats.put(att.getKey(), d0);
			}
		}

		stats = stats.entrySet().stream().sorted(Map.Entry.comparingByKey(comparator)).collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));

		return stats;
	}

	private void display(ItemTooltipEvent e, String section, HashMap<String, Double> map) {
		e.getToolTip().add(ChatFormatting.GOLD + "" + ChatFormatting.STRIKETHROUGH + "     " + ChatFormatting.GOLD + "»" + ChatFormatting.BLUE + " " + I18n.translateToLocal(section) + " " + ChatFormatting.GOLD + "«" + ChatFormatting.STRIKETHROUGH  +"     ");

		double d1;

		for(Map.Entry<String, Double> att : map.entrySet()) {
			if (!att.getKey().equalsIgnoreCase(RuneUtils.CRIT.getName()) && !att.getKey().equalsIgnoreCase(RuneUtils.CRIT_DMG.getName()) && !att.getKey().equalsIgnoreCase(RuneUtils.LIFE_STEAL.getName())) {
				d1 = att.getValue();
				e.getToolTip().add(I18n.translateToLocalFormatted("attribute.alfheim.modifier.equals.0", ChatFormatting.BOLD + "> " + ChatFormatting.DARK_AQUA + I18n.translateToLocal("attribute.name.alfheim." + att.getKey()) + ":", ChatFormatting.GRAY + ItemStack.DECIMALFORMAT.format(d1)));
			} else {
				d1 = att.getValue() * 100.0D;
				e.getToolTip().add(I18n.translateToLocalFormatted("attribute.alfheim.modifier.equals.0", ChatFormatting.BOLD + "> " + ChatFormatting.DARK_AQUA + I18n.translateToLocal("attribute.name.alfheim." + att.getKey()) + ":", ChatFormatting.GRAY + ItemStack.DECIMALFORMAT.format(d1)) + " %");
			}
		}
	}

	private void loadWings() {
		wings[0] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/cait_sith_texture.png");
		wings[1] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/cait_sith_texture_1250.png");
		wings[2] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/gnome_texture.png");
		wings[3] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/gnome_texture_1250.png");
		wings[4] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/pooka_texture.png");
		wings[5] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/pooka_texture_1250.png");
		wings[6] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/salamender_texture.png");
		wings[7] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/salamender_texture_1250.png");
		wings[8] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/spriggan_texture.png");
		wings[9] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/spriggan_texture_1250.png");
		wings[10] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/sylph_texture.png");
		wings[11] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/sylph_texture_1250.png");
		wings[12] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/undine_texture.png");
		wings[13] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/undine_texture_1250.png");
		wings[14] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/leprechaun_texture.png");
		wings[15] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/leprechaun_texture_1250.png");
		wings[16] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/imp_texture.png");
		wings[17] = new ResourceLocation(AlfheimRef.MODID, "textures/wings/imp_texture_1250.png");
	}

	public static ResourceLocation getWings(int index) {
		return wings[index + ModConfig.wingsConfig.WingResolution.ordinal()];
	}
	
	private void setWindowIcon() {
        Util.EnumOS util$enumos = Util.getOSType();

        if (util$enumos != Util.EnumOS.OSX) {
            InputStream inputstream = null;
            InputStream inputstream1 = null;

            try {
                inputstream = this.getStreamAssetsIcon(new ResourceLocation(AlfheimRef.MODID, "alo/ALO_rounded-16x.png"));
                inputstream1 = this.getStreamAssetsIcon(new ResourceLocation(AlfheimRef.MODID, "alo/ALO_half_rounded-32x.png"));
                if (inputstream != null && inputstream1 != null) {
                    Display.setIcon(new ByteBuffer[] {this.readImageToBuffer(inputstream), this.readImageToBuffer(inputstream1)});
                }
            }
            catch (IOException ignored) {}
            finally {
                IOUtils.closeQuietly(inputstream);
                IOUtils.closeQuietly(inputstream1);
            }
        }
    }
	
	private InputStream getStreamAssetsIcon(ResourceLocation resource) {
		return getClass().getClassLoader().getResourceAsStream("assets/" + resource.getResourceDomain() + "/" + resource.getResourcePath());
	}
	
	private ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException {
        BufferedImage bufferedimage = ImageIO.read(imageStream);
        int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[])null, 0, bufferedimage.getWidth());
        ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);

        for (int i : aint) {
            bytebuffer.putInt(i << 8 | i >> 24 & 255);
        }

        bytebuffer.flip();
        return bytebuffer;
    }
}
