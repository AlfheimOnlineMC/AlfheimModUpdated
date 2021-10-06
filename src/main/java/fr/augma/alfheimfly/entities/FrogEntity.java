package fr.augma.alfheimfly.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class FrogEntity extends EntityRabbit implements IAnimatable {

	private AnimationFactory factory = new AnimationFactory(this);

	public FrogEntity(World worldIn) {
		super(worldIn);
		this.setSize(0.8F, 0.7F);
		this.ignoreFrustumCheck = true;
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, new FrogEntity.AIPanic(this, 2.2D));
        this.tasks.addTask(2, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(3, new EntityAILookIdle(this));
	}

	private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<IAnimatable>(this, "controller", 0, this::animationPredicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	public EntityRabbit createChild(EntityAgeable ageable) {
		return null;
	}
	
	static class AIPanic extends EntityAIPanic {
        private final FrogEntity frog;

        public AIPanic(FrogEntity frog, double speedIn) {
            super(frog, speedIn);
            this.frog = frog;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask() {
            super.updateTask();
            this.frog.setMovementSpeed(this.speed);
        }
    }
}
