package ru.catleta;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(
    modid = Catleta.MODID,
    name = Catleta.NAME,
    version = Catleta.VERSION,
    acceptedMinecraftVersions = "[1.12.2]"
)
public class Catleta {
    public static final String MODID = "catleta";
    public static final String NAME = "Catleta";
    public static final String VERSION = "1.0.0";

    private static KeyBinding openMenu;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        openMenu = new KeyBinding(
            "key.catleta.open_menu",
            Keyboard.KEY_RSHIFT,
            "key.categories.catleta"
        );

        ClientRegistry.registerKeyBinding(openMenu);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (openMenu.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiCatleta());
        }
    }
  }
