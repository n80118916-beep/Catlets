package ru.catleta;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class GuiCatleta extends GuiScreen {
    private static final int PANEL_WIDTH = 240;
    private static final int PANEL_HEIGHT = 190;

    private int panelX;
    private int panelY;

    @Override
    public void initGui() {
        panelX = (width - PANEL_WIDTH) / 2;
        panelY = (height - PANEL_HEIGHT) / 2;
        buttonList.clear();

        int buttonX = panelX + 24;
        int buttonWidth = PANEL_WIDTH - 48;
        int buttonHeight = 20;

        buttonList.add(new GuiButton(
            1, buttonX, panelY + 55, buttonWidth, buttonHeight,
            toggleLabel("Watermark", CatletaConfig.showWatermark)
        ));

        buttonList.add(new GuiButton(
            2, buttonX, panelY + 82, buttonWidth, buttonHeight,
            toggleLabel("Coordinates", CatletaConfig.showCoordinates)
        ));

        buttonList.add(new GuiButton(
            3, buttonX, panelY + 109, buttonWidth, buttonHeight,
            toggleLabel("FPS", CatletaConfig.showFps)
        ));
    }

    private String toggleLabel(String name, boolean enabled) {
        return name + ": " + (enabled ? "ON" : "OFF");
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 1:
                CatletaConfig.showWatermark = !CatletaConfig.showWatermark;
                break;
            case 2:
                CatletaConfig.showCoordinates = !CatletaConfig.showCoordinates;
                break;
            case 3:
                CatletaConfig.showFps = !CatletaConfig.showFps;
                break;
            default:
                return;
        }

        initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        // Тёмная панель и яркая неоновая рамка.
        drawRect(
            panelX, panelY,
            panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT,
            0xEE10131D
        );

        drawRect(
            panelX, panelY,
            panelX + PANEL_WIDTH, panelY + 2,
            0xFF00E5FF
        );

        drawRect(
            panelX, panelY + PANEL_HEIGHT - 2,
            panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT,
            0xFFB000FF
        );

        drawCenteredString(
            fontRenderer,
            "CATLETA",
            width / 2,
            panelY + 16,
            0xFF00E5FF
        );

        drawCenteredString(
            fontRenderer,
            "VISUAL SETTINGS",
            width / 2,
            panelY + 34,
            0xFFB8C7D9
        );

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
            return;
        }

        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
          }
