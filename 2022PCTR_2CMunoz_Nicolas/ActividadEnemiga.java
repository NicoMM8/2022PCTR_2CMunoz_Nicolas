/**
 *
 * Representa la actividad enemiga en el juego.
 * Esta clase implementa la interfaz Runnable y se utiliza para crear hilos que
 * generan enemigos de un tipo específico.
 *
 * @author Nicolás Muñoz
 */

public class ActividadEnemiga implements Runnable {
	private  final Juego juego;
	private final tipoEnemigo;
	
	public ActividadEnemiga(Juego juego, int tipoEnemigo) {
		this.juego = juego;
		this.tipoEnemigo = tipoEnemigo;
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
			// Dormir durante un tiempo aleatorio entre 1 y 5 segundos(morir dentro del juego)
			Thread.sleep(new Random().nextInt(5000) + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			}
		}
	
	}
}