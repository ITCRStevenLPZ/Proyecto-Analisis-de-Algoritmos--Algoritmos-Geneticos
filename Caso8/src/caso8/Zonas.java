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
        Muestra ultimaMuestra = null;
        for (Muestra muestra : pSummary) {
            muestra.setPorcentage((double) ((muestra.getCantidad() * 100) / this.total_colores));//saca el porcentaje de este color en la zona mediante la cantidad de estos en la zona
            //System.out.println("Porcentaje = " + muestra.getPorcentaje() + "\n");
            muestra.setStart_Value(valueInicial);//establece el limite inferior del genoma
            muestra.setFinal_Value(valueInicial + (int) (muestra.getPorcentaje() * GENOMA) / 100);//mediante un calculo entre el genoma y el porcentaje de la zona se saca el valor del genoma
            //System.out.println("Genoma " + GENOMA + " Inicial = " + muestra.getStart_Value() + " y Final = " + muestra.getFinal_Value() + "\n");
            valueInicial = muestra.getFinal_Value() + 1;//se establece el valor inicial para la proxima zona
            ultimaMuestra = muestra;//este puntero hacia la ultima zona va a servir para agregar el ultimo genoma, ya que mediante porcentajes se ignora una cantidad
        }
        if (ultimaMuestra != null) {
            ultimaMuestra.setFinal_Value(GENOMA - 1);//agrega lo que "sobra" a la ultima zona para completar el genoma
        }

    }

    public Color getColor(int pValue) {//funcion encargada de retornar un color que se encuantra en un rango del genooma de cada zona
        Color result = new Color(0, 0, 0);
        for (Muestra muestra : SummaryTarget) {
            if (muestra.getStart_Value() <= pValue && muestra.getFinal_Value() >= pValue) {
                result = muestra.getColor();
                break;
            }
        }
        return result;
    }

    public boolean produceGenerations(int pGenerations) {//funcion que realiza el algoritmo genetico, devuelve true si ya se logro una poblacion acertada a la target. Recibe como parametro la cantidad de generaciones      
        ArrayList<Integer> fitCromosomas = new ArrayList<>();//arraylist encargada de contener todos los cromosomas que mediante la funcion Fitness son filtrados
        ArrayList<Integer> newPopulation = new ArrayList<>();//es la nueva poblacion de individuos, esta en constante cambio
        boolean result = false;
        for (int i = 0; i < pGenerations; i++) {//for que limita la cantidad de generaciones
            for (int cromosoma : population) {//por cada cromosoma de la poblacion se evalua este en la funcion fitness
                double fitvalue = fitness(cromosoma);
                if (fitvalue > 1.0) {//si el valor de fitness del cromosoma es mayor a 1.0 quiere decir que el cromosoma es apto para la reproduccion
                    //System.out.println("Se encontro un Fit " + cromosoma + "\n");
                    fitCromosomas.add(cromosoma);
                } else {
                    //System.out.println("No se encontro un Fit\n");
                }
            }
            muestrasActual = new ArrayList<>();
            if (fitCromosomas.size() != 0) {
                for (int cuentaHijos = 0; cuentaHijos < MAX_AMOUNT_INDIVIDUALS; cuentaHijos++) {//mediante la constante se establece la cantidad maxima de individuos a reproducir
                    int parent1 = fitCromosomas.get((int) (Math.random() * fitCromosomas.size()));//padre 1 buscado de forma random
                    int parent2 = fitCromosomas.get((int) (Math.random() * fitCromosomas.size()));//padre 2 buscado de forma random
                    int hijo = Genetic.mutate(Genetic.reproducir(parent1, parent2)); //hijo generado por los padres anteriores
                    newPopulation.add(hijo);
                    muestrasActual.add(new Muestra(getColor(hijo)));
                    population = newPopulation;
                    summaryActual = new ArrayList<>();//ArrayList que sirve como comparacion para elmetodo sumarizar, encargado de reorganizar los colores y darle su cantidad y probabilidad
                    Sumarizar(muestrasActual, summaryActual);
                    result = compare(summaryActual, SummaryTarget);//comparara la lista summaryActual con la Target, si estas coinciden quiere decir que el algoritmo genetico termino su trabajo
                    if (result && population.size()>=MAX_AMOUNT_INDIVIDUALS) {
                        //System.out.println("Tamano = "+population.size()+"\n");
                        break;
                    }
                }
            }

        }
        return result;
    }

    public double fitness(int pCromosoma) {//funcion encargada de dar una calificacion al cromosoma recibido
        Color aComparar = getColor(pCromosoma);//color obtenido mediante el metodo getColor
        double calificacion;
        boolean coincide = false;
        Muestra resp = null;
        for (Muestra Comparable : SummaryTarget) {//For each que compara el color anteriormente buscado con los colores del SummarizeTarget
            //System.out.println(Comparable.getColor().getRGB()+" Coincide " + aComparar.getRGB() + "\n");
            if (Comparable.getColor().getRGB() == aComparar.getRGB()) {//si el color es encontrado este genoma va a pertenecer a este rango
                resp = Comparable;//Muestra respuesta
                coincide = true;
                break;
            } else {
                coincide = false;
            }
        }
        if (!coincide) {
            calificacion = 0;//si no pertenece a ninguna de los colores de la zona, este es descartado
        } else {
            calificacion = EvaluarCromosoma(pCromosoma, resp);//si efectivamente se encuentra entre los colores de SummaryTarget se evaluan los cromosomas
        }
        return calificacion;
    }

    private double EvaluarCromosoma(int pCromosoma, Muestra resp) {
        double tolerancia = (resp.getPorcentaje() * (100 - TARGET_TOLERANCE)) / 100;//es la tolerancia maxima que puede aceptar
        double restaAporcentaje = (resp.getPorcentaje()) / resp.getCantidad();//se divide el porcentaje por cada individuo del color o la muestra
        System.out.println("Tolerancia = " + tolerancia + " Probabilidad = "+resp.getPorcentaje()+" Cantidad = "+resp.getCantidad()+" Operacion = "+(resp.getCantidad() - 1) * restaAporcentaje+"\n");
        if ((resp.getCantidad() - 1) * restaAporcentaje >= tolerancia /*&& (resp.getCantidad() - 1) * restaAporcentaje <= resp.getPorcentaje() ||(resp.getCantidad() - 1) * restaAporcentaje > resp.getPorcentaje()*/ ) {//si el resultado de este calculo es mayor a la tolerancia y mayor o ubicado entre el al porcentaje sin tolerancia y con toleracia, quiere decir que este cromosoma a la hora de que se elimine o de que no este no va a ser necesario
            return 0;
        } else {//en cualquier otro caso quiere decir que si este cromosoma es eliminado, va a hacer falta y es necesario
            return 1.5;
        }
    }

    public boolean compare(ArrayList<Muestra> pActual, ArrayList<Muestra> pTarget) {//funcion encargada de comparar y comparar si los colores coinciden y terminar la reproduccion de cromosomas;
        boolean resp = false;
        for (Muestra actual : pActual) {
            for (Muestra target : pTarget) {

                if (actual.getColor().getRGB() == target.getColor().getRGB() && Math.abs(actual.getPorcentaje() - target.getPorcentaje()) > TARGET_TOLERANCE) {
                    resp = false;
                } else {
                    resp = true;
                }
            }
        }
        return resp;
    }

    public void generarPoblacion() {//Funcion encargada de realizar la poblacion inicial clave para el algoritmo genetico
        population = new ArrayList<>();
        for (int cant = 0; cant < POBLACION_INICIAL; cant++) {
            population.add((int) (Math.random() * GENOMA));
        }
    }

}
