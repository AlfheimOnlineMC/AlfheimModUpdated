package fr.augma.alfheimfly.client.gui.button;

import org.lwjgl.opengl.GL11;

import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class DisconnectButton extends GuiButton {
	
	public static ResourceLocation IDLE = new ResourceLocation(AlfheimRef.MODID, "textures/gui/buttons/leave_idle.png");
	public static ResourceLocation HOVER = new ResourceLocation(AlfheimRef.MODID, "textures/gui/buttons/leave_hover.png");

	public DisconnectButton(int x, int y, int widthIn, int heightIn) {
		super(0, x, y, widthIn, heightIn, "");
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
