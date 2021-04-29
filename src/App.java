
import javafx.scene.Group;
import javafx.scene.Node;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

class Position {
    Double x;
    Double y;

    Position(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Double getX() {
        return x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getY() {
        return y;
    }
}

class Bricks extends Group {
    HashMap<Position, Integer> cord = new HashMap<>();
    ArrayList<Rectangle> bricksArrayList;

    public void Add(Rectangle rect) {
        getChildren().add(rect);
        bricksArrayList.add(rect);
    }
}

public class App extends Application {
    static ServerSocket first;
    static Socket sec;
    static DataInputStream in;
    static DataOutputStream out;

    Group steel = new Group();
    char ss;
    Group texture = new Group();
    Bricks bricks = new Bricks();
    Group water = new Group();
    Group trees = new Group();
    static Image b = new Image(
            "bullet-filled-outline-icon-line-vector-sign-linear-colorful-pictogram-isolated-white-ammunition-symbol-logo-illustration-pixel-109715663.jpg");
    // static ImageView bullet = new ImageView(b);
    Image img = new Image("copy_528718155.png");

    int dir = 0;
    boolean coold = true;
    Timeline timeline = new Timeline();
    // Image a = new Image("botsTank.png");
    // ImageView Tank = new ImageView(a);
    Image b1 = new Image(
            "bullet-filled-outline-icon-line-vector-sign-linear-colorful-pictogram-isolated-white-ammunition-symbol-logo-illustration-pixel-109715663.jpg");
    // ImageView Bullet = new ImageView(b);
    Circle Bullet = new Circle(3, Color.RED);
    Circle bullet = new Circle(3, Color.RED);
    Stage new1 = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        ImageView view = new ImageView(img);
        File file = new File("map.txt");

        Image stellimage = new Image("steel.png");
        Image brickimage = new Image("bricks.png");
        Image treeimage = new Image("trees.png");
        Image waterimage = new Image("water.png");
        primaryStage.setTitle("TankGame");
        // Tank.setFitWidth(100);
        // Tank.setFitHeight(100);
        view.setFitWidth(100);
        view.setFitHeight(100);
        bullet.setCenterX(-10);
        // bullet.setStyle("-fx-background-color: transparent");
        view.setStyle("-fx-background-color: transparent");
        Group root = new Group(view, bullet, steel, bricks, water, texture, Bullet, trees);

        // bullet.setFitHeight(5);
        // bullet.setFitWidth(5);
        // bullet.setVisible(false);
        // Bullet.setFitHeight(5);
        // Bullet.setFitWidth(5);
        // Bullet.setVisible(false);
        // bullet.setViewOrder(1);

        // try {
        // first = new ServerSocket(8004);
        // sec = first.accept();
        // in = new DataInputStream(sec.getInputStream());
        // out = new DataOutputStream(sec.getOutputStream());
        // System.out.println("connected");

        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // new Thread(() -> {
        // while (true) {
        // try {
        // Server r = new Server();
        // System.out.println(in.readUTF());
        // r.start(primaryStage);
        // } catch (Exception e) {
        // }
        // }
        // }).start();
        Scanner scan = new Scanner(file);
        int size = scan.nextInt();
        Scene scene = new Scene(root, size * 64, size * 64, Color.BLACK);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ss = scan.next().charAt(0);
                if (ss == 'S') {
                    Rectangle rect = new Rectangle(64, 64, new ImagePattern(stellimage));
                    rect.setX(j * 64);
                    rect.setY(i * 64);
                    steel.getChildren().add(rect);
                }
                if (ss == 'B') {
                    Rectangle rect = new Rectangle(64, 64, new ImagePattern(brickimage));
                    rect.setX(j * 64);
                    rect.setY(i * 64);
                    // texture.getChildren().add(rect);
                    bricks.getChildren().add(rect);

                }
                if (ss == 'W') {
                    Rectangle rect = new Rectangle(64, 64, new ImagePattern(waterimage));
                    rect.setX(j * 64);
                    rect.setY(i * 64);
                    water.getChildren().add(rect);

                }
                if (ss == 'T') {
                    Rectangle rect = new Rectangle(64, 64, new ImagePattern(treeimage));
                    rect.setX(j * 64);
                    rect.setY(i * 64);
                    trees.getChildren().add(rect);
                }
                if (ss == 'P') {
                    view.setX(j * 64 + 11);
                    view.setY(i * 64 + 11);
                }
                // if (ss == 'X') {
                // Tank.setX(j * 64 + 11);
                // Tank.setY(i * 64 + 11);
                // }
            }
        }
        scan.close();
        new Thread(() -> {
            while (true) {

                if (steel.contains(bullet.getCenterX(), bullet.getCenterY())) {
                    bullet.setCenterX(-10);
                } else if (bricks.contains(bullet.getCenterX(), bullet.getCenterY())) {
                    bullet.setCenterX(-10);
                    System.out.println(12);
                }

                try {
                    Thread.sleep(20);
                } catch (Exception e) {

                }
            }
        }).start();
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A) {
                if (view.getX() - 0 >= -20)
                    if (bricks.contains(view.getX() + 10, view.getY() + 55)
                            || water.contains(view.getX() + 10, view.getY() + 55)
                            || steel.contains(view.getX() + 10, view.getY() + 55)) {
                        view.setLayoutX(view.getLayoutX());

                    } else {
                        view.setX(view.getX() - 15);
                        bullet.setVisible(false);
                        view.setRotate(-90);
                    }
            }
            if (event.getCode() == KeyCode.D) {
                if (view.getX() + 5 <= 425)
                    if (bricks.contains(view.getX() + 70, view.getY() + 55)
                            || water.contains(view.getX() + 70, view.getY() + 55)
                            || steel.contains(view.getX() + 70, view.getY() + 55)) {
                        view.setLayoutX(view.getLayoutX());

                    }

                    else {
                        view.setX(view.getX() + 15);
                        bullet.setVisible(false);
                        view.setRotate(90);
                    }
            }
            if (event.getCode() == KeyCode.W) {
                if (view.getY() - 0 >= -20)
                    if (bricks.contains(view.getX() + 42, view.getY() + 20)
                            || water.contains(view.getX() + 42, view.getY() + 20)
                            || steel.contains(view.getX() + 42, view.getY() + 20)) {

                        view.setLayoutY(view.getLayoutY());

                    } else {
                        view.setY(view.getY() - 15);
                        bullet.setVisible(false);
                        view.setRotate(0);
                    }
            }

            if (event.getCode() == KeyCode.S) {
                if (view.getY() + 5 <= 430)
                    if (bricks.contains(view.getX() + 42, view.getY() + 80)
                            || water.contains(view.getX() + 42, view.getY() + 80)
                            || steel.contains(view.getX() + 42, view.getY() + 80)) {
                        view.setLayoutY(view.getLayoutY());

                    }

                    else {
                        view.setY(view.getY() + 15);
                        bullet.setVisible(false);
                        view.setRotate(180);
                    }
            }

            if (event.getCode() == KeyCode.SPACE && coold) {
                coold = false;

                bullet.setCenterX(view.getX() + 48);
                bullet.setCenterY(view.getY() + 48);
                timeline.setCycleCount(100);

                dir = (int) view.getRotate();

                KeyFrame a = new KeyFrame(Duration.seconds(0.016), (ActionEvent actionEvent) -> {

                    bullet.setRotate(dir);
                    if (dir == 90) {
                        bullet.setCenterX(bullet.getCenterX() + 15);
                        bullet.setVisible(true);
                    }
                    if (dir == -90) {
                        bullet.setCenterX(bullet.getCenterX() - 15);
                        bullet.setVisible(true);

                    }
                    if (dir == 0) {
                        bullet.setCenterY(bullet.getCenterY() - 15);
                        bullet.setVisible(true);
                    }
                    if (dir == 180) {
                        bullet.setCenterY(bullet.getCenterY() + 15);
                        bullet.setVisible(true);

                    }

                });
                timeline.getKeyFrames().add(a);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        timeline.getKeyFrames().clear();
                        coold = true;
                    }
                }, 1700);

                timeline.play();

            }
        });

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

