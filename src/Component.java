import processing.core.PVector;

public abstract class Component extends GameObject {
    private float health;
    private float baseHealth;

    private final float coolDown;
    private final float baseCoolDown;

    private final Ship ship;

    private static float pixelsPerCell;

    public static void setPixelsPerCell(float nPixelsPerCell) {
        pixelsPerCell = nPixelsPerCell;
    }

    public static float getPixelsPerCell() {
        return pixelsPerCell;
    }

    public Component(PVector position, Polygon hitbox, Ship ship, float health, float coolDown) {
        super(position, hitbox);
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

    public GameObject getParent() {
        return ship;
    }
}
