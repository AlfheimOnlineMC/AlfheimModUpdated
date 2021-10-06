package fr.augma.alfheimfly.packet;

import java.util.ArrayList;
import java.util.List;

import fr.augma.alfheimfly.client.player.layer.LayerCaitSithTail;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RefreshCaitPlayer implements IMessage {
	
	private List<String> cait = new ArrayList<>();
	
	public RefreshCaitPlayer() {}
	
	public RefreshCaitPlayer(List<String> player) {
		this.cait = player;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		while(buf.isReadable()) cait.add(ByteBufUtils.readUTF8String(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		for(String p : cait) ByteBufUtils.writeUTF8String(buf, p);
	}
	
	public static class Handler implements IMessageHandler<RefreshCaitPlayer, IMessage> {

		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(RefreshCaitPlayer message, MessageContext ctx) {
			LayerCaitSithTail.refreshPlayer(message.cait);
			return null;
		}
		
	}

}
