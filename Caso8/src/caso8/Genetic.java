package caso8;

public class Genetic implements Constantes {

    public static int reproducir(int pParent1, int pParent2) {
        int result = pParent1;
        int puntoCruce = (NIBBLE / 2);

        int mask = (GENOMA - 1) << NIBBLE - puntoCruce;
        result = pParent1 & mask;

        mask = (2 ^ (NIBBLE - puntoCruce)) - 1;

        result = result | mask;

        return result;
    }

    public static int mutate(int pCromosoma) {
        if (Math.random() <= MUTATE_PERCENTAGE) {
            int puntoMutate = (int) (NIBBLE * Math.random());
            int mask = 2 ^ puntoMutate;
            pCromosoma ^= mask;
        }
        return pCromosoma;
    }
}

