import processing.core.PVector;

public abstract class Component extends GameObject {
    private float health;
    private float baseHealth;

    private final float coolDown;
    private final float baseCoolDown;

    private Ship ship;

    private static float pixelsPerCell;

    public static void setPixelsPerCell(float nPixelsPerCell) {
        pixelsPerCell = nPixelsPerCell;
    }

    public static float getPixelsPerCell() {
        return pixelsPerCell;
    }

    public abstract Component copy();

    public Component(Component component) {
        super(component);
        this.ship = component.getShip(); //WATCH OUT !!!!!!!!!!!!!!!!!
        this.baseHealth = component.baseHealth;
        this.health = component.health;
        this.baseCoolDown = component.baseCoolDown;
        this.coolDown = component.coolDown;
    }

    public Component(PVector position, Polygon hitbox, Ship ship, float health, float coolDown) {
        super(position, scalePolygon(hitbox, pixelsPerCell));
        this.ship = ship;
        this.baseHealth = this.health = health;
        this.baseCoolDown = this.coolDown = coolDown;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(float baseHealth) {
        this.baseHealth = baseHealth;
    }

    public float getCoolDown() {
        return coolDown;
    }

    public float getBaseCoolDown() {
        return baseCoolDown;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public GameObject getParent() {
        return ship;
    }

    public static Polygon scalePolygon(Polygon p, float scaleFactor) {
        return new Polygon(p, scaleFactor, new PVector(0, 0));
    }

    public void displayHealth() {
        a.fill(220, 20, 20);
        a.rect(getAbsoluteBounds().x() + 2, getAbsoluteBounds().y() + 2, 16, 8, 0);

        float percentHealth = health / baseHealth;

        a.fill(20, 220, 20);
        a.rect(getAbsoluteBounds().x() + 2, getAbsoluteBounds().y() + 2, 16 * percentHealth, 8, 0);
    }
}
