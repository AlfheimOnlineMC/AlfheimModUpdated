package fr.augma.alfheimfly.utils.race;

import net.minecraft.util.ResourceLocation;

public class Gnome extends AlfheimRace {

	public Gnome(String path) {
		super(EnumRace.Gnome, path);
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
