package net.jukoz.me.entity.human.gondor;

import java.util.Arrays;
import java.util.Comparator;

public enum GondorianVariant {
    MAN(0),
    WOMAN(1);


    private static final GondorianVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(GondorianVariant::getId)).toArray(GondorianVariant[]::new);

    private final int id;

    GondorianVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static GondorianVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
