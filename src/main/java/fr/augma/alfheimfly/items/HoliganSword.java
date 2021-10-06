package fr.augma.alfheimfly.items;

import java.util.List;

import fr.augma.alfheimfly.client.item.renderer.HoliganItemRenderer;
import fr.augma.alfheimfly.client.skin.IHasSkin;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class HoliganSword extends GeckoItemSword implements IAnimatable, IHasSkin {
	
	public AnimationFactory factory = new AnimationFactory(this);

	public HoliganSword(String name, ToolMaterial mat, EnumItemRarity rarity) {
		super(name, mat, rarity);
	}
	
	@Override
	public String getDefaultModel() {
		return "holigan";
	}

	@Override
	public String getDefaultTexture() {
		return "epee/holigan";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
	}
	
	private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
		return PlayState.CONTINUE;
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Skin : " + (stack.hasTagCompound() && stack.getTagCompound().getBoolean("hasskin") ? stack.getTagCompound().getString("skin") : "default"));
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
