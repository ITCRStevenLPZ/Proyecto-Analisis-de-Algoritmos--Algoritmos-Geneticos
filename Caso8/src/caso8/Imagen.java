package caso8;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Imagen implements Constantes {

    public ArrayList<Zonas> zonas;
    BufferedImage img = null;

    public Imagen(File imagen_source) throws IOException {
        img = ImageIO.read(imagen_source);
        zonas = new ArrayList<>();
        CrearZonas();
    }

    public void CrearZonas() {

        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < COLUMNS; row++) {
                int x0 = ZONE_SIZE * column;
                int y0 = ZONE_SIZE * row;
                int x1 = ZONE_SIZE * column + ZONE_SIZE-1;
                int y1 = ZONE_SIZE * row + ZONE_SIZE-1; 
                Zonas nueva = new Zonas(x0, y0, x1, y1);
                nueva.SetID(column, row); 
                zonas.add(nueva);
                for (int i = 0; i < TANTEOS; i++) {            
                    boolean esBlanco = false;
                    if (Math.random() <= nueva.probabilidad) {
                        for (int j = 0; j < AMOUNT_OF_COLORS; j++) {
                            int x = (int) (Math.random() * (nueva.max_x - nueva.min_x) + 1) + nueva.min_x;
                            int y = (int) (Math.random() * (nueva.max_y - nueva.min_y) + 1) + nueva.min_y;
                             
                            Color c = new Color(img.getRGB(x, y));//Obtiene el rgb de cada punto
                            if (c.getRed() >= 240 && c.getGreen() >= 240 && c.getBlue() >= 240) {
                                //System.out.println("Probabilidad = " +nueva.probabilidad+  "\n");
                                esBlanco = true;
                            } else {
                                nueva.AddMuestra(c);
                            }
                        }
                        if (esBlanco) {
                            nueva.Decrease();
                        }
                    }

                }nueva.Sumarizar();
            }
        }
    }
}
