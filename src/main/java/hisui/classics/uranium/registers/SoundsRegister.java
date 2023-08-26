package hisui.classics.uranium.registers;

import hisui.classics.uranium.Main;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundsRegister {

    public static final SoundEvent ENTITY_GRENADE_THROW = register("entity.grenade.throw");
    public static final SoundEvent ITEM_ARMOR_EQUIP_URANIUM = register("item.armor.equip_uranium");
    public static final SoundEvent BLOCK_REACTOR_WORKING = register("block.reactor.working");

    public static final SoundEvent ENTITY_MUTANT_AMBIENT = register("entity.mutant.ambient");
    public static final SoundEvent ENTITY_MUTANT_HURT = register("entity.mutant.hurt");
    public static final SoundEvent ENTITY_MUTANT_DEATH = register("entity.mutant.death");
    public static final SoundEvent ENTITY_MUTANT_STEP = register("entity.mutant.step");

    public static final SoundEvent ENTITY_MUTANT_SKELETON_AMBIENT = register("entity.mutant_skeleton.ambient");
    public static final SoundEvent ENTITY_MUTANT_SKELETON_HURT = register("entity.mutant_skeleton.hurt");
    public static final SoundEvent ENTITY_MUTANT_SKELETON_DEATH = register("entity.mutant_skeleton.death");
    public static final SoundEvent ENTITY_MUTANT_SKELETON_STEP = register("entity.mutant_skeleton.step");
    public static final SoundEvent ENTITY_NUCLEAR_BOMB_PRIMED = register("entity.nuclear_bomb.primed");

    private static SoundEvent register(String id) {
        return register(new Identifier(Main.MODID, id));
    }

    private static SoundEvent register(Identifier id) {
        return register(id, id);
    }

    private static SoundEvent register(Identifier id, Identifier soundId) {
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }

    public static void init(){
        Main.LOGGER.info("Initializing Sounds for mod: \"" + Main.MODID + "\"");
    }
}
