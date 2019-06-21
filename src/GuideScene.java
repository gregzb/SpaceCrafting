import processing.core.PApplet;
import processing.core.PVector;

public class GuideScene extends Scene {

    private PApplet a;

    private Button[] buttons;

    private int pageCount = 3;
    private int currentPage = 0;

    public GuideScene(String id) {
        super(id);
        a = Main.pApplet();
    }

    public void init() {
        Button.setYOffset(-10);

        float bWidth = 100;
        float bHeight = 100;

        buttons = new Button[2];
        buttons[0] = new Button(new PVector(15, a.height - 15 - bHeight), new Polygon(new PVector(0, 0), new PVector(0, bHeight), new PVector(bWidth, bHeight), new PVector(bWidth, 0)), null, a.color(240, 240, 240), "<--", 60, "left");
        buttons[1] = new Button(new PVector(a.width - 15 - bWidth, a.height - 15 - bHeight), new Polygon(new PVector(0, 0), new PVector(0, bHeight), new PVector(bWidth, bHeight), new PVector(bWidth, 0)), null, a.color(240, 240, 240), "-->", 60, "right");
    }

    public void update(float secs, float dt) {
        int[] colors = {a.color(230, 200, 200), a.color(200, 230, 200), a.color(200, 200, 230)};
        a.background(loopColors(colors, 8, secs));

        a.fill(0);
        a.textAlign(a.CENTER, a.CENTER);
        a.textSize(64);
        a.text("Space Crafting", a.width / 2f, 30);

        String text = "";

        if (currentPage == 0) {
            text = "Page 1 Text";
        } else if (currentPage == 1) {
            text = "Page 2 Text";
        } else if (currentPage == 2) {
            text = "Page 3 Text";
        }

        a.fill(55);
        a.textSize(32);
        a.text(text, a.width/2f, 125);


        for (Button b : buttons) {
            b.update(secs, dt);
            b.display(secs, dt);
        }

        a.textSize(96);
        a.textAlign(a.CENTER, a.CENTER);
        a.text("#" + (currentPage + 1) + "/" + pageCount, a.width / 2f, a.height - 80);
    }

    private int loopColors(int[] colors, float period, float time) {
        float timeIntoPeriod = time % period;
        float percentOverall = timeIntoPeriod / period;
        float percentIntoColor = (percentOverall * colors.length) % 1;
        int index = (int) ((timeIntoPeriod / period) * colors.length);
        int nextIndex = (index + 1) % colors.length;
        return a.lerpColor(colors[index], colors[nextIndex], percentIntoColor);
    }

    public void buttonPressed(String id) {

    }

    public void buttonPressedOnce(String id) {
        if (id.equals("left")) {
            changePage(-1);
        } else if (id.equals("right")) {
            changePage(1);
        }
    }

    private void changePage(int offset) {
        if (currentPage + offset < 0 || currentPage + offset >= pageCount) {
            currentPage = 0;
            SceneManager.getInstance().switchTo("menu");
        } else {
            currentPage = PApplet.constrain(currentPage + offset, 0, pageCount - 1);
        }
    }

}
