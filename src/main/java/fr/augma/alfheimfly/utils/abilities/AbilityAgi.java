package fr.augma.alfheimfly.utils.abilities;

public class AbilityAgi extends AlfheimAbility {

	public AbilityAgi() {
		super(EnumAbility.AGI);
	}
	
	public AbilityAgi(float value) {
		super(EnumAbility.AGI, value);
	}

	@Override
	public float getCalculatedValue() {
		return 0;
	}
}
