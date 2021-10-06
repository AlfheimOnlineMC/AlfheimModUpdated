package fr.augma.alfheimfly.client.entity.render;

import fr.augma.alfheimfly.entities.FrogEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderFrogEntity extends GeoEntityRenderer<FrogEntity> {

	public RenderFrogEntity(RenderManager renderManager, AnimatedGeoModel<FrogEntity> modelProvider) {
		super(renderManager, modelProvider);
		this.shadowSize = 0;
	}
}
