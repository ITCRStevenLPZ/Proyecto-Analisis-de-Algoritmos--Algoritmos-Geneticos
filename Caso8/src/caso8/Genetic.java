package caso8;

public class Genetic implements Constantes {

    public static int reproducir(int pParent1, int pParent2) {
        int result = pParent1;
        int puntoCruce = (NIBBLE / 2);//se realiza el punto de cruce en la mitad
        int mask =(int) (GENOMA-1 >> NIBBLE - puntoCruce);//mascara que me ayuda a hacer el desplazamiento a la derecha
        result = (int) (pParent1 & mask);//deja los ultimos 8 bits en 0 y los otros 8 como el parent1 originalmente tenia     
        short mask2= (short) (GENOMA-1 << NIBBLE - puntoCruce);//deja los ultimos 8 bits en 0 y los primeros como parent2 los tenia originalmente
        int padre2=(int)(pParent2 & mask2);
        result = (int) (padre2 | result);//se obtiene el cruce entre los dos parents

        return result;
    }

    public static int mutate(int pCromosoma) {
        if (Math.random() <= MUTATE_PERCENTAGE) {
            //System.out.println("Mutacion\n");
            int puntoMutante = (int) (NIBBLE * Math.random());
            int mask = (int) (Math.pow(2,puntoMutante));
            pCromosoma ^= mask;
        }
        return pCromosoma;
    }
}

