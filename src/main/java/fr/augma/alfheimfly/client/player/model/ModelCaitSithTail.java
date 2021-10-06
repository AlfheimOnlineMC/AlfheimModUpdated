package fr.augma.alfheimfly.client.player.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCaitSithTail extends ModelBase {
	private final ModelRenderer tail;
	private final ModelRenderer root_r1;
	private final ModelRenderer raou;
	private final ModelRenderer root_r2;
	
	public ModelCaitSithTail() {
		textureWidth = 16;
		textureHeight = 16;

		tail = new ModelRenderer(this);
		tail.setRotationPoint(-1.9F, 11.5F, 0.0F);
		setRotationAngle(tail, 0.0873F, 0.0F, 0.0F);
		

		root_r1 = new ModelRenderer(this);
		root_r1.setRotationPoint(1.9F, -0.5F, 2.0F);
		tail.addChild(root_r1);
		setRotationAngle(root_r1, -1.0908F, 0.0F, 0.0F);
		root_r1.cubeList.add(new ModelBox(root_r1, 0, 0, -0.4F, -0.2F, -1.0F, 1, 1, 7, 0.0F, false));

		raou = new ModelRenderer(this);
		raou.setRotationPoint(1.9F, 4.0F, 5.0F);
		tail.addChild(raou);
		

		root_r2 = new ModelRenderer(this);
		root_r2.setRotationPoint(0.1F, -6.0F, -5.0F);
		raou.addChild(root_r2);
		setRotationAngle(root_r2, -0.7854F, 0.0F, 0.0F);
		root_r2.cubeList.add(new ModelBox(root_r2, 0, 8, -0.5F, 1.25F, 7.75F, 1, 1, 5, 0.0F, false));
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		GL11.glPushMatrix();
		if(entityIn.isSneaking()) {
			GL11.glTranslatef(0f, 0.05f, 0.3f);
		}
		tail.render(scale);
		GL11.glPopMatrix();
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
