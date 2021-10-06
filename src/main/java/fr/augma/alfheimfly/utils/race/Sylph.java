package fr.augma.alfheimfly.utils.race;

import net.minecraft.util.ResourceLocation;

public class Sylph extends AlfheimRace {

	public Sylph(String icone) {
		super(EnumRace.Sylph, icone);
	}

	@Override
	public String getName() {
		return this.NAME;
	}

	@Override
	public ResourceLocation getIcone() {
		return this.ICONE;
	}

}
