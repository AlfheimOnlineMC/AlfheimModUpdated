package fr.augma.alfheimfly.items;

import fr.augma.alfheimfly.AlfheimFly;
import net.minecraft.item.ItemSword;

public class AlfheimItemSword extends ItemSword {
	private EnumItemRarity rarity;

	public AlfheimItemSword(String name, ToolMaterial material, EnumItemRarity rarity) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(AlfheimFly.ALFHEIM_TAB);
        this.rarity = rarity;
    }
	
	public EnumItemRarity getRarity() {
		return this.rarity;
	}
}
