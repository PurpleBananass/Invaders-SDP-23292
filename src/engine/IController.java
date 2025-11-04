package engine;

public interface IController {

	/** Command updating Model's state */
	void updateModel(float deltaTime);
	/** Render view */
	void renderView();

}
