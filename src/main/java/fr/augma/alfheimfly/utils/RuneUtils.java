package fr.augma.alfheimfly.utils;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.text.DecimalFormat;
import java.util.*;

public class RuneUtils {

    private static final DecimalFormat df;
    private static final Random r;
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
        SPEED(SharedMonsterAttributes.MOVEMENT_SPEED),
        MAX_HEALTH(SharedMonsterAttributes.MAX_HEALTH),
        ARMOR(SharedMonsterAttributes.ARMOR),
        ARMOR_TOUGHNESS(SharedMonsterAttributes.ARMOR_TOUGHNESS),
        LUCK(SharedMonsterAttributes.LUCK),
        KNOCKBACK_RESISTANCE(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);

        RuneStatsType (IAttribute attrib) {
            this.attributes = attrib;
        }

        IAttribute attributes;

        IAttribute getAttribute() {
            return this.attributes;
        }
    }

    public enum RuneType {
        SWORD(Arrays.asList(RuneStatsType.ATTACK, RuneStatsType.ATTACK_SPEED, RuneStatsType.LUCK, RuneStatsType.CRIT, RuneStatsType.CRIT_DMG, RuneStatsType.DEF_PENE, RuneStatsType.LIFE_STEAL)),
        ARMOR(Arrays.asList(RuneStatsType.ARMOR_TOUGHNESS, RuneStatsType.ARMOR, RuneStatsType.LUCK, RuneStatsType.KNOCKBACK_RESISTANCE, RuneStatsType.SPEED, RuneStatsType.MAX_HEALTH));

        RuneType(List<RuneStatsType> attr) {
            this.attr = attr;
        }

        List<RuneStatsType> attr;

        List<RuneStatsType> getAttr() {
            return this.attr;
        }

        IAttribute getRandomAttribute() {
            return this.attr.get(r.nextInt(this.attr.size())).getAttribute();
        }

        public static RuneType getRand() {
            return new Random().nextBoolean() ? SWORD : ARMOR;
        }
    }


    private static boolean isAlreadyAttributed() {
        return true;
    }

    public static void roll(ItemStack item, RuneType type) {
        float   roll1 = getStats(),
                roll2 = getStats();

        if (!item.hasTagCompound()) item.setTagCompound(new NBTTagCompound());

        NBTTagCompound nbt = item.getTagCompound();
        String att1 = type.getRandomAttribute().getName(), att2 = type.getRandomAttribute().getName();

        while(att2.equalsIgnoreCase(att1)) {
            att2 = type.getRandomAttribute().getName();
        }

        nbt.setString("slot1", att1);
        nbt.setFloat("slot1_coef", roll1);

        nbt.setString("slot2", att2);
        nbt.setFloat("slot2_coef", roll2);

        nbt.setString("type", type.name());
    }

    private static float getStats() {
        double res;
        res = Math.min(Math.abs((r.nextGaussian() + 2 ) / 4), 1.4D);
        return Float.parseFloat(df.format((res * 1) / 1.4D));
    }
}
