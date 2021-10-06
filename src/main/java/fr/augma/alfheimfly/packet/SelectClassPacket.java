package fr.augma.alfheimfly.packet;

import fr.augma.alfheimfly.AlfheimFly;
import fr.augma.alfheimfly.capabilities.IPlayerDataCap;
import fr.augma.alfheimfly.capabilities.PlayerDataCapProvider;
import fr.augma.alfheimfly.common.AlfheimServer;
import fr.augma.alfheimfly.event.CommonEventHandler;
import fr.augma.alfheimfly.utils.race.AlfheimRace;
import fr.augma.alfheimfly.utils.race.CaitSith;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SelectClassPacket implements IMessage {
	
	public String race;
	
	public SelectClassPacket() {}
	
	public SelectClassPacket(String raceName) {
		this.race = raceName;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.race = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, this.race);
	}
	
	public static class Handler implements IMessageHandler<SelectClassPacket, IMessage> {

		@Override
		public IMessage onMessage(SelectClassPacket message, MessageContext ctx) {
			IPlayerDataCap cap = PlayerDataCapProvider.get(ctx.getServerHandler().player);
			if(cap.getRace() == null) {
				AlfheimRace race = AlfheimRace.getRace(message.race);
				cap.setRace(race);
				ctx.getServerHandler().player.sendMessage(new TextComponentString("Bravo vous avez choisi la classe " + message.race + " !"));
				PlayerDataCapProvider.sync(ctx.getServerHandler().player);
				AlfheimServer.HashWings.put(ctx.getServerHandler().player.getUniqueID(), cap.getRace().getEnumRace());
				AlfheimFly.network.sendToAll(new SyncWingsPacket(AlfheimServer.HashWings));
				if(race instanceof CaitSith) {
					if(!CommonEventHandler.caitPlayerServ.contains(ctx.getServerHandler().player.getName())) {
						CommonEventHandler.caitPlayerServ.add(ctx.getServerHandler().player.getName());
						AlfheimFly.network.sendToAll(new RefreshCaitPlayer(CommonEventHandler.caitPlayerServ));
					}
				} else if(CommonEventHandler.caitPlayerServ.contains(ctx.getServerHandler().player.getName())) {
					CommonEventHandler.caitPlayerServ.remove(ctx.getServerHandler().player.getName());
					AlfheimFly.network.sendToAll(new RefreshCaitPlayer(CommonEventHandler.caitPlayerServ));
				}
			} else {
				ctx.getServerHandler().player.sendMessage(new TextComponentString("Mais vous possedez déjà une classe è_é !"));
			}
			
			return null;
		}
		
	}

}
