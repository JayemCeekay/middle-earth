package net.jukoz.me.world.chunkgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jukoz.me.world.biomes.MEBiome;
import net.jukoz.me.world.biomes.MEBiomeKeys;
import net.jukoz.me.world.biomes.MEBiomesData;
import net.jukoz.me.world.biomes.ModBiomeSource;
import net.jukoz.me.world.chunkgen.map.MapImageLoader;
import net.jukoz.me.world.chunkgen.map.MiddleEarthHeightMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.util.math.random.RandomSeed;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.*;
import net.minecraft.world.gen.noise.NoiseConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class MiddleEarthChunkGenerator extends ChunkGenerator {
    public static final int STONE_HEIGHT = 32;
    public static final int WATER_HEIGHT = 64;
    public static final int HEIGHT = 24 + STONE_HEIGHT;
    public static final int DIRT_HEIGHT = 3 + HEIGHT;

    RegistryEntryLookup<Biome> biomeRegistry;
    public static final Codec<MiddleEarthChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(RegistryOps.getEntryLookupCodec(RegistryKeys.BIOME))
                    .apply(instance, instance.stable(MiddleEarthChunkGenerator::new)));

    public MiddleEarthChunkGenerator(RegistryEntryLookup<Biome> biomeRegistry) {
        super(new ModBiomeSource(
                new ArrayList<>(Arrays.asList(
                    biomeRegistry.getOrThrow(MEBiomeKeys.OCEAN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ANDUIN_VALES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BARROW_DOWNS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BLUE_MOUNTAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BLUE_MOUNTAINS_FOOTHILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.CORSAIR_COASTS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DALE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DARK_MIRKWOOD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DARK_MIRKWOOD_EDGE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DARK_ANDUIN_VALES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DOL_GULDUR ),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DORWINION_HILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DUNLAND_FOOTHILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EASTERN_RHOVANION),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ENEDWAITH),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EREGION),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ERIADOR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.FANGORN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.FORODWAITH),
                    biomeRegistry.getOrThrow(MEBiomeKeys.FROZEN_OCEAN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GONDOR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GREY_PLAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.HARAD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.HARAD_DESERT),
                    biomeRegistry.getOrThrow(MEBiomeKeys.HARONDOR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.IRON_HILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.IRON_HILLS_FRONTIER),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LINDON),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LONG_LAKE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LORIEN_EDGE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LOTHLORIEN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MILLPOND),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD_EDGE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD_FOOTHILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD_MOUNTAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD_SWAMP),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MISTY_MOUNTAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MORDOR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MORDOR_MOUNTAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MORDOR_WASTES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NORTHERN_DUNLAND),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NORTHERN_WASTELANDS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NURN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NURN_RIVER),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NURN_SEA),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OCEAN_COAST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_ANGMAR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_ARTHEDAIN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_RHUDAUR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.RHUN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.RIVENDELL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.RIVENDELL_FOOTHILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.RIVER),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ROHAN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SEA_OF_RHUN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SHIRE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SOUTHEAST_RHOVANION),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SOUTHERN_DUNLAND),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SOUTHERN_FOROCHEL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.THE_ANGLE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.THE_OLD_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.THE_WOLD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.TOLFALAS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.TROLLSHAWS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.UMBAR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WASTE_POND),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WHITE_MOUNTAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WOODLAND_REALM)
                ))
            )
        );
        this.biomeRegistry = biomeRegistry;
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig,
                      BiomeAccess biomeAccess, StructureAccessor structureAccessor,
                      Chunk chunk2, GenerationStep.Carver carverStep) {

    }

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk) {
        int bottomY = chunk.getBottomY();
        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
                int posX = (chunk.getPos().x * 16) + x;
                int posZ = (chunk.getPos().z * 16) + z;
                MEBiome meBiome;

                if(MiddleEarthHeightMap.isCoordinateInBounds(posX, posZ)) {
                    meBiome = MEBiomesData.biomeMap.get(MapImageLoader.getBiomeColor(posX, posZ));
                } else {
                    meBiome = MEBiomesData.defaultBiome;
                }

                if(meBiome == null) {
                    int c = MapImageLoader.getBiomeColor(posX, posZ);
                    meBiome = MEBiomesData.defaultBiome;
                }

                float height = MiddleEarthHeightMap.getHeight(posX, posZ);

                for(int y = bottomY + 1; y <= WATER_HEIGHT; y++) {
                    chunk.setBlockState(chunk.getPos().getBlockPos(x, y, z), Blocks.WATER.getDefaultState(), false);
                }
                chunk.setBlockState(chunk.getPos().getBlockPos(x, bottomY, z), Blocks.BEDROCK.getDefaultState(), false);
                for(int y = bottomY + 1; y < STONE_HEIGHT + height; y++) {
                    chunk.setBlockState(chunk.getPos().getBlockPos(x, y, z), meBiome.deepStoneBlock.getDefaultState(), false);
                }
                if(Math.random() < 0.5f) chunk.setBlockState(chunk.getPos().getBlockPos(x, chunk.getBottomY() + 1, z), Blocks.BEDROCK.getDefaultState(), false);
                for(int y = (int) (STONE_HEIGHT + height); y < HEIGHT + height; y++) {
                    chunk.setBlockState(chunk.getPos().getBlockPos(x, y, z), meBiome.stoneBlock.getDefaultState(), false);
                }
                for(int y = (int) (HEIGHT + height); y < DIRT_HEIGHT + height; y++) {
                    chunk.setBlockState(chunk.getPos().getBlockPos(x, y, z), meBiome.underSurfaceBlock.getDefaultState(), false);
                }

                BlockState surfaceBlock = meBiome.surfaceBlock.getDefaultState();
                if(DIRT_HEIGHT + height < WATER_HEIGHT && meBiome.surfaceBlock == Blocks.GRASS_BLOCK) {
                    surfaceBlock = Blocks.DIRT.getDefaultState();
                }
                chunk.setBlockState(chunk.getPos().getBlockPos(x, (int) (DIRT_HEIGHT + height), z), surfaceBlock, false);
            }
        }

    }


    @Override
    public void populateEntities(ChunkRegion region) {
        ChunkPos chunkPos = region.getCenterPos();
        RegistryEntry<Biome> registryEntry = region.getBiome(chunkPos.getStartPos().withY(region.getTopY() - 1));
        ChunkRandom chunkRandom = new ChunkRandom(new CheckedRandom(RandomSeed.getSeed()));
        chunkRandom.setPopulationSeed(region.getSeed(), chunkPos.getStartX(), chunkPos.getStartZ());
        SpawnHelper.populateEntities(region, registryEntry, chunkPos, chunkRandom);
    }

    @Override
    public int getWorldHeight() {
        return 256;
    }


    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender, NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {

        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public int getSeaLevel() {
        return WATER_HEIGHT;
    }

    @Override
    public int getMinimumY() {
        return 0;
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world, NoiseConfig noiseConfig) {
        float worldHeight = 1 + DIRT_HEIGHT + MiddleEarthHeightMap.getHeight(x, z);
        return (int)worldHeight;
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world, NoiseConfig noiseConfig) {
        return new VerticalBlockSample(0, new BlockState[0]);
    }

    @Override
    public void getDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {

    }
}
