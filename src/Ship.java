import processing.core.PVector;

import java.util.List;

public class Ship extends GameObject {

    private List<Component> components;
    private Ship enemyShip;
    private float fuel;

    public Ship(PVector position) {
        super(position, null);
    }

    @Override
    public void update(float secsPassed, float dt) {

    }

    @Override
    public void display(float secsPassed, float dt) {

    }

    @Override
    public GameObject getParent() {
        return null;
    }

    public void setEnemyShip(Ship s) {
        this.enemyShip = s;
    }

    public Ship getEnemyShip() {
        return enemyShip;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void addComponent(Component c) {
        components.add(c);
    }

    public void removeComponent(Component c) {
        components.remove(c);
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }
}
