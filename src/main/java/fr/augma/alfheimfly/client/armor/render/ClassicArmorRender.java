package fr.augma.alfheimfly.client.armor.render;

import fr.augma.alfheimfly.client.armor.custom.AlfheimGeoArmorRenderer;
import fr.augma.alfheimfly.client.armor.model.ClassicArmorModel;
import fr.augma.alfheimfly.items.ClassicArmorItem;

public class ClassicArmorRender extends AlfheimGeoArmorRenderer<ClassicArmorItem> {

	public ClassicArmorRender() {
		super(new ClassicArmorModel());
		
		this.headBone = "armorHead";
		this.bodyBone = "armorBody";
		this.rightArmBone = "armorRightArm";
		this.leftArmBone = "armorLeftArm";
		this.rightLegBone = "armorLeftLeg";
		this.leftLegBone = "armorRightLeg";
		this.rightBootBone = "armorLeftBoot";
		this.leftBootBone = "armorRightBoot";
	}

}
