import java.util.Hashtable;
import java.util.Random;
/**
 * La clase Juego representa el juego en sí y realiza el seguimiento de los enemigos
 * generados y eliminados.
 *
 *@author Nicolás Muñoz Miguel
 */
public class Juego {
    private static final int MAXENEMIGOS = 100;
    private static final int MINENEMIGOS = 0;
    private static final int MAX_TIPOS_ENEMIGO = 4;

    private Hashtable<Integer, Integer> enemigosTotales;
    private Hashtable<Integer, Integer> enemigosActuales;
    private Hashtable<Integer, Integer> enemigosEliminados;

    public Juego() {
    	//Inicializamos las tablas hash de enemigos
        enemigosTotales = new Hashtable<>();
        enemigosActuales = new Hashtable<>();
        enemigosEliminados = new Hashtable<>();
    }

    public synchronized void generarEnemigo(int tipoEnemigo) throws InterruptedException {
        //Esperamos un tiempo aleatorio entre 1 y 5 segundos
        Random random = new Random();
        int tiempoEspera = random.nextInt(5) + 1;
        Thread.sleep(tiempoEspera * 1000);

        //Incrementamos el contador de enemigos actuales y totales del tipo especificado
        int enemigosTipo = enemigosActuales.getOrDefault(tipoEnemigo, MINENEMIGOS);
        enemigosTipo++;
        enemigosActuales.put(tipoEnemigo, enemigosTipo);

        int enemigosTotal = enemigosTotales.getOrDefault(tipoEnemigo, MINENEMIGOS);
        enemigosTotal++;
        enemigosTotales.put(tipoEnemigo, enemigosTotal);

        //Imprimimos mensaje de generación de enemigo y mostrar información actualizada
        System.out.println("Generado enemigo tipo " + tipoEnemigo);
        mostrarInformacionEnemigos();
    }

    public synchronized void eliminarEnemigo(int tipoEnemigo) {
    	// Verificamos si hay enemigos del tipo especificado para eliminar
        int enemigosTipo = enemigosActuales.getOrDefault(tipoEnemigo, MINENEMIGOS);
        if (enemigosTipo > MINENEMIGOS) {
        	// Decrementamos el contador de enemigos actuales del tipo especificado
            enemigosTipo--;
            enemigosActuales.put(tipoEnemigo, enemigosTipo);

            //Incrementamos el contador de enemigos eliminados del tipo especificado
            int enemigosEliminadosTipo = enemigosEliminados.getOrDefault(tipoEnemigo, MINENEMIGOS);
            enemigosEliminadosTipo++;
            enemigosEliminados.put(tipoEnemigo, enemigosEliminadosTipo);

            //Imprimimos mensaje de eliminación de enemigo y mostrar información actualizada
            System.out.println("Eliminado enemigo tipo " + tipoEnemigo);
            mostrarInformacionEnemigos();
        }
    }

    public void mostrarInformacionEnemigos() {
    	//Mostramos información sobre los enemigos actuales y eliminados
        System.out.println("--> Enemigos totales: " + calcularEnemigosTotales());
        for (int i = MINENEMIGOS; i < MAX_TIPOS_ENEMIGO; i++) {
            System.out.println("----> Enemigos tipo " + i + ": " + enemigosActuales.getOrDefault(i, MINENEMIGOS)
                    + " ------ [Eliminados: " + enemigosEliminados.getOrDefault(i, MINENEMIGOS) + "]");
        }
        System.out.println();
    }

    private int calcularEnemigosTotales() {
    	//Calculamos el número total de enemigos sumando los contadores de enemigos totales de cada tipo
        int enemigosTotal = MINENEMIGOS;
        for (int i = MINENEMIGOS; i < MAX_TIPOS_ENEMIGO; i++) {
            enemigosTotal += enemigosTotales.getOrDefault(i, MINENEMIGOS);
        }
        return enemigosTotal;
    }
}
