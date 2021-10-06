package fr.augma.alfheimfly.command;

import com.google.common.collect.Multimap;
import fr.augma.alfheimfly.utils.RuneUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

import java.util.UUID;

public class testRune extends CommandBase {

    @Override
    public String getName() {
        return "testRune";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/testRune";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player = getCommandSenderAsPlayer(sender);
        ItemStack item = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);

        AttributeModifier attcrit = new AttributeModifier(RuneUtils.ATTRIBUTE_CRIT_ID, "crit", 1D, 1);
        AttributeModifier attcritdmg = new AttributeModifier(RuneUtils.ATTRIBUTE_CRIT_DMG_ID, "crit_dmg", 1.5D, 1);
        AttributeModifier attlifesteal = new AttributeModifier(RuneUtils.ATTRIBUTE_LIFE_STEAL_ID, "life_steal", 0.1D, 1);
        AttributeModifier attdef_pene = new AttributeModifier(RuneUtils.ATTRIBUTE_DEF_PENE_ID, "def_pene", 2D, 0);


        item.getAttributeModifiers(EntityEquipmentSlot.MAINHAND).forEach((s, attributeModifier) -> item.addAttributeModifier(s, attributeModifier, EntityEquipmentSlot.MAINHAND));

        item.addAttributeModifier(RuneUtils.CRIT.getName(), attcrit, EntityEquipmentSlot.MAINHAND);
        item.addAttributeModifier(RuneUtils.CRIT_DMG.getName(), attcritdmg, EntityEquipmentSlot.MAINHAND);
        item.addAttributeModifier(RuneUtils.LIFE_STEAL.getName(), attlifesteal, EntityEquipmentSlot.MAINHAND);
        item.addAttributeModifier(RuneUtils.DEF_PENE.getName(), attdef_pene, EntityEquipmentSlot.MAINHAND);
    }
}
