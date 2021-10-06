package fr.augma.alfheimfly.client.player.model;

import fr.augma.alfheimfly.client.AlfheimClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;

public class ModelSylphWing extends ModelWingsBase {

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

	public ModelSylphWing() {
		textureWidth = 64;
		textureHeight = 64;	

		mid = new ModelRenderer(this);
		mid.setRotationPoint(0.75F, 0.75F, 0.25F);
		mid.cubeList.add(new ModelBox(mid, 3, 17, -3.75F, 2.25F, 2.25F, 6, 4, 0, 0.0F, true));

		ailes = new ModelRenderer(this);
		ailes.setRotationPoint(-1.25F, 1.65F, 1.75F);
		mid.addChild(ailes);
		

		Droite = new ModelRenderer(this);
		Droite.setRotationPoint(1.25F, 1.6F, 1.0F);
		ailes.addChild(Droite);
		

		droitetop = new ModelRenderer(this);
		droitetop.setRotationPoint(-1.25F, 2.4F, -0.5F);
		Droite.addChild(droitetop);
		setRotation(droitetop, 0.0F, 0.0F, 0.8727F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-5.3142F, -0.7212F, 0.0F);
		droitetop.addChild(cube_r1);
		setRotation(cube_r1, 0.0F, 0.2182F, -0.9599F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 1, 21, -21.75F, 2.1F, 0.9822F, 23, 7, 0, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-2.0642F, -2.5712F, 1.0F);
		droitetop.addChild(cube_r2);
		setRotation(cube_r2, 0.0F, 0.2618F, -0.6545F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 0, -28.7235F, -4.4941F, -0.9402F, 27, 7, 0, 0.0F, false));

		Gauche = new ModelRenderer(this);
		Gauche.setRotationPoint(0.25F, 1.6F, 1.0F);
		ailes.addChild(Gauche);
		

		gauchetop = new ModelRenderer(this);
		gauchetop.setRotationPoint(0.45F, 2.4F, -0.6F);
		Gauche.addChild(gauchetop);
		setRotation(gauchetop, 0.0F, 0.0F, -0.8727F);
		

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(5.507F, -0.4913F, 0.1F);
		gauchetop.addChild(cube_r3);
		setRotation(cube_r3, 0.0F, -0.2182F, 0.9599F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 1, 21, -1.25F, 2.1F, 0.9822F, 23, 7, 0, 0.0F, true));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(2.257F, -2.3413F, 1.1F);
		gauchetop.addChild(cube_r4);
		setRotation(cube_r4, 0.0F, -0.2618F, 0.6545F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 0, 1.7235F, -4.4941F, -0.9402F, 27, 7, 0, 0.0F, true));
	}
	
	@Override
	public void render(EntityPlayer player) {
		GlStateManager.translate(0, -0.1f, 0);
		if(!player.onGround) {
			float angle = getWingAngle(player.capabilities.isFlying, 40, 4000, 250, player.getEntityId());
	
			setRotation(Droite, 0f, (float) Math.toRadians(angle + -4), 0f);
			setRotation(Gauche, 0f, (float) Math.toRadians(-angle - 4), 0f);
		} else {
			setRotation(Droite, 0f, 0f, -0.6f);
			setRotation(Gauche, 0f, 0f, 0.6f);
		}

		Minecraft.getMinecraft().getTextureManager().bindTexture(AlfheimClient.getWings(10));
		mid.render(SCALE);
	}
}
