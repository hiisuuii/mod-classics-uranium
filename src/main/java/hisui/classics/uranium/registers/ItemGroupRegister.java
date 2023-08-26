package hisui.classics.uranium.registers;

import hisui.classics.uranium.Main;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemGroupRegister {

    public static final ItemGroup ITEMGROUP_URANIUM = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ItemRegister.URANIUM_DUST))
            .displayName(Text.of("Classics: Uranium"))
            .entries((displayContext, entries) ->
            {
                for(Item item : ItemRegister.creativeTabBlockItems) {
                    entries.add(item);
                }
                for(Item item : ItemRegister.creativeTabItems) {
                    entries.add(item);
                }
            })
            .build();


    public static void init(){
        Registry.register(Registries.ITEM_GROUP, new Identifier(Main.MODID, "itemgroup_uranium"), ITEMGROUP_URANIUM);
        Main.LOGGER.info("Initializing ItemGroup for mod: \"" + Main.MODID + "\"");
    }
}
