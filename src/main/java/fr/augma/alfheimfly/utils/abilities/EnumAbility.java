package fr.augma.alfheimfly.utils.abilities;

public enum EnumAbility {
	STR("STR", 0F, 0.5F),
	AGI("AGI", 0F, 0.2F),
	DEX("DEX", 0F, 0.3F),
	DEF("DEF", 0F, 0.4F),
	VIT("VIT", 0F, 0.1F);
	
	String NAME;
	float BASEVALUE;
	float COEF;
	
	private EnumAbility(String name, float basevalue, float coef) {
		this.NAME = name;
		this.BASEVALUE = basevalue;
		this.COEF = coef;
	}
	
	public String getName() {
		return this.NAME;
	}
	
	float getBaseValue() {
		return this.BASEVALUE;
	}
	
	float getCoef() {
		return this.COEF;
	}
}
