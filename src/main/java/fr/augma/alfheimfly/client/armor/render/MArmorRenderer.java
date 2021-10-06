package fr.augma.alfheimfly.client.armor.render;

import fr.augma.alfheimfly.client.armor.custom.AlfheimGeoArmorRenderer;
import fr.augma.alfheimfly.client.armor.model.MArmorModel;
import fr.augma.alfheimfly.items.MArmorItem;

public class MArmorRenderer extends AlfheimGeoArmorRenderer<MArmorItem> {

	public MArmorRenderer() {
		super(new MArmorModel());
		
		this.headBone = "head";
		this.bodyBone = "body";
	    this.rightArmBone = "right_arm";
	    this.leftArmBone = "left_arm";
	    this.rightLegBone = "jambeD";
	    this.leftLegBone = "jambeG";
	    this.rightBootBone = "footD";
	    this.leftBootBone = "footG";
	}
}
