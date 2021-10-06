package fr.augma.alfheimfly.client.item.renderer;

import fr.augma.alfheimfly.client.item.model.GeckoHoliganModel;
import fr.augma.alfheimfly.client.skin.GeoSkinItemRenderer;
import fr.augma.alfheimfly.items.HoliganSword;

public class HoliganItemRenderer extends GeoSkinItemRenderer<HoliganSword, GeckoHoliganModel> {

	public HoliganItemRenderer() {
		super(new GeckoHoliganModel());
	}
}
