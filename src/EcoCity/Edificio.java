package EcoCity;

public abstract class Edificio {

  
    private String nombre;
    private double costo;           
    private double consumoEnergia;  
    private int    salud;           
    
    
    public Edificio(String nombre, double costo, double consumoEnergia) {
        this.nombre = nombre;
        this.costo  = costo;
        this.consumoEnergia = consumoEnergia;
        this.salud  = 100;
    }

    public abstract void aplicarEfectoMensual(Ciudad ciudad);

 
    public String getNombre()         {
      return nombre; 
}
    public double getCosto()          { 
    	return costo;
    	}
    public double getConsumoEnergia() { 
    	return consumoEnergia; 
    	}
    public int    getSalud()          { 
    	return salud; 
    	}

   
    public void setSalud(int salud) {
        this.salud = Math.max(0, Math.min(100, salud));
    }

 
    @Override
    public String toString() {
        String aviso = salud < 50 ? " [AVISO]" : "";
		return aviso;
    }
}