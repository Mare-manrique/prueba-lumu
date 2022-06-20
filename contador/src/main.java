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

     int lines=0;
     int words =0;
     int characters=0;
        ArrayList<String> listWord = new ArrayList<String>();



      File texto= new File("contador\\texto.txt");

      archivo archivo=new archivo(texto);
        lines=archivo.getLineas();
        words=archivo.getCountPalabras();
        listWord=archivo.getPalabras();

        String[][] histograma;


       histograma=archivo.getHistograma(listWord);
       String print= archivo.printHistograma(histograma);
        characters=archivo.getCaracteres();


        System.out.println("numero de lineas:  "+lines);
        System.out.println("cantidad de palabras:  "+words);
        System.out.println("caracteres:   "+characters);
        System.out.println("HISTOGRAMAAAAAA....."+print);





    }
}
