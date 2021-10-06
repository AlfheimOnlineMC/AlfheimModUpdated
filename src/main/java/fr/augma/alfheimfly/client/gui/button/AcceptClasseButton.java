package fr.augma.alfheimfly.client.gui.button;

import org.lwjgl.opengl.GL11;

import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class AcceptClasseButton extends GuiButton {
	
	public static ResourceLocation IDLE = new ResourceLocation(AlfheimRef.MODID, "textures/gui/buttons/accept_idle.png");
	public static ResourceLocation HOVER = new ResourceLocation(AlfheimRef.MODID, "textures/gui/buttons/accept_hover.png");

	public AcceptClasseButton(int x, int y, int width, int height) {
		super(0, x, y, width, height, "");
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if(this.visible) {
        	boolean mouseHover = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        	if(mouseHover) {
                mc.getTextureManager().bindTexture(HOVER);
            } else {
                mc.getTextureManager().bindTexture(IDLE);
            }
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Gui.drawModalRectWithCustomSizedTexture(this.x, this.y, 0.0F, 0.0F, this.width, this.height, this.width, this.height);
        }
	}
}
