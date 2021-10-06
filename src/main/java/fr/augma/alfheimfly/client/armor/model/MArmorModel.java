package fr.augma.alfheimfly.client.armor.model;

import fr.augma.alfheimfly.items.MArmorItem;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MArmorModel extends AnimatedGeoModel<MArmorItem> {

	@Override
	public ResourceLocation getAnimationFileLocation(MArmorItem animatable) {
		return null;
	}

	@Override
	public ResourceLocation getModelLocation(MArmorItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "geo/marmor.json");
	}

	@Override
	public ResourceLocation getTextureLocation(MArmorItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "textures/armor/marmor.png");
	}

}
