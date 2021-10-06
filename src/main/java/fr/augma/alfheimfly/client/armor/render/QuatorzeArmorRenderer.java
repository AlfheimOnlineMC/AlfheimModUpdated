package fr.augma.alfheimfly.client.armor.render;

import fr.augma.alfheimfly.client.armor.custom.AlfheimGeoArmorRenderer;
import fr.augma.alfheimfly.client.armor.model.QuatorzeArmorModel;
import fr.augma.alfheimfly.items.QuatorzeArmorItem;

public class QuatorzeArmorRenderer extends AlfheimGeoArmorRenderer<QuatorzeArmorItem> {

	public QuatorzeArmorRenderer() {
		super(new QuatorzeArmorModel());
		
		this.headBone = "armorHead";
		this.bodyBone = "armorBody";
		this.leftArmBone = "armorLeftArm";
		this.rightArmBone = "armorRightArm";
		this.leftLegBone = "armorRightLeg";
		this.rightLegBone = "armorLeftLeg";
		this.leftBootBone = "armorRightBoot";
		this.rightBootBone = "armorLeftBoot";
	}
}
