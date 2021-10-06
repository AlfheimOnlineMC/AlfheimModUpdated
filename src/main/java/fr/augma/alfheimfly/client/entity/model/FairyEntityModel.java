package fr.augma.alfheimfly.client.entity.model;

import fr.augma.alfheimfly.entities.FairyEntity;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FairyEntityModel extends AnimatedGeoModel<FairyEntity> {


    @Override
    public ResourceLocation getModelLocation(FairyEntity fairyEntity) {
        return new ResourceLocation(AlfheimRef.MODID, "geo/fairy.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FairyEntity fairyEntity) {
        return new ResourceLocation(AlfheimRef.MODID, "textures/entity/fairy.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FairyEntity fairyEntity) {
        return new ResourceLocation(AlfheimRef.MODID, "animations/fairy.animation.json");
    }
}
