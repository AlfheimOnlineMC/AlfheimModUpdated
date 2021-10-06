package fr.augma.alfheimfly.utils.handlers;

import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class AlfheimGuiHandler implements IGuiHandler {


    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch(ID) {
            case AlfheimRef.JOJOTABLE:
                //DO STUFF
                break;
        }

        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch(ID) {
            case AlfheimRef.JOJOTABLE:
                //DO STUFF
                break;
        }

        return null;
    }
}
