package screen;

import engine.Core;

import java.util.logging.Logger;

public interface IView {

	/** Milliseconds until the screen accepts user input. */
	int INPUT_DELAY = 1000;
	/** Screen width. */
	int VIEW_WIDTH = 800;
	/** Screen height. */
	int VIEW_HEIGHT = 1000;
	/** Frames per second shown on the screen. */
	int FPS = 60;
	/** Application logger. */
	Logger logger = Core.getLogger();

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
	/** Get screen width */
	default int getViewWidth(){return VIEW_WIDTH;}
	/** Get screen height */
	default int getViewHeight(){return VIEW_HEIGHT;}
}
