package supercoder79.greggen.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

public final class BiomePicker {
    private final List<Entry> biomeEntries = Lists.newArrayList();
    private double totalWeight;

    public Integer choose(IntUnaryOperator rand) {
        if (this.biomeEntries.size() == 0) {
            throw new UnsupportedOperationException("No biomes registered!!! This is a problem!");
        }

        double randVal = target(rand);
        int i = -1;

        while (randVal >= 0) {
            ++i;
            randVal -= this.biomeEntries.get(i).weight;
        }

        return this.biomeEntries.get(i).biome;
    }

    public void add(int biome, double weight) {
        this.biomeEntries.add(new Entry(biome, weight));
        this.totalWeight += weight;
    }

    private double target(IntUnaryOperator random) {
        return (double) random.applyAsInt(Integer.MAX_VALUE) * this.totalWeight / Integer.MAX_VALUE;
    }

    public List<Entry> getBiomeEntries() {
        return biomeEntries;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public static class Entry {
        private final int biome;
        private final double weight;
        private Entry(int biome, double weight) {
            this.biome = biome;
            this.weight = weight;
        }

        public int getBiome() {
            return biome;
        }

        public double getWeight() {
            return weight;
        }
    }
}
