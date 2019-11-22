package caso8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author lolil
 */
public class HTML {

    ArrayList<Coordenadas> Coor;
    String FileNam;

    public ArrayList<Coordenadas> getCoor() {
        return Coor;
    }

    public void setCoor(ArrayList<Coordenadas> Coor) {
        this.Coor = Coor;
    }
    String Html
            = "<html>"
            + "<body>"
            + "<svg width=\"1024\" height=\"1024\">";
    String Final
            = "</svg>"
            + "</body>"
            + "</html>";
    File nueva;
    BufferedWriter bw;

    public HTML(ArrayList<Coordenadas> Coor, String FileNam) throws IOException {
        this.nueva=new File(".\\"+FileNam+".html");
        this.Coor=Coor;
        this.FileNam=FileNam;
        for (Coordenadas a : Coor) {
            Html += "<polygon points=\"" + (a.getX1().getA()) + "," + (a.getX1().getB()) + " " + (a.getY1().getA()) + "," + (a.getY1().getB()) + " " + (a.getX2().getA()) + "," + (a.getX2().getB()) + " " + (a.getY2().getA()) + "," + (a.getY2().getB()) + "\" style=\"fill:rgb(" + (a.getColor().getRed()) + "," + (a.getColor().getGreen()) + "," + (a.getColor().getBlue()) + ");\" />";
        }
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
