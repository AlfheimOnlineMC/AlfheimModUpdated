package fr.augma.alfheimfly.client.player.layer;

import java.util.HashMap;
import java.util.UUID;

import fr.augma.alfheimfly.client.player.model.ModelCaitSithWing;
import fr.augma.alfheimfly.client.player.model.ModelGnomeWing;
import fr.augma.alfheimfly.client.player.model.ModelPookaWing;
import fr.augma.alfheimfly.client.player.model.ModelSalamenderWing;
import fr.augma.alfheimfly.client.player.model.ModelSprigganWing;
import fr.augma.alfheimfly.client.player.model.ModelSylphWing;
import fr.augma.alfheimfly.client.player.model.ModelUndineWing;
import fr.augma.alfheimfly.client.player.model.ModelWingsBase;
import fr.augma.alfheimfly.config.ModConfig;
import fr.augma.alfheimfly.utils.race.EnumRace;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;


public class LayerWings implements LayerRenderer<EntityPlayer> {
	
	private ModelWingsBase wings_spriggan,wings_salamender,wings_pooka,wings_undine,wings_sylph,wings_cait_sith,wings_gnome,wings_imp,wings_leprechaun;
	
	private static HashMap<UUID, EnumRace> hashMapWings = new HashMap<>();
	
	public LayerWings() {
		this.wings_spriggan = new ModelSprigganWing();
		this.wings_salamender = new ModelSalamenderWing();
		this.wings_pooka = new ModelPookaWing();
		this.wings_undine = new ModelUndineWing();
		this.wings_sylph = new ModelSylphWing();
		this.wings_cait_sith = new ModelCaitSithWing();
		this.wings_gnome = new ModelGnomeWing();
		this.wings_imp = null;
		this.wings_leprechaun = null;
	}

	@Override
	public void doRenderLayer(EntityPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if(hashMapWings.containsKey(entitylivingbaseIn.getUniqueID()) && entitylivingbaseIn.capabilities.allowFlying) {
			if(ModConfig.wingsConfig.WingDisplay) {
				GlStateManager.pushMatrix();
				GlStateManager.disableCull();
				if(entitylivingbaseIn.isSneaking()) {
					GlStateManager.rotate(22f, 1f, 0, 0);
					GlStateManager.translate(0, 0.2f, 0);
				}
				getWingsByRace(hashMapWings.get(entitylivingbaseIn.getUniqueID())).render(entitylivingbaseIn);
				GlStateManager.enableCull();
				GlStateManager.popMatrix();
			}
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
	
	public ModelWingsBase getWingsByRace(EnumRace race) {
		switch(race) {
		case CaitSith:
			return this.wings_cait_sith;
		case Gnome:
			return this.wings_gnome;
		case Imp:
			return this.wings_imp;
		case Leprechaun:
			return this.wings_leprechaun;
		case Pooka:
			return this.wings_pooka;
		case Salamender:
			return this.wings_salamender;
		case Spriggan:
			return this.wings_spriggan;
		case Sylph:
			return this.wings_sylph;
		case Undine:
			return this.wings_undine;
		default:
			return null;
		}
	}
	
	public static void refreshHashMap(HashMap<UUID, EnumRace> hashmap) {
		hashMapWings = hashmap;
	}
}
