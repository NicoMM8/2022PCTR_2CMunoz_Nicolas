/**
 *
 * Representa la ctividad aliada en el juego.
 * Esta clase implementa la interfaz Runnable y se utiliza para crear hilos que
 * eliminan enemigos de un tipo específico.
 *
 *@author Nicolás Muñoz
 */

public class ActividadAliada implements Runnable {
	private  final Juego juego;
	private final tipoEnemigo;
	
	public ActividadAliada(Juego juego, int tipoEnemigo) {
		this.juego = juego;
		this.tipoEnemigo = tipoEnemigo;
	}
	
	/**
	 * Método run que se ejecuta cuando inicia el hilo de la actividad aliada.
	 * Se intenta eliminar enemigos del tipo especificado en un bucle infinito.
	 * Después de cada eliminación, el hilo se duerme 
	 * durante un tiempo aleatorio antes de intentar eliminar otro enemigo
	 */
	
	@Override
	public void run() {
		while (true) {
		juego.eliminarEnemigo(tipoEnemigo);
		try {
			// Esperar un tiempo aleatorio antes de intentar eliminar otro enemigo
			Thread.sleep(new Random().nextInt(5000) + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
}