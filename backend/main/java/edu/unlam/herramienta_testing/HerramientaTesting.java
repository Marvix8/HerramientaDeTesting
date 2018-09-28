package edu.unlam.herramienta_testing;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HerramientaTesting {
	
	FileReader fileReader;
	Scanner scanner;
	
	ArrayList <String> lineasMetodoProcesado;
	ArrayList <String> fileContent;
	
	String method;
	String className;
	
	String[] archivosDirectorio;
	ArrayList <String> clasesArchivo;
	ArrayList <String> metodosClase;
	
	private int complejidadCiclomatica;
	private int cantidadComentarios;
	private int cantidadLineas;
	private String porcentajeComentarios;
	private Halstead halstead;
	private Integer longitudHalstead; 
	private String volumenHalstead;
	private int lineaFin;
	

	private static final String []KEYWORDS = {"if", "while", "case", "for", "switch", "do", "continue", "break", "&&","||", "?", ":", "catch", "finally", "throw", "throws"};
	private static final String TIPO_ARCHIVO = ".java";
	private static final String CLASE_REGEX = "(?:\\S*)\\s*(?:class) (\\w*)\\s*\\S*";
	private static final String METODO_REGEX = "\\s([a-z][A-Za-z0-9]*)\\s*\\(([A-Z][A-Za-z0-9\\<\\>]*\\s+[a-z][A-Za-z0-9]*,?)*\\)";
	
	public HerramientaTesting(String filename) {
		
		try {
			fileReader = new FileReader(filename);
			scanner = new Scanner(fileReader);
			this.fileContent = new ArrayList<String>();
			this.lineasMetodoProcesado = new ArrayList<String>();
			this.clasesArchivo = new ArrayList<String>();
			this.metodosClase = new ArrayList<String>();
			this.complejidadCiclomatica = 1;
			this.cantidadComentarios = 0;
			this.cantidadLineas = 0;
			this.porcentajeComentarios = "0,00";
			this.longitudHalstead = 0;
			this.volumenHalstead = "0,00";
			this.halstead = new Halstead();			
			
			while(scanner.hasNextLine()) {
				fileContent.add(scanner.nextLine());	
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				scanner.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// Retorna todos los archivos de extensión .java del directorio seleccionado.
	public void obtenerArchivosCarpeta(String path) {	
		FilenameFilter filtro = new FilenameFilter() {
	    @Override
	    public boolean accept(File file, String name) {
		    if (name.endsWith(TIPO_ARCHIVO)) {
		        return true;
		    } else {
		        return false;
		    }
	    }};
		
		File directorio = new File(path);
		archivosDirectorio = directorio.list(filtro);
	}
	
	// Método que obtiene todas las clases de un archivo
	public void obtenerClasesArchivo() {
		Pattern patronClase =  Pattern.compile(CLASE_REGEX);
		Matcher matcherClase = null;
		clasesArchivo.clear();
		for (String str : fileContent) {
			matcherClase = patronClase.matcher(str);
			if(matcherClase.find()) {
				clasesArchivo.add(matcherClase.group(1));
			}
		}
	}
	
	public void obtenerMetodosClase() {
		Pattern patronMetodo =  Pattern.compile(METODO_REGEX);
		Matcher matcherMetodo = null; 
		metodosClase.clear();
		for (String str : fileContent) {
			matcherMetodo = patronMetodo.matcher(str);
			if(matcherMetodo.find()) {
				metodosClase.add(matcherMetodo.group(1));
			}
		}
	}
	
	public void resolver() {
		int numeroLinea = 0;
		lineasMetodoProcesado.clear();
		this.cantidadComentarios = 0;
		this.complejidadCiclomatica = 1;
		this.cantidadLineas = 0;
		this.longitudHalstead = 0;
		this.volumenHalstead = "0,00";
		
		while(!fileContent.get(numeroLinea).contains(className)) {
			numeroLinea++;
		}
		numeroLinea++;

		while(!fileContent.get(numeroLinea).contains(method)) {
			numeroLinea++;
		}

		mcCabe(numeroLinea);
		
		halstead.procesar(fileContent, numeroLinea, this.lineaFin);
		
		calcularPorcentajeComentarios();
		
		DecimalFormat df = new DecimalFormat("0.00");
		this.volumenHalstead = df.format(halstead.getVolumenHalstead());
		this.longitudHalstead = halstead.getLongitudHalstead();
		
	}
	
	private void mcCabe(int numeroLinea) {
		int contadorLlaves = 0;
		String linea = "";
	
		do {
			lineasMetodoProcesado.add(fileContent.get(numeroLinea));
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
		this.lineaFin = numeroLinea;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	private void calcularPorcentajeComentarios() {
		DecimalFormat df = new DecimalFormat("0.00");
		this.porcentajeComentarios = df.format(Double.valueOf(100 * this.cantidadComentarios) / Double.valueOf(this.cantidadLineas));
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

	public String getPorcentajeComentarios() {
		return porcentajeComentarios;
	}

	public void setPorcentajeComentarios(String porcentajeComentarios) {
		this.porcentajeComentarios = porcentajeComentarios;
	}

	public ArrayList<String> getLineasMetodoProcesado() {
		return lineasMetodoProcesado;
	}

	public void setLineasMetodoProcesado(ArrayList<String> lineasMetodoProcesado) {
		this.lineasMetodoProcesado = lineasMetodoProcesado;
	}

	public String[] getArchivosDirectorio() {
		return archivosDirectorio;
	}

	public void setArchivosDirectorio(String[] archivosDirectorio) {
		this.archivosDirectorio = archivosDirectorio;
	}

	public ArrayList<String> getClasesArchivo() {
		return clasesArchivo;
	}

	public void setClasesArchivo(ArrayList<String> clasesArchivo) {
		this.clasesArchivo = clasesArchivo;
	}

	public ArrayList<String> getMetodosClase() {
		return metodosClase;
	}

	public void setMetodosClase(ArrayList<String> metodosClase) {
		this.metodosClase = metodosClase;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getLongitudHalstead() {
		return longitudHalstead;
	}

	public String getVolumenHalstead() {
		return volumenHalstead;
	}	
	
	
	
}


