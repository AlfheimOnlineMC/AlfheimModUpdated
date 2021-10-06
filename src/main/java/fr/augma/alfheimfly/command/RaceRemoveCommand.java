package fr.augma.alfheimfly.command;

import fr.augma.alfheimfly.AlfheimFly;
import fr.augma.alfheimfly.capabilities.IPlayerDataCap;
import fr.augma.alfheimfly.capabilities.PlayerDataCapProvider;
import fr.augma.alfheimfly.packet.GuiClassOpen;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class RaceRemoveCommand extends CommandBase {

	@Override
	public String getName() {
		return "removerace";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/removerace";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer p = getCommandSenderAsPlayer(sender);
		IPlayerDataCap cap = PlayerDataCapProvider.get(p);
		cap.setRace(null);
		PlayerDataCapProvider.sync(p);
		AlfheimFly.network.sendTo(new GuiClassOpen(), (EntityPlayerMP) p);
	}
}
