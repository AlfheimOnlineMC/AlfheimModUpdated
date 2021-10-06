package fr.augma.alfheimfly.utils.race;

import net.minecraft.util.ResourceLocation;

public class Pooka extends AlfheimRace {

	public Pooka(String icone) {
		super(EnumRace.Pooka, icone);
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
