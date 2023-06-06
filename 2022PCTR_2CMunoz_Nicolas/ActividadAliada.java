import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * Clase que crea un hilo que representa la actividad de un aliado.
 *
 *@author Nicolás Muñoz Miguel
 */
public class ActividadAliada extends Thread {
    private int tipoEnemigo;
    private IJuego juego;
    private Random random;

    public ActividadAliada(int tipoEnemigo, IJuego juego) {
        this.tipoEnemigo = tipoEnemigo;
        this.juego = juego;
        this.random = new Random();
    }

    @Override
    public void run() { //Donde creamos el hilo
        while (!interrupted()) {
            try {
            	//Esperamos un tiempo aleatorio entre 1 y 5 segundos
                TimeUnit.MILLISECONDS.sleep(random.nextInt(5000) + 1000);
                // Generamos un enemigo del tipo especificado
                juego.generarEnemigo(tipoEnemigo);
            } catch (InterruptedException e) { //Si capturamos la excepción se rompe el bucle para finalizar la ejecución del hilo
                break;
            }
        }
    }
}

