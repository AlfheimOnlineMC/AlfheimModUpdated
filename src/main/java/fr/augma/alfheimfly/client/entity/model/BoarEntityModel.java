package fr.augma.alfheimfly.client.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class BoarEntityModel extends ModelBase {
	private final ModelRenderer Sanglier;
	private final ModelRenderer corps;
	private final ModelRenderer Body;
	private final ModelRenderer head;
	private final ModelRenderer head2;
	private final ModelRenderer Oreilles;
	private final ModelRenderer O_gauche;
	private final ModelRenderer O_gauche_r1;
	private final ModelRenderer O_droite;
	private final ModelRenderer O_droite_r1;
	private final ModelRenderer museau;
	private final ModelRenderer nez;
	private final ModelRenderer Cornes;
	private final ModelRenderer C_droite;
	private final ModelRenderer C_gauche;
	private final ModelRenderer brasgauche;
	private final ModelRenderer Bgbot;
	private final ModelRenderer jambegauche;
	private final ModelRenderer jb_bot;
	private final ModelRenderer jambedroite;
	private final ModelRenderer jd_top;
	private final ModelRenderer bras_droit;
	private final ModelRenderer bd_bot;

	public BoarEntityModel() {
		textureWidth = 64;
		textureHeight = 64;

		Sanglier = new ModelRenderer(this);
		Sanglier.setRotationPoint(0.0F, 13.0F, 2.0F);
		

		corps = new ModelRenderer(this);
		corps.setRotationPoint(0.0F, 3.5F, -4.125F);
		Sanglier.addChild(corps);
		

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 1.625F);
		corps.addChild(Body);
		setRotationAngle(Body, -1.7453F, 0.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 0, -5.0F, -8.0F, -6.25F, 10, 16, 9, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -1.0F, -8.0F);
		Sanglier.addChild(head);
		

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, 1.5F, -4.0F);
		head.addChild(head2);
		setRotationAngle(head2, 0.1309F, 0.0F, 0.0F);
		head2.cubeList.add(new ModelBox(head2, 0, 26, -4.0F, -4.5F, -4.0F, 8, 9, 8, 0.0F, false));

		Oreilles = new ModelRenderer(this);
		Oreilles.setRotationPoint(0.0F, -4.0F, -15.0F);
		head.addChild(Oreilles);
		

		O_gauche = new ModelRenderer(this);
		O_gauche.setRotationPoint(3.0354F, -0.5835F, 11.7846F);
		Oreilles.addChild(O_gauche);
		

		O_gauche_r1 = new ModelRenderer(this);
		O_gauche_r1.setRotationPoint(0.25F, 0.5F, 0.0F);
		O_gauche.addChild(O_gauche_r1);
		setRotationAngle(O_gauche_r1, 0.2618F, 0.0F, 0.3927F);
		O_gauche_r1.cubeList.add(new ModelBox(O_gauche_r1, 0, 0, -1.5F, -1.1665F, -0.0346F, 3, 3, 0, 0.0F, false));

		O_droite = new ModelRenderer(this);
		O_droite.setRotationPoint(-2.75F, -0.25F, 11.75F);
		Oreilles.addChild(O_droite);
		

		O_droite_r1 = new ModelRenderer(this);
		O_droite_r1.setRotationPoint(-0.5F, 0.5F, 0.0F);
		O_droite.addChild(O_droite_r1);
		setRotationAngle(O_droite_r1, 0.2618F, 0.0F, -0.3927F);
		O_droite_r1.cubeList.add(new ModelBox(O_droite_r1, 0, 4, -1.5F, -1.5F, 0.0F, 3, 3, 0, 0.0F, false));

		museau = new ModelRenderer(this);
		museau.setRotationPoint(-0.125F, 7.9375F, -1.75F);
		head.addChild(museau);
		

		nez = new ModelRenderer(this);
		nez.setRotationPoint(-0.125F, -4.0625F, -8.0F);
		museau.addChild(nez);
		setRotationAngle(nez, 0.1309F, 0.0F, 0.0F);
		nez.cubeList.add(new ModelBox(nez, 0, 44, -2.5F, -1.875F, -2.0F, 5, 3, 4, 0.0F, false));

		Cornes = new ModelRenderer(this);
		Cornes.setRotationPoint(-2.875F, -3.6875F, -9.0F);
		museau.addChild(Cornes);
		

		C_droite = new ModelRenderer(this);
		C_droite.setRotationPoint(0.0F, 0.0F, 0.0F);
		Cornes.addChild(C_droite);
		setRotationAngle(C_droite, 0.1309F, 0.0F, 0.0F);
		C_droite.cubeList.add(new ModelBox(C_droite, 0, 26, -0.75F, -4.0F, 0.0F, 1, 4, 1, 0.0F, false));

		C_gauche = new ModelRenderer(this);
		C_gauche.setRotationPoint(6.5F, 0.25F, 0.0F);
		Cornes.addChild(C_gauche);
		setRotationAngle(C_gauche, 0.1309F, 0.0F, 0.0F);
		C_gauche.cubeList.add(new ModelBox(C_gauche, 25, 26, -1.25F, -4.0F, 0.0F, 1, 4, 1, 0.0F, false));

		brasgauche = new ModelRenderer(this);
		brasgauche.setRotationPoint(4.0F, 0.0F, -7.0F);
		Sanglier.addChild(brasgauche);
		brasgauche.cubeList.add(new ModelBox(brasgauche, 35, 22, -1.5F, 0.0F, -2.0F, 4, 6, 4, 0.0F, false));

		Bgbot = new ModelRenderer(this);
		Bgbot.setRotationPoint(0.5F, 4.75F, -0.25F);
		brasgauche.addChild(Bgbot);
		Bgbot.cubeList.add(new ModelBox(Bgbot, 19, 44, -1.5F, -0.25F, -1.5F, 3, 6, 3, 0.0F, false));

		jambegauche = new ModelRenderer(this);
		jambegauche.setRotationPoint(4.0F, 0.0F, 4.0F);
		Sanglier.addChild(jambegauche);
		jambegauche.cubeList.add(new ModelBox(jambegauche, 39, 11, -1.0F, 0.5F, -2.75F, 4, 6, 4, 0.0F, false));

		jb_bot = new ModelRenderer(this);
		jb_bot.setRotationPoint(0.75F, 5.0F, -0.375F);
		jambegauche.addChild(jb_bot);
		jb_bot.cubeList.add(new ModelBox(jb_bot, 50, 30, -1.5F, 0.0F, -1.625F, 3, 6, 3, 0.0F, false));

		jambedroite = new ModelRenderer(this);
		jambedroite.setRotationPoint(-4.625F, 0.25F, 3.8125F);
		Sanglier.addChild(jambedroite);
		jambedroite.cubeList.add(new ModelBox(jambedroite, 39, 0, -2.625F, 0.25F, -2.5625F, 4, 6, 4, 0.0F, false));

		jd_top = new ModelRenderer(this);
		jd_top.setRotationPoint(-0.625F, 4.75F, -0.1875F);
		jambedroite.addChild(jd_top);
		jd_top.cubeList.add(new ModelBox(jd_top, 43, 44, -1.5F, 0.0F, -1.625F, 3, 6, 3, 0.0F, false));

		bras_droit = new ModelRenderer(this);
		bras_droit.setRotationPoint(-5.25F, 1.125F, -7.5F);
		Sanglier.addChild(bras_droit);
		bras_droit.cubeList.add(new ModelBox(bras_droit, 33, 33, -1.25F, -0.875F, -1.5F, 4, 6, 4, 0.0F, false));

		bd_bot = new ModelRenderer(this);
		bd_bot.setRotationPoint(0.75F, 3.625F, 0.375F);
		bras_droit.addChild(bd_bot);
		bd_bot.cubeList.add(new ModelBox(bd_bot, 31, 44, -1.5F, -0.25F, -1.625F, 3, 6, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		if(this.isChild) {
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * f5, 0.0F);
			Sanglier.render(f5);
			GlStateManager.popMatrix();
		} else {
			Sanglier.render(f5);
		}
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		this.brasgauche.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.1f * limbSwingAmount;
		this.jambedroite.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.1f * limbSwingAmount;
		
		this.bras_droit.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float)Math.PI) * 1.1f * limbSwingAmount;
		this.jambegauche.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float)Math.PI) * 1.1f * limbSwingAmount;
		this.head.rotateAngleY = netHeadYaw * 0.017453292f;
		this.head.rotateAngleX = headPitch * 0.017453292f;
	}
}