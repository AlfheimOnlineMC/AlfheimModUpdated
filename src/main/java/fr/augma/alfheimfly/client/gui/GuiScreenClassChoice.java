package fr.augma.alfheimfly.client.gui;

import java.io.IOException;

import org.lwjgl.input.Mouse;

import fr.augma.alfheimfly.AlfheimFly;
import fr.augma.alfheimfly.client.gui.button.AcceptClasseButton;
import fr.augma.alfheimfly.client.gui.button.DisconnectButton;
import fr.augma.alfheimfly.client.gui.button.RaceBaseButton;
import fr.augma.alfheimfly.packet.SelectClassPacket;
import fr.augma.alfheimfly.utils.AlfheimRef;
import fr.augma.alfheimfly.utils.race.AlfheimRace;
import fr.augma.alfheimfly.utils.race.EnumRace;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.ResourceLocation;

public class GuiScreenClassChoice extends GuiScreen {
	
	public boolean GrabbedMouse = true;
	public boolean selected = false;
	public AlfheimRace race = null;
	public static ResourceLocation TEXTURE = new ResourceLocation(AlfheimRef.MODID, "textures/gui/background/test_background.png");
	
	public GuiScreenClassChoice() {}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		int x = (this.width - 185) / 2, y = (this.height - 228) / 2;
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		mc.getTextureManager().bindTexture(TEXTURE);
		drawModalRectWithCustomSizedTexture(x, y, 0, 0, 250, 223, 250, 223);
		GlStateManager.disableBlend();
		super.drawScreen(mouseX, mouseY, partialTicks);
		if(GrabbedMouse) {
			Mouse.setGrabbed(false);
			GrabbedMouse = !GrabbedMouse;
		}
	}
	
	@Override
	public void initGui() {
		int x = (this.width - 340) / 2, y = (this.height - 230) / 2;
		this.buttonList.clear();
		this.buttonList.add(new RaceBaseButton(x, y, 85, 25, EnumRace.CaitSith));
		this.buttonList.add(new RaceBaseButton(x, y + 25, 85, 25, EnumRace.Imp));
		this.buttonList.add(new RaceBaseButton(x, y + 50, 85, 25, EnumRace.Spriggan));
		this.buttonList.add(new RaceBaseButton(x, y + 75, 85, 25, EnumRace.Gnome));
		this.buttonList.add(new RaceBaseButton(x, y + 100, 85, 25, EnumRace.Sylph));
		this.buttonList.add(new RaceBaseButton(x, y + 125, 85, 25, EnumRace.Undine));
		this.buttonList.add(new RaceBaseButton(x, y + 150, 85, 25, EnumRace.Salamender));
		this.buttonList.add(new RaceBaseButton(x, y + 175, 85, 25, EnumRace.Pooka));
		this.buttonList.add(new RaceBaseButton(x, y + 200, 85, 25, EnumRace.Leprechaun));
		this.buttonList.add(new AcceptClasseButton(x + 285, y + 185, 25, 25));
		this.buttonList.add(new DisconnectButton(x + 292, y + 15, 20, 20));
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button instanceof RaceBaseButton) {
			RaceBaseButton btn = (RaceBaseButton) button;
			btn.setSelected();
			this.race = btn.getRace();
			RaceBaseButton btnunselect;
			for(GuiButton bouton : this.buttonList) {
				if(bouton instanceof RaceBaseButton) {
					btnunselect = (RaceBaseButton) bouton;
					if(btnunselect.isSelected && btnunselect != btn) btnunselect.setUnselected();
				}
			}
		} else if(button instanceof AcceptClasseButton) {
			if(this.race != null) {
				this.selected = !this.selected;
				mc.displayGuiScreen(null);
				AlfheimFly.network.sendToServer(new SelectClassPacket(this.race.getName()));
			} else {
				mc.player.sendChatMessage("Sélectionne une classe avant de cliquer sur ce bouton");
			}
		}else {
			boolean flag = this.mc.isIntegratedServerRunning();
            boolean flag1 = this.mc.isConnectedToRealms();
            button.enabled = false;
            this.mc.world.sendQuittingDisconnectingPacket();
            this.mc.loadWorld((WorldClient)null);
            this.selected = !selected;

            if (flag) {
                this.mc.displayGuiScreen(new GuiMainMenu());
            } else if (flag1) {
                RealmsBridge realmsbridge = new RealmsBridge();
                realmsbridge.switchToRealms(new GuiMainMenu());
            } else {
                this.mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
            }
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {}
	
	@Override
	public void onGuiClosed() {
		if(!selected) {
			mc.displayGuiScreen(new GuiScreenClassChoice());
		}
	}
}
