import java.util.Hashtable;

public class Juego {
    private int contadorEnemigosTotales;
    private Hashtable<Integer, Integer> contadoresEnemigosTipo;
    private Hashtable<Integer, Integer> contadoresEliminadosTipo;
    private final int MAXENEMIGOS;
    private final int MINENEMIGOS;

    public Juego(int numTiposEnemigos, int maxEnemigos) {
    	this.MAXENEMIGOS = maxEnemigos;
    	this.MINENEMIGOS = 0;
        contadorEnemigosTotales = 0;
        contadoresEnemigosTipo = new Hashtable<>();
        contadoresEliminadosTipo = new Hashtable<>();

        // Inicializar los contadores de cada tipo de enemigo
        for (int i = 0; i < numTiposEnemigos; i++) {
            contadoresEnemigosTipo.put(i, 0);
            contadoresEliminadosTipo.put(i, 0);
        }
    }
    
    public synchronized void comprobarAntesDeGenerar(int tipoEnemigo) {
    	if (contadoresEnemigosTipo.get(tipoEnemigo) < MAXENEMIGOS) {
            try {
                System.out.println("Esperando para generar enemigo de tipo " + tipoEnemigo);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void comprobarAntesDeEliminar(int tipoEnemigo) {
    	if (contadoresEnemigosTipo.get(tipoEnemigo) > MINENEMIGOS) {
            try {
                System.out.println("Esperando para eliminar enemigo de tipo " + tipoEnemigo);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public synchronized void generarEnemigo(int tipoEnemigo) {
        if (contadoresEnemigosTipo.get(tipoEnemigo) < MAXENEMIGOS) {
            contadoresEnemigosTipo.put(tipoEnemigo, contadoresEnemigosTipo.getOrDefault(tipoEnemigo, 0) + 1);
            contadorEnemigosTotales++;
            imprimirInfo(tipoEnemigo, "generado");
            notifyAll();
        } else {
            try {
                System.out.println("Esperando para generar enemigo de tipo " + tipoEnemigo);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public synchronized void eliminarEnemigo(int tipoEnemigo) {
        if (contadoresEnemigosTipo.getOrDefault(tipoEnemigo, 0) > 0) {
            contadoresEnemigosTipo.put(tipoEnemigo, contadoresEnemigosTipo.get(tipoEnemigo) - 1);
            contadoresEliminadosTipo.put(tipoEnemigo, contadoresEliminadosTipo.getOrDefault(tipoEnemigo, 0) + 1);
            contadorEnemigosTotales--;
            imprimirInfo(tipoEnemigo, "eliminado");
            notifyAll();
        } else {
            try {
                System.out.println("Esperando para eliminar enemigo de tipo " + tipoEnemigo);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void imprimirInfo(int tipoEnemigo, String accion) {
        System.out.println("Enemigo de tipo " + tipoEnemigo + " " + accion + ". Total: " + contadorEnemigosTotales +
                ", Tipo " + tipoEnemigo + ": " + contadoresEnemigosTipo.getOrDefault(tipoEnemigo, 0) +
                ", Eliminados " + tipoEnemigo + ": " + contadoresEliminadosTipo.getOrDefault(tipoEnemigo, 0));
    }

    public synchronized int sumarContadores() {
        int suma = 0;
        for (int tipoEnemigo : contadoresEnemigosTipo.keySet()) {
            suma += contadoresEnemigosTipo.get(tipoEnemigo);
        }
        return suma;
    }

    public synchronized void checkInvariante() {
        for (int tipoEnemigo : contadoresEnemigosTipo.keySet()) {
            int totalTipo = contadoresEnemigosTipo.get(tipoEnemigo) - contadoresEliminadosTipo.getOrDefault(tipoEnemigo, 0);
            assert totalTipo >= MINENEMIGOS && totalTipo <= MAXENEMIGOS : "Invariante violado para el tipo de enemigo: " + tipoEnemigo;
        }
    }
}
