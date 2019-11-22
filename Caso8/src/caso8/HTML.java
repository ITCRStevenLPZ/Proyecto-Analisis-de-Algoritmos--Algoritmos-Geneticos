package caso8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author lolil
 */
public class HTML {

    String Html
            = "<html>"
            + "<body>"
            + "<svg width=\"1024\" height=\"1024\">";
    String Final
            = "</svg>"
            + "</body>"
            + "</html>";
    File nueva = new File(".\\Imagen.html");
    BufferedWriter bw;

    public HTML() throws IOException {
        int suma = 0;
        /*for (int a = 0; a < 8; a++) {
            Html += "<polygon points=\"" + (suma + 0) + ",0 " + (suma + 0) + ",15 " + (suma + 15) + ",15 " + (suma + 15) + ",0\" style=\"fill:rgb(0,2115,0);\" />";           
            int suma2 = 0;
            for (int b = 0; b < 8; b++) {
                Html += "<polygon points=\"" + (suma + 0) + "," + (suma2 + 0) + " " + (suma + 0) + "," + (suma2 + 15) + " " + (suma + 15) + "," + (suma2 + 15) + " " + (suma + 15) + "," + (suma2 + 0) + "\" style=\"fill:rgb(0,2115,0);\" />";
                suma2 += 15;

            }
            suma += 15;
        }*/
        Html += Final;
        this.bw = new BufferedWriter(new FileWriter(nueva));
        bw.write(Html);
        bw.close();
    }
}
