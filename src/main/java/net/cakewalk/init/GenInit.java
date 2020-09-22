package net.cakewalk.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class GenInit {
  public static ConfiguredFeature<?, ?> ORE_LEAD = Feature.ORE
      .configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
          BlockInit.LEAD_ORE_BLOCK.getDefaultState(), 4)) // vein size
      .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, // bottom offset
          4, // min y level
          24))) // max y level
      .spreadHorizontally().repeat(7); // number of veins per chunk

  public static void init() {
    Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("cakewalk", "ore_lead_overworld"), ORE_LEAD);
  }
}
