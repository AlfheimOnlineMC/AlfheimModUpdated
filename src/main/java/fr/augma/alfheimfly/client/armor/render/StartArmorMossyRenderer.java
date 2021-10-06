package fr.augma.alfheimfly.client.armor.render;

import fr.augma.alfheimfly.client.armor.custom.AlfheimGeoArmorRenderer;
import fr.augma.alfheimfly.client.armor.model.StartArmorMossyModel;
import fr.augma.alfheimfly.items.StartArmorMossyItem;

public class StartArmorMossyRenderer extends AlfheimGeoArmorRenderer<StartArmorMossyItem> {

	public StartArmorMossyRenderer() {
		super(new StartArmorMossyModel());
	}

}
