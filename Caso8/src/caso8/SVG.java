/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caso8;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author fvive
 */
public class SVG {
    public static void SVGGenerator (String coordenadas) throws IOException {
    String svg = "";
    svg += "<?xml version=\"1.0\" standalone=\"no\"?>\n";
    svg += "<svg width=\"1024\" height=\"1024\" version=\"1.1\"\n";
    svg += "   xmlns=\"http://www.w3.org/2000/svg\">\n";
    svg += "  <desc>Caso8\n";
    svg += "  </desc>\n";

    svg += coordenadas;
    
    svg += "  <!-- Show outline of viewport using 'rect' element -->\n";
    svg += "</svg>\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\fvive\\OneDrive\\Desktop\\Analisis\\Caso_8_NEW\\Caso8\\rongay.svg"))) {
            writer.write(svg);
        }   
        
    }
}
