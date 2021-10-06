package fr.augma.alfheimfly.utils.race;

public enum EnumRace {
	CaitSith("CaitSith"),
	Gnome("Gnome"),
	Imp("Imp"),
	Leprechaun("Leprechaun"),
	Undine("Undine"),
	Pooka("Pooka"),
	Salamender("Salamender"),
	Spriggan("Spriggan"),
	Sylph("Sylph");
	
	String raceName;
	
	private EnumRace(String name) {
		this.raceName = name;
	}
	
	String getName() {
		return this.raceName;
	}
	
	public static EnumRace getEnumByString(String race) {
		EnumRace result = null;
		for(EnumRace value : EnumRace.values()) {
			if(value.getName().contentEquals(race)) {
				result = value;
				break;
			}
		}
		return result;
	}
}
