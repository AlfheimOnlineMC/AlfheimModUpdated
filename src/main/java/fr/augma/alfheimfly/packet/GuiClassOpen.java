package fr.augma.alfheimfly.packet;

import fr.augma.alfheimfly.client.gui.GuiScreenClassChoice;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiClassOpen implements IMessage {
	
	public GuiClassOpen() {}

	@Override
	public void fromBytes(ByteBuf buf) {}

	@Override
	public void toBytes(ByteBuf buf) {}
	
	public static class Handler implements IMessageHandler<GuiClassOpen, IMessage> {

		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(GuiClassOpen message, MessageContext ctx) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiScreenClassChoice());
 			return null;
		}
	}
}
