package fr.augma.alfheimfly.utils.race;

import net.minecraft.util.ResourceLocation;

public class Undine extends AlfheimRace {

	public Undine(String icone) {
		super(EnumRace.Undine, icone);
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
