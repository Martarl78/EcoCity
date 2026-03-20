package EcoCity;

public class CentralNuclear extends EdificioIndustrial implements Mantenimiento {

	private int peligro = 20; 
	private int alerta = 35;
	private double CostoReparacion = 50.0;
	private int mesualidas = 4;
	
	public CentralNuclear(String nombre) {
		super(nombre,15_000,0,500);
	}
	
	
	// Mensualidad
	@Override
	public void aplicarMensualidad(Ciudad ciudad) {
		if(salud <= peligro) {
			throw new ExplosionNuclear(nombre);
		}
		super.aplicarMensualidad(ciudad);
		desgaste(mesualidad);
		
		if (salud <= alerta) {
			System.out.println("Alerta en : " + nombre + " salud " + salud);
		}
	}
	
	
	// Recursos
	@Override
	public String getTipoRecurso() {
		return "Energia Nuclear";
	}
	
	
	// Mantenimiento
	
	@Override
	public void reparar(Ciudad ciudad) throws FondosInsuficientes {
		int dagno = 100 - salud;
		if (dagno == 0) {
			system.out.println("Edificio " + nombre + " esta dañado");
			return
		}
		
		double costo = dagno * CostoReparacion;
		if (ciudad.getDinero() < costo) {
			throw new FondosInsuficientes();
		}
		
		ciudad.addDinero(-costo);
		setSalud(100);
		System.out.println(nombre + " reparado ");
	}
	
	@Override
	public String toString() {
		return "Central Nuclear " + super.toString();
	}
	
}

