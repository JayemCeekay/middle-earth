package net.jesteur.me.world.chunkgen.map;

import net.jesteur.me.MiddleEarth;
import net.jesteur.me.world.biomes.MEBiomesData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

public class MapManager {
    /**
     * Size Multiplier used to multiply the image's size
     */
    private static final float size = 4;
    private static Random random = new Random(1379);

    public static BiomeMap biomeMap;
    public static HeightMap heightMap;

    public static void loadImage(ClassLoader classLoader) throws IOException, URISyntaxException {
        URL resource = classLoader.getResource("assets/" + MiddleEarth.MOD_ID + "/textures/map.png");

        BufferedImage img;
        try {
            img = ImageIO.read(resource);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

        biomeMap = new BiomeMap(img, size, random);
        heightMap = new HeightMap(biomeMap, random);
    }

}
