package fr.augma.alfheimfly.utils;

import fr.augma.alfheimfly.event.custom.AlfheimItemEvent;
import fr.augma.alfheimfly.items.AlfheimItemSword;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

public class SwordExperienceManager {
	
	private ItemStack item;
	private EntityPlayer player;
	private int level;
	private int max_level;
	private float current_experience;
	private float max_experience;
	
	public SwordExperienceManager(ItemStack item, EntityPlayer player) {
		this.player = player;
		this.item = item;
		if(!item.hasTagCompound()) this.initStats(item);
		this.level = item.getTagCompound().getInteger("level");
		this.current_experience = item.getTagCompound().getFloat("experience");
		this.max_experience = item.getTagCompound().getFloat("max_experience");
		this.max_level = ((AlfheimItemSword) item.getItem()).getRarity().getMaxLevel();
	}
	
	public void initStats(ItemStack stack) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("level", 0);
		this.level = 0;
		tag.setFloat("experience", 0);
		tag.setFloat("max_experience", (float) (this.level * (this.level + 2)) + 20f);
		stack.setTagCompound(tag);
	}
	
	public boolean isUpgradable() {
		return this.level != this.max_level;
	}
	
	public void processLevelUp(float exp, int levelBase) {
		if(!isUpgradable()) return;
		NBTTagCompound nbt = this.item.getTagCompound();
		nbt.setInteger("level", this.level + 1);
		float xp = (this.current_experience + exp) - this.max_experience;
		nbt.setFloat("experience", xp);
		this.level = this.level + 1;
		nbt.setFloat("max_experience", (float) (this.level * (this.level + 2)) + 20f);
		this.current_experience = nbt.getFloat("experience");
		this.max_experience = nbt.getFloat("max_experience");
		
		this.item.setTagCompound(nbt);
		
		if(this.current_experience >= this.max_experience) {
			this.processLevelUp(0, levelBase);
		} else {
			MinecraftForge.EVENT_BUS.post(new AlfheimItemEvent.ItemLevelUp(this.player, levelBase, this.level));
		}
	}
	
	public void addExperience(float exp) {
		if(!isUpgradable()) return;
		
		if(this.current_experience + exp >= this.max_experience) {
			this.processLevelUp(exp, this.level);
		} else {
			NBTTagCompound nbt = this.item.getTagCompound();
			nbt.setFloat("experience", this.current_experience + exp);
			item.setTagCompound(nbt);
		}
	}
}
