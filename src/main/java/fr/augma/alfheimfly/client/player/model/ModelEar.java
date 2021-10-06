package fr.augma.alfheimfly.client.player.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;

public class ModelEar extends ModelBase {
	
	private ModelRenderer rightEar;
	private ModelRenderer leftEar;
	private RenderPlayer render;

	public ModelEar(RenderPlayer render) {
		this.render = render;
		
		textureWidth = 64;
		textureHeight = 64;
		
		rightEar = new ModelRenderer(this);
        rightEar.setRotationPoint(-3.0F, -2.5F, 0.0F);
        this.render.getMainModel().bipedHead.addChild(rightEar);
        setRotationAngle(rightEar, -0.5672F, 0.0F, -0.3927F);
        rightEar.cubeList.add(new ModelBox(rightEar, 25, 2, -1.0F, -3.0F, -1.0F, 1, 3, 2, 0.0F, false));

        leftEar = new ModelRenderer(this);
        leftEar.setRotationPoint(3.0F, -2.5F, 0.0F);
        this.render.getMainModel().bipedHead.addChild(leftEar);
        setRotationAngle(leftEar, -0.5672F, 0.0F, 0.3927F);
        leftEar.cubeList.add(new ModelBox(leftEar, 25, 2, 0.0F, -3.0F, -1.0F, 1, 3, 2, 0.0F, false));
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
