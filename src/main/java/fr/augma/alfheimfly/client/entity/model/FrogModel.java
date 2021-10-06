package fr.augma.alfheimfly.client.entity.model;

import fr.augma.alfheimfly.entities.FrogEntity;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FrogModel extends AnimatedGeoModel<FrogEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(FrogEntity animatable) {
		return null;
	}

	@Override
	public ResourceLocation getModelLocation(FrogEntity object) {
		return new ResourceLocation(AlfheimRef.MODID, "geo/frog.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(FrogEntity object) {
		return new ResourceLocation(AlfheimRef.MODID, "textures/entity/frog.png");
	}

}
