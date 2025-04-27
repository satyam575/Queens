package org.example.mapper;

import java.util.HashMap;
import java.util.Map;

public class ColorRegionMapper {
    private final Map<String, Integer> colorToRegion = new HashMap<>();
    private int nextRegion = 1;

    public int getRegionForColor(double[] pixel) {
        String key = colorToKey(pixel);
        return colorToRegion.computeIfAbsent(key, k -> nextRegion++);
    }

    private String colorToKey(double[] pixel) {
        int b = (int) pixel[0] / 10;
        int g = (int) pixel[1] / 10;
        int r = (int) pixel[2] / 10;
        return r + "-" + g + "-" + b;
    }
}
