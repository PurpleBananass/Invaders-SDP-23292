package screen;

public class AchievementScreen extends Screen{
    /**
     * Constructor, establishes the properties of the screen.
     *
     * @param width  Screen width.
     * @param height Screen height.
     * @param fps    Frames per second, frame rate at which the game is run.
     */
    public AchievementScreen(int width, int height, int fps) {
        super(width, height, fps);

        this.returnCode = 1;
        
    }
}
