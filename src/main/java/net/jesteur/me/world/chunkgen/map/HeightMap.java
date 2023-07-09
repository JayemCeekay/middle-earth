package net.jesteur.me.world.chunkgen.map;

import net.jesteur.me.world.biomes.MEBiomesData;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import org.joml.Math;

import java.util.Random;

/**
 * Map responsible for base height map.
 */
public class HeightMap {

    public final float scale;

    public final byte[][] heightMap;

    public HeightMap(BiomeMap biomes, Random random) {
        scale = biomes.size;
        heightMap = new byte[biomes.imageXSize][biomes.imageYSize];
        for (int x = 0 ; x < heightMap.length ; x++) {
            for (int z = 0 ; z < heightMap[x].length ; z++) {
                int total = 0;
                float value = 0;
                for (int subX = -4 ; subX <= 4 ; subX++) {
                    for (int subZ = -4 ; subZ <= 4 ; subZ++) {
                        float height = biomes.getFromImageCoords(subX + x, subZ + z).height;
                        float own = (float) java.lang.Math.pow(0.95, height);
                        total += height * own;
                        value += own;
                    }
                }

                heightMap[x][z] = (byte) (total / value);
            }
        }
    }

    private byte getSafe(int x, int y) {
        if (x >= heightMap.length || x < 0 || y < 0 || y >= heightMap[0].length) {
            return 5;
        }
        return heightMap[x][y];
    }

    public float get(int posX, int posZ) {
        float x = posX / scale;
        float z = posZ / scale;
        int fX = (int) Math.floor(x);
        int fZ = (int) Math.floor(z);
        float dX = (x - fX);
        float dZ = (z - fZ);

        byte h1 = getSafe(fX, fZ);
        byte h2 = getSafe(fX + 1, fZ);
        byte h3 = getSafe(fX, fZ + 1);
        byte h4 = getSafe(fX + 1, fZ + 1);

        return Math.biLerp(
                h1, h2, h3, h4,
                dX, dZ
        )*2;
    }
}
