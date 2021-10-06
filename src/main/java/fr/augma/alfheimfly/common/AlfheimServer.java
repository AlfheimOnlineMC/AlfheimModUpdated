package fr.augma.alfheimfly.common;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import fr.augma.alfheimfly.utils.race.EnumRace;

public class AlfheimServer extends AlfheimCommon {
	
	public static HashMap<UUID, EnumRace> HashWings = new HashMap<>();

    @Override
    public void preinit(File configfile) {
        super.preinit(configfile);
    }

    @Override
    public void init() {
        super.init();
    }
 }
