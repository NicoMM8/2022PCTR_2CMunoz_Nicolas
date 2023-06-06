import java.util.Hashtable;
import java.util.Random;
/**
 * Clase que representa el juego y controla la generación y eliminación de enemigos.
 * Implementa el patrón Singleton para garantizar una única instancia del juego.
 * Proporciona métodos para generar enemigos de diferentes tipos y eliminar enemigos.
 * Realiza el seguimiento de los enemigos totales y los enemigos eliminados de cada tipo.
 * 
 * @author Nicolás Muñoz Miguel
 */
public class Juego {
    private static final int MAXENEMIGOS = 100;
    private static final int MINENEMIGOS = 0;
    private static final int MAX_TIPOS_ENEMIGO = 4;

    private static Juego instancia;
    private Hashtable<Integer, Integer> enemigosTotales;
    private Hashtable<Integer, Integer> enemigosActuales;
    private Hashtable<Integer, Integer> enemigosEliminados;

    // Constructor privado para evitar la creación directa de instancias
    private Juego() {
        enemigosTotales = new Hashtable<>();
        enemigosActuales = new Hashtable<>();
        enemigosEliminados = new Hashtable<>();
    }

    // Método estático para obtener la instancia única del juego
    public static synchronized Juego getInstancia() {
        if (instancia == null) {
            instancia = new Juego();
        }
        return instancia;
    }

    public void generarEnemigo(int tipoEnemigo) throws InterruptedException {

        // Esperar un tiempo aleatorio entre 1 y 5 segundos
        Random random = new Random();
        int tiempoEspera = random.nextInt(5) + 1;
        Thread.sleep(tiempoEspera * 1000);

        synchronized (this) {
            int enemigosTipo = enemigosActuales.getOrDefault(tipoEnemigo, MINENEMIGOS);
            enemigosTipo++;
            enemigosActuales.put(tipoEnemigo, enemigosTipo);

            int enemigosTotal = enemigosTotales.getOrDefault(tipoEnemigo, MINENEMIGOS);
            enemigosTotal++;
            enemigosTotales.put(tipoEnemigo, enemigosTotal);

            System.out.println("Generado enemigo tipo " + tipoEnemigo);
            mostrarInformacionEnemigos();
        }
    }

    public void eliminarEnemigo(int tipoEnemigo) {
        // Eliminación de enemigos

        synchronized (this) {
            int enemigosTipo = enemigosActuales.getOrDefault(tipoEnemigo, MINENEMIGOS);
            if (enemigosTipo > MINENEMIGOS) {
                enemigosTipo--;
                enemigosActuales.put(tipoEnemigo, enemigosTipo);

                int enemigosEliminadosTipo = enemigosEliminados.getOrDefault(tipoEnemigo, MINENEMIGOS);
                enemigosEliminadosTipo++;
                enemigosEliminados.put(tipoEnemigo, enemigosEliminadosTipo);

                System.out.println("Eliminado enemigo tipo " + tipoEnemigo);
                mostrarInformacionEnemigos();
            }
        }
    }

    public void mostrarInformacionEnemigos() {
        // Para mostrar información de los enemigos

        synchronized (this) {
            System.out.println("--> Enemigos totales: " + calcularEnemigosTotales());
            for (int i = MINENEMIGOS; i < MAX_TIPOS_ENEMIGO; i++) {
                System.out.println("----> Enemigos tipo " + i + ": " + enemigosActuales.getOrDefault(i, MINENEMIGOS)
                        + " ------ [Eliminados: " + enemigosEliminados.getOrDefault(i, MINENEMIGOS) + "]");
            }
            System.out.println();
        }
    }

    private int calcularEnemigosTotales() {
        // Para calcular el total de enemigos

        int enemigosTotal = MINENEMIGOS;
        for (int i = MINENEMIGOS; i < MAX_TIPOS_ENEMIGO; i++) {
            enemigosTotal += enemigosTotales.getOrDefault(i, MINENEMIGOS);
        }
        return enemigosTotal;
    }
}


