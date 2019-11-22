
package caso8;

import java.io.File;
import java.io.IOException;


public class Caso8 {

    public static void main(String[] args) throws IOException {
        File nueva= new File(".\\GUACAMAYA.jpg");
        File nueva1= new File(".\\BIOSHOCK.jpg");
        File nueva2= new File(".\\TUCAN.jpg");
        Imagen N1=new Imagen(nueva,"Guaca");
        Imagen N2=new Imagen(nueva1,"Bio");
        Imagen N3=new Imagen(nueva2,"Tuc");
        /*int a=65535;
        int resultado = Genetic.reproducir(a,65535);       
        System.out.println(Genetic.mutate(resultado));*/
        //HTML nova=new HTML();
    }
    
}
