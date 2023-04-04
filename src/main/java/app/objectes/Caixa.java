package app.objectes;

import processing.core.PVector;

// Clase Caixa
public class Caixa {
    private PVector daltEsquerra;
    private PVector baixDreta;

    public Caixa(PVector daltEsquerra, PVector baixDreta) {
        this.daltEsquerra = daltEsquerra;
        this.baixDreta = baixDreta;
    }

    public PVector getDaltEsquerra() {
        return daltEsquerra;
    }

    public void setDaltEsquerra(PVector daltEsquerra) {
        this.daltEsquerra = daltEsquerra;
    }

    public PVector getBaixDreta() {
        return baixDreta;
    }

    public void setBaixDreta(PVector baixDreta) {
        this.baixDreta = baixDreta;
    }

    public void desplacament(float x, float y) {
        daltEsquerra.add(x, y);
        baixDreta.add(x, y);
    }
}


