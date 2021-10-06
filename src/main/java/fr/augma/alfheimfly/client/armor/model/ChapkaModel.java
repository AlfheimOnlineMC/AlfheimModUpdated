package fr.augma.alfheimfly.client.armor.model;

import fr.augma.alfheimfly.items.ChapkaItem;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChapkaModel extends AnimatedGeoModel<ChapkaItem> {

	@Override
	public ResourceLocation getAnimationFileLocation(ChapkaItem animatable) {
		return null;
	}

	@Override
	public ResourceLocation getModelLocation(ChapkaItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "geo/chapka.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(ChapkaItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "textures/armor/chapka.png");
	}

}
