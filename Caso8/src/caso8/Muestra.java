package caso8;

import java.awt.Color;

public class Muestra {

    private double porcentaje;
    private Color color;
    private int cantidad;
    private static int total;
    private int Start_Value;
    private int Final_Value;

    public double getPorcentaje() {
        return porcentaje;
    }

    public Muestra(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public static int getTotal() {
        return total;
    }

    public static void setTotal(int total) {
        Muestra.total = total;
    }

    public int getStart_Value() {
        return Start_Value;
    }

    public void setStart_Value(int Start_Value) {
        this.Start_Value = Start_Value;
    }

    public int getFinal_Value() {
        return Final_Value;
    }

    public void setFinal_Value(int Final_Value) {
        this.Final_Value = Final_Value;
    }

    public void setPorcentage(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
