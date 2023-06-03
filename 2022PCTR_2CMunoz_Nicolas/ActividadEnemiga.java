import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * Representa la actividad enemiga en el juego.
 * Esta clase implementa la interfaz Runnable y se utiliza para crear hilos que
 * generan enemigos de un tipo específico.
 *
 * @author Nicolás Muñoz
 */

class ActividadEnemiga implements Runnable {
    private final int tipoEnemigo;
    private final Juego juego;
    private final Random random;

    public ActividadEnemiga(int tipoEnemigo, Juego juego) {
        this.tipoEnemigo = tipoEnemigo;
        this.juego = juego;
        this.random = new Random();
    }
    
	/**
	 * Método run que se ejecuta cuando inicia el hilo de la actividad enemiga.
	 * Se genera un enemigo del tipo especificado.
	 * Después de cada eliminación, el hilo se duerme durante un tiempo aleatorio.
	 */
	
    @Override
    public void run() {
        try {
            while (true) {
                juego.generarEnemigo(tipoEnemigo);
                TimeUnit.SECONDS.sleep(random.nextInt(3) + 1); // Simular tiempo de actividad
                juego.eliminarEnemigo(tipoEnemigo);
                TimeUnit.SECONDS.sleep(random.nextInt(3) + 1); // Simular tiempo de inactividad
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}