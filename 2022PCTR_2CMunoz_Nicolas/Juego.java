public class Juego {
    private int[] contadoresEnemigosTipo;
    private int contadorEnemigosTotales;
    private final int numTiposEnemigos;
    private final int MAX_ENEMIGOS;

    public Juego(int numTiposEnemigos, int maxEnemigos) {
        this.numTiposEnemigos = numTiposEnemigos;
        this.MAX_ENEMIGOS = maxEnemigos;
        contadoresEnemigosTipo = new int[numTiposEnemigos];
        contadorEnemigosTotales = 0;
    }

    public synchronized void generarEnemigo(int tipoEnemigo) {
        while (contadorEnemigosTotales >= MAX_ENEMIGOS) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        contadoresEnemigosTipo[tipoEnemigo]++;
        contadorEnemigosTotales++;

        System.out.println("Generado enemigo tipo " + tipoEnemigo);
        imprimirEstado();

        notifyAll();
    }

    public synchronized void eliminarEnemigo(int tipoEnemigo) {
        while (contadoresEnemigosTipo[tipoEnemigo] <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        contadoresEnemigosTipo[tipoEnemigo]--;
        contadorEnemigosTotales--;

        System.out.println("Eliminado enemigo tipo " + tipoEnemigo);
        imprimirEstado();

        notifyAll();
    }

    private void imprimirEstado() {
        System.out.println("--> Enemigos totales: " + contadorEnemigosTotales);
        for (int tipo = 0; tipo < numTiposEnemigos; tipo++) {
            System.out.println("----> Enemigos tipo " + tipo + ": " + contadoresEnemigosTipo[tipo] +
                    " ------ [Eliminados: " + (contadorEnemigosTotales - contadoresEnemigosTipo[tipo]) + "]");
        }
        System.out.println();
    }
}

