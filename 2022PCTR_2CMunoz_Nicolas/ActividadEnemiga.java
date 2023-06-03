
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
            juego.generarEnemigo(tipoEnemigo);

            try {
                Thread.sleep(1000); // Tiempo de espera entre generaciones
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}