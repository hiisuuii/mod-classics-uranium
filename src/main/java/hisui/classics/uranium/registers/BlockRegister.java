package hisui.classics.uranium.registers;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.block.BlockNuclearBomb;
import hisui.classics.uranium.block.BlockNuclearReactor;
import hisui.classics.uranium.block.BlockNuclearWaste;
import hisui.classics.uranium.block.OreUranium;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.ToIntFunction;

public class BlockRegister {

    public static final Block URANIUM_ORE = register("uranium_ore", new OreUranium(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.BASEDRUM).sounds(BlockSoundGroup.STONE).strength(3.0f, 3.0f).requiresTool()));
    public static final Block DEEPSLATE_URANIUM_ORE = register("deepslate_uranium_ore", new OreUranium(FabricBlockSettings.copyOf(URANIUM_ORE).mapColor(MapColor.DEEPSLATE_GRAY).sounds(BlockSoundGroup.DEEPSLATE).strength(4.5f, 3.0f).requiresTool()));
    public static final Block NUCLEAR_REACTOR = register("nuclear_reactor", new BlockNuclearReactor(FabricBlockSettings.copyOf(Blocks.FURNACE).luminance(createLightLevelFromLitBlockState(7))));
    public static final Block NUCLEAR_BOMB = register("nuclear_bomb", new BlockNuclearBomb(FabricBlockSettings.create().mapColor(MapColor.PURPLE).breakInstantly().sounds(BlockSoundGroup.GRASS).burnable()));
    public static final Block NUCLEAR_WASTE = register("nuclear_waste", new BlockNuclearWaste(FabricBlockSettings.create().breakInstantly().sounds(BlockSoundGroup.SLIME).nonOpaque()));

    private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return state -> state.get(Properties.LIT) ? litLevel : 0;
    }

    private static Block register(String id, Block block) {
//        Main.LOGGER.info("Registering Block " + Main.MODID + ":" + id);
        return register(new Identifier(Main.MODID, id), block);
    }

    private static Block register(Identifier id, Block block) {
        Item blockItem = block instanceof BlockNuclearBomb ? Registry.register(Registries.ITEM, id, new BlockItem(block, new FabricItemSettings().rarity(Rarity.UNCOMMON))) : Registry.register(Registries.ITEM, id, new BlockItem(block, new FabricItemSettings()));
        ItemRegister.creativeTabBlockItems.add(blockItem);
        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void init(){
        Main.LOGGER.info("Initializing Blocks for mod: \"" + Main.MODID + "\"");
    }
}
