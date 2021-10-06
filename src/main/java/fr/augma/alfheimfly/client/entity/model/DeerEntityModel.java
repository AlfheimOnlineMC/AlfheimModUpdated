package fr.augma.alfheimfly.client.entity.model;

import fr.augma.alfheimfly.entities.DeerEntity;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DeerEntityModel extends AnimatedGeoModel<DeerEntity>{

	@Override
	public ResourceLocation getAnimationFileLocation(DeerEntity animatable) {
		return new ResourceLocation(AlfheimRef.MODID, "animations/geometry.deer.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(DeerEntity object) {
		return new ResourceLocation(AlfheimRef.MODID, "geo/deer.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(DeerEntity object) {
		return new ResourceLocation(AlfheimRef.MODID, "textures/entity/deer.png");
	}

}
