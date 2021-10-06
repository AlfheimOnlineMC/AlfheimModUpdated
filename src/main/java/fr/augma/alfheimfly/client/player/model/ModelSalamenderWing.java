package fr.augma.alfheimfly.client.player.model;

import fr.augma.alfheimfly.client.AlfheimClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;

public class ModelSalamenderWing extends ModelWingsBase {

	private final ModelRenderer mid;
	private final ModelRenderer ailes;
	private final ModelRenderer Droite;
	private final ModelRenderer droitetop;
	private final ModelRenderer downRight_r1;
	private final ModelRenderer upperRight_r1;
	private final ModelRenderer Gauche;
	private final ModelRenderer gauchetop;
	private final ModelRenderer upperDown_r1;
	private final ModelRenderer upperLeft_r1;
	
	public ModelSalamenderWing() {
		textureWidth = 64;
		textureHeight = 64;

		mid = new ModelRenderer(this);
		mid.setRotationPoint(0.75F, 0.75F, 0.25F);
		mid.cubeList.add(new ModelBox(mid, 4, 40, -2.75F, -2.75F, 2.25F, 4, 7, 0, 0.0F, true));

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

		downRight_r1 = new ModelRenderer(this);
		downRight_r1.setRotationPoint(1.75F, 0.95F, 0.3F);
		droitetop.addChild(downRight_r1);
		setRotation(downRight_r1, 0.0F, 0.2182F, -1.9635F);
		downRight_r1.cubeList.add(new ModelBox(downRight_r1, 2, 25, -18.6448F, -3.6604F, 0.0332F, 17, 5, 0, 0.0F, false));

		upperRight_r1 = new ModelRenderer(this);
		upperRight_r1.setRotationPoint(1.0F, 0.0F, -0.2F);
		droitetop.addChild(upperRight_r1);
		setRotation(upperRight_r1, 0.0F, 0.2618F, -0.6545F);
		upperRight_r1.cubeList.add(new ModelBox(upperRight_r1, 0, 0, -28.0341F, -8.4941F, 0.2189F, 26, 10, 0, 0.0F, false));

		Gauche = new ModelRenderer(this);
		Gauche.setRotationPoint(0.25F, 1.6F, 1.0F);
		ailes.addChild(Gauche);

		gauchetop = new ModelRenderer(this);
		gauchetop.setRotationPoint(0.75F, -1.6F, -2.6F);
		Gauche.addChild(gauchetop);
		setRotation(gauchetop, 0.0F, 0.0F, -0.8727F);

		upperDown_r1 = new ModelRenderer(this);
		upperDown_r1.setRotationPoint(-1.75F, 0.95F, 2.4F);
		gauchetop.addChild(upperDown_r1);
		setRotation(upperDown_r1, 0.0F, -0.2182F, 1.9635F);
		upperDown_r1.cubeList.add(new ModelBox(upperDown_r1, 2, 25, 1.6448F, -3.6604F, 0.0332F, 17, 5, 0, 0.0F, true));

		upperLeft_r1 = new ModelRenderer(this);
		upperLeft_r1.setRotationPoint(-1.0F, 0.0F, 1.9F);
		gauchetop.addChild(upperLeft_r1);
		setRotation(upperLeft_r1, 0.0F, -0.2618F, 0.6981F);
		upperLeft_r1.cubeList.add(new ModelBox(upperLeft_r1, 0, 0, 2.0341F, -8.4941F, 0.2189F, 26, 10, 0, 0.0F, true));
	}
	
	@Override
	public void render(EntityPlayer player) {
		if(!player.onGround) {
			float angle = getWingAngle(player.capabilities.isFlying, 40, 4000, 250, player.getEntityId());
	
			setRotation(Droite, 0f, (float) Math.toRadians(angle + -4), 0f);
			setRotation(Gauche, 0f, (float) Math.toRadians(-angle - 4), 0f);
			setRotation(upperRight_r1, 0f, 0f, -0.6981f);
			setRotation(upperLeft_r1, 0f, 0f, 0.6981f);
		} else {
			setRotation(upperRight_r1, 0f, 0.2618f, -1.6f);
			setRotation(upperLeft_r1, 0f, -0.2618f, 1.6f);
			setRotation(Droite, 0f, 0f, 0f);
			setRotation(Gauche, 0f, 0f, 0f);
		}
		Minecraft.getMinecraft().getTextureManager().bindTexture(AlfheimClient.getWings(6));
		mid.render(SCALE);
	}
}
