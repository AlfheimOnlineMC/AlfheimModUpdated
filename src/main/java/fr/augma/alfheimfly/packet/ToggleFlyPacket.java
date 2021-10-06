package fr.augma.alfheimfly.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ToggleFlyPacket implements IMessage {
	
	public ToggleFlyPacket() {}

	@Override
	public void fromBytes(ByteBuf buf) {}

	@Override
	public void toBytes(ByteBuf buf) {}
	
	public static class Handler implements IMessageHandler<ToggleFlyPacket, IMessage> {

		@Override
		public IMessage onMessage(ToggleFlyPacket message, MessageContext ctx) {
			ctx.getServerHandler().player.capabilities.allowFlying = !ctx.getServerHandler().player.capabilities.allowFlying;
			if(!ctx.getServerHandler().player.capabilities.allowFlying) {
				if(ctx.getServerHandler().player.capabilities.isFlying) {
					ctx.getServerHandler().player.capabilities.isFlying = false;
				}
			}
			ctx.getServerHandler().player.sendPlayerAbilities();
			return null;
		}
		
	}

}
