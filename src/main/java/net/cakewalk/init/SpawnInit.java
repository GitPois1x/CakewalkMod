package net.cakewalk.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.ImmutableMap;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.SpawnSettings;

public class SpawnInit {

  public static void init() {
    addSpawnEntries();
    SpawnRestriction();
  }

  public static void addSpawnEntries() {
    for (Biome biome : BuiltinRegistries.BIOME) {
      if (biome.getCategory().equals(Biome.Category.ICY) || biome.getCategory().equals(Biome.Category.TAIGA)) {
        addMobSpawnToBiome(biome, SpawnGroup.MONSTER,
            new SpawnSettings.SpawnEntry(EntityInit.DIRE_WOLF_ENTITY_TYPE, 5, 1, 3));
      }
    }

  }

  public static void addMobSpawnToBiome(Biome biome, SpawnGroup classification, SpawnSettings.SpawnEntry... spawners) {
    convertImmutableSpawners(biome);
    List<SpawnSettings.SpawnEntry> spawnersList = new ArrayList<>(
        biome.getSpawnSettings().spawners.get(classification));
    spawnersList.addAll(Arrays.asList(spawners));
    biome.getSpawnSettings().spawners.put(classification, spawnersList);
  }

  private static void convertImmutableSpawners(Biome biome) {
    if (biome.getSpawnSettings().spawners instanceof ImmutableMap) {
      biome.getSpawnSettings().spawners = new HashMap<>(biome.getSpawnSettings().spawners);
    }
  }

  public static void SpawnRestriction() {
    SpawnRestriction.register(EntityInit.DIRE_WOLF_ENTITY_TYPE, SpawnRestriction.Location.ON_GROUND,
        Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
  }

}
