package fr.augma.alfheimfly.capabilities;

import java.util.Optional;

import javax.annotation.Nullable;

import fr.augma.alfheimfly.AlfheimFly;
import fr.augma.alfheimfly.packet.SCPacketDataCap;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PlayerDataCapProvider implements ICapabilitySerializable<NBTBase> {
	
	public static final ResourceLocation NAME = new ResourceLocation(AlfheimRef.MODID, "DMM_CAP");
	
	@CapabilityInject(IPlayerDataCap.class)
    public static final Capability<IPlayerDataCap> CAPABILITY = null;
	
	private final IPlayerDataCap plrData = CAPABILITY.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CAPABILITY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CAPABILITY) {
            return (T) plrData;
        }
        return null;
	}

	@Override
	public NBTBase serializeNBT() {
		return CAPABILITY.writeNBT(this.plrData, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		CAPABILITY.readNBT(this.plrData, null, nbt);
	}
	
	public final static IPlayerDataCap get(final EntityPlayer player) {
        return Optional.of(player.getCapability(CAPABILITY, null)).orElse(new IPlayerDataCap.Impl());
    }
	
	public static void sync(EntityPlayer player) {
        if (!player.world.isRemote) {
            AlfheimFly.network.sendTo(new SCPacketDataCap(get(player).data()), (EntityPlayerMP) player);
        }
    }
	
	public static void register() {
        CapabilityManager.INSTANCE.register(IPlayerDataCap.class, new PlayerDataCapProvider.Storage(), IPlayerDataCap.Impl::new);
    }
	
	public static class Storage implements Capability.IStorage<IPlayerDataCap> {

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IPlayerDataCap> capability, IPlayerDataCap instance, EnumFacing side) {
            return instance.data().copy();
        }

        @Override
        public void readNBT(Capability<IPlayerDataCap> capability, IPlayerDataCap instance, EnumFacing side, NBTBase nbt) {
            instance.data((NBTTagCompound) nbt.copy());
        }
    }
}
