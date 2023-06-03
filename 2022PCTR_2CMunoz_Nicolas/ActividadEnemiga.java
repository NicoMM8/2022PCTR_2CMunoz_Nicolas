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

public class ActividadEnemiga implements Runnable {
    private final int tipoEnemigo;
    private final Juego juego;

    public ActividadEnemiga(int tipoEnemigo, Juego juego) {
        this.tipoEnemigo = tipoEnemigo;
        this.juego = juego;
    }
	/**
	 * Método run que se ejecuta cuando inicia el hilo de la actividad enemiga.
	 * Se genera un enemigo del tipo especificado.
	 * Después de cada eliminación, el hilo se duerme durante un tiempo aleatorio.
	 */
	
    @Override
    public void run() {
        Random random = new Random();
        int tiempoActividad = random.nextInt(5000) + 1000;

        try {
            TimeUnit.MILLISECONDS.sleep(tiempoActividad);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        juego.generarEnemigo(tipoEnemigo);
    }
}