// class Server extends App {
// public void start(Stage primaryStage) throws IOException {
// System.out.println(in.readUTF());
// new Thread(() -> {
// try {
// if (in.readUTF().equals("A")) {
// if (Tank.getX() - 0 >= -20)
// if (bricks.contains(Tank.getX() + 10, Tank.getY() + 55)
// || water.contains(Tank.getX() + 10, Tank.getY() + 55)
// || steel.contains(Tank.getX() + 10, Tank.getY() + 55)) {
// Tank.setLayoutX(Tank.getLayoutX());
// System.out.println("Break");
// } else {
// Tank.setX(Tank.getX() - 15);
// Bullet.setVisible(false);
// Tank.setRotate(-90);
// }
// }
// } catch (IOException e) {

// e.printStackTrace();
// }
// try {
// if (in.readUTF().equals("D")) {
// if (Tank.getX() + 5 <= 425)
// if (bricks.contains(Tank.getX() + 70, Tank.getY() + 55)
// || water.contains(Tank.getX() + 70, Tank.getY() + 55)
// || steel.contains(Tank.getX() + 70, Tank.getY() + 55)) {
// Tank.setLayoutX(Tank.getLayoutX());
// System.out.println("Break");
// }

// else {
// Tank.setX(Tank.getX() + 15);
// Bullet.setVisible(false);
// Tank.setRotate(90);
// }
// }
// } catch (IOException e) {

