package fr.augma.alfheimfly.client.armor.render;

import fr.augma.alfheimfly.client.armor.custom.AlfheimGeoArmorRenderer;
import fr.augma.alfheimfly.client.armor.model.StartArmorModel;
import fr.augma.alfheimfly.items.StartArmorItem;

public class StartArmorRenderer extends AlfheimGeoArmorRenderer<StartArmorItem> {

	public StartArmorRenderer() {
		super(new StartArmorModel());
		
		this.headBone = "nothing";
		this.bodyBone = "armorBody";
		this.leftArmBone = "nothing";
		this.rightArmBone = "nothing";
		this.leftLegBone = "nothing";
		this.rightLegBone = "nothing";
		this.leftBootBone = "nothing";
		this.rightBootBone = "nothing";
	}

}
