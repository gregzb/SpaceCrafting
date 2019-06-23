import processing.core.PFont;

import javax.print.attribute.standard.PrinterMessageFromOperator;

public class GlobalData {

    private static GlobalData instance;

    public static GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

    public Ship ship1;
    public Ship ship2;

}
