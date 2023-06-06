import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * Clase que crea un hilo que representa la actividad de un aliado.
 *
 *@author Nicolás Muñoz
 */
public class ActividadEnemiga extends Thread {
    private int tipoEnemigo; //Tipo de enemigo que representa esta actividad
    private IJuego juego; //La instancia del juego en la que se realiza la actividad
    private Random random; //Generador de números aleatorios para la pausa

    public ActividadEnemiga(int tipoEnemigo, IJuego juego) {
        this.tipoEnemigo = tipoEnemigo;
        this.juego = juego;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        while (!interrupted()) {
            try {
            	//Esperamos un tiempo aleatorio entre 1 y 5 segundos
                TimeUnit.MILLISECONDS.sleep(random.nextInt(5000) + 1000);
             // Llamamos al método eliminarEnemigo del juego con el tipo de enemigo
                juego.eliminarEnemigo(tipoEnemigo);
            } catch (InterruptedException e) {
            	// Si se interrumpe la ejecución, se sale del bucle y se termina la actividad
                break;
            }
        }
    }
}