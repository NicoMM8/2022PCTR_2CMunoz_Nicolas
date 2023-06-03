import java.util.HashMap;
import java.util.Map;

public class Juego {
    private final int numTiposEnemigos;
    private final int MAXENEMIGOS;
    private final int MINENEMIGOS = 0;
    private final Map<Integer, Integer> contadoresEnemigosTipo;
    private final Map<Integer, Integer> enemigosEliminadosTipo;

    public Juego(int numTiposEnemigos, int maxEnemigos) {
        this.numTiposEnemigos = numTiposEnemigos;
        this.MAXENEMIGOS = maxEnemigos;
        this.contadoresEnemigosTipo = new HashMap<>();
        this.enemigosEliminadosTipo = new HashMap<>();

        // Inicializar los contadores de enemigos y enemigos eliminados para cada tipo
        for (int tipo = 0; tipo < numTiposEnemigos; tipo++) {
            contadoresEnemigosTipo.put(tipo, 0);
            enemigosEliminadosTipo.put(tipo, 0);
        }
    }

    public synchronized void generarEnemigo(int tipoEnemigo) throws InterruptedException {
        // Verificar la precondición
        if (tipoEnemigo > 0 && contadoresEnemigosTipo.get(tipoEnemigo - 1) == 0) {
            wait(); // Esperar hasta que se haya generado al menos un enemigo del tipo anterior
        }

        // Verificar el invariante
        int totalEnemigos = contadoresEnemigosTipo.values().stream().mapToInt(Integer::intValue).sum();
        int totalEnemigosEliminados = enemigosEliminadosTipo.values().stream().mapToInt(Integer::intValue).sum();
        if (totalEnemigos != totalEnemigosEliminados) {
            throw new IllegalStateException("El invariante de los contadores de enemigos vivos no se cumple");
        }

        // Verificar las postcondiciones
        if (totalEnemigos < MINENEMIGOS || totalEnemigos > MAXENEMIGOS) {
            throw new IllegalStateException("Las postcondiciones del número de enemigos no se cumplen");
        }

        // Generar enemigo
        int numEnemigosTipo = contadoresEnemigosTipo.get(tipoEnemigo);
        contadoresEnemigosTipo.put(tipoEnemigo, numEnemigosTipo + 1);

        // Imprimir información del enemigo generado
        System.out.println("Generado enemigo tipo " + tipoEnemigo);
        System.out.println("--> Enemigos totales: " + (totalEnemigos + 1));
        for (int tipo = 0; tipo < numTiposEnemigos; tipo++) {
            int enemigosTipo = contadoresEnemigosTipo.get(tipo);
            int enemigosEliminadosTipo = this.enemigosEliminadosTipo.get(tipo);
            System.out.println("----> Enemigos tipo " + tipo + ": " + enemigosTipo + " ------ [Eliminados:" + enemigosEliminadosTipo + "]");
        }

        // Notificar a los hilos esperando
        notifyAll();
    }
    
    public synchronized void eliminarEnemigo(int tipoEnemigo) throws InterruptedException {
        int numEnemigosTipo = contadoresEnemigosTipo.get(tipoEnemigo);
        int numEnemigosEliminadosTipo = enemigosEliminadosTipo.get(tipoEnemigo);

        // Verificar que haya enemigos del tipo para eliminar
        if (numEnemigosTipo - numEnemigosEliminadosTipo == 0) {
            wait(); // Esperar hasta que se generen más enemigos del tipo
        }

        // Eliminar enemigo
        enemigosEliminadosTipo.put(tipoEnemigo, numEnemigosEliminadosTipo + 1);

        // Imprimir información del enemigo eliminado
        System.out.println("Eliminado enemigo tipo " + tipoEnemigo);
        System.out.println("--> Enemigos eliminados totales: " + (numEnemigosEliminadosTipo + 1));
        for (int tipo = 0; tipo < numTiposEnemigos; tipo++) {
            int enemigosTipo = contadoresEnemigosTipo.get(tipo);
            int enemigosEliminadosTipo = this.enemigosEliminadosTipo.get(tipo);
            System.out.println("----> Enemigos tipo " + tipo + ": " + enemigosTipo + " ------ [Eliminados:" + enemigosEliminadosTipo + "]");
        }

        // Notificar a los hilos esperando
        notifyAll();
    }
}