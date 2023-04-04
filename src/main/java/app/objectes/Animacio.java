package app.objectes;


import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;


public class Animacio extends ObjecteGrafic {
    private ArrayList<PImage> fotogramesNormals;
    private ArrayList<PImage> fotogramesFinals;
    public int frameActual;
    public boolean animacioFinal;
    private int frameRate;
    private int temps;

    private int size = 0;


    PImage spriteSheet;

    public Animacio(int x, int y, Color color) {
        super(x, y, color);
        fotogramesNormals = new ArrayList<>();
        fotogramesFinals = new ArrayList<>();
        frameActual = 0;
        animacioFinal = true;
        frameRate = 15;
        temps = 0;
    }

    public void setup(PApplet pApplet, String prefix, String sufix) {
        int NFrames = 15;
        for (int i = 0; i < NFrames; i++) {


            if (i < NFrames) {
                spriteSheet = pApplet.loadImage(prefix + "Dead" + sufix);
                PImage imgNormal = getFrame(i, pApplet, 33, 32);
                fotogramesNormals.add(imgNormal);
                size = fotogramesNormals.size();
            }
        }
        NFrames = 13;
        for (int i = 0; i < NFrames; i++) {
            if(i < NFrames){
                spriteSheet = pApplet.loadImage(prefix + "Walk" + sufix);
                PImage imgFinal = getFrame(i, pApplet, 22, 32);
                fotogramesFinals.add(imgFinal);
                size = fotogramesFinals.size();
            }
        }
    }

    @Override
    public void pinta(PGraphics g) {
        super.pinta(g);
        if (animacioFinal) {
            g.image(fotogramesFinals.get(frameActual), x - 22/2, y - 33);
        } else {
            g.image(fotogramesNormals.get(frameActual), x - 33/2, y - 33);
        }
    }
    boolean oneTime = true;
    @Override
    public void mou() {
        super.mou();

        temps++;
        if(animacioFinal){
            if (temps % (60 / frameRate) == 0) {
                frameActual = (frameActual + 1) % size;
                oneTime = true;
            }
        }else{

            if (temps % (60 / frameRate) == 0  && oneTime) {
                frameActual = (frameActual + 1) % size;
                if(frameActual == 12 ) {
                    oneTime = false;
                }
            }
        }

    }

    public void canviarAnimacio() {
        animacioFinal = !animacioFinal;
    }


    public PImage getFrame(int col, PApplet pApplet, int w, int h) {
        PImage frame = pApplet.createImage(w, h, pApplet.ARGB);
        frame.copy(spriteSheet, col * w, 0, w, h, 0, 0, w, h);
        return frame;
    }
    @Override
    public Caixa getCaixa() {
        PImage currentFrame;
        if (animacioFinal) {
            currentFrame = fotogramesFinals.get(frameActual);
        } else {
            currentFrame = fotogramesNormals.get(frameActual);
            setVelocitat(0,0);
        }
        int width = currentFrame.width;
        int height = currentFrame.height;

        return new Caixa(new PVector(x - width / 2, y - height), new PVector(x + width / 2, y));
    }

}
