package EcoCity;

public interface Mantenimiento {

	
	public void Reparaciones() throws FondosInsuficientes; // Para excepciones verificadas
	
	public void desgastar(int cantidad);
}
