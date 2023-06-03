

/**
 *
 * Representa la actividad aliada en el juego.
 * Esta clase implementa la interfaz Runnable y se utiliza para crear hilos que
 * eliminan enemigos de un tipo específico.
 *
 *@author Nicolás Muñoz
 */

public class ActividadAliada implements Runnable {
    private final int tipoEnemigo;
    private final Juego juego;

    public ActividadAliada(int tipoEnemigo, Juego juego) {
        this.tipoEnemigo = tipoEnemigo;
        this.juego = juego;
    }
	
	/**
	 * Método run que se ejecuta cuando inicia el hilo de la actividad aliada.
	 * Se intenta eliminar enemigos del tipo especificado en un bucle infinito.
	 * Después de cada eliminación, el hilo se duerme 
	 * durante un tiempo aleatorio antes de intentar eliminar otro enemigo
	 */
	
    @Override
    public void run() {
        juego.eliminarEnemigo(tipoEnemigo);
    }
}