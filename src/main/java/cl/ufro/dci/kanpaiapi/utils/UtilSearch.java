package cl.ufro.dci.kanpaiapi.utils;

import java.text.Normalizer;


public class UtilSearch {
    private UtilSearch(){}

    public static String formatearTexto(String text) {
        text = text.toLowerCase();

        String[] symbolArray = {" ", ",", "-", "_", ";", ":", ".", ","};
        for (String symbol : symbolArray) {
            text = text.replace(symbol, "");
        }


        //A continuacion le quitamos los acentos
        text = stripAccents(text);

        return text;
    }

    public static String stripAccents(String s) {
        //Este metodo solamente quita los acentos de una cadena
        //Fuente: https://stackoverflow.com/a/15190787
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
}
