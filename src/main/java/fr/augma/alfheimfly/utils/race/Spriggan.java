package fr.augma.alfheimfly.utils.race;

import net.minecraft.util.ResourceLocation;

public class Spriggan extends AlfheimRace {

	public Spriggan(String path) {
		super(EnumRace.Spriggan, path);
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
