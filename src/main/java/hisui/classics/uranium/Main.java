package hisui.classics.uranium;

import hisui.classics.uranium.entity.MutantEntity;
import hisui.classics.uranium.events.EventListeners;
import hisui.classics.uranium.gui.ReactorGuiDescription;
import hisui.classics.uranium.gui.ReactorScreenHandler;
import hisui.classics.uranium.registers.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.world.gen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final String MODID = "classic_uranium";
    public static final Logger LOGGER = LoggerFactory.getLogger("classic_uranium");

	public static final ScreenHandlerType<ReactorGuiDescription> REACTOR_SCREEN_HANDLER;

	static {
		REACTOR_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
				BlockEntityRegister.REACTOR,
				new ScreenHandlerType<>((syncId, inventory) -> new ReactorGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY),
						FeatureFlags.VANILLA_FEATURES));
	}

	@Override
	public void onInitialize() {


		FabricDefaultAttributeRegistry.register(EntityRegister.MUTANT, MutantEntity.createZombieAttributes());
		FabricDefaultAttributeRegistry.register(EntityRegister.MUTANT_SKELETON, AbstractSkeletonEntity.createAbstractSkeletonAttributes());

		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, FeatureRegister.FEATURE_URANIUM_ORE);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, FeatureRegister.FEATURE_URANIUM_ORE_LOWER);

		ItemRegister.init();
		PacketIdRegister.init();
		BlockEntityRegister.init();
		BlockRegister.init();
		EntityRegister.init();
		ItemGroupRegister.init();
		SoundsRegister.init();
		EventListeners.init();
		MiscRegister.init();
	}
}