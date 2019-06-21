import processing.core.PVector;

public class CoreComponent extends Component{

    public CoreComponent(PVector position, Ship ship, float health, float coolDown) {
        super(position, new Polygon(new PVector(0, 0), new PVector(0, 2), new PVector(2, 2), new PVector(2, 0)), ship, health, coolDown);
    }

    public CoreComponent(CoreComponent component) {
        super(component);
    }

    @Override
    public void update(float secsPassed, float dt) {

    }

    @Override
    public void display(float secsPassed, float dt) {
        Rect bounds = getAbsoluteBounds();
        a.fill(127, 127, 127);
        //a.println(bounds.width() * getPixelsPerCell());
        a.rect(bounds.x(), bounds.y(), bounds.width(), bounds.height(), 8);
    }

    @Override
    public Component copy() {
        return new CoreComponent(this);
    }
}
