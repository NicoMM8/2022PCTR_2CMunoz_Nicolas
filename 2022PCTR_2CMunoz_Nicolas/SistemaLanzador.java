import java.util.Hashtable;
import java.util.Random;

/**
 *
 *Clase que contiene el método main para lanzar la simulación del juego.
 *
 * @author Nicolás Muñoz
 */

public class SistemaLanzador {
	public static void main(String[] args) {
        Juego juego = new Juego();

        Thread generadorEnemigos = new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    int tipoEnemigo = random.nextInt(10);
                    juego.generarEnemigo(tipoEnemigo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread eliminadorEnemigos = new Thread(() -> {
            Random random = new Random();
            while (true) {
                int tipoEnemigo = random.nextInt(10);
                juego.eliminarEnemigo(tipoEnemigo);
            }
        });

        generadorEnemigos.start();
        eliminadorEnemigos.start();
    }
	}