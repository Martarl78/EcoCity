package motorjuego;

import java.util.ArrayList;

/*
  Ciudad

 Almacena el estado completo de la ciudad:
 Recursos: dinero, energía, agua, población, felicidad.
 Registro de eventos: ArrayList<String> (historial).

 */
public class Ciudad {

    private final String nombre;
    private double dinero;
    private double energia;       // balance MW neto del mes actual
    private double consumoAgua;   // agua consumida en el mes
    private int    poblacion;     // población acumulada este mes
    private int    felicidad;     // 0–100
    private int    mes;
    private final ArrayList<Edificio> edificios; // ESTA CLASE LA TIENE MARTA  CUANDO SE MERGE TIENE QUE SOLUCIONARSE
    //COLECCIONES (requisito: ArrayList)
   
    private final ArrayList<String>   registroEventos; // historial de la partida

    // Constructor 
    public Ciudad(String nombre, double dineroInicial) {
        this.nombre          = nombre;
        this.dinero          = dineroInicial;
        this.felicidad       = 70; 
        this.mes             = 1;
        this.registroEventos = new ArrayList<>();
        this.edificios       = new ArrayList<>();   
    }

  

    // ── Acción: Construir ─────────────────────────────────────────────
    /**
     * Añade un edificio al inventario descontando su costo.
     * @throws FondosInsuficientesException si no hay dinero suficiente
     */
    public void construir(Edificio edificio) throws FondosInsuficientesException {
        if (dinero < edificio.getCosto()) {
            throw new FondosInsuficientesException(
                "No tienes fondos suficientes para construir " + edificio.getNombre() +
                ". Necesitas $" + String.format("%.0f", edificio.getCosto()) +
                " | Disponible: $" + String.format("%.0f", dinero),
                edificio.getCosto(), dinero
            );
        }
        dinero -= edificio.getCosto();
        edificios.add(edificio);
        registroEventos.add("Mes " + mes + ": Construido " + edificio.getNombre());
        System.out.println("    ✓ " + edificio.getNombre() +
                           " construido. Costo: $" + String.format("%.0f", edificio.getCosto()));
    }

    

    
    
    // Metodos de acceso, mutacion de recursos
    
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

    // Reinicia los contadores de flujo al inicio de cada mes nuevo. 
    
    public void resetRecursosMensuales() {
        this.energia     = 0;
        this.poblacion   = 0;
        this.consumoAgua = 0;
        // felicidad y dinero persisten entre meses
    }

    //Getters
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
    public ArrayList<Edificio> getEdificios(){ 
    	return edificios; }
    
}