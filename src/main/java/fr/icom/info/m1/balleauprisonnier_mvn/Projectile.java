package fr.icom.info.m1.balleauprisonnier_mvn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Projectile {
    int speed;
    int direction;
    int x;
    int y;
    Image img;

    ImageView imgView;

    Projectile(int s, int dir, int a, int b){
        Image img = new Image("assets/ball.png");
        this.speed = s;
        this.direction = dir;
        this.x = a;
        this.y = b;

    }

    public int getX(){
        return x;
    }
}
