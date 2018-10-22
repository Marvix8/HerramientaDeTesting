package edu.unlam.ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Clase que muestra los operadores o los operandos de Halstead. <br>
 */
public class Operands extends JFrame {

	/**
	 * Serial. <br>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Clase que muestra la ventana y su configuración. <br>
	 * 
	 * @param titulo
	 *            Título que aparece en la ventana. <br>
	 * @param listaOperands
	 *            Lista de operadores u operandos. <br>
	 */
	public Operands(final String titulo, final Set<String> listaOperands) {
		setResizable(false);
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 436, 267);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);

		panelPrincipal.setBackground(Color.LIGHT_GRAY);

		getContentPane().add(panelPrincipal, BorderLayout.CENTER);

		JTextArea textOperands = new JTextArea();
		textOperands.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textOperands.setEditable(false);
		textOperands.setBounds(0, 0, 688, 250);

		JScrollPane scrollBarOperands = new JScrollPane(textOperands);
		scrollBarOperands.setBounds(0, 0, 430, 238);
		panelPrincipal.add(scrollBarOperands);

		// Obtenemos los operadores u operandos del método.
		for (String operand : listaOperands) {
			textOperands.append(operand.trim() + "\n");
		}
	}
}
