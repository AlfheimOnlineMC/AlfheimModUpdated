package fr.augma.alfheimfly.utils.race;

import java.util.ArrayList;
import java.util.List;

import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.util.ResourceLocation;

public abstract class AlfheimRace {
	public String NAME;
	public ResourceLocation ICONE;
	public EnumRace ENUM;
	
	public static List<AlfheimRace> raceList = new ArrayList<>();
	
	public AlfheimRace(EnumRace race, String icone) {
		this.NAME = race.getName();
		this.ENUM = race;
		this.ICONE = new ResourceLocation(AlfheimRef.MODID, icone);
		
		raceList.add(this);
	}
	
	public abstract String getName();
	
	public abstract ResourceLocation getIcone();
	
	public static AlfheimRace getRace(EnumRace race) {
		return getRace(race.getName());
	}
	
	public static AlfheimRace getRace(String race) {
		int index = 0;
		
		for(AlfheimRace r : raceList) {
			if(r.getName().equalsIgnoreCase(race)) break;
			index++;
		}
		
		return index > raceList.size() - 1 ? null : raceList.get(index);
	}
	
	public EnumRace getEnumRace() {
		return this.ENUM;
	}
}
