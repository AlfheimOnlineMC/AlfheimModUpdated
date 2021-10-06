package fr.augma.alfheimfly.client.armor.model;

import fr.augma.alfheimfly.items.StartArmorMossyItem;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StartArmorMossyModel extends AnimatedGeoModel<StartArmorMossyItem> {

	@Override
	public ResourceLocation getAnimationFileLocation(StartArmorMossyItem animatable) {
		return null;
	}

	@Override
	public ResourceLocation getModelLocation(StartArmorMossyItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "geo/starter.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(StartArmorMossyItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "textures/armor/starterarmormossy.png");
	}

}
