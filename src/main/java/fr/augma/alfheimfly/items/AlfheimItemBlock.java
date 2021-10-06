package fr.augma.alfheimfly.items;

import fr.augma.alfheimfly.AlfheimFly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class AlfheimItemBlock extends ItemBlock {

	public AlfheimItemBlock(Block block, String name) {
		super(block);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(AlfheimFly.ALFHEIM_TAB);
	}

}
