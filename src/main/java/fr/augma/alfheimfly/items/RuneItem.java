package fr.augma.alfheimfly.items;

import fr.augma.alfheimfly.utils.RuneUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class RuneItem extends AlfheimItemBasic {
	private final EnumItemRarity rarity;

	public RuneItem(String name, EnumItemRarity rarity) {
		super(name);
		this.rarity = rarity;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();

			tooltip.add("Type : " + capitalize(nbt.getString("type")));
			tooltip.add("");

			int[] range = RuneUtils.RuneStatsType.getByAttributeName(nbt.getString("slot1")).getRange().get(this.rarity);
			System.out.println(range);
			float amount = ((range[1] - range[0] ) * nbt.getFloat("slot1_coef")) + range[0];
			tooltip.add(I18n.format("attribute.name.alfheim." + nbt.getString("slot1")) + " : " + amount);
			range = (RuneUtils.RuneStatsType.getByAttributeName(nbt.getString("slot2")).getRange().get(this.rarity));
			amount = ((range[1] - range[0] ) * nbt.getFloat("slot2_coef")) + range[0];
			tooltip.add(I18n.format("attribute.name.alfheim." + nbt.getString("slot2")) + " : " + amount);
		}
	}

	public EnumItemRarity getRarity() {
		return this.rarity;
	}

	private String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
}
