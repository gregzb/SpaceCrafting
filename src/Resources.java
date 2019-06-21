import processing.core.PFont;

public class Resources {
    private static Resources instance;

    public final PFont TEXT_FONT;

    private Resources() {
        TEXT_FONT = Main.pApplet().createFont("INVASION2000.TTF", 128);
    }

    public static Resources getInstance() {
        if (instance == null) {
            instance = new Resources();
        }
        return instance;
    }
}
