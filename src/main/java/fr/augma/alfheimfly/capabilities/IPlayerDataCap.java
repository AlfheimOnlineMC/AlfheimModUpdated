package fr.augma.alfheimfly.capabilities;

import javax.annotation.Nullable;

import fr.augma.alfheimfly.utils.abilities.AbilitiesManager;
import fr.augma.alfheimfly.utils.abilities.AbilityAgi;
import fr.augma.alfheimfly.utils.abilities.AbilityDef;
import fr.augma.alfheimfly.utils.abilities.AbilityDex;
import fr.augma.alfheimfly.utils.abilities.AbilityStr;
import fr.augma.alfheimfly.utils.abilities.AbilityVit;
import fr.augma.alfheimfly.utils.abilities.EnumAbility;
import fr.augma.alfheimfly.utils.race.AlfheimRace;
import net.minecraft.nbt.NBTTagCompound;

public interface IPlayerDataCap {
	
	NBTTagCompound data();
	
	void data(NBTTagCompound data);
	
	default AbilitiesManager getAbilities() {
		return new AbilitiesManager(getSTR(), getAGI(), getDEX(), getDEF(), getVIT());
	}
	
	default AbilityStr getSTR() {
		return new AbilityStr(this.data().getFloat(EnumAbility.STR.getName()));
	}
	
	default AbilityAgi getAGI() {
		return new AbilityAgi(this.data().getFloat(EnumAbility.AGI.getName()));
	}
	
	default AbilityVit getVIT() {
		return new AbilityVit(this.data().getFloat(EnumAbility.VIT.getName()));
	}
	
	default AbilityDex getDEX() {
		return new AbilityDex(this.data().getFloat(EnumAbility.DEX.getName()));
	}
	
	default AbilityDef getDEF() {
		return new AbilityDef(this.data().getFloat(EnumAbility.DEF.getName()));
	}
	
	default AlfheimRace getRace() {
		return AlfheimRace.getRace(this.data().getString("race"));
	}
	
	default boolean hasRace() {
		return getRace() == null ? false : true;
	}
	
	default void setRace(@Nullable AlfheimRace race) {
		if(race != null) {
			this.data().setString("race", race.getName());
		} else {
			this.data().setString("race", "");
		}
	}
	
	class Impl implements IPlayerDataCap {
		
		private NBTTagCompound data;
		
		public Impl(NBTTagCompound data) {
			this.data = data;
		}
		
		public Impl() {
            this(new NBTTagCompound());
        }
		
		public NBTTagCompound data() {
            return this.data;
        }

        public void data(NBTTagCompound data) {
            this.data = data;
        }
	}
}
