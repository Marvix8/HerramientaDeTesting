package ar.edu.unlam.analyzer;

import org.junit.Test;

import ar.edu.unlam.analyzer.Analizador;

public class AnalizadorTest {

	private static final String PATH = "C:/Users/Octi/workspaceAnSoft/Asistente_Virtual";

	@Test
	public void test() {
		Analizador analizador = new Analizador(PATH);
		analizador.analizarProyecto();
		// analizador.obtenerClases();
		// analizador.obtenerMetodos("Colombiano.java");

	}

}
