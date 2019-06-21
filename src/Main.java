import processing.core.PApplet;

public class Main extends PApplet{

    private static PApplet instance;

    public static PApplet pApplet() {
        return instance;
    }

    float lastTime;

    public void settings() {
        //size(600, 600);
        size(800, 800);
    }

    public void setup() {
        surface.setTitle("Space Crafting");
        frameRate(60);

        instance = this;

        Game.getInstance();
        noStroke();
    }

    public void draw() {
        float secsRunning = millis() / 1000f;
        float dt = secsRunning - lastTime;

        Game.getInstance().update(secsRunning, dt);

        lastTime = secsRunning;
    }

    public static void main(String[] args) {
        PApplet.main(Main.class);
    }
}
