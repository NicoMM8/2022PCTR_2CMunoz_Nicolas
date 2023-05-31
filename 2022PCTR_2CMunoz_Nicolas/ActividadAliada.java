



public class ActividadAliada implements Runnable {
	private  final Juego juego;
	private final tipoEnemigo;
	
	public ActividadAliada(Juego juego, int tipoEnemigo) {
		this.juego = juego;
		this.tipoEnemigo = tipoEnemigo;
	}
	
	@Override
	public void run() {
		while (true) {
		juego.eliminarEnemigo(tipoEnemigo);
		try {
			Thread.sleep(new Random().nextInt(5000) + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
}