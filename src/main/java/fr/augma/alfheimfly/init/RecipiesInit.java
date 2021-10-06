package fr.augma.alfheimfly.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipiesInit {

	public static void init() {
		GameRegistry.addSmelting(ItemInit.BOAR_STEAK, new ItemStack(ItemInit.COOKED_BOAR_STEAK), 1.0f);
	}
}
