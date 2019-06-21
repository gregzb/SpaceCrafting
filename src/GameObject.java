import processing.core.PApplet;
import processing.core.PVector;

public abstract class GameObject {
    private PVector position;
    private PVector velocity;
    private PVector maxVelocity;
    private PVector acceleration;
    private Polygon hitbox;

    private Rect bounds;

    public final PApplet a;

    public GameObject(PVector position, Polygon hitbox) {
        this(position, new PVector(0, 0), new PVector(Float.MAX_VALUE-1, Float.MAX_VALUE-1), new PVector(0, 0), hitbox);
    }

    public GameObject(PVector position, PVector velocity, PVector maxVelocity, PVector acceleration, Polygon hitbox) {
        this.setPosition(position.copy());
        this.setVelocity(velocity.copy());
        this.setMaxVelocity(maxVelocity.copy());
        this.setAcceleration(acceleration.copy());
        this.setRawHitbox(new Polygon(hitbox));

        a = Main.pApplet();

        this.bounds = this.hitbox.getBounds();
    }

    public abstract void update(float secsPassed, float dt);
    public abstract void display(float secsPassed, float dt);
    public abstract GameObject getParent();

    public Polygon getRawHitbox() {
        return new Polygon(hitbox);
    }

    public void setRawHitbox(Polygon hitbox) {
        this.hitbox = new Polygon(hitbox);
    }

    public Polygon getAbsoluteHitbox() {
        return new Polygon(hitbox, getAbsolutePosition());
    }

    public PVector getAbsolutePosition() {
        if (getParent() == null) {
            return getPosition();
        } else {
            return PVector.add(getPosition(), getParent().getAbsolutePosition());
        }
    }

    public PVector getPosition() {
        return position.copy();
    }

    public void setPosition(PVector position) {
        this.position = position.copy();
    }

    public PVector getVelocity() {
        return velocity.copy();
    }

    public void setVelocity(PVector velocity) {
        this.velocity = velocity.copy();
    }

    public PVector getMaxVelocity() {
        return maxVelocity.copy();
    }

    public void setMaxVelocity(PVector maxVelocity) {
        this.maxVelocity = maxVelocity.copy();
    }

    public PVector getAcceleration() {
        return acceleration.copy();
    }

    public void setAcceleration(PVector acceleration) {
        this.acceleration = acceleration.copy();
    }

    public Rect getRawBounds() {
        return new Rect(bounds);
    }

    public Rect getAbsoluteBounds() {
        return new Rect(bounds, getAbsolutePosition());
    }

    public void applyAcceleration() {
        velocity.add(acceleration);

        velocity.x = PApplet.constrain(velocity.x, -maxVelocity.x, maxVelocity.x);
        velocity.y = PApplet.constrain(velocity.y, -maxVelocity.y, maxVelocity.y);
    }

    public void applyVelocity() {
        position.add(velocity);
    }

    public void applyMovement() {
        applyAcceleration();
        applyVelocity();
    }
}
