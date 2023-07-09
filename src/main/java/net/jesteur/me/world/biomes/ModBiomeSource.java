package net.jesteur.me.world.biomes;

import com.mojang.serialization.Codec;
import net.jesteur.me.world.chunkgen.map.MapManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeCoords;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ModBiomeSource extends BiomeSource {
    private final ArrayList<RegistryEntry<Biome>> biomes;

    public ModBiomeSource(ArrayList<RegistryEntry<Biome>> biomes) {
        this.biomes = biomes;
    }

    @Override
    protected Codec<? extends BiomeSource> getCodec() {
        return CODEC;
    }

    @Override
    protected Stream<RegistryEntry<Biome>> biomeStream() {
        return biomes.stream();
    }

    @Override
    public RegistryEntry<Biome> getBiome(int x, int y, int z, MultiNoiseUtil.MultiNoiseSampler noise) {
        int i = BiomeCoords.toBlock(x);
        int k = BiomeCoords.toBlock(z);

        MEBiome meBiome = MapManager.biomeMap.get(i, k);
        RegistryKey<Biome> biome = meBiome.biome;

        return biomes.stream().filter(
                b -> b.getKey().get().toString().equalsIgnoreCase(biome.toString()))
            .findFirst().get();

    }
}
