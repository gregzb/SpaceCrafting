import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Grid extends GameObject{
    private float pixelsPerCell;
    private int rows;
    private int cols;
    //private boolean[][] filledSlots;
    private List<Component> components;

    private PApplet a;

    public Grid(PVector position, PVector size, float pixelsPerCell) {
        super(position, new Polygon(new PVector(0, 0), new PVector(0, size.y), new PVector(size.x, size.y), new PVector(size.x, 0)));

        a = Main.pApplet();

        components = new ArrayList<>();

        rows = (int)(size.y / pixelsPerCell);
        cols = (int)(size.x / pixelsPerCell);

//        this.filledSlots = new boolean[(int)(size.y / pixelsPerCell)][(int)(size.x / pixelsPerCell)];
//        for (int i = 0; i < this.filledSlots.length; i++) {
//            this.filledSlots[i] = new boolean[(int)(size.x / pixelsPerCell)];
//        }
        this.pixelsPerCell = pixelsPerCell;
    }

    public void addComponent(Component component) {
        components.add(component);
        //editFilled(component, true);
    }

    public void removeComponent(Component component) {
        components.remove(component);
        //editFilled(component, false);
    }

//    public void editFilled(Component component, boolean filled) {
//        Rect bounds = component.getRawHitbox().getBounds();
//        int cX = (int) Math.ceil((component.getAbsolutePosition().x-getAbsolutePosition().x) / pixelsPerCell);
//        int cY = (int) Math.ceil((component.getAbsolutePosition().y-getAbsolutePosition().y) / pixelsPerCell);
//        int cWidth = (int) Math.ceil(bounds.width() / pixelsPerCell);
//        int cHeight = (int) Math.ceil(bounds.height() / pixelsPerCell);
//
//        for (int y = 0; y < cHeight; y++) {
//            for (int x = 0; x < cWidth; x++) {
//                System.out.println(cY + ", " + y + ", " + cHeight);
//                System.out.println(cX + ", " + x + ", " + cWidth);
//                filledSlots[y + cY][x + cX] = filled;
//            }
//        }
//    }

    @Override
    public void update(float secsPassed, float dt) {
        for (Component component : components) {

        }
    }

    @Override
    public void display(float secsPassed, float dt) {
        a.fill(0);
        PVector pos = getAbsolutePosition();
        Rect bounds = getAbsoluteBounds();
        for (int row = 0; row <= rows; row++) {
            a.line(pos.x, pos.y + row * pixelsPerCell, pos.x + bounds.width(), pos.y + row * pixelsPerCell);
        }
        for (int col = 0; col <= cols; col++) {
            a.line(pos.x + col * pixelsPerCell, pos.y, pos.x + col * pixelsPerCell, pos.y + bounds.height());
        }
        for (Component component : components) {
            component.display(secsPassed, dt);
        }
    }

    @Override
    public GameObject getParent() {
        return null;
    }

    public PVector screenToGrid(PVector screen) {
        float x = screen.x;
        float y = screen.y;

        Rect bounds = getAbsoluteBounds();

        x -= bounds.x();
        y -= bounds.y();

        x /= pixelsPerCell;
        y /= pixelsPerCell;

        return new PVector(x, y);
    }

    public PVector gridToScreen(PVector grid) {
        float x = grid.x;
        float y = grid.y;

        Rect bounds = getAbsoluteBounds();

        x *= pixelsPerCell;
        y *= pixelsPerCell;

        x += bounds.x();
        y += bounds.y();

        return new PVector(x, y);
    }

    public List<Component> getComponents() {
        return components;
    }
}
