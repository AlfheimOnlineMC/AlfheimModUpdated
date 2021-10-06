package fr.augma.alfheimfly.client.item.model;

import fr.augma.alfheimfly.client.skin.ISkinnable;
import fr.augma.alfheimfly.items.HoliganSword;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeckoHoliganModel extends AnimatedGeoModel<HoliganSword> implements ISkinnable<HoliganSword> {

	@Override
	public ResourceLocation getAnimationFileLocation(HoliganSword animatable) {
		return null;
	}

	@Override
	public ResourceLocation getModelLocation(HoliganSword object) {
		return null;
	}

	@Override
	public ResourceLocation getTextureLocation(HoliganSword object) {
		return null;
	}

	@Override
	public ResourceLocation getSkinModel(HoliganSword item, String name) {
		return new ResourceLocation(AlfheimRef.MODID, "geo/" + name + ".geo.json");
	}

	@Override
	public ResourceLocation getSkinTexture(HoliganSword item, String name) {
		return new ResourceLocation(AlfheimRef.MODID, "textures/items/" + name + ".png");
	}
}
