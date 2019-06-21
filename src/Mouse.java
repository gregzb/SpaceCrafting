import processing.core.PApplet;
import processing.core.PVector;

public class Mouse extends GameObject{

    PApplet a;

    public Mouse() {
        super(new PVector(Main.pApplet().mouseX, Main.pApplet().mouseY), new Polygon(new PVector(0, 0)));
        a = Main.pApplet();
    }

    @Override
    public void update(float secsPassed, float dt) {
        setPosition(new PVector(a.mouseX, a.mouseY));
    }

    @Override
    public void display(float secsPassed, float dt) {

    }

    @Override
    public GameObject getParent() {
        return null;
    }
}
