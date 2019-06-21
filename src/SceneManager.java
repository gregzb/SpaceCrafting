import java.util.HashMap;
import java.util.Map;

public class SceneManager {

    private static SceneManager instance;

    private Map<String, Scene> sceneMap;

    private String prevScene;
    private String currentScene;
    private String nextScene;

    public SceneManager() {
        sceneMap = new HashMap<>();
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void addScene(Scene scene) {
        sceneMap.put(scene.getId(), scene);
        if (sceneMap.entrySet().size() == 1) {
            prevScene = "";
            currentScene = scene.getId();
            nextScene = currentScene;
        }
    }

    public void removeScene(String id) {
        sceneMap.remove(id);
    }

    public void removeScene(Scene scene) {
        removeScene(scene.getId());
    }

    public void switchTo(String id) {
        nextScene = id;
    }

    public Scene getActiveScene() {
        return sceneMap.get(currentScene);
    }

    public Scene getPreviousScene() {
        return sceneMap.get(prevScene);
    }

    public void lateUpdate() {
        prevScene = currentScene;
        currentScene = nextScene;
    }
}
