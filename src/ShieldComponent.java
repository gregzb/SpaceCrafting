import processing.core.PVector;

public class ShieldComponent extends Component{

    public ShieldComponent(PVector position, Ship ship, float health, float coolDown) {
        super(position, new Polygon(new PVector(0, 0), new PVector(0, 2), new PVector(2, 2), new PVector(2, 0)), ship, health, coolDown);
    }

    public ShieldComponent(ShieldComponent component) {
        super(component);
    }

    @Override
    public void update(float secsPassed, float dt) {

    }

    @Override
    public void display(float secsPassed, float dt) {
        Rect bounds = getAbsoluteBounds();
        a.fill(20, 240, 20);
        //a.println(bounds.width() * getPixelsPerCell());
        a.rect(bounds.x(), bounds.y(), bounds.width(), bounds.height(), 20);
        displayHealth();
    }

    @Override
    public Component copy() {
        return new ShieldComponent(this);
    }
}
