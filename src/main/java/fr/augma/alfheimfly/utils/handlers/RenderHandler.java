package fr.augma.alfheimfly.utils.handlers;

import fr.augma.alfheimfly.client.armor.render.ChapkaRenderer;
import fr.augma.alfheimfly.client.armor.render.ClassicArmorRender;
import fr.augma.alfheimfly.client.armor.render.EtronRenderer;
import fr.augma.alfheimfly.client.armor.render.MArmorRenderer;
import fr.augma.alfheimfly.client.armor.render.QuatorzeArmorRenderer;
import fr.augma.alfheimfly.client.armor.render.StartArmorMossyRenderer;
import fr.augma.alfheimfly.client.armor.render.StartArmorRenderer;
import fr.augma.alfheimfly.client.entity.model.*;
import fr.augma.alfheimfly.client.entity.render.*;
import fr.augma.alfheimfly.entities.*;
import fr.augma.alfheimfly.items.ChapkaItem;
import fr.augma.alfheimfly.items.ClassicArmorItem;
import fr.augma.alfheimfly.items.EtronItem;
import fr.augma.alfheimfly.items.MArmorItem;
import fr.augma.alfheimfly.items.QuatorzeArmorItem;
import fr.augma.alfheimfly.items.StartArmorItem;
import fr.augma.alfheimfly.items.StartArmorMossyItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderHandler {
	
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(BoarEntity.class, new IRenderFactory<BoarEntity>(){
			@Override
			public Render<? super BoarEntity> createRenderFor(RenderManager manager) {
				return new RenderBoarEntity(manager, new BoarEntityModel(), 0.7F);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(DeerEntity.class,
			    manager -> new RenderDeerEntity(manager, new DeerEntityModel(), 0.7f));
		
		RenderingRegistry.registerEntityRenderingHandler(MagBagCac.class,
				manager -> new RenderMagBagCacEntity(manager, new MagBagCacModel()));
		
		RenderingRegistry.registerEntityRenderingHandler(FrogEntity.class, 
				manager -> new RenderFrogEntity(manager, new FrogModel()));

		RenderingRegistry.registerEntityRenderingHandler(FairyEntity.class,
				manager -> new RenderFairyEntity(manager, new FairyEntityModel(), 0.7f));
	}

	public static void registerArmorRenderer() {
		GeoArmorRenderer.registerArmorRenderer(MArmorItem.class, new MArmorRenderer());
		GeoArmorRenderer.registerArmorRenderer(QuatorzeArmorItem.class, new QuatorzeArmorRenderer());
		GeoArmorRenderer.registerArmorRenderer(ChapkaItem.class, new ChapkaRenderer());
		GeoArmorRenderer.registerArmorRenderer(StartArmorItem.class, new StartArmorRenderer());
		GeoArmorRenderer.registerArmorRenderer(StartArmorMossyItem.class, new StartArmorMossyRenderer());
		GeoArmorRenderer.registerArmorRenderer(EtronItem.class, new EtronRenderer());
		GeoArmorRenderer.registerArmorRenderer(ClassicArmorItem.class, new ClassicArmorRender());
	}
}
