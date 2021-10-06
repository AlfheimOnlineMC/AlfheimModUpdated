package fr.augma.alfheimfly.ctab;

import fr.augma.alfheimfly.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AlfheimTab extends CreativeTabs {

	public AlfheimTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.BOAR_STEAK);
	}
}
