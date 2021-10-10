package fr.augma.alfheimfly.command;

import fr.augma.alfheimfly.items.RuneUnpolishedItem;
import fr.augma.alfheimfly.utils.RuneUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class polish extends CommandBase {
    @Override
    public String getName() {
        return "polish";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/polish";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player = getCommandSenderAsPlayer(sender);
        ItemStack unpolishedRune = player.getHeldItemMainhand();

        if(unpolishedRune.getItem() instanceof RuneUnpolishedItem) {
            ItemStack runePolished = new ItemStack(((RuneUnpolishedItem) unpolishedRune.getItem()).getPolished());

            RuneUtils.roll(runePolished, RuneUtils.RuneType.getRand());

            player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount() - 1);
            player.inventory.addItemStackToInventory(runePolished);
        } else {
            player.sendMessage(new TextComponentString("V'la comment tu pue"));
        }
    }
}
