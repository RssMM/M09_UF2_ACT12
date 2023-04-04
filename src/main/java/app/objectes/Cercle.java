package app.objectes;

import processing.core.PGraphics;
import processing.core.PVector;

import java.awt.*;

public class Cercle extends ObjecteGrafic {
    int radi;

    public Cercle(int x, int y, int radi, Color color) {
        super(x, y, color);
        this.radi = radi;
    }

    public void pinta(PGraphics g) {
        super.pinta(g);
        g.ellipse(x,y,radi*2, radi*2);
    }

    @Override
    public Caixa getCaixa() {
        return new Caixa(new PVector(x - radi, y - radi), new PVector(x + radi, y + radi));
    }
}
