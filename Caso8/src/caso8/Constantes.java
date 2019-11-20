package caso8;

public interface Constantes {

    public static final int COLUMNS = 8;
    public static final int DEFAULT_COLOR_DISTANCE = 40;
    public static final int SIZE = 1024;
    public static final double SAMPLE_SIZE = 0.1;
    public static final int ZONE_SIZE = SIZE / COLUMNS;
    public static final double DECREASE_PROB = 0.02;
    public static final int TANTEOS = 50;
    public static final int AMOUNT_ATEMPTS = 5;
    public static final int AMOUNT_OF_COLORS = ((int) (ZONE_SIZE * SAMPLE_SIZE)) / AMOUNT_ATEMPTS;
    public static final int NIBBLE = 16; //16 bits
    public static final int GENOMA = (int) Math.pow(2 , NIBBLE);
    public static final int POBLACION_INICIAL = 4;
    public final static int POLIGON_SIZE = 5;
    public final static int MAX_AMOUNT_INDIVIDUALS = (ZONE_SIZE * ZONE_SIZE) / POLIGON_SIZE;
    public final static double MUTATE_PERCENTAGE = 0.05;
    public final static double TARGET_TOLERANCE = 0.03;//porcentaje de tolerancia de aceptacion del porcentaje de probabilidad
}
