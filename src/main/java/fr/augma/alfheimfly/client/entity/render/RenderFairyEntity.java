package fr.augma.alfheimfly.client.entity.render;

import fr.augma.alfheimfly.entities.FairyEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderFairyEntity extends GeoEntityRenderer<FairyEntity> {

    public RenderFairyEntity(RenderManager renderManager, AnimatedGeoModel<FairyEntity> modelProvider, float size) {
        super(renderManager, modelProvider);
        this.shadowSize = size;
    }
}
