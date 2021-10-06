package fr.augma.alfheimfly.items;

import fr.augma.alfheimfly.AlfheimFly;
import net.minecraft.item.ItemFood;

public class AlfheimItemFoodBasic extends ItemFood {

	public AlfheimItemFoodBasic(String name, int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(AlfheimFly.ALFHEIM_TAB);
	}
}
