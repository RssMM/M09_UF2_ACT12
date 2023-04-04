package app;

import app.objectes.Animacio;
import app.objectes.Cercle;
import app.objectes.Ellipse;
import app.objectes.Triangle;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.awt.*;

public class App extends PApplet {
    Cercle cercle;
    Triangle triangle;
    Ellipse ellipse;

    Animacio animacio;
    private boolean colisionDetectada = false;
    private boolean colisionEnCurso = false;
    public  static int width = 400;
    public static int height = 400;

    public void settings() {
        size(width, height);
    }

    public void setup() {

        cercle = new Cercle(300, 300, 25, Color.gray);
        triangle = new Triangle(100, 100, new int[]{40, 40}, new int[]{40, 15}, Color.BLUE);
        ellipse = new Ellipse(370, 150, 40, 20, Color.getHSBColor(100,100,100));
        animacio = new Animacio(400/2, 400, Color.WHITE);
        animacio.setup(this, "../assets/Skeleton", ".png");

        cercle.setVelocitat(1, 2);
        ellipse.setVelocitat(-1, -3);
        triangle.setVelocitat(2, -1);
        animacio.setVelocitat(1, -1);
    }

    public void draw() {
        PGraphics g = getGraphics();
        background(46,46,46);

        cercle.mou();
        triangle.mou();
        ellipse.mou();
        animacio.mou();


        boolean colisionActual = animacio.choca(cercle.getCaixa()) || animacio.choca(triangle.getCaixa()) || animacio.choca(ellipse.getCaixa());



        if (colisionEnCurso && !colisionActual) {
            animacio.canviarAnimacio();
            colisionDetectada = false;
            animacio.setVelocitat(1,-1);

        }
        colisionEnCurso = colisionActual;



        if (!colisionDetectada && (animacio.choca(cercle.getCaixa()) || animacio.choca(triangle.getCaixa()) || animacio.choca(ellipse.getCaixa()))) {
            animacio.canviarAnimacio();
            colisionDetectada = true;

        }



        cercle.pinta(g);
        triangle.pinta(g);
        ellipse.pinta(g);
        animacio.pinta(g);
    }

    public void keyPressed() {
        if (key == ' ') {
            colisionDetectada = false;
            animacio.canviarAnimacio();
        } else if (keyCode == UP) {
            animacio.setVelocitat(animacio.vX, animacio.vY - 1);
        } else if (keyCode == DOWN) {
            animacio.setVelocitat(animacio.vX, animacio.vY + 1);
        } else if (keyCode == LEFT) {
            animacio.setVelocitat(animacio.vX - 1, animacio.vY);
        } else if (keyCode == RIGHT) {
            animacio.setVelocitat(animacio.vX + 1, animacio.vY);
        }
    }
    public static void main(String[] args) {
        PApplet.main("app.App");
    }

}