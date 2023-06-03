
/**
 *
 *Clase que contiene el método main para lanzar la simulación del juego.
 *
 * @author Nicolás Muñoz
 */

public class SistemaLanzador {
    public static void main(String[] args) {
        int numTiposEnemigos = 4;
        int maxEnemigos = 10;

        Juego juego = new Juego(numTiposEnemigos, maxEnemigos);

        // Crear hilos para las actividades enemigas
        for (int tipo = 0; tipo < numTiposEnemigos; tipo++) {
            Runnable actividadEnemiga = new ActividadEnemiga(tipo, juego);
            Thread hiloActividadEnemiga = new Thread(actividadEnemiga);
            hiloActividadEnemiga.start();
        }

        // Crear hilos para las actividades aliadas
        for (int tipo = 0; tipo < numTiposEnemigos; tipo++) {
            Runnable actividadAliada = new ActividadAliada(tipo, juego);
            Thread hiloActividadAliada = new Thread(actividadAliada);
            hiloActividadAliada.start();
        }
    }
}

