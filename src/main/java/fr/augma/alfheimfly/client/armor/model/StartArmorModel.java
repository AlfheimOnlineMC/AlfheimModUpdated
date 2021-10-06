package fr.augma.alfheimfly.client.armor.model;

import fr.augma.alfheimfly.items.StartArmorItem;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StartArmorModel extends AnimatedGeoModel<StartArmorItem> {

	@Override
	public ResourceLocation getAnimationFileLocation(StartArmorItem animatable) {
		return null;
	}

	@Override
	public ResourceLocation getModelLocation(StartArmorItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "geo/starter.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(StartArmorItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "textures/armor/starterarmor.png");
	}

}
