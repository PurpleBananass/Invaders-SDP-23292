package screen;

import java.awt.event.KeyEvent;
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
    public final int run() {
        super.run();

        return this.returnCode;
    }
    public void update(){
        super.update();

        draw();
        if (inputManager.isKeyDown(KeyEvent.VK_SPACE)
                && this.inputDelay.checkFinished()){
            this.isRunning = false;
        }
    }
    private void draw() {
        drawManager.initDrawing(this);

        drawManager.drawAchievementMenu(this);
        drawManager.drawKillAchievementsList(this);
        drawManager.completeDrawing(this);
    }
}
