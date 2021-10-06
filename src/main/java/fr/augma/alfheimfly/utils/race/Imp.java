package fr.augma.alfheimfly.utils.race;

import net.minecraft.util.ResourceLocation;

public class Imp extends AlfheimRace {

	public Imp(String path) {
		super(EnumRace.Imp, path);
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
