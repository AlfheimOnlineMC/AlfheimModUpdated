package fr.augma.alfheimfly.utils;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;

public class DamageCalculatorHelper {

    public static double getDamage(EntityLivingBase entity, DamageSource dmgSrc, double damageAmount, double def_pene) {
        damageAmount = applyArmorCalculations(entity, dmgSrc, (float) damageAmount, def_pene);
        damageAmount = applyPotionDamageCalculations(entity, dmgSrc, (float) damageAmount);
        float f = (float) damageAmount;
        damageAmount = Math.max(damageAmount - entity.getAbsorptionAmount(), 0.0F);
        entity.setAbsorptionAmount((float) (entity.getAbsorptionAmount() - (f - damageAmount)));

        return damageAmount;
    }

    private static float applyArmorCalculations(EntityLivingBase entity, DamageSource source, float damage, double def_pene) {
        if (!source.isUnblockable()) {
            if(entity instanceof EntityPlayer) {
                ((EntityPlayer)entity).inventory.damageArmor(damage);
            }
            damage = CombatRules.getDamageAfterAbsorb(damage, (float) Math.max(entity.getTotalArmorValue() - def_pene, 0F), (float)entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        }

        return damage;
    }

    private static float applyPotionDamageCalculations(EntityLivingBase entity, DamageSource source, float damage) {
        if (source.isDamageAbsolute()) {
            return damage;
        } else {
            if (entity.isPotionActive(MobEffects.RESISTANCE) && source != DamageSource.OUT_OF_WORLD) {
                int i = (entity.getActivePotionEffect(MobEffects.RESISTANCE).getAmplifier() + 1) * 5;
                int j = 25 - i;
                float f = damage * (float)j;
                damage = f / 25.0F;
            }

            if (damage <= 0.0F) {
                return 0.0F;
            } else {
                int k = EnchantmentHelper.getEnchantmentModifierDamage(entity.getArmorInventoryList(), source);

                if (k > 0) {
                    damage = CombatRules.getDamageAfterMagicAbsorb(damage, (float)k);
                }
                return damage;
            }
        }
    }
}
