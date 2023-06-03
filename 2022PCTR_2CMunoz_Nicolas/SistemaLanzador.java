
/**
 *
 *Clase que contiene el método main para lanzar la simulación del juego.
 *
 * @author Nicolás Muñoz
 */

public class SistemaLanzador {
    public static void main(String[] args) {
        int numTiposEnemigos = 3;
        final int maxEnemigos = 10; // Definición de la constante maxEnemigos
        
        Juego juego = new Juego(numTiposEnemigos, maxEnemigos);
        
        for (int tipoEnemigo = 0; tipoEnemigo < numTiposEnemigos; tipoEnemigo++) {
            Thread enemigoThread = new Thread(new ActividadEnemiga(tipoEnemigo, juego));
            Thread aliadoThread = new Thread(new ActividadAliada(tipoEnemigo, juego));
            
            enemigoThread.start();
            aliadoThread.start();
        }
    }
}

