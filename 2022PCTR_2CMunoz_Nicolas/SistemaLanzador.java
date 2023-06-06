import java.util.Random;

/**
 *
 *Clase que contiene el método main para lanzar la simulación del juego.
 *
 * @author Nicolás Muñoz Miguel
 */

public class SistemaLanzador {
	public static void main(String[] args) {
		//Crear instancia del juego
		Juego juego = Juego.getInstancia();
        
        //Crear un hilo para generar enemigos
        Thread generadorEnemigos = new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                	//Generar un tipo de enemigo aleatorio(0,1,2 o 3)
                    int tipoEnemigo = random.nextInt(4);
                 // Llamamos al método generarEnemigo del juego con el tipo de enemigo
                    juego.generarEnemigo(tipoEnemigo);
                } catch (InterruptedException e) {
                	// Si se interrumpe la ejecución, se imprime la traza del error
                    e.printStackTrace();
                }
            }
        });
        
     // Creamos un hilo para eliminar enemigos
        Thread eliminadorEnemigos = new Thread(() -> {
            Random random = new Random();
            while (true) {
            	// Generamos un tipo de enemigo aleatorio (0, 1, 2 o 3)
                int tipoEnemigo = random.nextInt(4);
             // Llamamos al método eliminarEnemigo del juego con el tipo de enemigo
                juego.eliminarEnemigo(tipoEnemigo);
            }
        });
        //Aquí iniciamos la ejecución de los hilos
        generadorEnemigos.start();
        eliminadorEnemigos.start();
    }
	}