package fr.augma.alfheimfly.items;

import fr.augma.alfheimfly.AlfheimFly;
import net.minecraft.item.Item;

public class AlfheimItemBasic extends Item {
	public AlfheimItemBasic(String name) {
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(AlfheimFly.ALFHEIM_TAB);
	}
}
