package hisui.classics.uranium.registers;

import hisui.classics.uranium.Main;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public class FeatureRegister {

    public static final RegistryKey<PlacedFeature> FEATURE_URANIUM_ORE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Main.MODID, "ore_uranium"));
    public static final RegistryKey<PlacedFeature> FEATURE_URANIUM_ORE_LOWER = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Main.MODID, "ore_uranium_lower"));
}
