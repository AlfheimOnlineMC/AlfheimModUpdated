package fr.augma.alfheimfly.utils.race;

import net.minecraft.util.ResourceLocation;

public class Salamender extends AlfheimRace {

	public Salamender(String icone) {
		super(EnumRace.Salamender, icone);
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
