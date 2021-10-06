package fr.augma.alfheimfly.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class setSkin extends CommandBase {

	@Override
	public String getName() {
		return "setSkin";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/setskin";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer p = getCommandSenderAsPlayer(sender);
		
		p.inventory.getCurrentItem().setTagCompound(new NBTTagCompound());
		p.inventory.getCurrentItem().getTagCompound().setInteger("skin", 2);
	}

}
