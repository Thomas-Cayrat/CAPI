package fr.icom.info.m1.balleauprisonnier_mvn;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Projectile {
    int speed;
    int direction;
    int x;
    int y;
    Image img;
    ImageView imgV;


    Projectile(int s, int dir, int a, int b){
        img = new Image("assets/ball.png");
        this.speed = s;
        this.direction = dir;
        this.x = a;
        this.y = b;
        imgV.setImage(img);
    }

    public int getX(){
        return x;
    }

}
