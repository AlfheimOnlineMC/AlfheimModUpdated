package fr.augma.alfheimfly.utils.race;

import net.minecraft.util.ResourceLocation;

public class CaitSith extends AlfheimRace {

	public CaitSith(String path) {
		super(EnumRace.CaitSith, path);
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
