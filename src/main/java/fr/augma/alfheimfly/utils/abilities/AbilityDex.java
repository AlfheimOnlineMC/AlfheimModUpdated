package fr.augma.alfheimfly.utils.abilities;

public class AbilityDex extends AlfheimAbility {
	
	public AbilityDex() {
		super(EnumAbility.DEX);
	}
	
	public AbilityDex(float value) {
		super(EnumAbility.DEX, value);
	}

	@Override
	public float getCalculatedValue() {
		return 0;
	}

}
