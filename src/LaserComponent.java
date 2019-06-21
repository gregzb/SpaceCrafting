import processing.core.PVector;

public class LaserComponent extends Component{
    public LaserComponent(PVector position, Ship ship, float health, float coolDown) {
        super(position, new Polygon(new PVector(0, 0), new PVector(0, 2), new PVector(1, 2), new PVector(1, 0)), ship, health, coolDown);
    }

    public LaserComponent(LaserComponent component) {
        super(component);
    }

    @Override
    public void update(float secsPassed, float dt) {

    }

    @Override
    public void display(float secsPassed, float dt) {
        Rect bounds = getAbsoluteBounds();
        a.fill(240, 20, 20);
        a.rect(bounds.x(), bounds.y(), bounds.width(), bounds.height(), 20);
    }

    @Override
    public Component copy() {
        return new LaserComponent(this);
    }
}
