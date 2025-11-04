package screen;

import engine.Core;

import java.util.logging.Logger;

public interface IView {
	/** Load assets from outside ( fonts/images/sounds etc. ) */
	void loadAssets(Object ... assets);
	/** Draws the achievements on the screen */
	void draw(float dt);
	/** Event when this view shows */
	default void onShow() {}
	/** Event when this view hides */
	default void onHide() {}
	/** Dispose resources */
	void dispose();
	/** Get view's width */
	int getWidth();
	/** Get view's height */
	int getHeight();
}
