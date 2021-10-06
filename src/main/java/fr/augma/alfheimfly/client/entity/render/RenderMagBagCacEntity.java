package fr.augma.alfheimfly.client.entity.render;

import fr.augma.alfheimfly.client.entity.model.MagBagCacModel;
import fr.augma.alfheimfly.entities.MagBagCac;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderMagBagCacEntity extends GeoEntityRenderer<MagBagCac> {

	public RenderMagBagCacEntity(RenderManager renderManager, MagBagCacModel modelProvider) {
		super(renderManager, modelProvider);
		this.shadowSize = 0;
	}

}
