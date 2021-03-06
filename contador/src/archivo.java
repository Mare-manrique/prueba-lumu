/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Maria Angelica Espitia Manrique
 */


public class archivo {

    private File fichero;
    private String contenido;

    public archivo() {

    }

    public archivo(File fichero) {
        this.fichero = fichero;
        this.contenido = "";
        leerContenido();
    }

    public void leerContenido() {
        String linea = null;
        FileReader f = null;
        BufferedReader b = null;
        StringBuilder aux = new StringBuilder();
        try {
            f = new FileReader(this.fichero);
            b = new BufferedReader(f);
            try {

                while ((linea = b.readLine()) != null) {
                    aux.append(linea);
                    aux.append("\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.contenido = aux.toString();
    }

    public String getContenido() {
        return contenido;
    }

    public int getLineas() {
        if (this.contenido.equals("")) {
            return 0;
        } else {
            return this.contenido.split("\n").length;
        }
    }

    public ArrayList<String> getPalabras() {

        ArrayList<String> listPalabras = new ArrayList<String>();
        contenido = contenido.replace("\n", " ");
        String[] palabras = contenido.split(" ");

        for (int i = 0; i < palabras.length; i++) {
            listPalabras.add(palabras[i]);
        }

        return listPalabras;
    }

    public String[][] getHistograma(ArrayList listWords) {


        ArrayList<Integer> listCountWord = new ArrayList<Integer>();
        ArrayList<Integer> listPositionWord = new ArrayList<Integer>();
        ArrayList<Integer> listPositionFinal = new ArrayList<Integer>();
        ArrayList<String> listFinal = new ArrayList<String>();


        for (int i = 0; i < listWords.size(); i++) {
            listFinal = deleteWord(listWords);
        }

        for (int i = 0; i < listFinal.size(); i++) {
            listCountWord.add(getPalabraRepetida(listFinal.get(i), listWords));
        }


        String[][] matrizHistograma = new String[2][listFinal.size()];
        String[][] matrizCopy = new String[2][listFinal.size()];


        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < listFinal.size(); y++) {
                if (x == 0) {
                    matrizHistograma[x][y] = listFinal.get(y);
                } else {
                    matrizHistograma[x][y] = String.valueOf(listCountWord.get(y));
                }

            }
        }

        listPositionWord.addAll(listCountWord);
        Collections.sort(listPositionWord, Collections.reverseOrder());  //  O(N*log(N))


        listPositionFinal=getPosicionColumn(listCountWord,listPositionWord);

       for(int i=0; i<listPositionFinal.size();i++){
           matrizCopy[0][i]=matrizHistograma[0][listPositionFinal.get(i)];
           matrizCopy[1][i]=matrizHistograma[1][listPositionFinal.get(i)];
        }



        return matrizCopy;
    }

    public ArrayList<String> deleteWord(ArrayList listWords) {
        ArrayList<String> listFinal = new ArrayList<String>();
        listFinal.addAll(listWords);

        listFinal = (ArrayList<String>) listWords.stream().distinct().collect(Collectors.toList());

        return listFinal;

    }

    public int getPalabraRepetida(String word, ArrayList listWords) {
        int totalCoincidencias = 0;

        for (int i = 0; i < listWords.size(); i++) {
            if (listWords.get(i).equals(word)) {
                totalCoincidencias++;

            }
        }

        return totalCoincidencias;
    }

    public int getCountPalabras() {
        if (this.contenido.equals("")) {
            return 0;
        } else {
            return this.contenido.replace("\n", " ").split("\\s+").length;
        }
    }

    public int getCaracteres() {

        return this.contenido.replaceAll("\\s+", "").replace("\n", "").length();
    }

    public ArrayList<Integer> getPosicionColumn(ArrayList listHistograma, ArrayList listOrder) {

        ArrayList<Integer> listPosicion = new ArrayList<Integer>();

        for (int i = 0; i < listOrder.size(); i++) {
            for(int j=0; j< listHistograma.size();j++){
                if (listOrder.get(i).equals(listHistograma.get(j))) {
                    listPosicion.add(j);
                }
            }
        }

        listOrder.clear();

        for (int element : listPosicion) {

            if (!listOrder.contains(element)) {
                listOrder.add(element);
            }
        }

        return listOrder;
    }

    public String printHistograma(String[][] matrizHistograma){

        String print=" ";

            for (int j = 0; j < matrizHistograma[0].length; j++) {
                print=print+"\n"+matrizHistograma[0][j] +
                        "          ------ " + matrizHistograma[1][j];
            }

           return print;

    }
}