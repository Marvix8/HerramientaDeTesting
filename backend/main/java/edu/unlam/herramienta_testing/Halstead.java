package edu.unlam.herramienta_testing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Halstead {
	 /**
     * Nombre de la métrica. 
     */ 
    private static final String NOMBRE_METRICAS = "Metricas Halstead"; 
     
    /**
     * La longitud de Halstead que tiene el archivo. 
     */ 
    private Integer longitudHalstead; 
     
    /**
     * El volumen de Halstead que tiene el archivo. 
     */ 
    private Double volumenHalstead; 
     
    /**
     * Valores enteros que se usarán para calcular las métricas de Halstead 
     */ 
    private Integer cantidadOperadoresUnicos = 0; 
    private Integer cantidadOperadores = 0; 
    private Integer cantidadOperandosUnicos = 0; 
    private Integer cantidadOperandos = 0; 
  
    /**
     * Listado de palabras que consideramos operadores 
     */ 
 String operadores [] = {"if", "else", "case", "default", "for", "while", "catch", "throw", 
       "+", "-", "*", "/", "==", "!=", "=", "<=", ">=", "<", ">", 
       "&&", "||", "and", "or", "equal to"}; 
 /**
     * Set que contendrá los operadores del código fuente 
     */ 
 Set<String> setOperadores = new HashSet<String>(); 
  
 /**
     * Set que contendrá los operandos del código fuente 
     */ 
 Set<String> setOperandos = new HashSet<String>(); 
  
    /**
     * Constructor por defecto. 
     */ 
    public Halstead() { } 
 
    /**
     * Construye una métrica de Halstead a partir de una cantidad dada. 
     * @param longitudHalstead 
     * @param volumenHalstead 
     */ 
    @SuppressWarnings("unused")
	private Halstead(int longitudHalstead, double volumenHalstead) { 
        this.longitudHalstead = longitudHalstead; 
        this.volumenHalstead = volumenHalstead; 
    } 
     
    public void procesar(ArrayList<String>  lineasArchivo) { 
     
     // Inicializo las metricas en 0 
     this.longitudHalstead = 0; 
     this.volumenHalstead = 0.0; 
           
        for (String linea : lineasArchivo) { 
            
             
            buscarOperadores(linea); 
            buscarOperandos(linea); 
        } 
         
        this.cantidadOperadoresUnicos = this.setOperadores.size(); 
        this.cantidadOperandosUnicos = this.setOperandos.size(); 
         
        this.longitudHalstead = this.cantidadOperadores + this.cantidadOperandos; 
        this.volumenHalstead = (this.longitudHalstead * (Math.log(this.cantidadOperadoresUnicos.doubleValue() +  
                 Math.log(this.cantidadOperandosUnicos.doubleValue())) / Math.log(2))); 
             // Hago esa cuenta para calcular el log en base 2. log en base 2 = log(x) / log(2) 
    } 
     
    void buscarOperadores(String linea) { 
     for(int i = 0; i < this.operadores.length - 1; i++) 
      if(linea.contains(this.operadores[i])) { 
       this.cantidadOperadores += 1; 
       this.setOperadores.add(this.operadores[i]); 
      } 
    }
    
    
    public void procesar(ArrayList<String> lineasArchivo, int inicio, int fin) {
    	this.longitudHalstead = 0; 
        this.volumenHalstead = 0.0;
        this.cantidadOperadores = 0;
        this.cantidadOperandos = 0;
        this.cantidadOperadoresUnicos = 0;
        this.cantidadOperandosUnicos = 0;
        setOperadores.clear();
        setOperandos.clear();
              
           for (int i = inicio; i < fin; i++) { 
               
               buscarOperadores(lineasArchivo.get(i)); 
               buscarOperandos(lineasArchivo.get(i)); 
           } 
            
           this.cantidadOperadoresUnicos = this.setOperadores.size(); 
           this.cantidadOperandosUnicos = this.setOperandos.size();
           
           this.longitudHalstead = this.cantidadOperadores + this.cantidadOperandos; 
           this.volumenHalstead = (this.longitudHalstead * (Math.log(this.cantidadOperadoresUnicos.doubleValue() +  
                    Math.log(this.cantidadOperandosUnicos.doubleValue())) / Math.log(2))); 
                // Hago esa cuenta para calcular el log en base 2. log en base 2 = log(x) / log(2) 
    }
     
    void buscarOperandos(String linea) { 
     String operandos[] = linea.split("^.*(if|else|case|default|for|while|catch|throw|\\+|-|\\*|\\/" 
              + "|={1}?|!=|={2}?|<=|>=|<{1}?|>{1}?|&&|\\|{2}?|and|or|equal to).*"); 
     for(int i = 0; i < operandos.length ; i++) 
     { 
      this.cantidadOperandos += 1; 
      this.setOperandos.add(operandos[i]); 
     } 
    } 
     
     
    public String getNombre() { 
        return NOMBRE_METRICAS; 
    } 
     
    
    public String getValor() { 
        if (this.longitudHalstead == null || this.volumenHalstead == null) 
            throw new RuntimeException("Las metricas de Hasltead todavía no se calcularon"); 
 
        return String.format("Longitud: %d - Volumen: %.2f - (Operadores %d - Operandos %d)",  
               longitudHalstead, volumenHalstead, cantidadOperadores, cantidadOperandos); 
    }

	public Integer getLongitudHalstead() {
		return longitudHalstead;
	}

	public Double getVolumenHalstead() {
		return volumenHalstead;
	} 
 
    
     
}
