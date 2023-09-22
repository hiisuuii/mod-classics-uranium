package hisui.classics.uranium.rei;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.misc.ReactorRecipeType;
import hisui.classics.uranium.registers.BlockRegister;
import hisui.classics.uranium.registers.MiscRegister;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReiPlugin implements REIClientPlugin {

    @Override
    public String getPluginProviderName() {
        return Main.MODID;
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new ReactorRecipeCategory());

        registry.addWorkstations(ReactorRecipeCategory.ID, EntryStacks.of(BlockRegister.NUCLEAR_REACTOR.asItem()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(ReactorRecipeType.class, MiscRegister.REACTOR_RECIPE_TYPE, ReactorDisplayCategory::new);
    }


}
