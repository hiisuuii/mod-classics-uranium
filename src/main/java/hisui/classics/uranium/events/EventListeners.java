package hisui.classics.uranium.events;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.registers.ItemRegister;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

public class EventListeners {

    private static void modifyItemGroups(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content ->
        {
            content.addAfter(Items.MULE_SPAWN_EGG, ItemRegister.SPAWN_EGG_MUTANT);
            content.addAfter(ItemRegister.SPAWN_EGG_MUTANT, ItemRegister.SPAWN_EGG_MUTANT_SKELETON);
        });
    }

    public static void init(){
        modifyItemGroups();

        Main.LOGGER.info("Running event calls for mod: " + Main.MODID);
    }
}
