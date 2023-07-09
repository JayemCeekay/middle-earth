package net.jesteur.me.world.chunkgen.map;

import net.jesteur.me.utils.noises.PerlinNoise;
import net.jesteur.me.world.biomes.MEBiome;
import net.jesteur.me.world.biomes.MEBiomesData;

import java.awt.image.BufferedImage;
import java.util.Random;

public class BiomeMap {

    private MEBiome[][] biomes;
    public final float size;

    public final int imageXSize;
    public final int imageYSize;

    private final float perlinHeightX;
    private final float perlinHeightY;

    public static final float PERLIN_SCALER = 0.2f;

    public BiomeMap(BufferedImage image, float sizeMultiplier, Random rand) {
        // load image into an array of MEBiomes
        biomes = new MEBiome[image.getWidth()][image.getHeight()];
        for (int x = 0 ; x < image.getWidth() ; x++) {
            for (int y = 0 ; y < image.getHeight() ; y++) {
                biomes[x][y] = MEBiomesData.biomeMap.get(image.getRGB(x, y));
                image.getRGB(x, y);
            }
        }

        imageXSize = image.getWidth();
        imageYSize = image.getHeight();
        size = sizeMultiplier;
        perlinHeightX = rand.nextFloat() * 100;
        perlinHeightY = rand.nextFloat() * 100;
    }

    public MEBiome getFromImageCoords(int x, int y) {
        if (x >= imageXSize || x < 0 || y < 0 || y >= imageYSize) {
            return MEBiomesData.defaultBiome;
        }
        return biomes[x][y];
    }

    public MEBiome get(int x, int y) {
        // use perlin noise to offset biome coords to not have squared edges
        // possibly could be changed in future to allow for diagonal edges or use different coord determined random
        int imgX = Math.round(x / size +
                (float)PerlinNoise.noise(x * PERLIN_SCALER, y * PERLIN_SCALER, perlinHeightX));
        int imgY = Math.round(y / size +
                (float)PerlinNoise.noise(x * PERLIN_SCALER, y * PERLIN_SCALER, perlinHeightY));

        if (imgX >= imageXSize || imgX < 0 || imgY < 0 || imgY >= imageYSize) {
            return MEBiomesData.defaultBiome;
        }

        return biomes[imgX]
                     [imgY];
    }



}
