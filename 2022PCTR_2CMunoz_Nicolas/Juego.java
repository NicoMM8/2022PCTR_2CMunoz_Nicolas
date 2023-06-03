import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa el juego y gestiona los enemigos.
 */
public class Juego {
    private final int numTiposEnemigos;
    private final int numEnemigosPorTipo;
    private final int maxEnemigosSimultaneos;
    private final Map<Integer, Integer> enemigosActuales;
    private final Map<Integer, Integer> enemigosEliminados;
    private int enemigosTotales;

    /**
     * Constructor de la clase Juego.
     *
     * @param numTiposEnemigos        Número de tipos de enemigos.
     * @param numEnemigosPorTipo      Número de enemigos por tipo.
     * @param maxEnemigosSimultaneos  Número máximo de enemigos simultáneos.
     */
    public Juego(int numTiposEnemigos, int numEnemigosPorTipo, int maxEnemigosSimultaneos) {
        this.numTiposEnemigos = numTiposEnemigos;
        this.numEnemigosPorTipo = numEnemigosPorTipo;
        this.maxEnemigosSimultaneos = maxEnemigosSimultaneos;
        this.enemigosActuales = new HashMap<>();
        this.enemigosEliminados = new HashMap<>();
        this.enemigosTotales = 0;
    }

    /**
     * Método que genera un enemigo del tipo especificado.
     *
     * @param tipoEnemigo Tipo de enemigo a generar.
     */
    public synchronized void generarEnemigo(int tipoEnemigo) {
        if (enemigosActuales.getOrDefault(tipoEnemigo, 0) < numEnemigosPorTipo && enemigosTotales < maxEnemigosSimultaneos) {
            enemigosActuales.put(tipoEnemigo, enemigosActuales.getOrDefault(tipoEnemigo, 0) + 1);
            enemigosTotales++;
            System.out.println("Se generó un enemigo de tipo " + tipoEnemigo + ". Enemigos totales: " + enemigosTotales);
        }
    }

    /**
     * Método que elimina un enemigo del tipo especificado.
     *
     * @param tipoEnemigo Tipo de enemigo a eliminar.
     */
    public synchronized void eliminarEnemigo(int tipoEnemigo) {
        if (enemigosActuales.containsKey(tipoEnemigo) && enemigosActuales.get(tipoEnemigo) > 0) {
            enemigosActuales.put(tipoEnemigo, enemigosActuales.get(tipoEnemigo) - 1);
            enemigosEliminados.put(tipoEnemigo, enemigosEliminados.getOrDefault(tipoEnemigo, 0) + 1);
            enemigosTotales--;
            System.out.println("Se eliminó un enemigo de tipo " + tipoEnemigo + ". Enemigos totales: " + enemigosTotales);
        }
    }

    // Otros métodos y lógica del juego...

}
