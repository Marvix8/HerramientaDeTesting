package ar.edu.unlam.analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizador {

	/**
	 * Map de los archivos Java y su código. <br>
	 */
	private Map<String, String> clases = new HashMap<String, String>();

	/**
	 * Métodos de cada clase. <br>
	 */
	private Map<String, List<String>> metodos = new HashMap<String, List<String>>();
	/**
	 * Path del proyecto a analizar. <br>
	 */
	private String pathProyecto;
	/**
	 * Regex de archivos de java. Incluye los tests. <br>
	 */
	private static final Pattern REGEX_CLASE = Pattern.compile("(\\w*)\\.java");

	/**
	 * Regex de definición de métodos. <br>
	 */
	private static final Pattern REGEX_DEF_METODO = Pattern.compile(
			"(?:public|private|protected)?\\s*(?:\\w*|\\<|\\>)\\s*(\\w*\\s*\\(.*\\))\\s*(?:throws\\s+\\w+)?\\s*\\{");

	/**
	 * Regex de palabras reservadas. <br>
	 */
	private static final Pattern REGEX_RESERVED = Pattern.compile("\\s*(?:if|while|for|catch|switch)\\s*");

	public Analizador(final String pathProyecto) {
		this.pathProyecto = pathProyecto;
	}

	public void analizarProyecto() {
		// Obtenemos todas clases involucradas.
		this.leerArchivos(this.pathProyecto);
		// Por cada clase obtenemos los métodos.
		for (String clase : this.clases.keySet()) {
			this.obtenerMetodos(clase);
		}
	}

	/**
	 * Obtiene todas las clases en un proyecto. <br>
	 */
	public void obtenerClases() {
		this.leerArchivos(this.pathProyecto);
		// for (String clase : this.clases.keySet()) {
		// System.out.println(clase);
		// }
	}

	/**
	 * Obtiene todos los métodos de una clase. <br>
	 * 
	 * @param clase
	 *            Clase a analizar. <br>
	 */
	public void obtenerMetodos(final String clase) {
		if (this.clases.containsKey(clase)) {
			Matcher matcherDefinicionMetodo = REGEX_DEF_METODO.matcher(this.clases.get(clase));
			List<String> metodos = new ArrayList<String>();
			System.out.println("# " + clase);
			while (matcherDefinicionMetodo.find()) {
				if (!REGEX_RESERVED.matcher(matcherDefinicionMetodo.group()).find()) {
					System.out.println("\t-" + matcherDefinicionMetodo.group(1));
					metodos.add(matcherDefinicionMetodo.group(1));
				}
			}
			this.metodos.put(clase, metodos);
		}
	}

	/**
	 * Obtiene todos los archivos Java en un directorio. <br>
	 * 
	 * @param path
	 *            Path actual a analizar. <br>
	 */
	private void leerArchivos(final String path) {
		String texto;
		byte[] data;
		FileInputStream archivo;
		File[] archivos = new File(path).listFiles();
		for (int i = 0; i < archivos.length; i++) {
			if (archivos[i].isFile() && REGEX_CLASE.matcher(archivos[i].getName()).find()) {
				try {
					archivo = new FileInputStream(archivos[i]);
					data = new byte[(int) archivos[i].length()];
					archivo.read(data);
					archivo.close();
					texto = new String(data, "UTF-8");
					this.clases.put(archivos[i].getName(), texto);
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
			if (archivos[i].isDirectory()) {
				this.leerArchivos(archivos[i].getAbsolutePath());
			}
		}
	}
}