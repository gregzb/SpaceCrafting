import processing.core.PApplet;
import processing.core.PVector;

import javax.swing.text.Position;

public class EditorScene extends Scene {

    private PApplet a;
    private Grid g;

    private Button[] buttons;
    private String text = "";
    private Component currentChoice = null;

    public EditorScene(String id) {
        super(id);
        a = Main.pApplet();
        int pixelsPerCell = 34;
        int trueSize = pixelsPerCell * 16;
        g = new Grid(new PVector(a.width/2f - (trueSize)/2f, a.height/2f - (trueSize)/2f), new PVector(trueSize, trueSize), pixelsPerCell);
        Component.setPixelsPerCell(pixelsPerCell);

        g.addComponent(new CoreComponent(g.gridToScreen(new PVector(7, 7)), null, 100, 0));

        float bWidth = 150;
        float bHeight = 80;

        buttons = new Button[4];
        buttons[0] = new Button(new PVector(10, 10), new Polygon(new PVector(0, 0), new PVector(0, bHeight), new PVector(bWidth, bHeight), new PVector(bWidth, 0)), null, a.color(240, 240, 240), "Laser", 40, "laser");
        buttons[1] = new Button(new PVector(10 + 170, 10), new Polygon(new PVector(0, 0), new PVector(0, bHeight), new PVector(bWidth, bHeight), new PVector(bWidth, 0)), null, a.color(240, 240, 240), "Shield", 40, "shield");
        buttons[2] = new Button(new PVector(10 + 340, 10), new Polygon(new PVector(0, 0), new PVector(0, bHeight), new PVector(bWidth, bHeight), new PVector(bWidth, 0)), null, a.color(240, 240, 240), "Crew", 40, "crew");
        buttons[3] = new Button(new PVector(a.width - 260, a.height - 110), new Polygon(new PVector(0, 0), new PVector(0, 100), new PVector(250, 100), new PVector(250, 0)), null, a.color(240, 240, 240), "Confirm", 55, "confirm");
    }

    @Override
    public void init() {
        a.stroke(0);
    }

    @Override
    public void update(float secs, float dt) {
        a.background(255);
        g.update(secs, dt);
        g.display(secs, dt);

        for (Button b : buttons) {
            b.update(secs, dt);
            b.display(secs, dt);
        }

        a.textSize(30);
        a.text(text, a.width - 100, 50);

        if (currentChoice != null) {

            if (KeyboardInput.getInstance().keyFirstDown('r') || KeyboardInput.getInstance().keyFirstDown('R')) {
                currentChoice.rotateCounterClockwise();
            }

            PVector mousePos = Game.getInstance().getMouse().getAbsolutePosition();
            PVector mouseOnGrid = g.screenToGrid(mousePos);
            PVector mouseOnScreen = g.gridToScreen(new PVector((float)Math.floor(mouseOnGrid.x), (float)Math.floor(mouseOnGrid.y)));
            currentChoice.setPosition(mouseOnScreen);

            boolean inBounds = g.getAbsoluteBounds().contains(currentChoice.getAbsoluteBounds());
            boolean notIntersecting = true;
            boolean tangent = false;

            for (Component component : g.getComponents()) {
                if (component.getAbsoluteBounds().collides(currentChoice.getAbsoluteBounds(), false)) {
                    notIntersecting = false;
                }
                int intersectingPoints = component.getAbsoluteBounds().getIntersectPoints(currentChoice.getAbsoluteBounds());
                if (intersectingPoints >= 2) {
                    tangent = true;
                }
            }

            if (notIntersecting && inBounds) {
                currentChoice.display(secs, dt);
                if (Game.getInstance().getMouse().firstPressed() && tangent) {
                    g.addComponent(currentChoice.copy());
                    currentChoice = null;
                }
            }
            //a.println(g.getComponents().size());
        }
    }

    public void buttonPressedOnce(String id) {
        text = id;
        if (text.equals("laser")) {
            currentChoice = new LaserComponent(new PVector(0, 0), null, 1, 1);
        } else if (text.equals("shield")) {
            currentChoice = new ShieldComponent(new PVector(0, 0), null, 1, 1);
        } else if (text.equals("crew")) {
            currentChoice = new CrewComponent(new PVector(0, 0), null, 1, 1);
        } else if (text.equals("confirm")) {
            Ship ship = new Ship(new PVector(0, 0));
            for (Component component : g.getComponents()) {
                ship.addComponent(component.copy());
            }
            g.getComponents().clear();
            GlobalData.getInstance().ship1 = new Ship(ship);
            currentChoice = null;
        } else {
            currentChoice = null;
        }
    }
}
