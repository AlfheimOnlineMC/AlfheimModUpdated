package fr.augma.alfheimfly.client.entity.render;

import fr.augma.alfheimfly.entities.BoarEntity;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBoarEntity extends RenderLiving<BoarEntity> {
	
	public static ResourceLocation TEXTURE = new ResourceLocation(AlfheimRef.MODID, "textures/entity/sanglier.png");
	public static ResourceLocation TEXTURE_CHILD = new ResourceLocation(AlfheimRef.MODID, "textures/entity/sanglier_child.png");

	public RenderBoarEntity(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(BoarEntity entity) {
		if(entity.isChild()) {
			return TEXTURE_CHILD;
		}
		return TEXTURE;
	}

	@Override
	protected void applyRotations(BoarEntity entityLiving, float p_77043_2_, float rotationYaw,
			float partialTicks) {
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}
