package hisui.classics.uranium.misc;

import hisui.classics.uranium.registers.BlockRegister;
import hisui.classics.uranium.registers.ItemRegister;
import hisui.classics.uranium.registers.MiscRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.util.Identifier;

public class ReactorRecipeType extends AbstractCookingRecipe {
    public ReactorRecipeType(Identifier id, String group, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime){
        super(MiscRegister.REACTOR_RECIPE_TYPE, id, group, category, input, output, experience, cookTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer(){
        return MiscRegister.REACTOR_RECIPE_SERIALIZER;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }
}
