package fr.augma.alfheimfly.entities.ai;

import fr.augma.alfheimfly.entities.MagBagCac;
import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAIMagBagCacAttack extends EntityAIAttackMelee {
	
	private final MagBagCac magbag;
	private int attackingTicks;

	public EntityAIMagBagCacAttack(MagBagCac creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
		this.magbag = creature;
	}
	
	@Override
	public void startExecuting() {
		super.startExecuting();
		this.attackingTicks = 0;
	}
	
	@Override
	public void resetTask() {
		super.resetTask();
		this.magbag.setAttacking(false);
	}
	
	@Override
	public void updateTask() {
		super.updateTask();
		
		++this.attackingTicks;

        if (this.attackingTicks >= 5 && this.attackTick < 10)
        {
            this.magbag.setAttacking(false);
        }
        else
        {
            this.magbag.setAttacking(true);
        }
	}

}
