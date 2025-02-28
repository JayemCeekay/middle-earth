package net.jukoz.me.datageneration.content.loot_tables;

import net.jukoz.me.block.ModBlocks;
import net.jukoz.me.block.ModDecorativeBlocks;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockDrops {
    public static List<Block> blocks = new ArrayList<>() {
        {
            add(ModBlocks.CALCITE_PILLAR);
            add(ModBlocks.LIMESTONE_PILLAR);
            add(ModBlocks.CHISELED_BLUE_ROCK_BRICKS);
            add(ModBlocks.CHISELED_CALCITE);
            add(ModBlocks.CHISELED_LIMESTONE_BRICKS);
            add(ModBlocks.DRY_DIRT);
            add(ModBlocks.MORDOR_DIRT);
            add(ModBlocks.REED_BLOCK);
            add(ModBlocks.REED_STAIRS);
            add(ModBlocks.REED_SLAB);
            add(ModBlocks.REED_WALL);
            add(ModBlocks.STRAW_BLOCK);
            add(ModBlocks.STRAW_STAIRS);
            add(ModBlocks.STRAW_SLAB);
            add(ModBlocks.STRAW_WALL);
            add(ModBlocks.STONE_TRAPDOOR);
            add(ModBlocks.BLACKSTONE_TRAPDOOR);
            add(ModDecorativeBlocks.SILVER_LANTERN);
        }
    };
}
