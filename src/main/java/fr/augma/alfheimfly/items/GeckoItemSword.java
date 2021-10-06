package fr.augma.alfheimfly.items;

import fr.augma.alfheimfly.AlfheimFly;
import net.minecraft.item.ItemSword;

public class GeckoItemSword extends ItemSword {
	
	protected EnumItemRarity rarity;
	
	public GeckoItemSword(String name, ToolMaterial mat, EnumItemRarity rarity) {
		super(mat);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(AlfheimFly.ALFHEIM_TAB);
        this.rarity = rarity;
	}
}
