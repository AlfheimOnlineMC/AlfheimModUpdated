package fr.augma.alfheimfly.config;

import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid=AlfheimRef.MODID)
public class ModConfig {
	
	@Name("Configuration des ailes")
	public static WingsConfig wingsConfig = new WingsConfig();
	
	@Name("Expérience des armes/armures")
	public static WeaponArmorExpConfig weapArmorConfig = new WeaponArmorExpConfig();
	
	public static class WingsConfig {
		
		@Name("Résolution des ailes")
		public Wings WingResolution = Wings.Low;
		
		@Name("Affiché les ailes ?")
		public boolean WingDisplay = true;
		
		public enum Wings {
			High,
			Low;
		}
	}
	
	public static class WeaponArmorExpConfig {
		
		@Name("Configuration armures")
		public Armor armor = new Armor();
		
		@Name("Configuration armes")
		public Weapon weapon = new Weapon();
		
		public enum ExpWeapArmor {
			Tooltip,
			Screen,
			Both;
		}
		
		public static class Armor {
			
			@Name("Affichage de l'experience des armures")
			public ExpWeapArmor display = ExpWeapArmor.Both;
			
			@Name("Affiché l'experience des armures ?")
			public boolean ExperienceArmorDisplay = true;
		}
		
		public static class Weapon {
			
			@Name("Affichage de l'experience des armes")
			public ExpWeapArmor display = ExpWeapArmor.Both;
			
			@Name("Affiché l'experience des armes ?")
			public boolean ExperienceWeaponDisplay = true;
		}
	}
	
	@Mod.EventBusSubscriber(modid=AlfheimRef.MODID)
	private static class EventHandler {
		
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(AlfheimRef.MODID)) {
				ConfigManager.sync(AlfheimRef.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
