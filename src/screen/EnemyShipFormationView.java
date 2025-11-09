package screen;

import java.awt.Color;
import java.util.List;

import engine.Core;
import engine.DrawManager;
import engine.level.Level;
import entity.EnemyShipFormationModel;
import entity.EnemyShip;

/**
 * Responsible for drawing the EnemyShipFormation.
 * (VIEW)
 */
public class EnemyShipFormationView {

    /** DrawManager instance. */
    private DrawManager drawManager;
    /** Model instance to get data from. */
    private EnemyShipFormationModel model;

    /**
     * Constructor.
     * @param model The data model to draw.
     */
    public EnemyShipFormationView(EnemyShipFormationModel model) {
        this.model = model;
        this.drawManager = Core.getDrawManager();
    }

    /**
     * Draws every individual component of the formation.
     */
    public final void draw() {
        for (List<EnemyShip> column : this.model.getEnemyShips()) {
            for (EnemyShip enemyShip : column) {
                drawManager.drawEntity(enemyShip, enemyShip.getPositionX(),
                        enemyShip.getPositionY());
            }
        }
    }

    /**
     * Applies a specific color to all ships in the formation.
     * @param color The color to apply.
     */
    public void applyEnemyColor(final Color color) {
        for (java.util.List<EnemyShip> column : this.model.getEnemyShips()) {
            for (EnemyShip ship : column) {
                if (ship != null && !ship.isDestroyed()) {
                    ship.setColor(color);
                }
            }
        }
    }

    /**
     * Applies a color to all ships based on the level number.
     * @param level The current level.
     */
    public void applyEnemyColorByLevel(final Level level) {
        if (level == null) return;
        final int lv = level.getLevel();
        applyEnemyColor(getColorForLevel(lv));
    }

    /**
     * Gets the appropriate color for a given level number.
     * @param levelNumber The level number.
     * @return The color for that level.
     */
    private Color getColorForLevel(final int levelNumber) {
        switch (levelNumber) {
            case 1: return new Color(0x3DDC84); // green
            case 2: return new Color(0x00BCD4); // cyan
            case 3: return new Color(0xFF4081); // pink
            case 4: return new Color(0xFFC107); // amber
            case 5: return new Color(0x9C27B0); // purple
            case 6: return new Color(0xFF5722); // deep orange
            case 7: return new Color(0x8BC34A); // light green
            case 8: return new Color(0x03A9F4); // light blue
            case 9: return new Color(0xE91E63); // magenta
            case 10: return new Color(0x607D8B); // blue gray
            default: return Color.WHITE;
        }
    }
}