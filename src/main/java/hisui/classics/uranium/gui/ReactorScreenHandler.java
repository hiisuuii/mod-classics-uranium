package hisui.classics.uranium.gui;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.registers.ItemRegister;
import hisui.classics.uranium.registers.MiscRegister;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;

public class ReactorScreenHandler extends AbstractFurnaceScreenHandler {
    public ReactorScreenHandler(int i, PlayerInventory playerInventory) {
        super(Main.REACTOR_SCREEN_HANDLER, MiscRegister.REACTOR_RECIPE_TYPE, RecipeBookCategory.FURNACE, i, playerInventory);
    }

    public ReactorScreenHandler(int i, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Main.REACTOR_SCREEN_HANDLER, MiscRegister.REACTOR_RECIPE_TYPE, RecipeBookCategory.FURNACE, i, playerInventory, inventory, propertyDelegate);
    }

    @Override
    protected boolean isFuel(ItemStack itemStack){
        return itemStack.isOf(ItemRegister.URANIUM_DUST) || itemStack.isOf(ItemRegister.URANIUM_COAL);
    }

}
