package fr.augma.alfheimfly.client.gui;

import java.io.IOException;

import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.config.GuiConfig;

public class GuiIngameMenuCustom extends GuiIngameMenu {

	
	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.add(new GuiButton(10, this.width / 2 - 100, this.height / 4 + 120 + -16 + 24, I18n.format("Alfheim Configuration")));
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button.id == 10) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiConfig(this, AlfheimRef.MODID, "Alfheim Fly"));
		}
		
	}}
