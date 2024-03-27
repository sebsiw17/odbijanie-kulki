package org.example.odbijaniekulki;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;

public class odbijanieKulki extends Application {

        private static final int SCENE_WIDTH = 800;
        private static final int SCENE_HEIGHT = 600;
        private static final int BALL_RADIUS = 20;
        private static final double BALL_SPEED = 0.5;

        private double ballX;
        private double ballY;
        private double deltaX;
        private double deltaY;

        private Circle ball;

        @Override
        public void start(Stage primaryStage) {
            Pane root = new Pane();
            Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Animacja odbijania kulki");

            // Inicjalizacja kulki
            initBall();

            // Dodanie kulki do sceny
            root.getChildren().add(ball);

            // Obsługa animacji kulki
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    // Aktualizacja pozycji kulki
                    ballX += deltaX;
                    ballY += deltaY;

                    // Sprawdzenie kolizji z krawędziami sceny
                    if (ballX - BALL_RADIUS <= 0 || ballX + BALL_RADIUS >= SCENE_WIDTH) {
                        deltaX *= -1; // Odbicie kulki od lewej/prawej krawędzi sceny
                    }
                    if (ballY - BALL_RADIUS <= 0 || ballY + BALL_RADIUS >= SCENE_HEIGHT) {
                        deltaY *= -1; // Odbicie kulki od górnej/dolnej krawędzi sceny
                    }

                    // Aktualizacja pozycji kulki na scenie
                    ball.setCenterX(ballX);
                    ball.setCenterY(ballY);
                }
            };
            timer.start();

            primaryStage.show();
        }

        // Inicjalizacja kulki
        private void initBall() {
            ballX = Math.random() * (SCENE_WIDTH - 2 * BALL_RADIUS) + BALL_RADIUS;
            ballY = Math.random() * (SCENE_HEIGHT - 2 * BALL_RADIUS) + BALL_RADIUS;

            // Wygenerowanie losowego kierunku dla kulki
            Random random = new Random();
            double angle = random.nextDouble() * 2 * Math.PI;
            deltaX = BALL_SPEED * Math.cos(angle);
            deltaY = BALL_SPEED * Math.sin(angle);

            ball = new Circle(ballX, ballY, BALL_RADIUS, Color.PURPLE);
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

