import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Button extends GameObject {

    private String text;
    private int textSize;
    private String id;

    private PImage image;
    private Integer color;

    private PApplet a;

    private int tintColor;

    private boolean lastPressed;

    private float roundedness = 10;

    private static float yOffset;

    public static void setYOffset(float yOffsetP){
        yOffset = yOffsetP;
    }

    public Button(PVector position, Polygon hitbox, PImage image, Integer color, String text, int textSize, String id) {
        super(position, hitbox);

        this.a = Main.pApplet();

        this.text = text;
        this.textSize = textSize;
        this.id = id;
        this.color = color;
        this.image = image;

        tintColor = a.color(255, 255, 255);
    }

    public void setRoundedness(float roundedness) {
        this.roundedness = roundedness;
    }

    public void update(float secs, float dt) {
        if (mouseOver()) {
            if (a.mousePressed) {
                tintColor = a.color(205, 205, 205);
                SceneManager.getInstance().getActiveScene().buttonPressed(id);
                if (!lastPressed) {
                    SceneManager.getInstance().getActiveScene().buttonPressedOnce(id);
                }
                lastPressed = true;
            } else {
                lastPressed = false;
                tintColor = a.color(230, 230, 230);
            }
        } else {
            tintColor = a.color(255, 255, 255);
        }
    }

    public boolean mouseOver() {
        return Game.getInstance().getMouse().getAbsoluteHitbox().intersects(getAbsoluteHitbox()).hasCollided();
    }

    public void display(float secsPassed, float dt) {
        a.pushMatrix();

        a.tint(tintColor);
        a.translate(getPosition().x, getPosition().y);
        if (image != null) {
            a.image(image, 0, 0, getRawBounds().width(), getRawBounds().height());
        } else if (color != null){
            float tR = tintColor >> 16 & 0xFF;
            float tG = tintColor >> 8 & 0xFF;
            float tB = tintColor & 0xFF;

            float cR = color >> 16 & 0xFF;
            float cG = color >> 8 & 0xFF;
            float cB = color & 0xFF;

            int newColor = a.color((tR * cR) / 255, (tG * cG) / 255, (tB * cB) / 255);
            a.fill(newColor);
            a.rect(0, 0, getRawBounds().width(), getRawBounds().height(), roundedness);
        } else {
            System.out.println("YEET BUTTON NO DISPLAY");
        }
        a.textAlign(a.CENTER, a.CENTER);
        a.textSize(textSize);
        a.fill(0);
        a.text(text, getRawBounds().width()/2f, getRawBounds().height()/2f + yOffset);
        a.tint(a.color(255, 255, 255));

        a.popMatrix();
    }

    @Override
    public GameObject getParent() {
        return null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public String getId() {
        return id;
    }
}
