package fr.icom.info.m1.balleauprisonnier_mvn;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class Projectile {
    int speed;
    double direction;
    double directionRad = Math.toRadians(direction);
    double x;
    double y;
    Image img;

    ImageView BallDirection;

    GraphicsContext graphicsContext;

    Projectile(GraphicsContext gc, int s, double dir, double a, double b){
        this.img = new Image("assets/ball.png"); //crée image
        this.speed = s;
        this.directionRad = dir;
        graphicsContext = gc;
        this.x = a;
        this.y = b;


        BallDirection = new ImageView();
        BallDirection.setImage(img);
        BallDirection.setFitWidth(5);
        BallDirection.setPreserveRatio(true);
        BallDirection.setSmooth(true);
        BallDirection.setCache(true);

    }


    public double getX(){
        return x;
    }

    public void display()
    {
        graphicsContext.save(); // saves the current state on stack, including the current transform
        graphicsContext.drawImage(img, this.x, this.y);
        graphicsContext.restore(); // back to original state (before rotation)
    }

    public void update(){

        this.x += Math.cos(this.directionRad) * this.speed;
        this.y += Math.sin(this.directionRad) * this.speed;
    }
}

