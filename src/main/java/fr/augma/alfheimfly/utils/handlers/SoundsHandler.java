package fr.augma.alfheimfly.utils.handlers;

import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {
	public static SoundEvent ENTITY_SANGLIER_AMBIENT, ENTITY_SANGLIER_HURT, ENTITY_SANGLIER_DEATH;
	
	public static void registerSounds() {
		ENTITY_SANGLIER_AMBIENT = registerSound("entity.sanglier.ambient");
		ENTITY_SANGLIER_HURT = registerSound("entity.sanglier.hurt");
		ENTITY_SANGLIER_DEATH = registerSound("entity.sanglier.death");
	}
	
	public static SoundEvent registerSound(String name) {
		ResourceLocation loc = new ResourceLocation(AlfheimRef.MODID, name);
		SoundEvent sound = new SoundEvent(loc);
		sound.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(sound);
		return sound;
	}
}
