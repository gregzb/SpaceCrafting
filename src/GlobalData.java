public class GlobalData {

    private static GlobalData instance;

    public static GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

}
