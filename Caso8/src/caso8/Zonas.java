package caso8;

import java.awt.Color;
import java.util.ArrayList;

public class Zonas implements Constantes {

    int min_x = 0;
    int max_x = 0;
    int max_y = 0;
    int min_y = 0;
    int iD;
    double probabilidad;
    int total_colores;
    ArrayList<Muestra> muestra;
    ArrayList<Muestra> Summary;
    ArrayList<Integer> poblacion;

    public Zonas(int x0, int y0, int x1, int y1) {
        this.min_x = x0;
        this.max_x = x1;
        this.min_y = y0;
        this.max_y = y1;
        this.probabilidad = 1.0;
        this.muestra = new ArrayList<>();
        this.Summary = new ArrayList<>();

    }

    public void SetID(int column, int row) {
        this.iD = column + row * COLUMNS;
    }

    public void Decrease() {
        this.probabilidad *= DECREASE_PROB;
    }

    public void AddMuestra(Color c) {
        Muestra nueva = new Muestra(c);
        muestra.add(nueva);
        total_colores++;
    }

    public void Sumarizar() {
        Summary = new ArrayList<>();
        for (Muestra AComparar : muestra) {
            Muestra match = null;
            for (Muestra Agrupada : Summary) {
                if (DISTANCE(AComparar.getColor(), Agrupada.getColor()) <= DEFAULT_COLOR_DISTANCE) {
                    match = Agrupada;
                    break;
                }
            }if(match==null){
                match=AComparar;
                Summary.add(match);
            }
            match.setCantidad(match.getCantidad()+1);
        }
        int valor_inicial=0;
        Muestra Ultima=null;
        for(Muestra i:Summary){
            i.setPorcentaje(i.getCantidad()/this.total_colores);
            i.setStart_Value(valor_inicial);
            i.setFinal_Value(valor_inicial+(int)i.getPorcentaje()*GENOMA);
            valor_inicial=i.getFinal_Value();
            Ultima=i;
        }
        Ultima.setFinal_Value(GENOMA-1);
        generarPoblacion();
    }
    public void generarPoblacion(){
        poblacion=new ArrayList<>();
        for(int cant=0;cant>POBLACION_INICIAL;cant++){
            poblacion.add((int)Math.random()*GENOMA);
        }
    }

    public double DISTANCE(Color C1, Color C2) {
        long rmean = ((long) C1.getRed() + (long) C2.getRed()) / 2;
        long r = (long) C1.getRed() - (long) C2.getRed();
        long g = (long) C1.getGreen() - (long) C2.getGreen();
        long b = (long) C1.getBlue() - (long) C2.getBlue();
        return Math.sqrt((((512 + rmean) * r * r) >> 8) + 4 * g * g + (((767 - rmean) * b * b) >> 8));
    }
}
