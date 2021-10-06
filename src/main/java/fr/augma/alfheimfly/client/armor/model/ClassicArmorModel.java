package fr.augma.alfheimfly.client.armor.model;

import fr.augma.alfheimfly.items.ClassicArmorItem;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ClassicArmorModel extends AnimatedGeoModel<ClassicArmorItem> {

	@Override
	public ResourceLocation getAnimationFileLocation(ClassicArmorItem animatable) {
		return null;
	}

	@Override
	public ResourceLocation getModelLocation(ClassicArmorItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "geo/classicarmor.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(ClassicArmorItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "textures/armor/classic_armor_layer_1.png");
	}

}
