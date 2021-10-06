package fr.augma.alfheimfly.blocks;

import fr.augma.alfheimfly.AlfheimFly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AlfheimBlockBasic extends Block {

	public AlfheimBlockBasic(Material materialIn, String name) {
		super(materialIn);
		this.setCreativeTab(AlfheimFly.ALFHEIM_TAB);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}

}
