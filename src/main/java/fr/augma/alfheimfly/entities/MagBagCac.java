package fr.augma.alfheimfly.entities;

import fr.augma.alfheimfly.entities.ai.EntityAIMagBagCacAttack;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MagBagCac extends EntityMob implements IAnimatable {
	
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(MagBagCac.class, DataSerializers.BOOLEAN);
	private AnimationFactory factory = new AnimationFactory(this);

	public MagBagCac(World worldIn) {
		super(worldIn);
		this.ignoreFrustumCheck = true;
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMagBagCacAttack(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.ApplyEntityAI();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void ApplyEntityAI() {
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(ATTACKING, Boolean.valueOf(false));
		this.setSize(1.4F, 1.75F);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
	}

	private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
		if (this.isAttacking()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("attack1", true));
			return PlayState.CONTINUE;
		} else if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
			return PlayState.CONTINUE;
		}
		event.getController().setAnimation(new AnimationBuilder().addAnimation("Idle", true));
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<IAnimatable>(this, "controller", 0, this::animationPredicate));
	}
	
	public void setAttacking(boolean attack) {
		this.getDataManager().set(ATTACKING, Boolean.valueOf(attack));
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isAttacking() {
		return ((Boolean) this.getDataManager().get(ATTACKING)).booleanValue();
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

}
