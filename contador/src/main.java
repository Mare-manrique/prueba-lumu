import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Maria Angelica Espitia Manrique
 */
public class main {



    public static void main (String[] args){

     int lineas=0;
     int palabras =0;
     int caracteres=0;
        ArrayList<String> listPalabras = new ArrayList<String>();



      File texto= new File("contador\\texto.txt");

      archivo archivo=new archivo(texto);
      lineas=archivo.getLineas();
      palabras=archivo.getCountPalabras();
        listPalabras=archivo.getPalabras();

        String[][] histograma;


       histograma=archivo.getHistograma(listPalabras);
       String print= archivo.printHistograma(histograma);
       caracteres=archivo.getCaracteres();


        System.out.println("numero de lineas:  "+lineas);
        System.out.println("cantidad de palabras:  "+palabras);
        System.out.println("caracteres:   "+caracteres);
        System.out.println("HISTOGRAMAAAAAA....."+print);





    }
}
