package fr.augma.alfheimfly.utils.race;

import net.minecraft.util.ResourceLocation;

public class Leprechaun extends AlfheimRace {

	public Leprechaun(String icone) {
		super(EnumRace.Leprechaun, icone);
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
