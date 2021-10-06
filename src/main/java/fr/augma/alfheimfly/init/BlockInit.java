package fr.augma.alfheimfly.init;

import fr.augma.alfheimfly.blocks.AlfheimRuneBlock;
import fr.augma.alfheimfly.blocks.JojotableBlock;
import fr.augma.alfheimfly.blocks.VodkaBlock;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=AlfheimRef.MODID)
public class BlockInit {

	public static Block VODKA;
	public static Block JOJOTABLE;
	
	public static Block RUNE_COMMUN, RUNE_RARE, RUNE_EPIQUE, RUNE_MYTHIQUE, RUNE_UNIQUE;
	
	public static void init() {
		VODKA = new VodkaBlock(Material.ROCK, "vodka");
		JOJOTABLE = new JojotableBlock(Material.WOOD, "jojotable");
		RUNE_COMMUN = new AlfheimRuneBlock(Material.ROCK, "rune_commun_block");
		RUNE_RARE = new AlfheimRuneBlock(Material.ROCK, "rune_rare_block");
		RUNE_EPIQUE = new AlfheimRuneBlock(Material.ROCK, "rune_epique_block");
		RUNE_MYTHIQUE = new AlfheimRuneBlock(Material.ROCK, "rune_mythique_block");
		RUNE_UNIQUE = new AlfheimRuneBlock(Material.ROCK, "rune_unique_block");
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(VODKA, JOJOTABLE, RUNE_COMMUN, RUNE_RARE, RUNE_EPIQUE, RUNE_MYTHIQUE, RUNE_UNIQUE);
	}
}
