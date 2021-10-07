package fr.augma.alfheimfly.init;

import fr.augma.alfheimfly.client.item.renderer.HoliganItemRenderer;
import fr.augma.alfheimfly.items.AlfheimItemBasic;
import fr.augma.alfheimfly.items.AlfheimItemFoodBasic;
import fr.augma.alfheimfly.items.AlfheimItemSword;
import fr.augma.alfheimfly.items.ChapkaItem;
import fr.augma.alfheimfly.items.EnumItemRarity;
import fr.augma.alfheimfly.items.EtronItem;
import fr.augma.alfheimfly.items.HoliganSword;
import fr.augma.alfheimfly.items.MArmorItem;
import fr.augma.alfheimfly.items.QuatorzeArmorItem;
import fr.augma.alfheimfly.items.RuneItem;
import fr.augma.alfheimfly.items.StartArmorItem;
import fr.augma.alfheimfly.items.StartArmorMossyItem;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid=AlfheimRef.MODID)
public class ItemInit {

	public static Item BOAR_STEAK;
	public static Item BOAR_LEATHER;
	public static Item COOKED_BOAR_STEAK;
	
	public static ToolMaterial staff_staff_mat;
	public static Item staff_staff;
	
	public static ToolMaterial poop_staff_mat;
	public static Item poop_staff;
	
	public static ToolMaterial hestia_material;
    public static Item hestia_dagger;
    
    public static ToolMaterial starter_scythe_material;
    public static Item starter_scythe;
    
    public static ToolMaterial holigan_mat;
    public static Item holigan_sword;
    
    public static MArmorItem chestplate;
    
    public static QuatorzeArmorItem armor14helmet;
    public static QuatorzeArmorItem armor14chestplate;
    public static QuatorzeArmorItem armor14legging;
    public static QuatorzeArmorItem armor14boots;
    
    public static ChapkaItem chapka;
    
    public static EtronItem etron;
    
    public static StartArmorItem chestStart;
    public static StartArmorMossyItem chestStartMossy;
    
    public static Item vodka;
    public static Item jojotable;
    
    public static Item rune_commun, rune_rare, rune_epique, rune_mythique, rune_unique;
    public static Item rune_commun_unpolished, rune_rare_unpolished, rune_epique_unpolished, rune_mythique_unpolished, rune_unique_unpolished;
    
    public static Item rune_commun_block, rune_rare_block, rune_epique_block, rune_mythique_block, rune_unique_block;
	
