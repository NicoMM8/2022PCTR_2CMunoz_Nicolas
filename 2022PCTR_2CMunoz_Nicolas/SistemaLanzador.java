
/**
 *
 *Clase que contiene el método main para lanzar la simulación del juego.
 *
 * @author Nicolás Muñoz
 */

public class SistemaLanzador {
    public static void main(String[] args) {
        int numTiposEnemigos = 4;
        final int maxEnemigos = 10; // Definición de la constante maxEnemigos
        
        Juego juego = new Juego(numTiposEnemigos, maxEnemigos);
        
     // Crear hilos para las actividades enemigas
        for (int tipo = 0; tipo < numTiposEnemigos; tipo++) {
            Thread enemigoThread = new Thread(new ActividadEnemiga(tipo, juego));
            enemigoThread.start();
        }

        // Crear hilos para las actividades aliadas
        for (int tipo = 0; tipo < numTiposEnemigos; tipo++) {
            Thread aliadoThread = new Thread(new ActividadAliada(tipo, juego));
            aliadoThread.start();
        }
    }
}

