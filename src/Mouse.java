import processing.core.PApplet;
import processing.core.PVector;

public class Mouse extends GameObject{

    private PApplet a;
    private boolean prevPressed;

    public Mouse() {
        super(new PVector(Main.pApplet().mouseX, Main.pApplet().mouseY), new Polygon(new PVector(0, 0)));
        a = Main.pApplet();
        prevPressed = false;
    }

    @Override
    public void update(float secsPassed, float dt) {
        setPosition(new PVector(a.mouseX, a.mouseY));
    }

    public void lateUpdate(float secsPassed, float dt) {
        prevPressed = a.mousePressed;
    }

    @Override
    public void display(float secsPassed, float dt) {

    }

    @Override
    public GameObject getParent() {
        return null;
    }

    public boolean mousePressed() {
        return a.mousePressed;
    }

    public boolean mousePrevPressed() {
        return prevPressed;
    }

    public boolean firstPressed() {
        return a.mousePressed && !prevPressed;
    }

    public boolean firstReleased() {
        return !a.mousePressed && prevPressed;
    }
}
