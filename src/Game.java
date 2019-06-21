import processing.core.PApplet;
import processing.core.PVector;

public class Game {

    private static Game instance;

    private SceneManager sceneManager;
    private PApplet a;
    private Resources r;
    private Mouse mouse;

    private Game() {
        a = Main.pApplet();
        r = Resources.getInstance();
        mouse = new Mouse();

        sceneManager = SceneManager.getInstance();
        sceneManager.addScene(new MenuScene("menu"));
        sceneManager.addScene(new GuideScene("guide"));
        sceneManager.addScene(new EditorScene("editor"));

        a.textFont(r.TEXT_FONT);
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void update(float secsRunning, float dt) {
        mouse.update(secsRunning, dt);
        if (!sceneManager.getActiveScene().equals(sceneManager.getPreviousScene())) {
            sceneManager.getActiveScene().init();
        }
        sceneManager.getActiveScene().update(secsRunning, dt);
        sceneManager.lateUpdate();
    }
}
