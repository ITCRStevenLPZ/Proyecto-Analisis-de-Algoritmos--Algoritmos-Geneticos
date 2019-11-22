/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caso8;

import java.awt.Color;

/**
 *
 * @author lolil
 */
public class Coordenadas implements Constantes {

    public class Bounds implements Constantes {

        int a;
        int b;

        public Bounds(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }
    int x;
    int y;
    Bounds x1;
    Bounds x2;
    Bounds y1;
    Bounds y2;
    Color color;

    public Coordenadas(Color color, int x, int y) {
        this.x = x;
        this.y = y;
        this.x1 = new Bounds(x, y);
        this.color = color;
    }

    public Bounds getX1() {
        return x1;
    }

    public Bounds getY1() {
        return y1;
    }

    public void setY1() {
        this.y1 = new Bounds(x,y+POLIGON_SIZE);
    }

    public Bounds getX2() {
        return x2;
    }

    public void setX2() {
        this.x2 = new Bounds(x+POLIGON_SIZE,y+POLIGON_SIZE );
    }

    public Bounds getY2() {
        return y2;
    }

    public void setY2() {
        this.y2 = new Bounds(x+POLIGON_SIZE,y);
    }

    public Color getColor() {
        return color;
    }

}
