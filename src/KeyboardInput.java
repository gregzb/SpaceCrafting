import java.util.HashSet;
import java.util.Set;

public class KeyboardInput {

    private static KeyboardInput instance;

    private Set<Character> keysDown;
    private Set<Character> prevKeysDowns;

    private Set<Integer> keyCodesDown;
    private Set<Integer> prevKeyCodesDown;

    private KeyboardInput() {
        keysDown = new HashSet<>();
        prevKeysDowns = new HashSet<>();

        keyCodesDown = new HashSet<>();
        prevKeyCodesDown = new HashSet<>();
    }

    public static KeyboardInput getInstance() {
        if (instance == null) {
            instance = new KeyboardInput();
        }
        return instance;
    }

    public void addKey(Character key) {
        keysDown.add(key);
    }

    public void addKey(Integer key) {
        keyCodesDown.add(key);
    }

    public void removeKey(Character key) {
        keysDown.remove(key);
    }

    public void removeKey(Integer key) {
        keyCodesDown.remove(key);
    }

    public void lateUpdate() {
        prevKeysDowns = new HashSet<>(keysDown);
        prevKeyCodesDown = new HashSet<>(keyCodesDown);
    }

    public boolean keyDown(Character key) {
        return keysDown.contains(key);
    }

    public boolean keyFirstDown(Character key) {
        return keysDown.contains(key) && !prevKeysDowns.contains(key);
    }

    public boolean keyCodeDown(Integer key) {
        return keyCodesDown.contains(key);
    }

    public boolean keyCodeFirstDown(Integer key) {
        return keyCodesDown.contains(key) && !prevKeyCodesDown.contains(key);
    }
}
