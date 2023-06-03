
/**
 *
 *Clase que contiene el método main para lanzar la simulación del juego.
 *
 * @author Nicolás Muñoz
 */

public class SistemaLanzador {
    public static void main(String[] args) {
        int numTiposEnemigos = 3;
        int numEnemigosPorTipo = 5;
        int maxEnemigosSimultaneos = 10;

        Juego juego = new Juego(numTiposEnemigos, numEnemigosPorTipo, maxEnemigosSimultaneos);

        // Lanzar hilos de ActividadEnemiga y ActividadAliada por pares
        for (int tipoEnemigo = 1; tipoEnemigo <= numTiposEnemigos; tipoEnemigo++) {
            Thread enemigoThread = new Thread(new ActividadEnemiga(tipoEnemigo, juego));
            Thread aliadoThread = new Thread(new ActividadAliada(tipoEnemigo, juego));

            enemigoThread.start();
            aliadoThread.start();
        }
    }
}

