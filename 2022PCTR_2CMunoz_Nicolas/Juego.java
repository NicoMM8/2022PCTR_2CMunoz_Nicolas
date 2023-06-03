import java.util.Hashtable;

public class Juego {
    private int contadorEnemigosTotales;
    private Hashtable<Integer, Integer> contadoresEnemigosTipo;
    private Hashtable<Integer, Integer> contadoresEliminadosTipo;
    private final int MAXENEMIGOS;
    private final int MINENEMIGOS = 0;

    public Juego(int numTiposEnemigos, int numEnemigosPorTipo, int maxEnemigosSimultaneos) {
        contadorEnemigosTotales = 0;
        contadoresEnemigosTipo = new Hashtable<>();
        contadoresEliminadosTipo = new Hashtable<>();
        MAXENEMIGOS = maxEnemigosSimultaneos;

        // Inicializar los contadores de enemigos de cada tipo
        for (int tipoEnemigo = 1; tipoEnemigo <= numTiposEnemigos; tipoEnemigo++) {
            contadoresEnemigosTipo.put(tipoEnemigo, 0);
            contadoresEliminadosTipo.put(tipoEnemigo, 0);
        }
    }

    public synchronized void generarEnemigo(int tipoEnemigo) throws InterruptedException {
        comprobarAntesDeGenerar(tipoEnemigo);

        // Generar un nuevo enemigo del tipo especificado
        contadoresEnemigosTipo.put(tipoEnemigo, contadoresEnemigosTipo.get(tipoEnemigo) + 1);
        contadorEnemigosTotales++;

        imprimirInfo(contadorEnemigosTotales, "generado", tipoEnemigo);

        // Notificar a todos los hilos que están esperando
        notifyAll();
    }

    public synchronized void eliminarEnemigo(int tipoEnemigo) throws InterruptedException {
        comprobarAntesDeEliminar(tipoEnemigo);

        // Eliminar un enemigo del tipo especificado
        contadoresEnemigosTipo.put(tipoEnemigo, contadoresEnemigosTipo.get(tipoEnemigo) - 1);
        contadoresEliminadosTipo.put(tipoEnemigo, contadoresEliminadosTipo.get(tipoEnemigo) + 1);
        contadorEnemigosTotales--;

        imprimirInfo(contadorEnemigosTotales, "eliminado", tipoEnemigo);

        // Notificar a todos los hilos que están esperando
        notifyAll();
    }

    public synchronized void imprimirInfo(int enemigosTotales, String accion, int tipoEnemigo) {
        System.out.println("Enemigo " + tipoEnemigo + " " + accion + ". Enemigos totales: " + enemigosTotales +
                ", Enemigos actuales de tipo " + tipoEnemigo + ": " + contadoresEnemigosTipo.get(tipoEnemigo) +
                ", Enemigos eliminados de tipo " + tipoEnemigo + ": " + contadoresEliminadosTipo.get(tipoEnemigo));
    }

    public synchronized int sumarContadores() {
        int sum = 0;
        for (int contador : contadoresEnemigosTipo.values()) {
            sum += contador;
        }
        return sum;
    }

    public synchronized void checkInvariante() {
        int sumaContadores = sumarContadores();
        if (contadorEnemigosTotales != sumaContadores) {
            System.err.println("Error en el invariante: contadorEnemigosTotales no coincide con la suma de los contadores de enemigos");
        }

        for (int tipoEnemigo : contadoresEnemigosTipo.keySet()) {
            int enemigosActuales = contadoresEnemigosTipo.get(tipoEnemigo);
            int enemigosEliminados = contadoresEliminadosTipo.get(tipoEnemigo);
            int enemigosTotalesTipo = enemigosActuales + enemigosEliminados;

            if (enemigosTotalesTipo > MAXENEMIGOS) {
                System.err.println("Error en el invariante: el número de enemigos totales del tipo " + tipoEnemigo + " excede el máximo permitido");
            }

            if (enemigosActuales < MINENEMIGOS || enemigosActuales > numEnemigosPorTipo) {
                System.err.println("Error en el invariante: el número de enemigos actuales del tipo " + tipoEnemigo + " está fuera del rango permitido");
            }

            if (enemigosEliminados < MINENEMIGOS || enemigosEliminados > numEnemigosPorTipo) {
                System.err.println("Error en el invariante: el número de enemigos eliminados del tipo " + tipoEnemigo + " está fuera del rango permitido");
            }
        }
    }
    
    public synchronized void comprobarAntesDeGenerar(int tipoEnemigo) throws InterruptedException {
        while (contadorEnemigosTotales >= MAXENEMIGOS || contadoresEnemigosTipo.get(tipoEnemigo) >= numEnemigosPorTipo) {
            wait();
        }
    }
    
    public synchronized void comprobarAntesDeEliminar(int tipoEnemigo) throws InterruptedException {
        while (contadoresEnemigosTipo.get(tipoEnemigo) <= MINENEMIGOS) {
            wait();
        }
    }


