package fr.augma.alfheimfly.client.armor.model;

import fr.augma.alfheimfly.items.QuatorzeArmorItem;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class QuatorzeArmorModel extends AnimatedGeoModel<QuatorzeArmorItem> {

	@Override
	public ResourceLocation getAnimationFileLocation(QuatorzeArmorItem animatable) {
		return new ResourceLocation(AlfheimRef.MODID, "animations/geometry.14.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(QuatorzeArmorItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "geo/14.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(QuatorzeArmorItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "textures/armor/14.png");
	}
}
