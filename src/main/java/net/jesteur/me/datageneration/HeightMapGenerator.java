package net.jesteur.me.datageneration;

import net.jesteur.me.world.chunkgen.map.BiomeMap;
import net.jesteur.me.world.chunkgen.map.MapManager;

public class HeightMapGenerator {

    public static void generate() {
        BiomeMap map = MapManager.biomeMap;
        byte[] bytes = new byte[map.imageXSize * map.imageYSize];

    }

}
