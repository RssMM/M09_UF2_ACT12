package app.objectes;

import processing.core.PGraphics;
import processing.core.PVector;

import java.awt.*;

public class Triangle extends ObjecteGrafic {
    private int[] p1;
    private int[] p2;

    public Triangle(int x, int y, int[] p1, int[] p2, Color color) {
        super(x, y, color);
        this.p1 = p1;
        this.p2 = p2;
    }

    public void pinta(PGraphics g) {
        super.pinta(g);
        g.triangle(x, y, x + p1[0], y + p1[1], x + p2[0], y + p2[1]);
    }

    @Override
    public Caixa getCaixa() {
        int minX = Math.min(x, Math.min(x + p1[0], x + p2[0]));
        int maxX = Math.max(x, Math.max(x + p1[0], x + p2[0]));
        int minY = Math.min(y, Math.min(y + p1[1], y + p2[1]));
        int maxY = Math.max(y, Math.max(y + p1[1], y + p2[1]));
        return new Caixa(new PVector(minX, minY), new PVector(maxX, maxY));
    }
}