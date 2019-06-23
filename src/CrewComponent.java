import processing.core.PVector;

public class CrewComponent extends Component{

    public CrewComponent(PVector position, Ship ship, float health, float coolDown) {
        super(position, new Polygon(new PVector(0, 0), new PVector(0, 1), new PVector(1, 1), new PVector(1, 0)), ship, health, coolDown);
    }

    public CrewComponent(CrewComponent component) {
        super(component);
    }

    @Override
    public void update(float secsPassed, float dt) {

    }

    @Override
    public void display(float secsPassed, float dt) {
        Rect bounds = getAbsoluteBounds();
        a.fill(60, 20, 40);
        //a.println(bounds.width() * getPixelsPerCell());
        a.rect(bounds.x(), bounds.y(), bounds.width(), bounds.height(), 20);
        displayHealth();
    }

    @Override
    public Component copy() {
        return new CrewComponent(this);
    }
}
