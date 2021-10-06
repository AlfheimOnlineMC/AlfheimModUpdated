package fr.augma.alfheimfly.init;

import fr.augma.alfheimfly.utils.race.CaitSith;
import fr.augma.alfheimfly.utils.race.Gnome;
import fr.augma.alfheimfly.utils.race.Imp;
import fr.augma.alfheimfly.utils.race.Leprechaun;
import fr.augma.alfheimfly.utils.race.Pooka;
import fr.augma.alfheimfly.utils.race.Salamender;
import fr.augma.alfheimfly.utils.race.Spriggan;
import fr.augma.alfheimfly.utils.race.Sylph;
import fr.augma.alfheimfly.utils.race.Undine;

public class RaceInit {

	public static void init() {
		new CaitSith("textures/race/cait_sith.png");
		new Gnome("textures/race/gnome.png");
		new Imp("textures/race/imp.png");
		new Spriggan("textures/race/spriggan.png");
		new Sylph("textures/race/sylph.png");
		new Pooka("textures/race/pooka.png");
		new Leprechaun("textures/race/leprechaun.png");
		new Salamender("textures/race/salamender.png");
		new Undine("textures/race/undine.png");
	}
}
