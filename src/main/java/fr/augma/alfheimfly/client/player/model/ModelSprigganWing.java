package fr.augma.alfheimfly.client.player.model;

import fr.augma.alfheimfly.client.AlfheimClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;

public class ModelSprigganWing extends ModelWingsBase {

	private final ModelRenderer mid;
	private final ModelRenderer ailes;
	private final ModelRenderer Droite;
	private final ModelRenderer droitetop;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer Gauche;
	private final ModelRenderer gauchetop;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;

	public ModelSprigganWing() {
		textureWidth = 64;
		textureHeight = 64;

		mid = new ModelRenderer(this);
		mid.setRotationPoint(0.75F, 0.75F, 0.25F);
		mid.cubeList.add(new ModelBox(mid, 1, 28, -3.75F, -1.75F, 2.25F, 6, 8, 0, 0.0F, true));

		ailes = new ModelRenderer(this);
		ailes.setRotationPoint(-1.25F, 1.65F, 1.75F);
		mid.addChild(ailes);
		

		Droite = new ModelRenderer(this);
		Droite.setRotationPoint(1.25F, 1.6F, 1.0F);
		ailes.addChild(Droite);
		

		droitetop = new ModelRenderer(this);
		droitetop.setRotationPoint(-1.25F, -1.6F, -0.5F);
		Droite.addChild(droitetop);
		setRotation(droitetop, 0.0F, 0.0F, 0.8727F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-2.25F, 1.85F, 0.0F);
		droitetop.addChild(cube_r1);
		setRotation(cube_r1, 0.0F, 0.2182F, -0.9599F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 4, 8, -17.75F, 1.1F, 0.9822F, 18, 4, 0, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(1.0F, 0.0F, -2.0F);
		droitetop.addChild(cube_r2);
		setRotation(cube_r2, 0.0F, 0.2618F, -0.6545F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 0, -30.5F, -5.4941F, 1.9576F, 31, 7, 0, 0.0F, false));

		Gauche = new ModelRenderer(this);
		Gauche.setRotationPoint(0.25F, 1.6F, 1.0F);
		ailes.addChild(Gauche);
		

		gauchetop = new ModelRenderer(this);
		gauchetop.setRotationPoint(0.75F, -1.6F, -2.6F);
		Gauche.addChild(gauchetop);
		setRotation(gauchetop, 0.0F, 0.0F, -0.8727F);
		

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(2.25F, 1.85F, 2.0F);
		gauchetop.addChild(cube_r3);
		setRotation(cube_r3, 0.0F, -0.2182F, 0.9599F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 4, 8, -0.25F, 1.1F, 0.9822F, 18, 4, 0, 0.0F, true));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-1.0F, 0.0F, 0.0F);
		gauchetop.addChild(cube_r4);
		setRotation(cube_r4, 0.0F, -0.2618F, 0.6545F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 0, -0.5F, -5.4941F, 1.9576F, 31, 7, 0, 0.0F, true));
	}
	
	@Override
	public void render(EntityPlayer player) {
		if(!player.onGround) {
			float angle = getWingAngle(player.capabilities.isFlying, 40, 4000, 250, player.getEntityId());
	
			setRotation(Droite, 0f, (float) Math.toRadians(angle + -4), 0f);
			setRotation(Gauche, 0f, (float) Math.toRadians(-angle - 4), 0f);
		} else {
			setRotation(Droite, 0f, 0f, -1f);
			setRotation(Gauche, 0f, 0f, 1f);
		}

		Minecraft.getMinecraft().renderEngine.bindTexture(AlfheimClient.getWings(8));
		mid.render(SCALE);
	}
}