	public static void init() {
		BOAR_STEAK = new AlfheimItemFoodBasic("boar_steak", 2, 1f, false);
		BOAR_LEATHER = new AlfheimItemBasic("boar_leather");
		COOKED_BOAR_STEAK = new AlfheimItemFoodBasic("cooked_boar_steak", 5, 3f, false);
		
		staff_staff_mat = EnumHelper.addToolMaterial("staff_staff", 0, 4000, 20.0f, 99995, 22);
		staff_staff = new AlfheimItemSword("staff_staff", staff_staff_mat, EnumItemRarity.Unique);
		
		poop_staff_mat = EnumHelper.addToolMaterial("poop_staff", 0, 4000, 20.0f, 99995, 22);
		poop_staff = new AlfheimItemSword("poop_staff", staff_staff_mat, EnumItemRarity.Unique);
		
		hestia_material = EnumHelper.addToolMaterial("hestia_dagger", 0, 4000, 20.0F, 5, 22);
        hestia_dagger = new AlfheimItemSword("hestia_dagger", hestia_material, EnumItemRarity.Unique);
        
        starter_scythe_material = EnumHelper.addToolMaterial("starter_scythe", 0, 4000, 20.0F, 5, 22);
        starter_scythe = new AlfheimItemSword("starter_scythe", starter_scythe_material, EnumItemRarity.Commun);
        
        chestplate = new MArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.CHEST, "marmor");
        armor14helmet = new QuatorzeArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.HEAD, "14_helmet");
        armor14chestplate = new QuatorzeArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.CHEST, "14_chestplate");
        armor14legging = new QuatorzeArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.LEGS, "14_legging");
        armor14boots = new QuatorzeArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.FEET, "14_boots");
        chapka = new ChapkaItem(ItemArmor.ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.HEAD, "chapka");
        etron = new EtronItem(ItemArmor.ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.HEAD, "etron");
        chestStart = new StartArmorItem(ItemArmor.ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.CHEST, "starter_chestplate");
        chestStartMossy = new StartArmorMossyItem(ItemArmor.ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.CHEST, "starter_chestplate_mossy");
        
        vodka = new ItemBlock(BlockInit.VODKA).setRegistryName(BlockInit.VODKA.getRegistryName());
        jojotable = new ItemBlock(BlockInit.JOJOTABLE).setRegistryName(BlockInit.JOJOTABLE.getRegistryName());
        
        rune_commun = new RuneItem("rune_commun");
        rune_rare = new RuneItem("rune_rare");
        rune_epique = new RuneItem("rune_epique");
        rune_mythique = new RuneItem("rune_mythique");
        rune_unique = new RuneItem("rune_unique");
        
        rune_commun_unpolished = new RuneItem("rune_commun_unpolished");
        rune_rare_unpolished = new RuneItem("rune_rare_unpolished");
        rune_epique_unpolished = new RuneItem("rune_epique_unpolished");
        rune_mythique_unpolished = new RuneItem("rune_mythique_unpolished");
        rune_unique_unpolished = new RuneItem("rune_unique_unpolished");
        
        rune_commun_block = new ItemBlock(BlockInit.RUNE_COMMUN).setRegistryName(BlockInit.RUNE_COMMUN.getRegistryName());
        rune_rare_block = new ItemBlock(BlockInit.RUNE_RARE).setRegistryName(BlockInit.RUNE_RARE.getRegistryName());
        rune_epique_block = new ItemBlock(BlockInit.RUNE_EPIQUE).setRegistryName(BlockInit.RUNE_EPIQUE.getRegistryName());
        rune_mythique_block = new ItemBlock(BlockInit.RUNE_MYTHIQUE).setRegistryName(BlockInit.RUNE_MYTHIQUE.getRegistryName());
        rune_unique_block = new ItemBlock(BlockInit.RUNE_UNIQUE).setRegistryName(BlockInit.RUNE_UNIQUE.getRegistryName());
        
        holigan_mat = EnumHelper.addToolMaterial("holigan_sword", 0, 4000, 20.0f, 99995, 22);
		holigan_sword = new HoliganSword("holigan_sword", holigan_mat, EnumItemRarity.Commun);
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(BOAR_STEAK, BOAR_LEATHER,COOKED_BOAR_STEAK,hestia_dagger,starter_scythe,chestplate,armor14helmet,armor14chestplate,armor14legging,armor14boots,chapka,chestStart,chestStartMossy,vodka,staff_staff,poop_staff,etron,jojotable,
				rune_commun,rune_rare,rune_epique,rune_mythique,rune_unique,rune_commun_unpolished,rune_rare_unpolished,rune_epique_unpolished,rune_mythique_unpolished,rune_unique_unpolished,rune_commun_block,rune_rare_block, rune_epique_block,rune_mythique_block,rune_unique_block,
				holigan_sword);
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		registerRender(BOAR_STEAK);
		registerRender(BOAR_LEATHER);
		registerRender(COOKED_BOAR_STEAK);
		registerRender(hestia_dagger);
		registerRender(starter_scythe);
		registerRender(chestplate);
		registerRender(armor14helmet);
		registerRender(armor14chestplate);
		registerRender(armor14legging);
		registerRender(armor14boots);
		registerRender(chapka);
		registerRender(chestStart);
		registerRender(chestStartMossy);
		registerRender(vodka);
		registerRender(staff_staff);
		registerRender(poop_staff);
		registerRender(etron);
		registerRender(jojotable);
		registerRender(rune_commun);
		registerRender(rune_rare);
		registerRender(rune_epique);
		registerRender(rune_mythique);
		registerRender(rune_unique);
		registerRender(rune_commun_unpolished);
		registerRender(rune_rare_unpolished);
		registerRender(rune_epique_unpolished);
		registerRender(rune_mythique_unpolished);
		registerRender(rune_unique_unpolished);
		registerRender(rune_commun_block);
		registerRender(rune_rare_block);
		registerRender(rune_epique_block);
		registerRender(rune_mythique_block);
		registerRender(rune_unique_block);
		registerRender(holigan_sword);
		holigan_sword.setTileEntityItemStackRenderer(new HoliganItemRenderer());
	}
	
	public static void registerRender(Item item) {
		registerRender(item, 0);
	}
	
	public static void registerRender(Item item, int metadata) {
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
