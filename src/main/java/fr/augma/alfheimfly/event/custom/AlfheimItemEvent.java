package fr.augma.alfheimfly.event.custom;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

public class AlfheimItemEvent extends Event {
	
	private final EntityPlayer player;
	
	public AlfheimItemEvent(EntityPlayer player) {
		this.player = player;
	}
	
	public EntityPlayer getEntityPlayer() {
		return this.player;
	}
	
	@Cancelable
	public static class ItemLevelUp extends AlfheimItemEvent {
		private final int oldLevel, newLevel;

		public ItemLevelUp(EntityPlayer player, int oldLevel, int newLevel) {
			super(player);
			this.oldLevel = oldLevel;
			this.newLevel = newLevel;
		}
		
		public int getOldLevel() {
			return this.oldLevel;
		}
		
		public int getNewLevel() {
			return this.newLevel;
		}
	}
}
