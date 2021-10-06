package fr.augma.alfheimfly.client.armor.render;

import fr.augma.alfheimfly.client.armor.custom.AlfheimGeoArmorRenderer;
import fr.augma.alfheimfly.client.armor.model.EtronModel;
import fr.augma.alfheimfly.items.EtronItem;

public class EtronRenderer extends AlfheimGeoArmorRenderer<EtronItem> {

	public EtronRenderer() {
		super(new EtronModel());
		this.headBone = "armorHead";
		this.bodyBone = "nothing";
		this.leftArmBone = "nothing";
		this.rightArmBone = "nothing";
		this.leftLegBone = "nothing";
		this.rightLegBone = "nothing";
		this.leftBootBone = "nothing";
		this.rightBootBone = "nothing";
	}
}
