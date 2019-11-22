package caso8;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Imagen implements Constantes {

    public ArrayList<Coordenadas> coordenadas = new ArrayList<>();
    public ArrayList<Zonas> zonas;
    BufferedImage img = null;
    String FileName;

    public Imagen(File imagen_source,String FileName) throws IOException {
        img = ImageIO.read(imagen_source);
        this.FileName=FileName;
        zonas = new ArrayList<>();

        CrearZonas();
    }

    public void CrearZonas() throws IOException { //encargadas de dividir la imagen en zonas para poder trabajar de forma individual con el fin de obtener resultados mas precisos

        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < COLUMNS; row++) {
                int x0 = ZONE_SIZE * column; //coordenada que indica la esquina superior derecha del cuadrante
                int y0 = ZONE_SIZE * row; //coordenada que indica la esquina inferior derecha del cuadrante
                int x1 = ZONE_SIZE * column + ZONE_SIZE - 1; //coordenada que indica la esquina superior izquierda del cuadrante
                int y1 = ZONE_SIZE * row + ZONE_SIZE - 1; //coordenada que indica la esquina inferior izquierda del cuadrante
                Zonas nueva = new Zonas(x0, y0, x1, y1);
                nueva.SetID(column, row); //la id se genera mediante la suma de la columna y la fila multiplicado por la constante COLUMNS
                zonas.add(nueva);//se anade a la ArrayList zonas
                for (int i = 0; i < TANTEOS; i++) {   //mediante este for se realizara la busqueda de color o de blancos         
                    boolean esBlanco = false;
                    if (Math.random() <= nueva.probabilidad) {//Math.random genera un numero random entre 0 y 1 y lo compara con la probabilidad de la nueva zona anteriormente creada
                        for (int j = 0; j < AMOUNT_OF_COLORS; j++) {//El for indica la cantidad de colores que seran tanteados
                            int x = (int) (Math.random() * (nueva.max_x - nueva.min_x) + 1) + nueva.min_x; //se genera un random con rango, que va desde el min_x y el max_x
                            int y = (int) (Math.random() * (nueva.max_y - nueva.min_y) + 1) + nueva.min_y;//se genera un random con rango, que va desde el min_y y el max_y                            
                            Color c = new Color(img.getRGB(x, y));//Obtiene el rgb de cada punto con las coordenadas x , y generadas anteriormente
                            //System.out.println("Probabilidad = " +nueva.probabilidad+" ID "+nueva.iD+"\n");
                            if (c.getRed() >= 240 && c.getGreen() >= 240 && c.getBlue() >= 240) { //este if me indica que si el color tiende mucho a blanco se active un booleano o se ponga en true                               
                                esBlanco = true;
                            } else {
                                nueva.AddMuestra(c);//si este no es blanco, simplemente es anadido al ArrayList MuestrasReales de la zona
                            }
                        }
                        if (esBlanco) {
                            nueva.Decrease();//si detecta que hubo colores blancos, mediante el booleano esBlanco se decrementa la probabilidad de la Zona, generando el algoritmo probabilista
                        }
                    }

                }
                //System.out.println("Cant colores = " + nueva.total_colores + " Probabilidad " + nueva.probabilidad + " ID " + nueva.iD + "\n");

                if (nueva.total_colores != 0) {
                    nueva.Sumarizar();
                    nueva.generarPoblacion();
                    boolean resp = nueva.produceGenerations(10);
                    System.out.println("Se ha encontrado la respuesta? " + resp + "\n");                
                    generarCoordenadas(nueva);
                    System.out.println("SIZE " + coordenadas.size()+ "\n");
                    HTML Prueba = new HTML(coordenadas,FileName);
                }

            }
        }
    }

    public void generarCoordenadas(Zonas z) {
        for (int a = 0; a < z.population.size(); a++) {
            Color nuevo = z.getColor(z.population.get(a), z.SummaryTarget);
            int x = (int) (Math.random() * (z.max_x - z.min_x) + 1) + z.min_x;
            int y = (int) (Math.random() * (z.max_y - z.min_y) + 1) + z.min_y;
            Coordenadas nueva = new Coordenadas(nuevo, x, y);
            nueva.setY1();
            nueva.setX2();
            nueva.setY2();
            coordenadas.add(nueva);
        }

    }

}
