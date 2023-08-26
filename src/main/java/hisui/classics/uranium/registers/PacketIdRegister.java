package hisui.classics.uranium.registers;

import hisui.classics.uranium.Main;
import net.minecraft.util.Identifier;

public class PacketIdRegister {

    public static final Identifier EXPLODE_PACKET_ID = new Identifier(Main.MODID, "reactor_explode");

     public static void init(){
         Main.LOGGER.info("Initializing packet identifiers for mod: " + Main.MODID);
     }
}
