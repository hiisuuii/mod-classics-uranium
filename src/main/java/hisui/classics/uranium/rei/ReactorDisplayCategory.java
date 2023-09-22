package hisui.classics.uranium.rei;

import com.google.common.collect.ImmutableList;
import hisui.classics.uranium.misc.ReactorRecipeType;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

public class ReactorDisplayCategory implements Display {

    private final ReactorRecipeType recipe;
    private final List<EntryIngredient> inputs;
    private final List<EntryIngredient> outputs;

    public ReactorDisplayCategory(ReactorRecipeType recipe){
        this.recipe = recipe;
        this.inputs = ImmutableList.of(
                EntryIngredients.ofIngredient(recipe.getIngredients().get(0)),
                EntryIngredients.ofIngredient(recipe.getIngredients().get(1)));
        this.outputs = ImmutableList.of(
                EntryIngredients.of(recipe.getOutput(MinecraftClient.getInstance().player.getWorld().getRegistryManager())));
    }
    @Override
    public List<EntryIngredient> getInputEntries() {
        return inputs;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return outputs;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ReactorRecipeCategory.ID;
    }

    @Override
    public Optional<Identifier> getDisplayLocation() {
        return Optional.of(recipe.getId());
    }
}
