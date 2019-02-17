package com.mattsmeets.macrokey.config;

import com.mattsmeets.macrokey.ModReference;
import com.mattsmeets.macrokey.service.OSService;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ModReference.MOD_ID)
@Config.LangKey("macrokey.config.title")
public class ModConfig {

    static {
        if(OSService.isWindows()) {
            editor = "notepad.exe";
        }
        if(OSService.isMac()) {
            editor = "open -a TextEdit";
        }
        if(OSService.isUnix()) {
            editor = "vim";
        }
    }

    @Config.Comment("What editor do you want to use while editing advanced macro's (If the executable is not in your path, then add full directory path (i.e. C:\\Program Files (x86)\\Notepad++\\notepad++.exe)")
    public static String editor;

    @Config.Comment("How many ticks need to pass for the repeatable command cooldown timer to expire (default: 20 ticks -> 1 second)")
    public static int repeatDelay = 20;

    @Config.Comment("What file should be used for saving the bindings and various other dynamic information (default: bindings.json)")
    public static String bindingFile = "bindings.json";

    @Config.Comment("Customize the ID that is used when calling the macro management GUI (default: 423458971)")
    public static int guiMacroManagementId = 423458971;

    @Config.Comment("Customize the ID that is used when creating the button for switching layers (default: 823358142), (-1 = disabled)")
    public static int buttonLayerSwitcherId = 823358142;

    @Config.Comment("Customize the position that the button for switching layer should use, expected is {x, y, width, height} (default: -100, 128, 200, 20)")
    public static int[] buttonLayerSwitchSettings = {-100, 128, 200, 20};

    @Config.Comment("Customize the entrypoint that will be called when running custom macro's")
    public static String javascriptMain = "main";

    @Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(ModReference.MOD_ID)) {
                ConfigManager.sync(ModReference.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }

}
