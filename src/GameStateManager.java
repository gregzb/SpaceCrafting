public class GameStateManager {
    private GameState prevState;
    private GameState state;
    private GameState nextState;

    public GameStateManager(GameState state) {
        this.prevState = this.nextState = this.state = state;
    }

    public GameStateManager() {
        this(GameState.MENU);
    }

    public void setState(GameState state) {
        this.nextState = state;
    }

    public GameState getState() {
        return state;
    }

    public GameState getPrevState() {
        return prevState;
    }

    public GameState getNextState() {
        return nextState;
    }

    public void update() {
        this.prevState = this.state;
        this.state = this.nextState;
    }
}
