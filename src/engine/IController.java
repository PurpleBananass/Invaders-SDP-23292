package engine;

public interface IController {

	/** Update Model's state using inner logics */
	void update(float deltaTime);
	/** Render view */
	void render();

}