// e.printStackTrace();
// }
// try {
// if (in.readUTF().equals("W")) {
// if (Tank.getY() - 0 >= -20)
// if (bricks.contains(Tank.getX() + 42, Tank.getY() + 20)
// || water.contains(Tank.getX() + 42, Tank.getY() + 20)
// || steel.contains(Tank.getX() + 42, Tank.getY() + 20)) {

// Tank.setLayoutY(Tank.getLayoutY());
// System.out.println("Break");
// } else {
// Tank.setY(Tank.getY() - 15);
// Bullet.setVisible(false);
// Tank.setRotate(0);
// }
// }
// } catch (IOException e) {

// e.printStackTrace();
// }

// try {
// if (in.readUTF().equals("S")) {
// if (Tank.getY() + 5 <= 430)
// if (bricks.contains(Tank.getX() + 42, Tank.getY() + 80)
// || water.contains(Tank.getX() + 42, Tank.getY() + 80)
// || steel.contains(Tank.getX() + 42, Tank.getY() + 80)) {
// Tank.setLayoutY(Tank.getLayoutY());
// System.out.println("Break");
// }

// else {
// Tank.setY(Tank.getY() + 15);
// Bullet.setVisible(false);
// Tank.setRotate(180);
// }
// }
// } catch (IOException e) {
// e.printStackTrace();
// }

// try {
// if (in.readUTF().equals("SPACE") && coold == true) {
// coold = false;

// Bullet.setX(Tank.getX() + 44);
// Bullet.setY(Tank.getY() + 41);
// timeline.setCycleCount(100);

// dir = (int) Tank.getRotate();

// KeyFrame a = new KeyFrame(Duration.seconds(0.016), (ActionEvent actionEvent)
// -> {

// Bullet.setRotate(dir);
// if (dir == 90) {
// Bullet.setX(Bullet.getX() + 15);
// Bullet.setVisible(true);
// }
// if (dir == -90) {
// Bullet.setX(Bullet.getX() - 15);
// Bullet.setVisible(true);

// }
// if (dir == 0) {
// Bullet.setY(Bullet.getY() - 15);
// Bullet.setVisible(true);
// }
// if (dir == 180) {
// Bullet.setY(Bullet.getY() + 15);
// Bullet.setVisible(true);

// }

// });
// timeline.getKeyFrames().add(a);
// Timer timer = new Timer();
// timer.schedule(new TimerTask() {
// @Override
// public void run() {
// timeline.getKeyFrames().clear();
// coold = true;
// }
// }, 1600);

// timeline.play();
// }
// } catch (IOException e) {

// e.printStackTrace();
// }

// }).start();
// }

// }