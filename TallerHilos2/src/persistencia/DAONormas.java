package persistencia;

import persistencia.utilidades.Archivo;

import java.util.ArrayList;
import java.util.Arrays;

public class DAONormas {
    private static final String RUTA = "DataNorms.csv";

    public ArrayList<String> dataNorms(){
        return new Archivo().GetInfoContenido(RUTA);
    }
}
