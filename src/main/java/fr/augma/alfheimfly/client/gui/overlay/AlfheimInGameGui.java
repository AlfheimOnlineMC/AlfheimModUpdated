package fr.augma.alfheimfly.client.gui.overlay;

import javax.annotation.Nullable;

import com.mojang.realmsclient.gui.ChatFormatting;
import fr.augma.alfheimfly.items.AlfheimItemSword;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;

public class AlfheimInGameGui extends GuiIngameForge {
	
	public static ResourceLocation position = new ResourceLocation(AlfheimRef.MODID, "textures/gui/overlay/position.png"),
			healthBar = new ResourceLocation(AlfheimRef.MODID, "textures/gui/health/healthbar.png"),
			health = new ResourceLocation(AlfheimRef.MODID, "textures/gui/health/health.png"),
			mana = new ResourceLocation(AlfheimRef.MODID, "textures/gui/health/mana.png"),
			food = new ResourceLocation(AlfheimRef.MODID, "textures/gui/health/food.png"),
			itemxpbackground = new ResourceLocation(AlfheimRef.MODID, "textures/gui/item_exp/background.png"),
			itemxpfiller = new ResourceLocation(AlfheimRef.MODID, "textures/gui/item_exp/filler.png"),
			absorption = new ResourceLocation(AlfheimRef.MODID, "textures/gui/health/absorption.png");
	public int pWidth = 80, pHeight = 20;
	public static ResourceLocation raceLogo = null;
	public static String region = null;
	
		
	public AlfheimInGameGui(Minecraft mc) {
		super(mc);
	}
	
	@Override
	public void renderGameOverlay(float partialTicks) {
		super.renderGameOverlay(partialTicks);
		
		if(!mc.gameSettings.showDebugInfo) {
			this.renderHealthBar(getResolution().getScaledWidth(), getResolution().getScaledHeight());
			if(raceLogo != null) {
				bind(raceLogo);
				Gui.drawModalRectWithCustomSizedTexture(13, 17, 0, 0, 45, 35, 45, 35);
				
				if(region != null) {
					this.drawCenteredString(mc.fontRenderer, region, getResolution().getScaledWidth() / 2, 5, 0xffffff);
				}
			}
		}
		
		if(this.getItemInMainHand().getItem() instanceof AlfheimItemSword) this.renderItemXp(this.getItemInMainHand());

		if(mc.pointedEntity != null) {
			renderInteract();
		}
	}
	
	private void bind(ResourceLocation res) {
        mc.getTextureManager().bindTexture(res);
    }
	
	private void renderItemXp(ItemStack item) {
		int level = item.hasTagCompound() ? item.getTagCompound().getInteger("level") : 0;
		float current_exp = item.hasTagCompound() ? item.getTagCompound().getFloat("experience") : 0,
		max_exp = item.hasTagCompound() ? item.getTagCompound().getFloat("max_experience") : (float) (level * (level + 2)) + 20f;
		
		this.drawCenteredString(getFontRenderer(), ChatFormatting.GRAY + "Level : " + Integer.toString(level) + "/" + ((AlfheimItemSword) item.getItem()).getRarity().getMaxLevel(), getResolution().getScaledWidth() - 75, getResolution().getScaledHeight() - 30, 0xffffff);
		GlStateManager.pushMatrix();
		bind(itemxpbackground);
		Gui.drawModalRectWithCustomSizedTexture(getResolution().getScaledWidth() - 125, getResolution().getScaledHeight() - 17, 0, 0, 100, 7, 100, 7);
		
		float oneUnit = (float) 100 / max_exp;
        int currentWidth = (int)(oneUnit * current_exp);
		
		bind(itemxpfiller);
		if(level == ((AlfheimItemSword) item.getItem()).getRarity().getMaxLevel()) currentWidth = 100;
		Gui.drawModalRectWithCustomSizedTexture(getResolution().getScaledWidth() - 125, getResolution().getScaledHeight() - 17, 0, 0, currentWidth, 7, 100, 7);

		GlStateManager.popMatrix();
	}

	private void renderInteract() {
		this.drawString(mc.fontRenderer, mc.pointedEntity.getName(), getResolution().getScaledWidth() / 2 + 15, getResolution().getScaledHeight() / 2, 0xffffff);
		this.drawString(mc.fontRenderer, "Press KEY to interact" + mc.pointedEntity, getResolution().getScaledWidth() / 2 + 15, getResolution().getScaledHeight() / 2 + 10, 0xffffff);
	}
	
	private void renderHealthBar(int width, int height) {
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.75f, 0.75f, 0.75f);
		this.drawString(getFontRenderer(), this.mc.player.getName(), 74, 21, 0xffffff);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.scale(0.03d, 0.03d, 0.03d);
		bind(healthBar);
		Gui.drawModalRectWithCustomSizedTexture(350, 500, 0, 0, 7856, 1313, 7856, 1313);
		bind(health);
		float unit = (float) 6033 / mc.player.getMaxHealth();
		int current = (int) (unit * mc.player.getHealth());
		Gui.drawModalRectWithCustomSizedTexture(1819, 848, 0, 0, current, 181, 6033, 181);
		if(mc.player.getAbsorptionAmount() > 0) {
			bind(absorption);
			current = (int) (unit * mc.player.getAbsorptionAmount());
			if(mc.player.getAbsorptionAmount() > mc.player.getMaxHealth()) current = 6033;
			Gui.drawModalRectWithCustomSizedTexture(1819, 848, 0, 0, current, 181, 6033, 181);
		}
		bind(mana);
		Gui.drawModalRectWithCustomSizedTexture(1819, 1078, 0, 0, 6033, 182, 6033, 182);
		bind(food);
		unit = (float) 4472 / 20;
		current = (int) (unit * mc.player.getFoodStats().getFoodLevel());
		Gui.drawModalRectWithCustomSizedTexture(1819, 1304, 0, 0, current, 183, 4472, 183);
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}
	
	private ItemStack getItemInMainHand() {
		return mc.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
	}
	
	public static void setLogo(@Nullable ResourceLocation logo) {
		raceLogo = logo;
	}
	
	public static void setRegion(@Nullable String reg) {
		region = reg;
	}
}
