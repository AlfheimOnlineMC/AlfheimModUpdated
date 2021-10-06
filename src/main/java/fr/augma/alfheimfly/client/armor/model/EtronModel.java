package fr.augma.alfheimfly.client.armor.model;

import fr.augma.alfheimfly.items.EtronItem;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EtronModel extends AnimatedGeoModel<EtronItem> {

	@Override
	public ResourceLocation getAnimationFileLocation(EtronItem animatable) {
		return null;
	}

	@Override
	public ResourceLocation getModelLocation(EtronItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "geo/etron.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EtronItem object) {
		return new ResourceLocation(AlfheimRef.MODID, "textures/armor/etron.png");
	}

}
