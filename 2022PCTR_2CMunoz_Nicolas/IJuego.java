/**
 * La interfaz IJuego define dos metodos, los cuales son implementados por 
 * cualquier clase que implemente la interfaz IJuego
 * Esta interfaz está relacionada con todos los demás archivos.
 *@author Nicolás Muñoz Miguel
 */
public interface IJuego {
	//Método para generar un enemigo del tipo especificado
    void generarEnemigo(int tipoEnemigo);
    //Método para eliminar un enemigo del tipo especificado
    void eliminarEnemigo(int tipoEnemigo);
}

