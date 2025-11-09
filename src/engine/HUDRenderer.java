package engine;

import entity.Ship;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import screen.Screen;

/**
 * Handles all on-screen HUD rendering such as scores, coins, and timers.
 * Acts as a sub-view in the MVC structure.
 */
public final class HUDRenderer {

    private final BackBuffer backBuffer;
    private final FontPack fontPack;
    private final EntityRenderer entityRenderer;

    public HUDRenderer(BackBuffer backBuffer, FontPack fontPack, EntityRenderer entityRenderer) {
        this.backBuffer = backBuffer;
        this.fontPack = fontPack;
        this.entityRenderer =  entityRenderer;
    }

    /** Draws Player 1 score. */
    public void drawScoreP1(final Screen screen, final int score) {
        Graphics g = backBuffer.getGraphics();
        Font font = fontPack.getRegular();
        g.setFont(font);
        g.setColor(Color.WHITE);
        String scoreString = String.format("P1:%04d", score);
        g.drawString(scoreString, screen.getWidth() - 120, 25);
    }

    /** Draws Player 2 score. */
    public void drawScoreP2(final Screen screen, final int scoreP2) {
        Graphics g = backBuffer.getGraphics();
        Font font = fontPack.getRegular();
        g.setFont(font);
        g.setColor(Color.WHITE);
        String text = String.format("P2:%04d", scoreP2);
        g.drawString(text, screen.getWidth() - 120, 40);
    }

    /** Draw elapsed time on screen. */
    public void drawTime(final Screen screen, final long milliseconds) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getRegular());
        g.setColor(Color.GRAY);

        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        seconds %= 60;

        String timeString = String.format("Time: %02d:%02d", minutes, seconds);
        g.drawString(timeString, 10, screen.getHeight() - 20);
    }

    /** Draw current coin count on screen. */
    public void drawCoin(final Screen screen, final int coin) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getRegular());
        g.setColor(Color.WHITE);

        String coinString = String.format("%03d$", coin);
        int textWidth = fontPack.getRegularMetrics().stringWidth(coinString);
        int x = screen.getWidth() / 2 - textWidth / 2;
        int y = screen.getHeight() - 50;

        g.drawString(coinString, x, y);
    }

    /** Draw number of remaining lives for Player 1. */
    public void drawLivesP1(final Screen screen, final int lives) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getRegular());
        g.setColor(Color.WHITE);
        g.drawString("P1:", 15, 25);

        Ship dummyShip = new Ship(0, 0, Color.GREEN);
        for (int i = 0; i < lives; i++) {
            entityRenderer.drawEntity(dummyShip, 40 + 35 * i, 10);
        }
    }

    /** Draw number of remaining lives for Player 2. */
    public void drawLivesP2(final Screen screen, final int lives) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getRegular());
        g.setColor(Color.WHITE);
        g.drawString("P2:", 15, 40);

        Ship dummyShip = new Ship(0, 0, Color.PINK);
        for (int i = 0; i < lives; i++) {
            entityRenderer.drawEntity(dummyShip, 40 + 35 * i, 30);
        }
    }
    public void drawItemsHUD(final Screen screen) {
        Graphics g = backBuffer.getGraphics();
        ItemHUDManager hud = ItemHUDManager.getInstance();
        hud.initialize(screen);
        hud.drawItems(screen, g);
    }

    public void drawLevel(final Screen screen, final String levelName) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getRegular());
        g.setColor(Color.WHITE);
        g.drawString(levelName, 20, screen.getHeight() - 50);
    }

    public void drawAchievementPopup(final Screen screen, final String text) {
        Graphics g = backBuffer.getGraphics();
        int popupWidth = 250, popupHeight = 50;
        int x = screen.getWidth() / 2 - popupWidth / 2;
        int y = 80;

        g.setColor(new Color(0, 0, 0, 200));
        g.fillRoundRect(x, y, popupWidth, popupHeight, 15, 15);
        g.setColor(Color.YELLOW);
        g.drawRoundRect(x, y, popupWidth, popupHeight, 15, 15);

        g.setFont(fontPack.getRegular());
        g.setColor(Color.WHITE);
        int textWidth = fontPack.getRegularMetrics().stringWidth(text);
        g.drawString(text, (screen.getWidth() - textWidth) / 2, y + popupHeight / 2 + 5);
    }

    public void drawHealthPopup(final Screen screen, final String text) {
        Graphics g = backBuffer.getGraphics();
        int popupWidth = 250, popupHeight = 40;
        int x = screen.getWidth() / 2 - popupWidth / 2;
        int y = 100;

        g.setColor(new Color(0, 0, 0, 200));
        g.fillRoundRect(x, y, popupWidth, popupHeight, 15, 15);

        g.setColor(text.startsWith("+") ? new Color(50, 255, 50) : new Color(255, 50, 50));
        g.setFont(fontPack.getFontBig());
        int textWidth = fontPack.getBigMetrics().stringWidth(text);
        g.drawString(text, (screen.getWidth() - textWidth) / 2, y + popupHeight / 2 + 5);
    }
}
