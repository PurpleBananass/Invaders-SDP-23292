package engine;

import java.util.List;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import screen.Screen;

public final class UIRenderer {
    private final BackBuffer backBuffer;
    private final FontPack fontPack;

    public UIRenderer(BackBuffer backBuffer, FontPack fontPack) {
        this.backBuffer = backBuffer;
        this.fontPack = fontPack;
    }

    public void drawHorizontalLine(final Screen screen, final int y) {
        Graphics g = backBuffer.getGraphics();
        g.setColor(Color.GREEN);
        g.drawLine(0, y, screen.getWidth(), y);
        g.drawLine(0, y + 1, screen.getWidth(), y + 1);
    }

    public void drawTitle(final Screen screen) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getFontBig());
        g.setColor(Color.GREEN);
        g.drawString("Invaders", screen.getWidth() / 2 - 100, screen.getHeight() / 3);

        g.setFont(fontPack.getRegular());
        g.setColor(Color.GRAY);
        g.drawString("Select with W/S or Arrows, Confirm with Space",
                screen.getWidth() / 2 - 180, screen.getHeight() / 2);
    }

    /** Draws main menu options with pulsing selection effect. */
    public void drawMenu(final Screen screen, final int option) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getRegular());

        String[] options = { "Exit", "Play", "High Scores", "Shop", "Achievements" };

        // Pulse effect for selection
        float pulse = (float) ((Math.sin(System.currentTimeMillis() / 200.0) + 1.0) / 2.0);
        Color pulseColor = new Color(0, 0.5f + pulse * 0.5f, 0);

        int baseY = screen.getHeight() * 2 / 3;
        int spacing = fontPack.getRegularMetrics().getHeight();

        int selectedIndex = switch (option){
            case 0 -> 0;
            case 2 -> 1; // Play
            case 3 -> 2; // High Scores
            case 4 -> 3; // Shop
            case 6 -> 4; // Achievements
            default -> -1; // none (ex. sound button focus)
        };

        for (int i = 0; i < options.length; i++) {
            if (i == selectedIndex)
                g.setColor(pulseColor);
            else
                g.setColor(Color.WHITE);

            int textWidth = fontPack.getRegularMetrics().stringWidth(options[i]);
            int x = (screen.getWidth() - textWidth) / 2;
            int y = baseY + spacing * i;

            g.drawString(options[i], x, y);
        }
    }

    /** Draws game results on the end screen.*/
    public void drawResults(final Screen screen, final int score, final int livesRemaining,
                            final int shipsDestroyed, final float accuracy, final boolean isNewRecord) {

        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getRegular());
        g.setColor(Color.WHITE);

        String scoreString = String.format("Score: %04d", score);
        String livesRemainingString = "Lives remaining: " + livesRemaining;
        String shipsDestroyedString = "Enemies destroyed: " + shipsDestroyed;
        String accuracyString = String.format("Accuracy: %.2f%%", accuracy * 100);

        int baseY = isNewRecord ? screen.getHeight() / 4 : screen.getHeight() / 2;
        int spacing = fontPack.getRegularMetrics().getHeight() * 2;

        g.drawString(scoreString, centerX(screen, g, scoreString), baseY);
        g.drawString(livesRemainingString, centerX(screen, g, livesRemainingString), baseY + spacing);
        g.drawString(shipsDestroyedString, centerX(screen, g, shipsDestroyedString), baseY + spacing * 2);
        g.drawString(accuracyString, centerX(screen, g, accuracyString), baseY + spacing * 3);
    }

    /**
     * Draws interactive name input after new record.
     */
    public void drawNameInput(final Screen screen, final char[] name, final int nameCharSelected) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getRegular());

        String newRecordString = "NEW RECORD!";
        String promptString = "Enter your initials:";

        g.setColor(Color.GREEN);
        g.drawString(newRecordString, centerX(screen, g, newRecordString),
                screen.getHeight() / 4 + fontPack.getRegularMetrics().getHeight() * 10);

        g.setColor(Color.WHITE);
        g.drawString(promptString, centerX(screen, g, promptString),
                screen.getHeight() / 4 + fontPack.getRegularMetrics().getHeight() * 12);

        // --- Draw name characters ---
        int baseY = screen.getHeight() / 4 + fontPack.getRegularMetrics().getHeight() * 14;
        int x = screen.getWidth() / 2 - 50;

        for (int i = 0; i < name.length; i++) {
            if (i == nameCharSelected) g.setColor(Color.GREEN);
            else g.setColor(Color.WHITE);

            String ch = Character.toString(name[i]);
            g.drawString(ch, x + i * 35, baseY);
        }
    }

    public void drawGameOver(final Screen screen, final boolean acceptsInput, final boolean isNewRecord) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getFontBig());
        String gameOverText = "GAME OVER";
        String continueText = "Press SPACE to play again, ESC to exit";

        int baseY = isNewRecord ? screen.getHeight() / 4 : screen.getHeight() / 2;

        /** title */
        g.setColor(Color.GREEN);
        g.drawString(gameOverText,
                centerX(screen, g, gameOverText),
                baseY - fontPack.getBigMetrics().getHeight() * 2);

        /** instructions */
        g.setFont(fontPack.getRegular());
        g.setColor(acceptsInput ? Color.GREEN : Color.GRAY);
        g.drawString(continueText,
                centerX(screen, g, continueText),
                screen.getHeight() / 2 + fontPack.getRegularMetrics().getHeight() * 10);
    }

    /** high score title */
    public void drawHighScoreMenu(final Screen screen) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getFontBig());
        String title = "High Scores";
        String instructions = "Press SPACE to return";

        g.setColor(Color.GREEN);
        g.drawString(title, centerX(screen, g, title), screen.getHeight() / 8);

        g.setFont(fontPack.getRegular());
        g.setColor(Color.GRAY);
        g.drawString(instructions, centerX(screen, g, instructions), screen.getHeight() / 5);
    }

    /** high score list */
    public void drawHighScores(final Screen screen, final List<Score> highScores) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getRegular());
        g.setColor(Color.WHITE);

        int startY = screen.getHeight() / 4;
        int spacing = fontPack.getRegularMetrics().getHeight() * 2;

        int i = 0;
        for (Score s : highScores) {
            String text = String.format("%s        %04d", s.getName(), s.getScore());
            g.drawString(text, centerX(screen, g, text), startY + spacing * (i + 1));
            i++;
        }
    }

    /** achievement list */
    public void drawAchievements(final Screen screen, final List<Achievement> achievements) {
        Graphics g = backBuffer.getGraphics();

        g.setFont(fontPack.getFontBig());
        g.setColor(Color.GREEN);
        g.drawString("Achievements", centerX(screen, g, "Achievements"), screen.getHeight() / 8);

        g.setFont(fontPack.getRegular());

        int startY = screen.getHeight() / 5;
        int spacing = fontPack.getRegularMetrics().getHeight() * 2;

        int i = 0;
        for (Achievement a : achievements) {
            g.setColor(a.isUnlocked() ? Color.GREEN : Color.WHITE);
            String text = a.getName() + " - " + a.getDescription();
            g.drawString(text, centerX(screen, g, text), startY + spacing * (i + 1));
            i++;
        }

        g.setColor(Color.GRAY);
        String backText = "Press ESC to return";
        g.drawString(backText, centerX(screen, g, backText), screen.getHeight() - 50);
    }

    /** Credit title */
    public void drawCreditsMenu(final Screen screen) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getFontBig());
        g.setColor(Color.GREEN);
        String creditsString = "Credits";
        g.drawString(creditsString,
                (screen.getWidth() - g.getFontMetrics().stringWidth(creditsString)) / 2,
                screen.getHeight() / 8);

        g.setFont(fontPack.getRegular());
        g.setColor(Color.GRAY);
        String instructionsString = "Press Space to return";
        g.drawString(instructionsString,
                (screen.getWidth() - g.getFontMetrics().stringWidth(instructionsString)) / 2,
                screen.getHeight() / 5);
    }

    /** Credit context */
    public void drawCredits(final Screen screen, final java.util.List<String> creditLines) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getFontSmall());

        int y = screen.getHeight() / 4;
        final int x = screen.getWidth() / 10;
        final int lineSpacing = fontPack.getSmallMetrics().getHeight() + 6;

        for (String line : creditLines) {
            g.setColor(Color.GREEN);
            g.drawString(line, x, y);
            y += lineSpacing;
        }
    }

    /** text center Regular */
    public void drawCenteredRegularString(final Screen screen, final String text, final int y) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getRegular());
        g.setColor(Color.WHITE);
        int x = (screen.getWidth() - g.getFontMetrics().stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }

    /** text center FontBig */
    public void drawCenteredBigString(final Screen screen, final String text, final int y) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getFontBig());
        g.setColor(Color.WHITE);
        int x = (screen.getWidth() - g.getFontMetrics().stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }

    /**
     * Draws a level countdown ("Level X", "3", "2", "1", "GO!") on the screen.
     */
    public void drawCountDown(final Screen screen, final int level, final int number, final boolean bonusLife) {
        Graphics g = backBuffer.getGraphics();
        g.setFont(fontPack.getFontBig());
        g.setColor(Color.GREEN);

        int rectWidth = screen.getWidth();
        int rectHeight = screen.getHeight() / 6;
        int rectY = screen.getHeight() / 2 - rectHeight / 2;

        /** background box */
        g.setColor(Color.BLACK);
        g.fillRect(0, rectY, rectWidth, rectHeight);


        String text;
        if (number >= 4) {
            text = bonusLife ? "Level " + level + " - Bonus life!" : "Level " + level;
        } else if (number != 0) {
            text = Integer.toString(number);
        } else {
            text = "GO!";
        }

        FontMetrics metrics = g.getFontMetrics();
        int textX = (screen.getWidth() - metrics.stringWidth(text)) / 2;
        int textY = screen.getHeight() / 2 + metrics.getHeight() / 3;

        g.setColor(Color.GREEN);
        g.drawString(text, textX, textY);
    }


    // center text horizontally
    private int centerX(Screen screen, Graphics g, String text) {
        return (screen.getWidth() - g.getFontMetrics().stringWidth(text)) / 2;
    }
}
