package fr.augma.alfheimfly.utils.abilities;

public class AbilityVit extends AlfheimAbility {

	public AbilityVit() {
		super(EnumAbility.VIT);
	}
	
	public AbilityVit(float value) {
		super(EnumAbility.VIT, value);
	}

	@Override
	public float getCalculatedValue() {
		return 0;
	}

}
