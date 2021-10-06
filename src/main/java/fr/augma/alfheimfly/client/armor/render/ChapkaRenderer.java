package fr.augma.alfheimfly.client.armor.render;

import fr.augma.alfheimfly.client.armor.model.ChapkaModel;
import fr.augma.alfheimfly.items.ChapkaItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ChapkaRenderer extends GeoArmorRenderer<ChapkaItem> {

	public ChapkaRenderer() {
		super(new ChapkaModel());
		
		this.headBone = "head";
		this.bodyBone = "body";
		this.leftArmBone = "leftArm";
		this.rightArmBone = "rightArm";
		this.leftLegBone = "leftLeg";
		this.rightLegBone = "rightLeg";
		this.leftBootBone = "leftBoot";
		this.rightBootBone = "rightBoot";
	}

}
