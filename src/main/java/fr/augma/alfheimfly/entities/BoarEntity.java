package fr.augma.alfheimfly.entities;

import fr.augma.alfheimfly.utils.handlers.LootTableHandler;
import fr.augma.alfheimfly.utils.handlers.SoundsHandler;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class BoarEntity extends EntityPig {

	public BoarEntity(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return LootTableHandler.SANGLIER;
	}
	
	@Override
	public EntityPig createChild(EntityAgeable ageable) {
		return new BoarEntity(world);
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsHandler.ENTITY_SANGLIER_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundsHandler.ENTITY_SANGLIER_HURT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsHandler.ENTITY_SANGLIER_DEATH;
	}
}
