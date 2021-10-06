package fr.augma.alfheimfly.utils;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.item.ItemStack;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class RuneUtils {

    private static final DecimalFormat df;
    private static final Random r;
    public static final UUID    ATTRIBUTE_CRIT_ID = UUID.fromString("4e15497c-68f1-4abe-9bcb-f2e9a28c3737"),
                                ATTRIBUTE_CRIT_DMG_ID = UUID.fromString("18629440-25bb-11ec-9621-0242ac130002"),
                                ATTRIBUTE_LIFE_STEAL_ID = UUID.fromString("5c8ed50d-0e79-4add-a0f9-df2e4f770013"),
                                ATTRIBUTE_DEF_PENE_ID = UUID.fromString("ecefb47c-598d-4aee-a552-781c9aca868a");
    public static final IAttribute CRIT = (new RangedAttribute(null, "alfheim.crit", 0.0D, 0D, 1.0D)).setShouldWatch(true);
    public static final IAttribute CRIT_DMG = (new RangedAttribute(null, "alfheim.crit_dmg", 0.0D, 0D, 1000.0D)).setShouldWatch(true);
    public static final IAttribute LIFE_STEAL = (new RangedAttribute(null, "alfheim.life_steal", 0.0D, 0D, 1000.0D)).setShouldWatch(true);
    public static final IAttribute DEF_PENE = (new RangedAttribute(null, "alfheim.def_pene", 0.0D, 0D, 1000.0D)).setShouldWatch(true);

    static {
        df = new DecimalFormat();
        r = new Random();
    }

    public enum RuneStatsType {
        ATTACK(SharedMonsterAttributes.ATTACK_DAMAGE),
        CRIT(RuneUtils.CRIT),
        CRIT_DMG(RuneUtils.CRIT_DMG),
        LIFE_STEAL(RuneUtils.LIFE_STEAL),
        DEF_PENE(RuneUtils.DEF_PENE),
        ATTACK_SPEED(SharedMonsterAttributes.ATTACK_SPEED),
        DEFENCE(SharedMonsterAttributes.ARMOR),
        HP(SharedMonsterAttributes.MAX_HEALTH),
        SPEED(SharedMonsterAttributes.MOVEMENT_SPEED);

        RuneStatsType (IAttribute attrib) {
            this.attributes = attrib;
        }

        IAttribute attributes;

        IAttribute getAttribute() {
            return this.attributes;
        }
    }

    private static boolean isAlreadyAttributed() {
        return true;
    }

    public static void roll(ItemStack item) {
        double  roll1 = Math.min(getStats(), 1D),
                roll2 = Math.min(getStats(), 1D);



    }

    private static double getStats() {
        return Double.parseDouble(df.format(Math.abs((r.nextGaussian() - 2 ) / 4)).replace(',', '.'));
    }
}
