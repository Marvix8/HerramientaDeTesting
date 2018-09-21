package edu.unlam.herramienta_testing;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HerramientaTesting {
	
	FileReader fileReader;
	Scanner scanner;
	
	ArrayList <String> metodoProcesado;
	ArrayList <String> fileContent;
	
	String method;
	String className;
	
	private int complejidadCiclomatica;
	private int cantidadComentarios;
	private int cantidadLineas;
	private double porcentajeComentarios;
	

	String []KEYWORDS = {"if", "while", "case", "for", "switch", "do", "continue", "break", "&&","||", "?", ":", "catch", "finally", "throw", "throws"};
	

	public HerramientaTesting(String filename, String className, String method) {
		
		try {
			fileReader = new FileReader(filename);
			scanner = new Scanner(fileReader);
			this.fileContent = new ArrayList<String>();
			this.metodoProcesado = new ArrayList<String>();
			this.className = className;
			this.method = method;
			this.complejidadCiclomatica = 1;
			this.cantidadComentarios = 0;
			this.cantidadLineas = 0;
			this.porcentajeComentarios = 0;
			
			while(scanner.hasNextLine()) {
				fileContent.add(scanner.nextLine());	
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				scanner.close();
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void resolver() {
		int numeroLinea = 0;
		
		while(!fileContent.get(numeroLinea).contains(className)) {
			numeroLinea++;
		}
		numeroLinea++;

		while(!fileContent.get(numeroLinea).contains(method)) {
			numeroLinea++;
		}

		mcCabe(numeroLinea);
		calcularPorcentajeComentarios();
	}
	
	public void mostrarResultado() {
		for(String str : this.getMetodoProcesado()) {
			System.out.println(str);
		}
		System.out.println("La complejidad ciclomatica del metodo " + method + " de la clase " + className + " es: " + this.getComplejidadCiclomatica());
		System.out.println("Cantidad de líneas: " + this.getCantidadLineas());
		System.out.println("Cantidad de comentarios: " + this.getCantidadComentarios());
		System.out.println("Porcentaje de comentarios: " + this.getPorcentajeComentarios());
	}
	
	private void mcCabe(int numeroLinea) {
		int contadorLlaves = 0;
		String linea = "";
		
		do {
			metodoProcesado.add(fileContent.get(numeroLinea));
			linea = fileContent.get(numeroLinea);
			String[] palabras = linea.split(" |\\t|\\(|\\)");
			
			for(String pal : palabras) {
				for (int j = 0; j < KEYWORDS.length; j++) {
					if (pal.equals("//")) {
						this.cantidadComentarios ++;
						break;
					} else if (KEYWORDS[j].equals(pal)) {
						this.complejidadCiclomatica ++;
					} 
				}
				if("{".contains(pal)) {
					contadorLlaves ++;
				} if("}".contains(pal)) {
					contadorLlaves --;
				}
			}
			this.cantidadLineas ++;
			numeroLinea++;
			
		} while(!fileContent.isEmpty() && contadorLlaves != 0);
	}
	
	private void calcularPorcentajeComentarios() {
		this.porcentajeComentarios = Double.valueOf(100 * this.cantidadComentarios) / Double.valueOf(this.cantidadLineas);
	}

	public int getComplejidadCiclomatica() {
		return complejidadCiclomatica;
	}

	public void setComplejidadCiclomatica(int complejidadCiclomatica) {
		this.complejidadCiclomatica = complejidadCiclomatica;
	}

	public int getCantidadComentarios() {
		return cantidadComentarios;
	}

	public void setCantidadComentarios(int cantidadComentarios) {
		this.cantidadComentarios = cantidadComentarios;
	}

	public int getCantidadLineas() {
		return cantidadLineas;
	}

	public void setCantidadLineas(int cantidadLineas) {
		this.cantidadLineas = cantidadLineas;
	}

	public double getPorcentajeComentarios() {
		return porcentajeComentarios;
	}

	public void setPorcentajeComentarios(double porcentajeComentarios) {
		this.porcentajeComentarios = porcentajeComentarios;
	}

	public ArrayList<String> getMetodoProcesado() {
		return metodoProcesado;
	}

	public void setMetodoProcesado(ArrayList<String> metodoProcesado) {
		this.metodoProcesado = metodoProcesado;
	}
	
	
}


