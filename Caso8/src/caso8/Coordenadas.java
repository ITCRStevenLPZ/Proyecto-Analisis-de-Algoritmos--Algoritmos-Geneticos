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

        public Bounds() {

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
    Bounds x1 = new Bounds();
    Bounds x2 = new Bounds();
    Bounds y1 = new Bounds();
    Bounds y2 = new Bounds();
    Color color;

    public Coordenadas(Color color, int x, int y) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Bounds getX1() {
        return x1;
    }

    public void setX1(Bounds x1) {
        this.x1 = x1;
    }

    public Bounds getY1() {
        return y1;
    }

    public void setY1(Bounds y1) {
        this.y1 = y1;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
