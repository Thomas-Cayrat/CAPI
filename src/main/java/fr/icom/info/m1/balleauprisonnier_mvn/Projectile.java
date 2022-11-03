package fr.icom.info.m1.balleauprisonnier_mvn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Projectile {
    int speed;
    int direction;
    int x;
    int y;
    Image img;

    Projectile(int s, int dir, int a, int b){
        Image img = new Image("assets/ball.png");
        this.speed = s;
        this.direction = dir;
        this.x = a;
        this.y = b;
        //Creating the image view
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(img);
        //Setting the image view parameters
        imageView.setX(100);
        imageView.setY(100);
        imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);
    }
}
