package fr.augma.alfheimfly.client.entity.model;

import fr.augma.alfheimfly.entities.MagBagCac;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MagBagCacModel extends AnimatedGeoModel<MagBagCac> {

	@Override
	public ResourceLocation getAnimationFileLocation(MagBagCac animatable) {
		return new ResourceLocation(AlfheimRef.MODID, "animations/geometry.magbagcac.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(MagBagCac object) {
		return new ResourceLocation(AlfheimRef.MODID, "geo/magbagcac.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(MagBagCac object) {
		return new ResourceLocation(AlfheimRef.MODID, "textures/entity/magbagcac.animation.png");
	}

}
