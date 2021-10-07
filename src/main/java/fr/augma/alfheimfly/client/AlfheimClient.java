package fr.augma.alfheimfly.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.*;

import javax.imageio.ImageIO;

import com.google.common.collect.Multimap;
import com.mojang.realmsclient.gui.ChatFormatting;
import fr.augma.alfheimfly.items.AlfheimItemSword;
import fr.augma.alfheimfly.utils.RuneUtils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.apache.commons.io.IOUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;

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
	private static IPCClient client;
	private static final ResourceLocation[] wings = new ResourceLocation[18];

	public static void setRPC(String details) {
		if(client == null) {
			client = new IPCClient(801084290866610186L);
			client.setListener(new IPCListener(){
			    @Override
			    public void onReady(IPCClient client) {
			        client.sendRichPresence(getRPC(details));
			    }
			});
			try {
				client.connect();
			} catch (NoDiscordClientException | RuntimeException e) {
				client = null;
			}
		} else {
			client.sendRichPresence(getRPC(details));
		}
	}
	
	private static RichPresence getRPC(String details) {
		RichPresence.Builder builder = new RichPresence.Builder();
        builder.setState("Coming soon ...")
            .setDetails(details)
            .setLargeImage("alo")
        	.setButton1("Discord", "https://discord.gg/Yw9ZN6bMU3")
        	.setButton2("Site", "https://letmegooglethat.com/?q=Il+est+en+dev");
        return builder.build();
	}
	
	@Override
	public void preinit(File configfile) {
		Display.setTitle("Alfheim Online 1.12.2 - " + Minecraft.getMinecraft().getSession().getUsername());
		this.setWindowIcon();
		this.loadWings();
		OBJLoader.INSTANCE.addDomain(AlfheimRef.MODID);
		MinecraftForge.EVENT_BUS.register(this);
		RenderHandler.registerEntityRenders();
		RenderHandler.registerArmorRenderer();
		setRPC("ALO mmorpg mc [fr]");
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

		if(e.getItemStack().getItem() instanceof AlfheimItemSword) {
			ItemStack item = e.getItemStack();
			e.getToolTip().add(e.getItemStack().getDisplayName() + " [" + (item.hasTagCompound() ? item.getTagCompound().getInteger("level") : 0) + "]");
		} else {
			e.getToolTip().add(e.getItemStack().getDisplayName());
		}

		HashMap<String, AttributeModifier> stats = new HashMap<>();
		HashMap<String, AttributeModifier> statsRune = new HashMap<>();

		e.getToolTip().add("");
		if(e.getItemStack().getItem() instanceof ItemSword) {
			if(e.getItemStack().getAttributeModifiers(EntityEquipmentSlot.MAINHAND).size() > 0) {
				for (Map.Entry<String, AttributeModifier> entry : e.getItemStack().getAttributeModifiers(EntityEquipmentSlot.MAINHAND).entries()) {
					AttributeModifier att = entry.getValue();
					if(att.getName().contains("rune")) {
						statsRune.put(entry.getKey(), att);
					} else {
						stats.put(entry.getKey(), att);
					}
				}

				if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
					e.getToolTip().add(ChatFormatting.GOLD + "" + ChatFormatting.STRIKETHROUGH + "     " + ChatFormatting.GOLD + "»" + ChatFormatting.BLUE + " " + I18n.translateToLocal("tooltip.header.equipment") + " " + ChatFormatting.GOLD + "«" + ChatFormatting.STRIKETHROUGH  +"     ");
					e.getToolTip().add("");

					this.displayStats(stats, e.getToolTip(), e);

					e.getToolTip().add("");
					e.getToolTip().add(ChatFormatting.GOLD + "" + ChatFormatting.STRIKETHROUGH + "     " + ChatFormatting.GOLD + "»" + ChatFormatting.BLUE + " " + I18n.translateToLocal("tooltip.header.rune") + " " + ChatFormatting.GOLD + "«" + ChatFormatting.STRIKETHROUGH  +"     ");
					e.getToolTip().add("");

					this.displayStats(statsRune, e.getToolTip(), e);

					e.getToolTip().add("");
				} else {
					e.getToolTip().add(ChatFormatting.GOLD + "" + ChatFormatting.STRIKETHROUGH + "     " + ChatFormatting.GOLD + "»" + ChatFormatting.BLUE + " " + I18n.translateToLocal("tooltip.header.all") + " " + ChatFormatting.GOLD + "«" + ChatFormatting.STRIKETHROUGH  +"     ");
					e.getToolTip().add("");
					this.displayOverallStats(EntityEquipmentSlot.MAINHAND, e);
					e.getToolTip().add(ChatFormatting.DARK_GRAY + I18n.translateToLocalFormatted("tooltip.hold", Keyboard.getKeyName(Keyboard.KEY_LSHIFT)));
				}
			}
		} else if(e.getItemStack().getItem() instanceof ItemArmor) {
			EntityEquipmentSlot type = ((ItemArmor) e.getItemStack().getItem()).armorType;
			if(e.getItemStack().getAttributeModifiers(type).size() > 0) {
				for (Map.Entry<String, AttributeModifier> entry : e.getItemStack().getAttributeModifiers(type).entries()) {
					AttributeModifier att = entry.getValue();
					if(att.getName().contains("rune")) {
						statsRune.put(entry.getKey(), att);
					} else {
						stats.put(entry.getKey(), att);
					}
				}

				if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
					e.getToolTip().add(ChatFormatting.GOLD + "" + ChatFormatting.STRIKETHROUGH + "     " + ChatFormatting.GOLD + "»" + ChatFormatting.BLUE + " " + I18n.translateToLocal("tooltip.header.equipment") + " " + ChatFormatting.GOLD + "«" + ChatFormatting.STRIKETHROUGH  +"     ");
					e.getToolTip().add("");

					this.displayStats(stats, e.getToolTip(), e);

					e.getToolTip().add("");
					e.getToolTip().add(ChatFormatting.GOLD + "" + ChatFormatting.STRIKETHROUGH + "     " + ChatFormatting.GOLD + "»" + ChatFormatting.BLUE + " " + I18n.translateToLocal("tooltip.header.rune") + " " + ChatFormatting.GOLD + "«" + ChatFormatting.STRIKETHROUGH  +"     ");
					e.getToolTip().add("");

					this.displayStats(statsRune, e.getToolTip(), e);

					e.getToolTip().add("");
				} else {
					e.getToolTip().add(ChatFormatting.GOLD + "" + ChatFormatting.STRIKETHROUGH + "     " + ChatFormatting.GOLD + "»" + ChatFormatting.BLUE + " " + I18n.translateToLocal("tooltip.header.all") + " " + ChatFormatting.GOLD + "«" + ChatFormatting.STRIKETHROUGH  +"     ");
					e.getToolTip().add("");
					this.displayOverallStats(type, e);
					e.getToolTip().add(ChatFormatting.DARK_GRAY + I18n.translateToLocalFormatted("tooltip.hold", Keyboard.getKeyName(Keyboard.KEY_LSHIFT)));
				}
			}
		}
		e.getToolTip().add("");
		e.getToolTip().add(ChatFormatting.BLUE + "Alfheim Online MMORPG");
	}

	private void displayStats(HashMap<String, AttributeModifier> map, List<String> lore, ItemTooltipEvent e) {
		double d0;
		for(Map.Entry<String, AttributeModifier> attRune : map.entrySet()) {
			d0 = attRune.getValue().getAmount();

			if(e.getEntityPlayer() != null) {
				if(attRune.getValue().getID().equals(UUID.fromString("cb3f55d3-645c-4f38-a497-9c13a33db5cf"))) {
					d0 += e.getEntityPlayer().getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
				} else if (attRune.getValue().getID().equals(UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca3"))) {
					d0 += e.getEntityPlayer().getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue();
				}
			}
			if(d0 <= 0D) continue;

			double d1;

			if (attRune.getValue().getOperation() != 1 && attRune.getValue().getOperation() != 2) {
				d1 = d0;
			} else {
				d1 = d0 * 100.0D;
			}

			lore.add(I18n.translateToLocalFormatted("attribute.alfheim.modifier.equals." + attRune.getValue().getOperation(), ChatFormatting.BOLD + "> " + ChatFormatting.DARK_AQUA + I18n.translateToLocal("attribute.name.alfheim." + attRune.getKey()) + ":", ChatFormatting.GRAY + ItemStack.DECIMALFORMAT.format(d1)));
		}
	}

	private void displayOverallStats(EntityEquipmentSlot type, ItemTooltipEvent e) {
		ItemStack item = e.getItemStack();
		HashMap<String, Double> stats = new HashMap<>();

		for(Map.Entry<String, AttributeModifier> att : item.getAttributeModifiers(type).entries()) {
			double d0 = att.getValue().getAmount();

			if(e.getEntityPlayer() != null) {
				if(att.getValue().getID().equals(UUID.fromString("cb3f55d3-645c-4f38-a497-9c13a33db5cf"))) {
					d0 += e.getEntityPlayer().getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
				} else if (att.getValue().getID().equals(UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca3"))) {
					d0 += e.getEntityPlayer().getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue();
				}
			}

			if(stats.containsKey(att.getKey() + att.getValue().getOperation())) {
				stats.put(att.getKey() + att.getValue().getOperation(), stats.get(att.getKey() + att.getValue().getOperation()) + att.getValue().getAmount());
			} else {
				stats.put(att.getKey() + att.getValue().getOperation(), d0);
			}
		}

		for(Map.Entry<String, Double> statsOverall : stats.entrySet()) {
			double d1;
			String name = statsOverall.getKey().substring(0, statsOverall.getKey().length() - 1);
			int operation = Integer.valueOf(statsOverall.getKey().substring(statsOverall.getKey().length() - 1));

			if(statsOverall.getValue() <= 0D) continue;

			if (operation != 1 && operation != 2) {
				d1 = statsOverall.getValue();
			} else {
				d1 = statsOverall.getValue() * 100.0D;
			}

			e.getToolTip().add(I18n.translateToLocalFormatted("attribute.alfheim.modifier.equals." + operation, ChatFormatting.BOLD + "> " + ChatFormatting.DARK_AQUA + I18n.translateToLocal("attribute.name.alfheim." + name) + ":", ChatFormatting.GRAY + ItemStack.DECIMALFORMAT.format(d1)));
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
            catch (IOException ioexception) {}
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
