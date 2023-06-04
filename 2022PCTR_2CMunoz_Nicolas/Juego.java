import java.util.Hashtable;
import java.util.Random;

public class Juego {
    private static final int MAX_ENEMIGOS = 100;
    private static final int MIN_ENEMIGOS = 0;
    private static final int MAX_TIPOS_ENEMIGO = 4;

    private Hashtable<Integer, Integer> enemigosTotales;
    private Hashtable<Integer, Integer> enemigosActuales;
    private Hashtable<Integer, Integer> enemigosEliminados;

    public Juego() {
        enemigosTotales = new Hashtable<>();
        enemigosActuales = new Hashtable<>();
        enemigosEliminados = new Hashtable<>();
    }

    public synchronized void generarEnemigo(int tipoEnemigo) throws InterruptedException {
        // Esperar un tiempo aleatorio entre 1 y 5 segundos
        Random random = new Random();
        int tiempoEspera = random.nextInt(5) + 1;
        Thread.sleep(tiempoEspera * 1000);

        int enemigosTipo = enemigosActuales.getOrDefault(tipoEnemigo, MIN_ENEMIGOS);
        enemigosTipo++;
        enemigosActuales.put(tipoEnemigo, enemigosTipo);

        int enemigosTotal = enemigosTotales.getOrDefault(tipoEnemigo, MIN_ENEMIGOS);
        enemigosTotal++;
        enemigosTotales.put(tipoEnemigo, enemigosTotal);

        System.out.println("Generado enemigo tipo " + tipoEnemigo);
        mostrarInformacionEnemigos();
    }

    public synchronized void eliminarEnemigo(int tipoEnemigo) {
        int enemigosTipo = enemigosActuales.getOrDefault(tipoEnemigo, MIN_ENEMIGOS);
        if (enemigosTipo > MIN_ENEMIGOS) {
            enemigosTipo--;
            enemigosActuales.put(tipoEnemigo, enemigosTipo);

            int enemigosEliminadosTipo = enemigosEliminados.getOrDefault(tipoEnemigo, MIN_ENEMIGOS);
            enemigosEliminadosTipo++;
            enemigosEliminados.put(tipoEnemigo, enemigosEliminadosTipo);

            System.out.println("Eliminado enemigo tipo " + tipoEnemigo);
            mostrarInformacionEnemigos();
        }
    }

    public void mostrarInformacionEnemigos() {
        System.out.println("--> Enemigos totales: " + calcularEnemigosTotales());
        for (int i = MIN_ENEMIGOS; i < MAX_TIPOS_ENEMIGO; i++) {
            System.out.println("----> Enemigos tipo " + i + ": " + enemigosActuales.getOrDefault(i, MIN_ENEMIGOS)
                    + " ------ [Eliminados: " + enemigosEliminados.getOrDefault(i, MIN_ENEMIGOS) + "]");
        }
        System.out.println();
    }

    private int calcularEnemigosTotales() {
        int enemigosTotal = MIN_ENEMIGOS;
        for (int i = MIN_ENEMIGOS; i < MAX_TIPOS_ENEMIGO; i++) {
            enemigosTotal += enemigosTotales.getOrDefault(i, MIN_ENEMIGOS);
        }
        return enemigosTotal;
    }
}
