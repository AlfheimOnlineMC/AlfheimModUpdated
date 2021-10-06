package fr.augma.alfheimfly.utils.abilities;

public abstract class AlfheimAbility {
	
	public String NAME;
	public float VALUE;
	public float COEF;
	
	public AlfheimAbility(EnumAbility ability) {
		this.NAME = ability.getName();
		this.VALUE = ability.getBaseValue();
		this.COEF = ability.getCoef();
	}
	
	public AlfheimAbility(EnumAbility ability, float value) {
		this.NAME = ability.getName();
		this.VALUE = value;
		this.COEF = ability.getCoef();
	}
	
	public String getName() {
		return this.NAME;
	}
	
	public float getValue() {
		return this.VALUE;
	}
	
	public float getCoef() {
		return this.COEF;
	}
	
	public abstract float getCalculatedValue();
}
