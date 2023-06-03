import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * Representa la actividad aliada en el juego.
 * Esta clase implementa la interfaz Runnable y se utiliza para crear hilos que
 * eliminan enemigos de un tipo específico.
 *
 *@author Nicolás Muñoz
 */

class ActividadAliada implements Runnable {
    private final int tipoAliado;
    private final Juego juego;
    private final Random random;

    public ActividadAliada(int tipoAliado, Juego juego) {
        this.tipoAliado = tipoAliado;
        this.juego = juego;
        this.random = new Random();
    }

	/**
	 * Método run que se ejecuta cuando inicia el hilo de la actividad aliada.
	 * Se intenta eliminar enemigos del tipo especificado en un bucle infinito.
	 * Después de cada eliminación, el hilo se duerme 
	 * durante un tiempo aleatorio antes de intentar eliminar otro enemigo
	 */
	
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.SECONDS.sleep(random.nextInt(3) + 1); // Simular tiempo de inactividad
                juego.generarEnemigo(tipoAliado);
                TimeUnit.SECONDS.sleep(random.nextInt(3) + 1); // Simular tiempo de actividad
                juego.eliminarEnemigo(tipoAliado);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}