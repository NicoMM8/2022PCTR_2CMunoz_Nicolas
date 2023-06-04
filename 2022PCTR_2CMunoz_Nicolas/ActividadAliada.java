import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ActividadAliada extends Thread {
    private int tipoEnemigo;
    private IJuego juego;
    private Random random;

    public ActividadAliada(int tipoEnemigo, IJuego juego) {
        this.tipoEnemigo = tipoEnemigo;
        this.juego = juego;
        this.random = new Random();
    }

    public void run() {
        while (!interrupted()) {
            try {
                // Lógica de actividad del aliado
                TimeUnit.MILLISECONDS.sleep(random.nextInt(5000) + 1000);
                juego.generarEnemigo(tipoEnemigo);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

