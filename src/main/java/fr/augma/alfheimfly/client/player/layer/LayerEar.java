package fr.augma.alfheimfly.client.player.layer;

import fr.augma.alfheimfly.client.player.model.ModelEar;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public class LayerEar implements LayerRenderer<EntityLivingBase> {
	
	private ModelEar earModel;

    public LayerEar(RenderPlayer render) {
        this.earModel = new ModelEar(render);
    }

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        earModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

}
