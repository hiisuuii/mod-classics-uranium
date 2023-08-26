package hisui.classics.uranium.gui;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.block.entity.ReactorBlockEntity;
import hisui.classics.uranium.registers.ItemRegister;
import hisui.classics.uranium.registers.MiscRegister;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.VerticalAlignment;
import net.fabricmc.api.EnvType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.command.argument.ItemStringReader;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Predicate;

public class ReactorGuiDescription extends SyncedGuiDescription {
    private static final int INVENTORY_SIZE = 3;

    private static final Identifier fuel_bg = new Identifier("classic_uranium", "textures/gui/container/reactor_fuel_empty.png");
    private static final Identifier fuel_bar = new Identifier("classic_uranium", "textures/gui/container/reactor_fuel_filled.png");
    private static final Identifier progress_bg = new Identifier("classic_uranium", "textures/gui/container/reactor_progress_empty.png");
    private static final Identifier progress_bar = new Identifier("classic_uranium", "textures/gui/container/reactor_progress_filled.png");
    private static final Identifier fuelSlotTexture = new Identifier(Main.MODID, "textures/gui/container/reactor_fuel_slot.png");

    public ReactorGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context){
        super(Main.REACTOR_SCREEN_HANDLER, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context, 4));

        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(9*18, 6*18);
        root.setInsets(Insets.ROOT_PANEL);

        int width = root.getWidth();
        int anchor = (width/2)-8-18;

        root.add(new WLabel(Text.translatable("container.classic_uranium.reactor")).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.TOP), root.getWidth()/2, 0, 4, 1);


        WItemSlot fuelSlot = WItemSlot.of(blockInventory, 1);
        fuelSlot.setFilter(new Predicate<ItemStack>() {
            @Override
            public boolean test(ItemStack itemStack) {
                return context.get((world, pos) -> {
                    BlockEntity entity = world.getBlockEntity(pos);

                    if (entity instanceof ReactorBlockEntity) {
                        return ((ReactorBlockEntity) entity).isValid(1, itemStack);
                    }
                    return false;
                }).orElse(false);
            }
        });
        root.add(fuelSlot, anchor, 16);
        WSprite sprite = new WSprite(fuelSlotTexture);
        root.add(sprite, anchor, 16);

        WItemSlot inputSlot = WItemSlot.of(blockInventory, 0);
        inputSlot.setFilter(new Predicate<ItemStack>() {
            @Override
            public boolean test(ItemStack itemStack) {
                return context.get((world, pos) -> {
                    BlockEntity entity = world.getBlockEntity(pos);

                    if (entity instanceof ReactorBlockEntity) {
                        return ((ReactorBlockEntity) entity).isValid(0, itemStack);
                    }
                    return false;
                }).orElse(false);
            }
        });
        root.add(inputSlot, anchor+21, 16);

        WItemSlot outputSlot = WItemSlot.of(blockInventory, 2);
        outputSlot.setInsertingAllowed(false);
        root.add(outputSlot, anchor+42, 16);


        WBar fbar = new WBar(fuel_bg, fuel_bar, 0, 1, WBar.Direction.UP);
        WBar pbar = new WBar(progress_bg, progress_bar, 2, 3, WBar.Direction.DOWN);


        root.add(fbar, anchor-4, 16, 22, 18);
        root.add(pbar, anchor, 16, 22, 18);

        root.add(this.createPlayerInventoryPanel(), 0, 44);

        root.validate(this);
    }
}
