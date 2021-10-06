package fr.augma.alfheimfly.packet;

import fr.augma.alfheimfly.client.gui.overlay.AlfheimInGameGui;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BukkitPacket implements IMessage {
	
	public String sentance;
	
	public BukkitPacket() {}
	
	public BukkitPacket(String phrase) {
		this.sentance = phrase;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.sentance = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, this.sentance);
	}
	
	public static class Handler implements IMessageHandler<BukkitPacket, IMessage> {

		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(BukkitPacket message, MessageContext ctx) {
			AlfheimInGameGui.setRegion(message.sentance);
			return null;
		}
		
	}
}
