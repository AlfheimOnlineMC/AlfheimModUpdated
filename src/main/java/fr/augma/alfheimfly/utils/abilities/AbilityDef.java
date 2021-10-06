package fr.augma.alfheimfly.utils.abilities;

public class AbilityDef extends AlfheimAbility {

	public AbilityDef() {
		super(EnumAbility.DEF);
	}
	
	public AbilityDef(float value) {
		super(EnumAbility.DEF, value);
	}

	@Override
	public float getCalculatedValue() {
		return 0;
	}
}
