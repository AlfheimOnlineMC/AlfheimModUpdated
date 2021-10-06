package fr.augma.alfheimfly.client.skin;

import net.minecraft.util.ResourceLocation;

public interface ISkinnable<T extends IHasSkin> {

	ResourceLocation getSkinModel(T item, String name);
	
	ResourceLocation getSkinTexture(T item, String name);
}
