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
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.server.MinecraftServer;
import org.w3c.dom.Attr;

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
        EntityEquipmentSlot type = item.getItem() instanceof ItemArmor ? ((ItemArmor) item.getItem()).getEquipmentSlot() : EntityEquipmentSlot.MAINHAND;

        AttributeModifier attcrit = new AttributeModifier(UUID.randomUUID(), "crit", 1D, 0);
        AttributeModifier attcritdmg = new AttributeModifier(UUID.randomUUID(), "crit_dmg", 1.5D, 0);
        AttributeModifier attlifesteal = new AttributeModifier(UUID.randomUUID(), "life_steal", 0.1D, 0);
        AttributeModifier attdef_pene = new AttributeModifier(UUID.randomUUID(), "def_pene", 2D, 0);
        AttributeModifier attdef_pene_rune = new AttributeModifier(UUID.randomUUID(), "def_pene_rune", 1D, 0);
        AttributeModifier attAttack = new AttributeModifier(UUID.randomUUID(), "attack_rune", 4D, 0);
        AttributeModifier attSpeed = new AttributeModifier(UUID.randomUUID(), "attack_speed_rune", 1D, 0);
        AttributeModifier attSpeed2 = new AttributeModifier(UUID.randomUUID(), "attack_speed_rune2", 0.5D, 0);


        item.getAttributeModifiers(type).forEach((s, attributeModifier) -> item.addAttributeModifier(s, attributeModifier, type));
        item.addAttributeModifier(RuneUtils.DEF_PENE.getName(), attdef_pene, type);
        item.addAttributeModifier(RuneUtils.CRIT.getName(), attcrit, type);

        item.addAttributeModifier(RuneUtils.DEF_PENE.getName(), attdef_pene_rune, type);
        item.addAttributeModifier(RuneUtils.LIFE_STEAL.getName(), attlifesteal, type);
        item.addAttributeModifier(RuneUtils.CRIT_DMG.getName(), attcritdmg, type);
        item.addAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED.getName(), attSpeed2, type);

        item.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), attAttack, type);
        item.addAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED.getName(), attSpeed, type);

    }
}
