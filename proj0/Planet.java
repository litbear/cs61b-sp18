import java.util.Arrays;

public class Planet {

    private static final String IMAGE_PATH = "./images/";

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11; 

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return this.mass * p.mass * Planet.G  / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        return this.calcForceExertedBy(p) * dx / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        return this.calcForceExertedBy(p) * dy / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet [] pList) {
        return Arrays
            .stream(pList)
            .filter(e -> this.equals(e) == false)
            .map(e -> this.calcForceExertedByX(e))
            .mapToDouble(Double::doubleValue)
            .sum();
    }

    public double calcNetForceExertedByY(Planet [] pList) {
        return Arrays
            .stream(pList)
            .filter(e -> this.equals(e) == false)
            .map(e -> this.calcForceExertedByY(e))
            .mapToDouble(Double::doubleValue)
            .sum();
    }

    public void update(double dt, double fx, double fy) {
        // a = f / m
        double ax = fx / this.mass;
        double ay = fy / this.mass;

        // v1 = v0 + dt * a 
        double vx = this.xxVel + dt * ax;
        double vy = this.yyVel + dt * ay;

        // p1 = p0 + dt * v1
        double px = this.xxPos + dt * vx;
        double py = this.yyPos + dt * vy;

        // make it more clear
        this.xxPos = px;
        this.yyPos = py;
        this.xxVel = vx;
        this.yyVel = vy;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, IMAGE_PATH + this.imgFileName);
    }
}
