public class Triangulo {

	private int lado1;
	private int lado2;
	private int lado3;
	private String tipoTriangulo;
	
	public Triangulo(int l1, int l2, int l3) {
		this.lado1 = l1;
		this.lado2 = l2;
		this.lado3 = l3;
	}
	
	private boolean ladosValidos() {
		if (this.lado1 > 0 
				&& this.lado2 > 0 
				&& this.lado3 > 0)
			return true;		
		return false;
	}
	
	private boolean trianguloValido() {
		if (ladosValidos()) {
			if (this.lado1 < this.lado2 + this.lado3 
					&& this.lado2 < this.lado1 + this.lado3 
					&& this.lado3 < this.lado1 + this.lado2
				)
				return true;
		}
		return false;
	}
	
	public String obtenerTipo() {
		if ( trianguloValido() ){
			if (this.lado1 == this.lado2 
					&& this.lado1 == this.lado3) {
				this.tipoTriangulo = "EQUILATERO";
			} 
			// soy un comentario bonito
			else if(this.lado1 != this.lado2 
					&& this.lado1 != this.lado3 
					&& this.lado2 != this.lado3) {
				this.tipoTriangulo = "ESCALENO";
			} else {
				this.tipoTriangulo = "ISOSCELES";
			}
			return this.tipoTriangulo;
		}
		
		return "INVALIDO";
	}
	
	
}