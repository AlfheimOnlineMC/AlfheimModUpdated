package fr.augma.alfheimfly.packet;

import fr.augma.alfheimfly.capabilities.IPlayerDataCap;
import fr.augma.alfheimfly.capabilities.PlayerDataCapProvider;
import fr.augma.alfheimfly.client.gui.overlay.AlfheimInGameGui;
import fr.augma.alfheimfly.utils.race.AlfheimRace;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SCPacketDataCap implements IMessage {
	
	private NBTTagCompound data;
	
	public SCPacketDataCap() {}
	
	public SCPacketDataCap(NBTTagCompound data) {
		this.data = data;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.data = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, this.data);
	}

	public static class Handler implements IMessageHandler<SCPacketDataCap, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(SCPacketDataCap message, MessageContext ctx) {
            PlayerDataCapProvider.get(Minecraft.getMinecraft().player).data(message.data);
            IPlayerDataCap cap = PlayerDataCapProvider.get(Minecraft.getMinecraft().player);
            AlfheimRace race = cap.getRace();
            if(race != null) {
            	AlfheimInGameGui.setLogo(race.getIcone());
            }
            return null;
        }
    }
}
