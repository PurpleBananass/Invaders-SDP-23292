package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import engine.Frame;
import screen.Screen;

public final class BackBuffer {

    private final Frame frame;
    private BufferedImage buffer;
    private Graphics graphics;
    private Graphics backGraphics;

    public BackBuffer(Frame frame) {
        this.frame = frame;
    }

    public void initDraw(Screen screen) {
        buffer = new BufferedImage(screen.getWidth(), screen.getHeight(), BufferedImage.TYPE_INT_RGB);
        graphics = frame.getGraphics();
        backGraphics = buffer.getGraphics();

        backGraphics.setColor(Color.BLACK);
        backGraphics.fillRect(0, 0, screen.getWidth(), screen.getHeight());
    }

    public void end(Screen screen) {
        graphics.drawImage(buffer, frame.getInsets().left, frame.getInsets().top, frame);
    }

    public Graphics getGraphics() {
        return backGraphics;
    }
}
