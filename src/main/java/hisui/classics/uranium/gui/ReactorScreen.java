package hisui.classics.uranium.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.recipebook.FurnaceRecipeBookScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ReactorScreen extends CottonInventoryScreen<ReactorGuiDescription> {

    public ReactorScreen(ReactorGuiDescription gui, PlayerEntity player, Text title) {
        super(gui, player, title);
    }
}
