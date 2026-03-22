import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Motor
 * ─────
 * Corazón del juego. Gestiona:
 *   • El bucle principal de juego.
 *   • El ciclo mensual (polimorfismo sobre List<Edificio>).
 *   • Menús interactivos por consola.
 *   • Eventos aleatorios climáticos.
 *   • Condiciones de victoria/derrota.
 */
public class Motor {

    private final Ciudad  ciudad;
    private final Scanner scanner;
    private final Random  random;
    private       boolean jugando;

    // Catálogo de edificios disponibles para construir
    private static final Object[][] CATALOGO = {
        // { nombre, clase, descripción para mostrar }
        { "Casa Magna",           "RES",  "$2,000 ||| +100 hab/mes ||| Consume 20MW" },
        { "Bloque Residencial",   "RES",  "$3,500 ||| +200 hab/mes ||| Consume 35MW" },
        { "Complejo Habitacional","RES",  "$6,000 ||| +400 hab/mes ||| Consume 60MW" },
        { "Central Nuclear Alfa", "NUC",  "$15,000 ||| +500MW ||| ⚠  Alto riesgo" },
        { "Central Eólica A",     "EOL",  "$3,000  ||| +80MW  ||| 🌿 Eco-friendly" },
        { "Parque Eólico Norte",  "EOL",  "$3,000  ||| +80MW  ||| 🌿 Mantenimiento mínimo" },
        { "Centro Comercial",     "COM",  "$5,000  ||| +Felicidad +10 ||| $500/mes" },
        { "Gran Mall",            "COM",  "$8,000  ||| +Felicidad +20 ||| $1,200/mes" },
    };

    public Motor(Ciudad ciudad) {
        this.ciudad  = ciudad;
        this.scanner = new Scanner(System.in);
        this.random  = new Random();
        this.jugando = true;
    }

    // ── Punto de entrada ──────────────────────────────────────────────
    public void iniciar() {
        mostrarBienvenida();
        while (jugando) {
            mostrarEstado();
            mostrarMenu();
            procesarOpcion();
        }
        mostrarResumenFinal();
        scanner.close();
    }

    // ── Pantallas ─────────────────────────────────────────────────────
    private void mostrarBienvenida() {
        
        System.out.println("	  ECOCITY – SIMULADOR 2025   ");
        System.out.println("Construye, gestiona y cuida tu ciudad  ");
        System.out.println("\nConsejo: ¡Equilibra la producción de energía o");
        System.out.println("  la felicidad de tus ciudadanos caerá!\n");
        pausar();
    }
  
    private void mostrarEstado() {
        System.out.println("\n  -----||| ESTADO DE " + ciudad.getNombre().toUpperCase() +" – MES " + ciudad.getMes() + "|||-----");
        System.out.println("Recursos: $" + ciudad.getDinero() + " | Energía: " + ciudad.getEnergia() + "MW" +" | Población: " + ciudad.getPoblacion());
        System.out.println("Felicidad: " + ciudad.getFelicidad() + "% " + barraFelicidad(ciudad.getFelicidad()));

        ArrayList<Edificio> lista = ciudad.getEdificios();

        System.out.println("Edificios construidos:");

        if (lista.isEmpty()) {
            System.out.println("  (ninguno) -- Deberías Construir algo");
        } else {
            int i = 1;
            for (Edificio e : lista) {
                System.out.println(i + ". " + e);
                i++;
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n¿Qué desea hacer?");
        System.out.println("> 1. Construir  2. Reparar  3. Pasar Mes  4. Guardar y Salir");
        System.out.print("Opción: ");
    }
    private void procesarOpcion() {
        String input = scanner.nextLine().trim();
        System.out.println();
        switch (input) {
            case "1": menuConstruir(); break;
            case "2": menuReparar();   break;
            case "3": pasarMes();      break;
            case "4": jugando = false; break;
            default:
                System.out.println("  Opción no válida. Intente de nuevo.");
        }
    }
    
    private void menuConstruir() {
        System.out.println("══════════ CATÁLOGO DE CONSTRUCCIÓN ══════════");

        // Mostrar edificios
        for (int i = 0; i < CATALOGO.length; i++) {
            System.out.println((i + 1) + ". " + CATALOGO[i][0] + " (" + CATALOGO[i][1] + ") - " + CATALOGO[i][2]);
        }
        System.out.println((CATALOGO.length + 1) + ". Cancelar");

        // Leer opción del usuario
        System.out.print("Elige un edificio: ");
        String entrada = scanner.nextLine().trim();

        int opcion;
        try {
            opcion = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("  Ingresa un número válido.");
            return;
        }

        if (opcion == CATALOGO.length + 1) {
            System.out.println("  Construcción cancelada.");
            return;
        }

        if (opcion < 1 || opcion > CATALOGO.length) {
            System.out.println("  Opción inválida.");
            return;
        }

        // Crear y construir el edificio
        Edificio nuevo = crearEdificio(opcion);
        if (nuevo != null) {
            try {
                ciudad.construir(nuevo);
            } catch (FondosInsuficientesException e) {
                System.out.println("  ❌ " + e.getMessage());
            }
        }
    }
    
     // CLASES DE COMPAÑEROS ME SIRVE CUANDO SE HAGA MERGE
    private Edificio crearEdificio(int opcion) {
        switch (opcion) {
            case 1: return new EdificioResidencial("Casa Magna",           2_000, 20, 100);
            case 2: return new EdificioResidencial("Bloque Residencial",   3_500, 35, 200);
            case 3: return new EdificioResidencial("Complejo Habitacional",6_000, 60, 400);
            case 4: return new CentralNuclear("Central Nuclear Alfa");
            case 5: return new ParqueEolico("Central Eólica A");
            case 6: return new ParqueEolico("Parque Eólico Norte");
            case 7: return new EdificioComercial("Centro Comercial", 5_000, 15, 10,  500);
            case 8: return new EdificioComercial("Gran Mall",         8_000, 25, 20, 1200);
            default: return null;
        }
    }

    
}