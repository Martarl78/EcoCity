package EcoCity;

public class FondosInsuficientes extends Exception {

	private double fondosNecesarios;
	private double fondosDisponibles;
	
	public FondosInsuficientes(String mensaje,double fondosNecesarios, double fondosDisponibles) {
		super(mensaje);
		this.fondosNecesarios = fondosNecesarios;
		this.fondosDisponibles = fondosDisponibles;
		
	}
	 public FondosInsuficientes(String mensaje) {
	        super(mensaje);
	        this.fondosNecesarios = 0;
	        this.fondosDisponibles = 0;
	    }

	 public double getFondosNecesarios()   { 
		 return fondosNecesarios; 
		 }
	    public double getFondosDisponibles()  { 
	    	return fondosDisponibles; 
	    	}
	
}
