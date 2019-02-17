package sample;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Playes extends Rectangle {
    final String type;
    boolean dead = false;

    Playes(int width, int hight, int rectangleWidth, int rectangleHight, String type, Color color) {
        super(rectangleWidth, rectangleHight, color);
        this.type = type;
        setTranslateX(width);
        setTranslateY(hight);
    }

    void moveLeft() {
        setTranslateX(getTranslateX() - 5);
    }

    void moveRight() {
        setTranslateX(getTranslateX() + 5);
    }

    void moveUp() {
        setTranslateY(getTranslateY() - 5);
    }

    void moveDown() {
        setTranslateY(getTranslateY() + 5);
    }

}
