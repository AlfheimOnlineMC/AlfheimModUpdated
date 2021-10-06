package fr.augma.alfheimfly.client.gui.button;

import org.lwjgl.opengl.GL11;

import fr.augma.alfheimfly.utils.AlfheimRef;
import fr.augma.alfheimfly.utils.race.AlfheimRace;
import fr.augma.alfheimfly.utils.race.EnumRace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class RaceBaseButton extends GuiButton {
	public ResourceLocation IDLE;
	public ResourceLocation HOVER;
	public ResourceLocation SELECTED;
	public AlfheimRace race;
	public boolean isSelected;
	

	public RaceBaseButton(int x, int y, int width, int height, EnumRace race) {
		super(0, x, y, width, height, "");
		this.IDLE = new ResourceLocation(AlfheimRef.MODID, "textures/gui/buttons/" + race.name().toLowerCase() + "_idle.png");
		this.HOVER = new ResourceLocation(AlfheimRef.MODID, "textures/gui/buttons/" + race.name().toLowerCase() + "_hover.png");
		this.SELECTED = new ResourceLocation(AlfheimRef.MODID, "textures/gui/buttons/" + race.name().toLowerCase() + "_select.png");
		this.isSelected = false;
		this.race = AlfheimRace.getRace(race);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if(this.visible) {
        	boolean mouseHover = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        	if(this.isSelected) {
        		mc.getTextureManager().bindTexture(this.SELECTED);
        	}else if(mouseHover) {
                mc.getTextureManager().bindTexture(this.HOVER);
            } else {
                mc.getTextureManager().bindTexture(this.IDLE);
            }
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Gui.drawModalRectWithCustomSizedTexture(this.x, this.y, 0.0F, 0.0F, this.width, this.height, this.width, this.height);
        }
	}
	
	public void setSelected() {
		this.isSelected = true;
	}
	
	public void setUnselected() {
		this.isSelected = false;
	}
	
	public boolean isSelected() {
		return this.isSelected;
	}
	
	public AlfheimRace getRace() {
		return this.race;
	}
}
