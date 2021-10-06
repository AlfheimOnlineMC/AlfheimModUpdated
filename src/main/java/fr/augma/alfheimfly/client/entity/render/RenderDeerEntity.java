package fr.augma.alfheimfly.client.entity.render;

import fr.augma.alfheimfly.client.entity.model.DeerEntityModel;
import fr.augma.alfheimfly.entities.DeerEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderDeerEntity extends GeoEntityRenderer<DeerEntity>{

	public RenderDeerEntity(RenderManager renderManager, DeerEntityModel model, float size) {
		super(renderManager, model);
		this.shadowSize = size;
	}

}
