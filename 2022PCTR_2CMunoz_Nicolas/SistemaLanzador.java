/**
 *
 *Clase que contiene el método main para lanzar la simulación del juego.
 *
 * @author Nicolás Muñoz
 */

public class SistemaLanzador {
	public static void main(String[] args) {
		int numTiposEnemigos = 3; //Número de tipos de enemigos
		int numEnemigosPorTipo = 5; //Número de enemigos por tipo
		int maxEnemigosSimultaneos = 2; //Número máximo de enemigos simultáneos
		
		Juego juego = new Juego(numTiposEnemigos, numEnemigosPorTipo, numEnemigosSimultaneos);
		
		//Generar hilos de actividad enemiga y actividad aliada para cada tipo de enemigo
		for (int i = 1; i <= numTiposEnemigos; i++) {
			Thread enemigoThread = new Thread(new ActividadEnemiga(juego, i));
			Thread aliadoThread = new Thread(new ActividadAliada(juego, i));
			enemigoThread.start();
			aliadoThread.start();
		}
	}
}