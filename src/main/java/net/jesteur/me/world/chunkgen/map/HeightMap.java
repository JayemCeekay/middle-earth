package net.jesteur.me.world.chunkgen.map;

import org.joml.Math;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Map responsible for base height map.
 */
public class HeightMap {

    public final float scale;

    public final byte[][] heightMap;

    public HeightMap(BiomeMap biomes, Random rand, BufferedImage heightMapImage) {
        scale = biomes.size;
        heightMap = new byte[heightMapImage.getWidth()][heightMapImage.getHeight()];
        BufferedImage debug = new BufferedImage(heightMapImage.getWidth(), heightMapImage.getHeight(), Image.SCALE_DEFAULT);
        for (int x = 0 ; x < heightMapImage.getWidth() ; x++) {
            for (int z = 0 ; z < heightMapImage.getHeight() ; z++) {
                // let's just use blue
                heightMap[x][z] = (byte) ((new Color(heightMapImage.getRGB(x, z)).getBlue() / 1.5) - 128);
                debug.setRGB(x, z, new Color(0, 0, getSafe(x, z)).getRGB());
            }
        }
        try {
            ImageIO.write(debug, "png", new File("dbg.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int getSafe(int x, int y) {
        if (x >= heightMap.length || x < 0 || y < 0 || y >= heightMap[0].length) {
            return 5;
        }
        return heightMap[x][y] + 128;
    }

    public float get(int posX, int posZ) {
        float x = posX / scale;
        float z = posZ / scale;
        int fX = (int) Math.floor(x);
        int fZ = (int) Math.floor(z);
        float dX = (x - fX);
        float dZ = (z - fZ);

        int h1 = getSafe(fX, fZ);
        int h2 = getSafe(fX + 1, fZ);
        int h3 = getSafe(fX, fZ + 1);
        int h4 = getSafe(fX + 1, fZ + 1);

        return Math.biLerp(
                h1, h2, h3, h4,
                dX, dZ
        );
    }
}
