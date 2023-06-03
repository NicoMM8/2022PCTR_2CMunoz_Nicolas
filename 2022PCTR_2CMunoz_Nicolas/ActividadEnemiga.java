import java.util.Random;
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
        while (true) {
            try {
                // Realizar la actividad enemiga aquí

                // Esperar un tiempo aleatorio
                int tiempo = generarTiempoAleatorio();
                Thread.sleep(tiempo);

                // Generar un nuevo enemigo del tipo correspondiente
                juego.generarEnemigo(tipoEnemigo);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int generarTiempoAleatorio() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }
}