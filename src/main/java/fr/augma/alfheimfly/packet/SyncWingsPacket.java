package fr.augma.alfheimfly.packet;

import java.util.HashMap;
import java.util.UUID;

import fr.augma.alfheimfly.client.player.layer.LayerWings;
import fr.augma.alfheimfly.utils.race.EnumRace;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SyncWingsPacket implements IMessage {
	
	private HashMap<UUID, EnumRace> HashMapWings = new HashMap<>();
	
	public SyncWingsPacket() {}
	
	public SyncWingsPacket(HashMap<UUID, EnumRace> hashmap) {
		this.HashMapWings = hashmap;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		while(buf.isReadable()) {
			this.HashMapWings.put(UUID.fromString(ByteBufUtils.readUTF8String(buf)), EnumRace.getEnumByString(ByteBufUtils.readUTF8String(buf)));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		this.HashMapWings.forEach((k, v) -> {
			ByteBufUtils.writeUTF8String(buf, k.toString());
			ByteBufUtils.writeUTF8String(buf, v.name());
		});
	}

	public static class Handler implements IMessageHandler<SyncWingsPacket, IMessage> {

		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(SyncWingsPacket message, MessageContext ctx) {
			LayerWings.refreshHashMap(message.HashMapWings);
			return null;
		}
		
	}
}
