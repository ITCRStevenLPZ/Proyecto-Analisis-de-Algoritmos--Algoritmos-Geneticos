package caso8;

public interface Constantes {

    public static final int COLUMNS = 8;
    public static final int DEFAULT_COLOR_DISTANCE=40;
    public static final int SIZE = 1024;
    public static final double SAMPLE_SIZE = 0.1;
    public static final int ZONE_SIZE = SIZE / COLUMNS;
    public static final double DECREASE_PROB = 0.20;
    public static final int TANTEOS = 5;
    public static final int AMOUNT_ATEMPTS = 5;
    public static final int AMOUNT_OF_COLORS = ((int) (ZONE_SIZE * SAMPLE_SIZE)) / AMOUNT_ATEMPTS;
    public static final int NIBBLE = 16; //16 bits
    public static final int GENOMA = 2^NIBBLE;
    public static final int POBLACION_INICIAL = 4;
    
}
