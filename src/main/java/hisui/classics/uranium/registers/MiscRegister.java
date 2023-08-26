package hisui.classics.uranium.registers;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.misc.ArmorMaterialUranium;
import hisui.classics.uranium.misc.ReactorRecipeType;
import hisui.classics.uranium.misc.StatusEffectMutation;
import hisui.classics.uranium.mixin.BrewingRecipeRegistryMixin;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class MiscRegister {

    public static final ArmorMaterial MATERIAL_URANIUM = new ArmorMaterialUranium();
    public static final RecipeType<ReactorRecipeType> REACTOR_RECIPE_TYPE;
    public static final RecipeSerializer<ReactorRecipeType> REACTOR_RECIPE_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Main.MODID, "reactor"), new CookingRecipeSerializer<>(ReactorRecipeType::new, 200));

    public static final TagKey<Block> PAXEL_MINEABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier(Main.MODID, "mineable/paxel"));
    public static final StatusEffect MUTATION = new StatusEffectMutation();

    public static final Potion MUTATION_POTION = Registry.register(Registries.POTION, new Identifier(Main.MODID, "mutation"),
            new Potion(new StatusEffectInstance(MiscRegister.MUTATION, 200, 0)));

    static {
        REACTOR_RECIPE_TYPE = Registry.register(Registries.RECIPE_TYPE, new Identifier(Main.MODID, "reactor"), new RecipeType<ReactorRecipeType>() {
            @Override
            public String toString() { return "reactor";}
        });
    }

    private static void registerPotionsRecipes(){
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, ItemRegister.MUTAGENIC_FLESH, MiscRegister.MUTATION_POTION);
    }
    private static void registerFuels() {
        FuelRegistry.INSTANCE.add(ItemRegister.URANIUM_COAL, 3200);
    }
    private static void registerStatusEffects() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(Main.MODID, "mutation"), MUTATION);
    }

    public static void init(){
        registerFuels();
        registerStatusEffects();
        registerPotionsRecipes();
        Main.LOGGER.info("Initializing miscellaneous registries for mod: \"" + Main.MODID + "\"");
    }
}
