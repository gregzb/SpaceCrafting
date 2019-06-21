public abstract class Scene {
    private String id;

    public Scene(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract void init();

    public abstract void update(float secs, float dt);

    public boolean equals(Object o) {
        if (!(o instanceof Scene)) return false;
        Scene s = (Scene) o;
        return s.id.equals(id);
    }

    public void buttonPressed(String id) {

    }

    public void buttonPressedOnce(String id) {

    }
}
