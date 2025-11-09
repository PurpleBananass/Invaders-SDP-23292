package entity;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.awt.Color;

import screen.Screen;
import screen.GameScreen;
import screen.EnemyShipFormationView;
import engine.level.Level;

/**
 * Groups enemy ships into a formation that moves together.
 * (CONTROLLER - Manages Model and View)
 */
public class EnemyShipFormation implements Iterable<EnemyShip> {

	/** The Model component */
	private EnemyShipFormationModel model;
	/** The View component */
	private EnemyShipFormationView view;

	/** Screen to draw ships on. */
	private Screen screen;

	/**
	 * Constructor (Refactored to use FormationBuilder)
	 * @param level Current level data.
	 */
	public EnemyShipFormation(final Level level) {

		// Use the Builder to create the ship list
		FormationBuilder builder = new FormationBuilder();
		List<List<EnemyShip>> builtShips = builder.build(
				level,
				level.getFormationWidth(),
				level.getFormationHeight()
		);

		// Pass the pre-built list to the Model
		this.model = new EnemyShipFormationModel(level, builtShips);

		// Create the View
		this.view = new EnemyShipFormationView(this.model);
	}

	/**
	 * Associates the formation to a given screen.
	 *
	 * @param newScreen
	 * Screen to attach.
	 */
	public final void attach(final Screen newScreen) {
		this.screen = newScreen;
	}

	/**
	 * Draws every individual component of the formation.
	 * (Delegates to View)
	 */
	public final void draw() {
		this.view.draw();
	}

	/**
	 * Updates the position of the ships.
	 * (Delegates to Model)
	 */
	public final void update() {
		if (this.screen == null) {
			return;
		}
		// Pass screen boundaries to the Model
		this.model.update(this.screen.getWidth(), GameScreen.getItemsSeparationLineHeight());
	}

	/**
	 * Shoots a bullet downwards.
	 * (Delegates to Model)
	 */
	public final void shoot(final Set<Bullet> bullets) {
		this.model.shoot(bullets);
	}

	/**
	 * Destroys a ship.
	 * (Delegates to Model)
	 */
	public final void destroy(final EnemyShip destroyedShip) {
		this.model.destroy(destroyedShip);
	}

	/**
	 * Gets the ship on a given column that will be in charge of shooting.
	 * (Delegates to Model)
	 */
	public final EnemyShip getNextShooter(final List<EnemyShip> column) {
		return this.model.getNextShooter(column);
	}

	/**
	 * Returns an iterator over the ships in the formation.
	 * (Delegates to Model)
	 */
	@Override
	public final Iterator<EnemyShip> iterator() {
		return this.model.iterator();
	}

	/**
	 * Destroy all ships in the formation.
	 * (Delegates to Model)
	 */
	public final int destroyAll() {
		return this.model.destroyAll();
	}

	/**
	 * Checks if there are any ships remaining.
	 * (Delegates to Model)
	 */
	public final boolean isEmpty() {
		return this.model.isEmpty();
	}

	/**
	 * Activates slowdown effect on the formation.
	 * (DelegATES to Model)
	 */
	public void activateSlowdown() {
		this.model.activateSlowdown();
	}

	/**
	 * Clears the formation, removing all enemy ships.
	 */
	public final void clear() {
		this.model.clear();
	}

	/**
	 * Applies a color to all ships based on the level number.
	 */
	public final void applyEnemyColorByLevel(final Level level) {
		this.view.applyEnemyColorByLevel(level);
	}

	/**
	 * Applies a specific color to all ships in the formation.
	 *
	 * @param color
	 * The color to apply.
	 */
	public final void applyEnemyColor(final Color color) {
		this.view.applyEnemyColor(color);
	}
}