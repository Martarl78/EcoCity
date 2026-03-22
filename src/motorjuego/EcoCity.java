package motorjuego;

public class EcoCity {

	public static void main(String[] args) {
        // Crear ciudad con nombre y dinero inicial
        Ciudad ciudad = new Ciudad("EcoCity", 5_000);

        // Iniciar motor del juego
        Motor motor = new Motor(ciudad);
        motor.iniciar();
    }
}

 