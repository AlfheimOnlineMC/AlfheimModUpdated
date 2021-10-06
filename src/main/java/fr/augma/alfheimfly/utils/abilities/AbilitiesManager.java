package fr.augma.alfheimfly.utils.abilities;

import java.util.ArrayList;
import java.util.List;

public class AbilitiesManager {
	
	public List<AlfheimAbility> abilities;
	
	public AbilitiesManager() {
		this.abilities = new ArrayList<>();
		
		this.abilities.add(new AbilityStr());
		this.abilities.add(new AbilityAgi());
		this.abilities.add(new AbilityDex());
		this.abilities.add(new AbilityDef());
		this.abilities.add(new AbilityVit());
	}
	
	public AbilitiesManager(AbilityStr str, AbilityAgi agi, AbilityDex dex, AbilityDef def, AbilityVit vit) {
		this.abilities = new ArrayList<>();
		
		this.abilities.add(str);
		this.abilities.add(agi);
		this.abilities.add(dex);
		this.abilities.add(def);
		this.abilities.add(vit);
	}
	
	public AlfheimAbility getAbility(EnumAbility name) {
		int index = 0;
		
		for(AlfheimAbility ability : this.abilities) {
			if(ability.getName() == name.getName()) break;
			index++;
		}
		
		return index > this.abilities.size() - 1 ? null : this.abilities.get(index);
	}

}
