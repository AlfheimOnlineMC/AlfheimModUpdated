package fr.augma.alfheimfly.event;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import fr.augma.alfheimfly.AlfheimFly;
import fr.augma.alfheimfly.capabilities.IPlayerDataCap;
import fr.augma.alfheimfly.capabilities.PlayerDataCapProvider;
import fr.augma.alfheimfly.common.AlfheimServer;
import fr.augma.alfheimfly.event.custom.AlfheimItemEvent;
import fr.augma.alfheimfly.items.AlfheimItemSword;
import fr.augma.alfheimfly.packet.GuiClassOpen;
import fr.augma.alfheimfly.packet.RefreshCaitPlayer;
import fr.augma.alfheimfly.packet.SyncWingsPacket;
import fr.augma.alfheimfly.utils.DamageCalculatorHelper;
import fr.augma.alfheimfly.utils.RuneUtils;
import fr.augma.alfheimfly.utils.SwordExperienceManager;
import fr.augma.alfheimfly.utils.race.CaitSith;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import scala.tools.nsc.Global;

@Mod.EventBusSubscriber
public class CommonEventHandler {

	private static IPlayerDataCap cap;
	public static List<String> caitPlayerServ = new ArrayList<>();
	public static HashMap<UUID, Float> damageMap = new HashMap<>();
	
	@SubscribeEvent
    public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) event.addCapability(PlayerDataCapProvider.NAME, new PlayerDataCapProvider());
    }
	
	@SubscribeEvent
    public static void onPlayerCloned(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            if (event.getOriginal().hasCapability(PlayerDataCapProvider.CAPABILITY, null)) {
                cap = PlayerDataCapProvider.get(event.getOriginal());
                IPlayerDataCap newCap = PlayerDataCapProvider.get(event.getEntityPlayer());
                newCap.data(cap.data().copy());
            }
        }
    }
	
	@SubscribeEvent
    public static void onPlayerRespawn(PlayerRespawnEvent event) {
        PlayerDataCapProvider.sync(event.player);
    }
	
	@SubscribeEvent
	public static void onPlayerLoggedInEvent(PlayerLoggedInEvent event) {
		if(!event.player.world.isRemote) {
			PlayerDataCapProvider.sync(event.player);
			
			if(!PlayerDataCapProvider.get(event.player).hasRace()) AlfheimFly.network.sendTo(new GuiClassOpen(), (EntityPlayerMP) event.player);
			else {
				AlfheimServer.HashWings.put(event.player.getUniqueID(), PlayerDataCapProvider.get(event.player).getRace().getEnumRace());
				AlfheimFly.network.sendToAll(new SyncWingsPacket(AlfheimServer.HashWings));
			}
			
			if(PlayerDataCapProvider.get(event.player).getRace() instanceof CaitSith) {
				if(!caitPlayerServ.contains(event.player.getName())) {
					caitPlayerServ.add(event.player.getName());
					AlfheimFly.network.sendToAll(new RefreshCaitPlayer(caitPlayerServ));
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerLoggedOutEvent(PlayerLoggedOutEvent event) {
		if(!event.player.world.isRemote)
			if(caitPlayerServ.contains(event.player.getName())) caitPlayerServ.remove(event.player.getName());
			if(AlfheimServer.HashWings.containsKey(event.player.getUniqueID())) AlfheimServer.HashWings.remove(event.player.getUniqueID());
	}
	
	@SubscribeEvent
	public static void onPlayerKillEntity(LivingDeathEvent event) {
		if(event.getSource().getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			if(player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() instanceof AlfheimItemSword) {
				float entityXpValue = getEntityExpValue(event.getEntityLiving());
				ItemStack item = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
				SwordExperienceManager expManager = new SwordExperienceManager(item, player);
				expManager.addExperience(entityXpValue);
			}
		}
	}
	
	public static float getEntityExpValue(EntityLivingBase entity) {
		if(entity instanceof EntityZombie) {
			return AlfheimFly.expEntityValue.get("zombie");
		} else if(entity instanceof EntitySpider) {
			return AlfheimFly.expEntityValue.get("spider");
		} else if(entity instanceof EntityCreeper) {
			return AlfheimFly.expEntityValue.get("creeper");
		} else if(entity instanceof EntitySkeleton) {
			return AlfheimFly.expEntityValue.get("skeleton");
		} else if(entity instanceof EntityAnimal) {
			return AlfheimFly.expEntityValue.get("animal");
		} else {
			return AlfheimFly.expEntityValue.get("other");
		}
	}
	
	@SubscribeEvent
	public static void onSwordLevelUpEvent(AlfheimItemEvent.ItemLevelUp event) {
		if(!event.getEntityPlayer().world.isRemote) {
			event.getEntityPlayer().sendMessage(new TextComponentString("Bravo ton épée a gagné " + (event.getNewLevel() - event.getOldLevel()) + " level."));
		}
	}

	@SubscribeEvent
	public static void onCrit(CriticalHitEvent event) {
		event.setResult(Event.Result.DENY);
	}

	@SubscribeEvent
	public static void onHitEntity(LivingHurtEvent e) {
		if(e.getSource().getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getSource().getTrueSource();
			ItemStack item = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);

			double 	percentage = 0D,
					dmgMultiplier = 0D,
					lifeSteal = 0D,
					def_pene = 0D;

			for(Map.Entry<String, AttributeModifier> att : item.getAttributeModifiers(EntityEquipmentSlot.MAINHAND).entries()) {
				if(att.getValue().getID().equals(RuneUtils.ATTRIBUTE_CRIT_ID)) {
					percentage = att.getValue().getAmount();
				} else if(att.getValue().getID().equals(RuneUtils.ATTRIBUTE_CRIT_DMG_ID)) {
					dmgMultiplier += att.getValue().getAmount();
				} else if(att.getValue().getID().equals(RuneUtils.ATTRIBUTE_LIFE_STEAL_ID)) {
					lifeSteal += att.getValue().getAmount();
				} else if(att.getValue().getID().equals(RuneUtils.ATTRIBUTE_DEF_PENE_ID)) {
					def_pene += att.getValue().getAmount();
				}
			}

			if(dmgMultiplier == 0D) {
				dmgMultiplier = 1.1D;
			}

			if(Math.random() <= percentage) {
				e.setAmount((float) (e.getAmount() * dmgMultiplier));
				player.sendMessage(new TextComponentString("" + e.getAmount()));
			}

			if(e.getAmount() * lifeSteal != 0D) {
				player.sendMessage(new TextComponentString("Vous avez été heal de " + (e.getAmount() * lifeSteal) + " HP"));
				player.heal((float) (e.getAmount() * lifeSteal));
			}

			double damage = DamageCalculatorHelper.getDamage(e.getEntityLiving(), e.getSource(), e.getAmount(), (int) def_pene);
			player.sendMessage(new TextComponentString("" + (damage)));
			damageMap.put(e.getEntityLiving().getUniqueID(), (float) damage);
		}
	}

	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event) {
		if(!damageMap.containsKey(event.getEntityLiving().getUniqueID())) return;
		event.setAmount(damageMap.get(event.getEntityLiving().getUniqueID()));
		damageMap.remove(event.getEntityLiving().getUniqueID());
	}
}