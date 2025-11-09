package engine;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.IOException;
import engine.FileManager;

public final class FontPack {
    public final Font fontRegular;
    public final Font fontBig;
    public final Font fontSmall;

    public final FontMetrics regularMetrics;
    public final FontMetrics bigMetrics;
    public final FontMetrics smallMetrics;

    public FontPack(Graphics graphics, FileManager fm) {
        try {
            fontRegular = fm.loadFont(14f);
            fontBig = fm.loadFont(24f);
            fontSmall = fm.loadFont(9f);

            regularMetrics = graphics.getFontMetrics(fontRegular);
            bigMetrics = graphics.getFontMetrics(fontBig);
            smallMetrics = graphics.getFontMetrics(fontSmall);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException("[FontPack] Failed to load fonts", e);
        }
    }
    public Font getRegular() {return fontRegular;}
    public Font getFontBig() {return fontBig;}
    public Font getFontSmall() {return fontSmall;}
    public FontMetrics getRegularMetrics() {return regularMetrics;}
    public FontMetrics getBigMetrics() {return bigMetrics;}
    public FontMetrics getSmallMetrics() {return smallMetrics;}
}
