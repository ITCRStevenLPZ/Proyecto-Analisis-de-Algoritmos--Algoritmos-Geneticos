package caso8;

public interface Constantes {

    public static final int COLUMNS = 48;
    public static final int DEFAULT_COLOR_DISTANCE = 40;
    public static final int SIZE = 1024;//dependiendo de la resolucion, recuerde que debe ser cuadrada, en este caso 1024*1024
    public static final double SAMPLE_SIZE = 0.5;
    public static final int ZONE_SIZE = SIZE / COLUMNS;
    public static final double DECREASE_PROB = 0.02;
    public static final int TANTEOS = 3;
    public static final int AMOUNT_ATEMPTS = 5;
    public static final int AMOUNT_OF_COLORS = ((int) (ZONE_SIZE * SAMPLE_SIZE)) / AMOUNT_ATEMPTS;
    public static final int NIBBLE = 16; //16 bits
    public static final int GENOMA = (int) Math.pow(2 , NIBBLE);
    public static final int POBLACION_INICIAL = 2;
    public final static int POLIGON_SIZE = 15;
    public final static int MAX_AMOUNT_INDIVIDUALS = (int) (ZONE_SIZE*0.2);//La cantidad maxima de individuoa que se reproduzcan va a ser aproximadamente un 20% del tamano de la zona
    public final static double MUTATE_PERCENTAGE = 0.05;
    public final static double TARGET_TOLERANCE = 3;//porcentaje de tolerancia de aceptacion del porcentaje de probabilidad
}
