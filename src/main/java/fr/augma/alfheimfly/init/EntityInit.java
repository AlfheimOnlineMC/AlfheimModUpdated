package fr.augma.alfheimfly.init;

import fr.augma.alfheimfly.AlfheimFly;
import fr.augma.alfheimfly.entities.*;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
	
	public static void registerEntities() {
		registerEntity("sanglier", BoarEntity.class, AlfheimRef.ENTITY_BOAR, 50 ,13811421, 13695182);
		registerEntity("deer", DeerEntity.class, AlfheimRef.ENTITY_DEER, 50, 13811421, 13695182);
		registerEntity("magbagcac", MagBagCac.class, AlfheimRef.ENTITY_MAGBAGCAC , 50, 13811421, 13695182);
		registerEntity("frog", FrogEntity.class, AlfheimRef.ENTITY_FROG , 50, 13811421, 13695182);
		registerEntity("fairy", FairyEntity.class, AlfheimRef.ENTITY_FAIRY , 50, 13811421, 13695182);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(AlfheimRef.MODID, "textures/entity/" + name + ".png"), entity, name, id, AlfheimFly.INSTANCE, range, 1, true, color1, color2);
	}
}
