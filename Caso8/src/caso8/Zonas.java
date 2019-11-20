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
    private ArrayList<Muestra> MuestrasReales;
    private ArrayList<Muestra> SummaryTarget;
    private ArrayList<Integer> population;
    private ArrayList<Muestra> muestrasActual;
    private ArrayList<Muestra> summaryActual;

    public Zonas(int x0, int y0, int x1, int y1) {
        this.min_x = x0;
        this.max_x = x1;
        this.min_y = y0;
        this.max_y = y1;
        this.probabilidad = 1.0;
        this.MuestrasReales = new ArrayList<>();
        this.SummaryTarget = new ArrayList<>();

    }

    public int getFila() {
        return iD / COLUMNS;
    }

    public int getColuma() {
        return iD - getFila() * COLUMNS;
    }

    public void SetID(int column, int row) {
        this.iD = column + row * COLUMNS;
    }

    public void Decrease() {
        this.probabilidad -= DECREASE_PROB;
    }

    public void AddMuestra(Color color) {
        Muestra prueba = new Muestra(color);
        MuestrasReales.add(prueba);
        total_colores++;
    }

    public double DISTANCE(Color C1, Color C2) { //algoritmo que me retorna la distancia que hay entre dos colores
        long rmean = ((long) C1.getRed() + (long) C2.getRed()) / 2;
        long r = (long) C1.getRed() - (long) C2.getRed();
        long g = (long) C1.getGreen() - (long) C2.getGreen();
        long b = (long) C1.getBlue() - (long) C2.getBlue();
        return Math.sqrt((((512 + rmean) * r * r) >> 8) + 4 * g * g + (((767 - rmean) * b * b) >> 8));
    }

    public void Sumarizar() {
        Sumarizar(MuestrasReales, SummaryTarget);
    }

    public void Sumarizar(ArrayList<Muestra> pMuestras, ArrayList<Muestra> pSummary) {
        for (Muestra muestraAComparar : pMuestras) {//este for each itera todos los elementos de la ArrayList pMuestras
            Muestra match = null;
            for (Muestra muestraAgrupada : pSummary) {//compara un elemento de la ArrayList pMuestras con los que hay en pSummary para asi comparar la distancia y clasificar los colores con ditancias mas cortas                
                if (DISTANCE(muestraAComparar.getColor(), muestraAgrupada.getColor()) <= DEFAULT_COLOR_DISTANCE) { //DEFAULT_COLOR_DISTANCE es la medida maxima aceptada, una Distancia menor o igual a este sera aceptado                  
                    match = muestraAgrupada;//quiere decir que el color tiene una distancia aceptable y este va a pertenecer a este color y asi se van a poder filtrar en colores dominantes dentro de la imagen
                    break;
                }
            }
            if (match == null) {//si la distancia es mas grande quiere decir que es un nuevo color a a filtrar y es agregado a la lista pSummary 
                match = muestraAComparar;
                pSummary.add(match);
            }
            match.setCantidad(match.getCantidad() + 1);//se le suma 1 a la cantidad de colores, independientemente si es un nuevo color o si es un color filtrado ya existente
        }

        int valueInicial = 0;//sirve para establecer los limites del genoma
        Muestra ultimaMuestra=null;
        for (Muestra muestra : pSummary) {
            muestra.setPorcentage((double) ((muestra.getCantidad()*100) / this.total_colores));//saca el porcentaje de este color en la zona mediante la cantidad de estos en la zona
            System.out.println("Porcentaje = " +muestra.getPorcentaje()+"\n");
            muestra.setStart_Value(valueInicial);//establece el limite inferior del genoma
            muestra.setFinal_Value(valueInicial + (int) (muestra.getPorcentaje() * GENOMA)/100);//mediante un calculo entre el genoma y el porcentaje de la zona se saca el valor del genoma
            System.out.println("Genoma "+GENOMA+ " Inicial = "+muestra.getStart_Value()+ " y Final = "+muestra.getFinal_Value()+"\n");
            valueInicial = muestra.getFinal_Value() + 1;//se establece el valor inicial para la proxima zona
            ultimaMuestra = muestra;//este puntero hacia la ultima zona va a servir para agregar el ultimo genoma, ya que mediante porcentajes se ignora una cantidad
        }if(ultimaMuestra!=null){
             ultimaMuestra.setFinal_Value(GENOMA - 1);//agrega lo que "sobra" a la ultima zona para completar el genoma
        }
       
    }

    public Color getColor(int pValue) {//funcion encargada de retornar un color que se encuantra en un rango del genooma de cada zona
        Color result = new Color(0, 0, 0);
        for (Muestra muestra : SummaryTarget) {
            if (muestra.getStart_Value() >= pValue && muestra.getFinal_Value() <= pValue) {
                result = muestra.getColor();
                break;
            }
        }
        return result;
    }

    public boolean produceGenerations(int pGenerations) {
        ArrayList<Integer> fitCromosomas = new ArrayList<>();
        ArrayList<Integer> newPopulation = new ArrayList<>();
        boolean result = false;
        for (int cuentaGeneraciones = 0; cuentaGeneraciones < pGenerations; cuentaGeneraciones++) {
            for (int cromosoma : population) {
                double fitvalue = fitness(cromosoma);
                if (fitvalue > 1.0) {
                    fitCromosomas.add(cromosoma);
                }
            }

            muestrasActual = new ArrayList<>();
            for (int cuentaHijos = 0; cuentaHijos < MAX_AMOUNT_INDIVIDUALS; cuentaHijos++) {
                int parent1 = fitCromosomas.get((int) (Math.random() * fitCromosomas.size()));
                int parent2 = fitCromosomas.get((int) (Math.random() * fitCromosomas.size()));
                int hijo = Genetic.mutate(Genetic.reproducir(parent1, parent2));
                newPopulation.add(hijo);
                muestrasActual.add(new Muestra(getColor(hijo)));
            }
            population = newPopulation;

            summaryActual = new ArrayList<>();
            Sumarizar(muestrasActual, summaryActual);
            result = compare(summaryActual, SummaryTarget);
            if (result) {
                break;
            }
        }
        return result;
    }

    public double fitness(int pCromosoma) {
        return 1.0;
    }

    public boolean compare(ArrayList<Muestra> pActual, ArrayList<Muestra> pTarget) {
        boolean result = true;
        for (Muestra actual : pActual) {
            for (Muestra target : pTarget) {
                if (actual.getColor().getRGB() == target.getColor().getRGB() && Math.abs(actual.getPorcentaje() - target.getPorcentaje()) > TARGET_TOLERANCE) {
                    result = false;
                }
            }
        }
        return result;
    }

    public void generarPoblacion() {
        population = new ArrayList<>();
        for (int cant = 0; cant > POBLACION_INICIAL; cant++) {
            population.add((int) Math.random() * GENOMA);
        }
    }

}
