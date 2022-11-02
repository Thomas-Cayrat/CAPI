package fr.icom.info.m1.balleauprisonnier_mvn;

public class Projectile {
    int speed;
    int direction;
    int x;
    int y;
    public Projectile(int s, int dir, int a, int b){
        this.speed = s;
        this.direction = dir;
        this.x = a;
        this.y = b;
    }
}
