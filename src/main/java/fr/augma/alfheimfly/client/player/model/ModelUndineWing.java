package fr.augma.alfheimfly.client.player.model;

import fr.augma.alfheimfly.client.AlfheimClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;

public class ModelUndineWing extends ModelWingsBase {

	private final ModelRenderer mid;
	private final ModelRenderer ailes;
	private final ModelRenderer Droite;
	private final ModelRenderer droitetop;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer Gauche;
	private final ModelRenderer gauchetop;
	private final ModelRenderer upperRight_r1;
	private final ModelRenderer upperRight_r2;

	public ModelUndineWing() {
		textureWidth = 64;
		textureHeight = 64;

		mid = new ModelRenderer(this);
		mid.setRotationPoint(0.75F, 0.75F, 0.25F);
		mid.cubeList.add(new ModelBox(mid, 6, 45, -3.75F, -1.75F, 2.25F, 6, 8, 0, 0.0F, true));

		ailes = new ModelRenderer(this);
		ailes.setRotationPoint(-1.25F, 1.65F, 1.75F);
		mid.addChild(ailes);
		

		Droite = new ModelRenderer(this);
		Droite.setRotationPoint(0.75F, 1.6F, 1.0F);
		ailes.addChild(Droite);
		

		droitetop = new ModelRenderer(this);
		droitetop.setRotationPoint(-0.75F, -1.6F, -0.5F);
		Droite.addChild(droitetop);
		setRotation(droitetop, 0.0F, 0.0F, 0.8727F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(2.75F, 3.85F, 0.0F);
		droitetop.addChild(cube_r1);
		setRotation(cube_r1, 0.0F, 0.2182F, -2.3562F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 4, 24, -16.9504F, -7.1429F, 0.7161F, 16, 7, 0, 0.0F, false));

		upperRight_r1 = new ModelRenderer(this);
		upperRight_r1.setRotationPoint(-2.0F, 2.0F, 1.0F);
		droitetop.addChild(upperRight_r1);
		setRotation(upperRight_r1, 0.0F, 0.2618F, -0.6545F);
		upperRight_r1.cubeList.add(new ModelBox(upperRight_r1, 1, 2, -27.9485F, -7.1545F, -0.0091F, 29, 9, 0, 0.0F, false));

		Gauche = new ModelRenderer(this);
		Gauche.setRotationPoint(0.25F, 1.6F, 1.0F);
		ailes.addChild(Gauche);
		

		gauchetop = new ModelRenderer(this);
		gauchetop.setRotationPoint(0.75F, -1.6F, -2.6F);
		Gauche.addChild(gauchetop);
		setRotation(gauchetop, 0.0F, 0.0F, -0.8727F);
		

		upperRight_r2 = new ModelRenderer(this);
		upperRight_r2.setRotationPoint(2.0F, 2.0F, 3.1F);
		gauchetop.addChild(upperRight_r2);
		setRotation(upperRight_r2, 0.0F, -0.2618F, 0.6545F);
		upperRight_r2.cubeList.add(new ModelBox(upperRight_r2, 1, 2, -1.0515F, -7.1545F, -0.0091F, 29, 9, 0, 0.0F, true));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-2.75F, 3.85F, 2.1F);
		gauchetop.addChild(cube_r2);
		setRotation(cube_r2, 0.0F, -0.2182F, 2.3562F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 4, 24, 0.9504F, -7.1429F, 0.7161F, 16, 7, 0, 0.0F, true));
	}
	
	@Override
	public void render(EntityPlayer player) {
		if(!player.onGround) {
			float angle = getWingAngle(player.capabilities.isFlying, 40, 4000, 250, player.getEntityId());
	
			setRotation(Droite, 0f, (float) Math.toRadians(angle + -4), 0f);
			setRotation(Gauche, 0f, (float) Math.toRadians(-angle - 4), 0f);
			setRotation(upperRight_r1, 0f, 0f, -0.6545f);
			setRotation(upperRight_r2, 0f, 0f, 0.6545f);
		} else {
			setRotation(upperRight_r1, 0f, 0.2182f, -2f);
			setRotation(upperRight_r2, 0f, -0.2182f, 2f);
			setRotation(Droite, 0f, 0f, 0f);
			setRotation(Gauche, 0f, 0f, 0f);
		}
		Minecraft.getMinecraft().getTextureManager().bindTexture(AlfheimClient.getWings(12));
		mid.render(SCALE);
	}
}
