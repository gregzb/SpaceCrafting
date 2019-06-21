import processing.core.PApplet;
import processing.core.PVector;

public class MenuScene extends Scene{

    private PApplet a;

    private Button[] buttons;

    public MenuScene(String id) {
        super(id);
        a = Main.pApplet();
    }

    public void init() {
        Button.setYOffset(-10);

        float bWidth = 220;
        float bHeight = 100;

        buttons = new Button[2];
        buttons[0] = new Button(new PVector(a.width/2f - bWidth / 2, a.height/2f - bHeight / 2 - 100), new Polygon(new PVector(0, 0), new PVector(0, bHeight), new PVector(bWidth, bHeight), new PVector(bWidth, 0)), null, a.color(240, 240, 240), "PLAY", 60, "play");
        buttons[1] = new Button(new PVector(a.width/2f - bWidth / 2, a.height/2f - bHeight / 2 - 100 + 120), new Polygon(new PVector(0, 0), new PVector(0, bHeight), new PVector(bWidth, bHeight), new PVector(bWidth, 0)), null, a.color(240, 240, 240), "GUIDE", 54, "guide");
        for (Button b : buttons) {
            b.setRoundedness(30);
        }
    }

    public void update(float secs, float dt) {
        drawMenu(secs, dt);
    }

    public void drawMenu(float secs, float dt) {

        int[] colors = {a.color(230, 200, 200), a.color(200, 230, 200), a.color(200, 200, 230)};
        a.background(loopColors(colors, 8, secs));

        a.fill(0);
        a.textAlign(a.CENTER, a.CENTER);
        a.textSize(64);
        a.text("Space Crafting", a.width/2f, 30);

        for (Button b : buttons) {
            b.update(secs, dt);
            b.display(secs, dt);
        }

        a.fill(220, 20, 20);
        a.textSize(32);
        a.text("By: AngeredGecko\n(Greg Zborovsky)", a.width/2f, a.height - 60);
    }

    private int loopColors(int[] colors, float period, float time) {
        float timeIntoPeriod = time % period;
        float percentOverall = timeIntoPeriod / period;
        float percentIntoColor = (percentOverall * colors.length) % 1;
        int index = (int)((timeIntoPeriod / period) * colors.length);
        int nextIndex = (index + 1) % colors.length;
        return a.lerpColor(colors[index], colors[nextIndex], percentIntoColor);
    }

    public void buttonPressed(String id) {

    }

    public void buttonPressedOnce(String id) {
        if (id.equals("play")) {
            SceneManager.getInstance().switchTo("editor");
        } else if (id.equals("guide")) {
            SceneManager.getInstance().switchTo("guide");
        }
    }

}
