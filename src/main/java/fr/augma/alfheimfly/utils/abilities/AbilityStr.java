package fr.augma.alfheimfly.utils.abilities;

public class AbilityStr extends AlfheimAbility {

	public AbilityStr() {
		super(EnumAbility.STR);
	}
	
	public AbilityStr(float value) {
		super(EnumAbility.STR, value);
	}

	@Override
	public float getCalculatedValue() {
		return 0;
	}
}
