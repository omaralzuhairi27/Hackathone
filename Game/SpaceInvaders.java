package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class SpaceInvaders extends Application {


    double timer = 0;
    private Pane root = new Pane();
    private Playes player = new Playes(300, 750, 40, 40, "player", Color.BLUE);

    public static void main(String[] args) {
        launch(args);
    }

    private Parent createContent() {
        root.setPrefSize(600, 800);
        root.getChildren().add(player);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                update();
            }

        };
        timer.start();
        nextLevel1();

        return root;
    }

    private void nextLevel1() {
        IntStream.range(1, 5)
                .forEach(e -> root.getChildren().add(new Playes(50 + e * 100, 150, 30, 30, "enemy", Color.RED)));
    }

    private List <Playes> sprites() {
        return root.getChildren().stream().map(e -> (Playes) e).collect(Collectors.toList());
    }

    private void update() {
        timer += 0.016;
        sprites().forEach(s -> {
            switch (s.type) {
                case "enemybullet":
                    s.moveDown();
                    if (s.getBoundsInParent().intersects(player.getBoundsInParent())) {
                        player.dead = true;
                        s.dead = true;
                    }
                    break;
                case "playerbullet":
                    s.moveUp();
                    sprites().stream().filter(e -> e.type.equals("enemy")).forEach(enemy -> {
                        if (s.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                            enemy.dead = true;
                            s.dead = true;
                        }
                    });
                    break;
                case "enemy":
                    if (timer > 2) {
                        if (Math.random() < 0.3) {
                            shoot(s);
                        }
                    }
                    break;

            }
        });
        root.getChildren().removeIf(n -> {
            Playes s = (Playes) n;
            return s.dead;
        });
        if (timer > 2) {
            timer = 0;
        }

    }

    private void shoot(Playes who) {
        Playes s = new Playes((int) who.getTranslateX() + 20, (int) who.getTranslateY(), 5, 20, who.type + "bullet", Color.BLACK);
        root.getChildren().add(s);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene getScene() {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    player.moveLeft();
                    break;
                case D:
                    player.moveRight();
                    break;
                case SPACE:
                    shoot(player);
                    break;
                case W:
                    player.moveUp();
                    break;
                case S:
                    player.moveDown();
                    break;
            }
        });
        return scene;
    }


}
