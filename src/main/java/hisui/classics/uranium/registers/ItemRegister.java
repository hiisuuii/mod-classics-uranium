package hisui.classics.uranium.registers;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.item.GrenadeItem;
import hisui.classics.uranium.misc.ToolMaterialUranium;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ItemRegister {

    public static List<Item> creativeTabItems = new ArrayList<>();
    public static List<Item> creativeTabBlockItems = new ArrayList<>();
    public static List<Item> excludeTabItems = new ArrayList<>();



    public static final Item URANIUM_DUST = register("uranium_dust", new Item(new FabricItemSettings()));
    public static final Item URANIUM_COAL = register("uranium_coal", new Item(new FabricItemSettings()));
    public static final Item URANIUM_INGOT = register("uranium_ingot", new Item(new FabricItemSettings()));

    public static final Item URANIUM_HELMET = register("uranium_helmet", new ArmorItem(MiscRegister.MATERIAL_URANIUM, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Item URANIUM_CHESTPLATE = register("uranium_chestplate",new ArmorItem(MiscRegister.MATERIAL_URANIUM, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item URANIUM_LEGGINGS = register("uranium_leggings",new ArmorItem(MiscRegister.MATERIAL_URANIUM, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item URANIUM_BOOTS = register("uranium_boots",new ArmorItem(MiscRegister.MATERIAL_URANIUM, ArmorItem.Type.BOOTS, new Item.Settings()));

    public static final Item URANIUM_SWORD = register("uranium_sword", new SwordItem(ToolMaterialUranium.INSTANCE, 3, -2.4f, new FabricItemSettings()));
    public static final Item URANIUM_SHOVEL = register("uranium_shovel", new ShovelItem(ToolMaterialUranium.INSTANCE, 1.5f, -3f, new FabricItemSettings()));
    public static final Item URANIUM_PICKAXE = register("uranium_pickaxe", new PickaxeItem(ToolMaterialUranium.INSTANCE, 1, -2.8f, new FabricItemSettings()));
    public static final Item URANIUM_AXE = register("uranium_axe", new AxeItem(ToolMaterialUranium.INSTANCE, 5.5f, -3f, new FabricItemSettings()));
    public static final Item URANIUM_HOE = register("uranium_hoe", new HoeItem(ToolMaterialUranium.INSTANCE, -2, 0.0f, new FabricItemSettings()));
    public static final Item URANIUM_PAXEL = register("uranium_paxel", new MiningToolItem(4, -2.6f, ToolMaterialUranium.INSTANCE, MiscRegister.PAXEL_MINEABLE, new FabricItemSettings()));

    public static final Item MUTAGENIC_FLESH = register("mutagenic_flesh", new Item(new FabricItemSettings()));
    public static final Item GRENADE = register("grenade", new GrenadeItem(new FabricItemSettings().maxCount(8)));

    public static final Item SPAWN_EGG_MUTANT = register("mutant_spawn_egg", new SpawnEggItem(EntityRegister.MUTANT,
            0x7A6E2F, 0x964150, new FabricItemSettings()));
    public static final Item SPAWN_EGG_MUTANT_SKELETON = register("mutant_skeleton_spawn_egg", new SpawnEggItem(EntityRegister.MUTANT_SKELETON,
            0x81DF81, 0x494949, new FabricItemSettings()));



    private static Item register(String id, Item item) {
//        Main.LOGGER.info("Registering Item " + Main.MODID + ":" + id);

        if(!(item instanceof SpawnEggItem) && !(creativeTabItems.contains(item))) {
            creativeTabItems.add(item);
        }
        return register(new Identifier(Main.MODID, id), item);
    }

    private static Item register(Identifier id, Item item) {
        return Registry.register(Registries.ITEM, id, item);
    }

    public static void init(){
        excludeTabItems.add(BlockRegister.NUCLEAR_WASTE.asItem());
        Main.LOGGER.info("Initializing Items for mod: \"" + Main.MODID + "\"");
    }
}
