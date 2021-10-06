package fr.augma.alfheimfly.items;

import fr.augma.alfheimfly.AlfheimFly;
import net.minecraft.inventory.EntityEquipmentSlot;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

public class ClassicArmorItem extends GeoArmorItem implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);

	public ClassicArmorItem(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot slot, String name) {
		super(materialIn, renderIndexIn, slot);
		this.setCreativeTab(AlfheimFly.ALFHEIM_TAB);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}
	
	private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<ClassicArmorItem>(this, "controller", 20, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

}
