package ru.catleta;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEvents {
    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Text event) {
        if (mc.player == null || mc.world == null) {
            return;
        }

        ScaledResolution resolution = event.getResolution();
        int x = 6;
        int y = 6;

        if (CatletaConfig.showWatermark) {
            mc.fontRenderer.drawStringWithShadow(
                "CATLETA",
                x, y,
                0x00E5FF
            );
            y += 11;
        }

        if (CatletaConfig.showCoordinates) {
            String coords = String.format(
                "XYZ: %.1f / %.1f / %.1f",
                mc.player.posX,
                mc.player.posY,
                mc.player.posZ
            );

            mc.fontRenderer.drawStringWithShadow(
                coords,
                x, y,
                0xFFFFFF
            );
            y += 11;
        }

        if (CatletaConfig.showFps) {
            mc.fontRenderer.drawStringWithShadow(
                "FPS: " + Minecraft.getDebugFPS(),
                x, y,
                0xB000FF
            );
        }
    }
        }
