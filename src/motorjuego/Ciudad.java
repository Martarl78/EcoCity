package motorjuego;

import java.util.ArrayList;

/**
 * Ciudad
 * ──────
 * Almacena el estado completo de la ciudad:
 *   • Recursos: dinero, energía, agua, población, felicidad.
 *   • Colección de edificios: ArrayList<Edificio> (requisito).
 *   • Registro de eventos: ArrayList<String> (historial).
 *
 * El motor del juego llama a construir() para añadir edificios y
 * recorre la lista usando polimorfismo en cada ciclo mensual.
 */
public class Ciudad {

    private final String nombre;
    private double dinero;
    private double energia;       // balance MW neto del mes actual
    private double consumoAgua;   // agua consumida en el mes
    private int    poblacion;     // población acumulada este mes
    private int    felicidad;     // 0–100
    private int    mes;

    // ── COLECCIONES (requisito: ArrayList) ────────────────────────────
   
    private final ArrayList<String>   registroEventos; // historial de la partida

    // ── Constructor ───────────────────────────────────────────────────
    public Ciudad(String nombre, double dineroInicial) {
        this.nombre          = nombre;
        this.dinero          = dineroInicial;
        this.felicidad       = 70; // ciudad nueva, neutral-positiva
        this.mes             = 1;
        this.registroEventos = new ArrayList<>();
        
    }

  

    // ── Métodos de acceso / mutación de recursos ──────────────────────
    public void addDinero(double cantidad)    { 
    	this.dinero += cantidad; }
    public void addEnergia(double cantidad)   { 
    	this.energia += cantidad; }
    public void addPoblacion(int cantidad)    { 
    	this.poblacion   += cantidad; }
    public void addConsumoAgua(double cant)   { 
    	this.consumoAgua += cant; }
    public void addFelicidad(int cantidad) {
    this.felicidad = Math.max(0, Math.min(100, this.felicidad + cantidad));
    }
    public void addEvento(String evento) { 
    	registroEventos.add(evento); }
    public void incrementarMes()         { 
    	mes++; }

    /** Reinicia los contadores de flujo al inicio de cada mes nuevo. */
    public void resetRecursosMensuales() {
        this.energia     = 0;
        this.poblacion   = 0;
        this.consumoAgua = 0;
        // felicidad y dinero persisten entre meses
    }

    // ── Getters ───────────────────────────────────────────────────────
    public String getNombre(){
    	return nombre; }
    public double getDinero(){ 
    	return dinero; }
    public double getEnergia(){ 
    	return energia; }
    public double getConsumoAgua(){ 
    	return consumoAgua; }
    public int getPoblacion(){
    return poblacion; }
    public int getFelicidad(){ 
    	return felicidad; }
    public int getMes(){ 
    	return mes; }
    public ArrayList<String>  getRegistro(){ 
    	return registroEventos; }
}