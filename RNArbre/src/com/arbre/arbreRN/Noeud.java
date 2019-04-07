package com.arbre.arbreRN;

import javafx.scene.paint.Color;

public class Noeud {

    Color couleur;
    Data info;
    Noeud gauche, droit, parent;
    private int dessinX, dessinY;

    Noeud(Data o) {
        couleur = Color.BLACK;
        info = o;
        gauche = droit = parent = null;
        dessinX = dessinY = -1;
    }

    Noeud(Data o, Color c, Noeud g, Noeud d, Noeud p) {
        couleur = c;
        info = o; 
        gauche = g;
        droit = d;
        parent = p;
    }

    public int getNGauche() {
        return 1 + getNbrFils(this.getDroit());
    }

    public int getNDroit() {
        return 1 + getNbrFils(this.getGauche());
    }

    private int getNbrFils(Noeud n) {
        if (n.isSentinelle()) {
            return 0;
        }
        return 1 + getNbrFils(n.getGauche()) + getNbrFils(n.getDroit());
    }

    public Color getCouleur() {
        return couleur;
    }

    public Data getInfo() {
        return info;
    }

    public Noeud getGauche() {
        return gauche;
    }

    public Noeud getDroit() {
        return droit;
    }

    public Noeud getParent() {
        return parent;
    }

    public boolean isSentinelle() {
        return this == ArbreRougeNoir.sentinelle;
    }

    public int getHauteur() {
        if (isSentinelle()) {
            return 0;
        } else {
            return 1 + Math.max(gauche.getHauteur(), droit.getHauteur());
        }
    }

    public int getDessinX() {
        return dessinX;
    }

    public int getDessinY() {
        return dessinY;
    }

    public void setDessinX(int dessinX) {
        this.dessinX = dessinX;
    }

    public void setDessinY(int dessinY) {
        this.dessinY = dessinY;
    }

}
