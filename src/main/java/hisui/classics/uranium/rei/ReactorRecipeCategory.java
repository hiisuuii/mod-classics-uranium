package hisui.classics.uranium.rei;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.registers.ItemRegister;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ReactorRecipeCategory implements DisplayCategory<ReactorDisplayCategory> {

    private static final int PADDING = 5;
    private static final int SLOT_INPUT_TOP = 0;
    private static final int SLOT_INPUT_BOTTOM = 1;

    static final CategoryIdentifier<ReactorDisplayCategory> ID = CategoryIdentifier
            .of(new Identifier(Main.MODID, "reactor"));

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ItemRegister.URANIUM_DUST);
    }

    @Override
    public Text getTitle() {
        return Text.translatable("container.classic_uranium.reactor");
    }

    @Override
    public CategoryIdentifier<? extends ReactorDisplayCategory> getCategoryIdentifier() {
        return ID;
    }

    @Override
    public List<Widget> setupDisplay(ReactorDisplayCategory display, Rectangle bounds) {
//        return DisplayCategory.super.setupDisplay(display, bounds);
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));


        var innerX = bounds.x + PADDING;
        var innerY = bounds.y + PADDING;


        List<EntryIngredient> ingredients = display.getInputEntries();
        EntryIngredient output = display.getOutputEntries().get(0);

        widgets.add(Widgets.createSlot(new Point(innerX + 1, innerY + 1)).disableBackground().markInput()
                .entries(ingredients.get(SLOT_INPUT_TOP)));
        widgets.add(Widgets.createSlot(new Point(innerX + 1, innerY + 47)).disableBackground().markInput()
                .entries(ingredients.get(SLOT_INPUT_BOTTOM)));
        widgets.add(Widgets.createSlot(new Point(innerX + 69, innerY + 25)).disableBackground().markOutput()
                .entries(output));


        return widgets;
    }



    @Override
    public int getDisplayHeight() {
        return 64 + 2 * PADDING;
    }

    @Override
    public int getDisplayWidth(ReactorDisplayCategory display) {
        return 97 + 2 * PADDING;
    }
}
