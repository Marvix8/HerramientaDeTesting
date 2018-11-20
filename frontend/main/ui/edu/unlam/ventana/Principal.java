package edu.unlam.ventana;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.unlam.herramienta_testing.Directory;
import edu.unlam.herramienta_testing.HerramientaTesting;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String OPERADORES = "Operadores";
	private static final String OPERANDOS = "Operandos";

	private JPanel contentPane;
	private JTextField textFieldDirectorioSeleccionado;
	private static HerramientaTesting ht;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Principal.class.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		setResizable(false);
		setTitle("Herramienta de Testing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(25, 25, 750, 715);

		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);

		setContentPane(contentPane);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		setJMenuBar(menuBar);

		JMenu menuAnalisis = new JMenu("Análisis");
		menuAnalisis.setBackground(Color.BLACK);
		menuAnalisis.setForeground(Color.WHITE);
		menuBar.add(menuAnalisis);

		textFieldDirectorioSeleccionado = new JTextField();
		textFieldDirectorioSeleccionado.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textFieldDirectorioSeleccionado.setEditable(false);
		textFieldDirectorioSeleccionado.setColumns(10);

		JLabel lblCodigoMetodoSeleccionado = new JLabel("Código del método seleccionado:");
		lblCodigoMetodoSeleccionado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigoMetodoSeleccionado.setForeground(Color.WHITE);

		JLabel lblseleccionArchivo = new JLabel("Seleccione un archivo de la lista:");
		lblseleccionArchivo.setForeground(Color.WHITE);
		lblseleccionArchivo.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblSeleccionClase = new JLabel("Seleccione una clase de la lista: ");
		lblSeleccionClase.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccionClase.setForeground(Color.WHITE);

		JLabel lblSeleccionMetodo = new JLabel("Seleccione un método de la lista:");
		lblSeleccionMetodo.setForeground(Color.WHITE);
		lblSeleccionMetodo.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLayeredPane layeredPane = new JLayeredPane();

		JLabel lblAnalisisMetodo = new JLabel("Análisis del Método");
		lblAnalisisMetodo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAnalisisMetodo.setForeground(new Color(255, 255, 255));

		JLabel lblDirectorioSeleccionado = new JLabel("Directorio Seleccionado:");
		lblDirectorioSeleccionado.setForeground(Color.WHITE);
		lblDirectorioSeleccionado.setFont(new Font("Tahoma", Font.BOLD, 11));

		JPanel panelCodigoAnalizado = new JPanel();
		panelCodigoAnalizado.setLayout(null);

		JTextArea textAreaCodigo = new JTextArea();
		textAreaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textAreaCodigo.setEditable(false);
		textAreaCodigo.setBounds(0, 0, 688, 250);

		JScrollPane scrollBarAreaCodigo = new JScrollPane(textAreaCodigo);
		scrollBarAreaCodigo.setBounds(0, 0, 713, 250);
		panelCodigoAnalizado.add(scrollBarAreaCodigo);

		JPanel panelArchivoLista = new JPanel();
		panelArchivoLista.setLayout(null);

		JList<String> listArchivo = new JList<String>();
		listArchivo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listArchivo.setBounds(0, 0, 440, 115);

		JScrollPane scrollBarListArchivo = new JScrollPane(listArchivo);
		scrollBarListArchivo.setBounds(0, 0, 442, 115);
		panelArchivoLista.add(scrollBarListArchivo);

		JPanel panelListClase = new JPanel();
		panelListClase.setLayout(null);

		JList<String> listClase = new JList<String>();
		listClase.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listClase.setBounds(0, 0, 200, 164);

		JScrollPane scrollPaneListClase = new JScrollPane(listClase);
		scrollPaneListClase.setBounds(0, 0, 200, 164);
		panelListClase.add(scrollPaneListClase);

		JPanel panelListMetodo = new JPanel();
		panelListMetodo.setLayout(null);

		JList<String> listMetodo = new JList<String>();
		listMetodo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listMetodo.setBounds(0, 0, 203, 163);

		JScrollPane scrollPaneListMetodo = new JScrollPane(listMetodo);
		scrollPaneListMetodo.setBounds(0, 0, 203, 163);
		panelListMetodo.add(scrollPaneListMetodo);

		JButton btnOperadores = new JButton(OPERADORES);
		btnOperadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarOperands(OPERADORES, ht.getListaOperadores());
			}
		});

		JButton btnOperandos = new JButton(OPERANDOS);
		btnOperandos.setVisible(false);
		btnOperandos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarOperands(OPERANDOS, ht.getListaOperandos());
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
								.createSequentialGroup().addContainerGap().addGroup(
										gl_contentPane
												.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
														.createParallelGroup(Alignment.LEADING, false)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(lblDirectorioSeleccionado)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(textFieldDirectorioSeleccionado,
																		GroupLayout.PREFERRED_SIZE, 286,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(90).addComponent(lblAnalisisMetodo,
																		GroupLayout.PREFERRED_SIZE, 119,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addGroup(gl_contentPane
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblseleccionArchivo)
																		.addGroup(gl_contentPane.createSequentialGroup()
																				.addGroup(gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(lblSeleccionClase)
																						.addComponent(panelListClase,
																								GroupLayout.PREFERRED_SIZE,
																								200,
																								GroupLayout.PREFERRED_SIZE))
																				.addGap(38)
																				.addGroup(gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(panelListMetodo,
																								GroupLayout.PREFERRED_SIZE,
																								203,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblSeleccionMetodo)))
																		.addComponent(panelArchivoLista,
																				GroupLayout.PREFERRED_SIZE, 442,
																				GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE,
																		266, GroupLayout.PREFERRED_SIZE))
														.addComponent(panelCodigoAnalizado, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblCodigoMetodoSeleccionado).addGap(297)
														.addComponent(btnOperadores).addGap(18)
														.addComponent(btnOperandos)))
								.addContainerGap(20, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDirectorioSeleccionado, GroupLayout.PREFERRED_SIZE, 16,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldDirectorioSeleccionado,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAnalisisMetodo))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
						.createSequentialGroup().addComponent(lblseleccionArchivo)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelArchivoLista, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblSeleccionClase)
								.addComponent(lblSeleccionMetodo))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelListClase, GroupLayout.PREFERRED_SIZE, 164,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(panelListMetodo, GroupLayout.PREFERRED_SIZE, 163,
										GroupLayout.PREFERRED_SIZE)))
						.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOperandos, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addComponent(lblCodigoMetodoSeleccionado)
						.addComponent(btnOperadores, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panelCodigoAnalizado, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
				.addGap(8)));

		JLabel lblLineasCodigo = new JLabel("Líneas del código totales");
		lblLineasCodigo.setForeground(Color.WHITE);
		lblLineasCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLineasCodigo.setBounds(0, 0, 266, 14);
		layeredPane.add(lblLineasCodigo);

		JLabel labelCantLineasCodigo = new JLabel("");
		labelCantLineasCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelCantLineasCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		labelCantLineasCodigo.setForeground(Color.ORANGE);
		labelCantLineasCodigo.setBounds(0, 16, 266, 14);
		layeredPane.add(labelCantLineasCodigo);

		JLabel labelCantComentadas = new JLabel("");
		labelCantComentadas.setHorizontalAlignment(SwingConstants.CENTER);
		labelCantComentadas.setForeground(Color.ORANGE);
		labelCantComentadas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelCantComentadas.setBounds(0, 57, 266, 14);
		layeredPane.add(labelCantComentadas);

		JLabel lblLineasComentadas = new JLabel("Líneas del código comentadas");
		lblLineasComentadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblLineasComentadas.setForeground(Color.WHITE);
		lblLineasComentadas.setBounds(0, 41, 266, 14);
		layeredPane.add(lblLineasComentadas);

		JLabel labelPorcentajeComentadas = new JLabel("");
		labelPorcentajeComentadas.setHorizontalAlignment(SwingConstants.CENTER);
		labelPorcentajeComentadas.setForeground(Color.ORANGE);
		labelPorcentajeComentadas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelPorcentajeComentadas.setBounds(0, 98, 266, 14);
		layeredPane.add(labelPorcentajeComentadas);

		JLabel lblPorcentajeDeLneas = new JLabel("Porcentaje de líneas de código comentadas");
		lblPorcentajeDeLneas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorcentajeDeLneas.setForeground(Color.WHITE);
		lblPorcentajeDeLneas.setBounds(0, 82, 266, 14);
		layeredPane.add(lblPorcentajeDeLneas);

		JLabel labelCantComplejidadCiclomatica = new JLabel("");
		labelCantComplejidadCiclomatica.setHorizontalAlignment(SwingConstants.CENTER);
		labelCantComplejidadCiclomatica.setForeground(Color.ORANGE);
		labelCantComplejidadCiclomatica.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelCantComplejidadCiclomatica.setBounds(0, 139, 266, 14);
		layeredPane.add(labelCantComplejidadCiclomatica);

		JLabel lblComplejidadCiclomatica = new JLabel("Complejidad Ciclomática");
		lblComplejidadCiclomatica.setHorizontalAlignment(SwingConstants.CENTER);
		lblComplejidadCiclomatica.setForeground(Color.WHITE);
		lblComplejidadCiclomatica.setBounds(0, 123, 266, 14);
		layeredPane.add(lblComplejidadCiclomatica);

		JLabel labelCantFanIn = new JLabel("");
		labelCantFanIn.setHorizontalAlignment(SwingConstants.CENTER);
		labelCantFanIn.setForeground(Color.ORANGE);
		labelCantFanIn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelCantFanIn.setBounds(0, 180, 266, 14);
		layeredPane.add(labelCantFanIn);

		JLabel lblFanIn = new JLabel("Fan In");
		lblFanIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanIn.setForeground(Color.WHITE);
		lblFanIn.setBounds(0, 164, 266, 14);
		layeredPane.add(lblFanIn);

		JLabel labelCantFanOut = new JLabel("");
		labelCantFanOut.setHorizontalAlignment(SwingConstants.CENTER);
		labelCantFanOut.setForeground(Color.ORANGE);
		labelCantFanOut.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelCantFanOut.setBounds(0, 221, 266, 14);
		layeredPane.add(labelCantFanOut);

		JLabel lblFanOut = new JLabel("Fan Out");
		lblFanOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanOut.setForeground(Color.WHITE);
		lblFanOut.setBounds(0, 205, 266, 14);
		layeredPane.add(lblFanOut);

		JLabel lblHalsteadLongitud = new JLabel("Halstead Longitud");
		lblHalsteadLongitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblHalsteadLongitud.setForeground(Color.WHITE);
		lblHalsteadLongitud.setBounds(0, 246, 266, 14);
		layeredPane.add(lblHalsteadLongitud);

		JLabel labelCantHalsteadLongitud = new JLabel("");
		labelCantHalsteadLongitud.setHorizontalAlignment(SwingConstants.CENTER);
		labelCantHalsteadLongitud.setForeground(Color.ORANGE);
		labelCantHalsteadLongitud.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelCantHalsteadLongitud.setBounds(0, 262, 266, 14);
		layeredPane.add(labelCantHalsteadLongitud);

		JLabel lblHalsteadVolumen = new JLabel("Halstead Volumen");
		lblHalsteadVolumen.setHorizontalAlignment(SwingConstants.CENTER);
		lblHalsteadVolumen.setForeground(Color.WHITE);
		lblHalsteadVolumen.setBounds(0, 280, 266, 14);
		layeredPane.add(lblHalsteadVolumen);

		JLabel labelCantHalsteadVolumen = new JLabel("");
		labelCantHalsteadVolumen.setHorizontalAlignment(SwingConstants.CENTER);
		labelCantHalsteadVolumen.setForeground(Color.ORANGE);
		labelCantHalsteadVolumen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelCantHalsteadVolumen.setBounds(0, 296, 266, 14);
		layeredPane.add(labelCantHalsteadVolumen);
		contentPane.setLayout(gl_contentPane);

		DefaultListModel<String> listaModelArchivo = new DefaultListModel<String>();
		listaModelArchivo
				.addElement("Seleccione una carpeta con archivos .java desde el menú Análisis -> Elegir Carpeta...");
		listArchivo.setModel(listaModelArchivo);

		DefaultListModel<String> listaModelClase = new DefaultListModel<String>();
		listClase.setModel(listaModelClase);

		DefaultListModel<String> listaModelMetodo = new DefaultListModel<String>();
		listMetodo.setModel(listaModelMetodo);

		listArchivo.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				if (listArchivo.getSelectedValue() != null) {
					String dirArchivo = textFieldDirectorioSeleccionado.getText().replaceAll("\\\\", "/") + "/"
							+ listArchivo.getSelectedValue();
					ht = new HerramientaTesting(dirArchivo);
					ht.obtenerClasesArchivo();
					ArrayList<String> clasesArchivo = ht.getClasesArchivo();

					textAreaCodigo.setText("");
					listaModelClase.removeAllElements();
					listaModelMetodo.removeAllElements();

					for (String str : clasesArchivo) {
						listaModelClase.addElement(str);
					}
				}
			}
		});

		listClase.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				if (listClase.getSelectedValue() != null) {
					ht.setClassName(listClase.getSelectedValue());
					ht.obtenerMetodosClase();
					textAreaCodigo.setText("");
					ArrayList<String> metodosClase = ht.getMetodosClase();
					listaModelMetodo.removeAllElements();
					textAreaCodigo.setText("");
					for (String str : metodosClase) {
						listaModelMetodo.addElement(str);
					}
				}
			}
		});

		listMetodo.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				if (listMetodo.getSelectedValue() != null) {
					textAreaCodigo.setText("");
					ht.setMethod(listMetodo.getSelectedValue());
					ht.resolver();
					ArrayList<String> lineasMetodoProcesado = ht.getLineasMetodoProcesado();
					for (String str : lineasMetodoProcesado) {
						textAreaCodigo.append(str + "\n");
					}

					labelCantLineasCodigo.setText(String.valueOf(ht.getCantidadLineas()));
					labelCantComentadas.setText(String.valueOf(ht.getCantidadComentarios()));
					labelPorcentajeComentadas.setText(String.valueOf(ht.getPorcentajeComentarios()));
					labelCantComplejidadCiclomatica.setText(String.valueOf(ht.getComplejidadCiclomatica()));

					labelCantFanIn.setText(String.valueOf(ht.getFanIn()));
					labelCantFanOut.setText(String.valueOf(ht.getFanOut()));

					labelCantHalsteadLongitud.setText(String.valueOf(ht.getLongitudHalstead()));
					labelCantHalsteadVolumen.setText(String.valueOf(ht.getVolumenHalstead()));
				}
			}
		});

		JMenuItem opcionElegir = new JMenuItem("Elegir Carpeta");
		opcionElegir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String directorio = obtenerDirectorio();
				Directory directory = new Directory(directorio);
				ArrayList<String> archivo = directory.getFilesList();
				textFieldDirectorioSeleccionado.setText(directorio);
				listaModelArchivo.removeAllElements();
				listaModelClase.removeAllElements();
				listaModelMetodo.removeAllElements();
				textAreaCodigo.setText("");

				if (archivo != null) {
					for (String str : archivo) {
						listaModelArchivo.addElement(str);
					}
				}
			}
		});
		menuAnalisis.add(opcionElegir);

		JMenuItem opcionSalir = new JMenuItem("Salir");
		opcionSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				System.exit(0);
			}
		});
		menuAnalisis.add(opcionSalir);

	}

	private String obtenerDirectorio() {
		String directorioObtenido;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setDialogTitle("Seleccione el directorio");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		fileChooser.setAcceptAllFileFilterUsed(false);

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			directorioObtenido = fileChooser.getSelectedFile().getAbsolutePath().toString().replaceAll("\\\\", "/");
			return directorioObtenido;
		} else {
			return "";
		}
	}

	/**
	 * Carga la ventana mostrando los operadores u operandos involucrados en el
	 * cálculo del Halstead. <br>
	 * 
	 * @param titulo
	 *            Título de la ventana. <br>
	 * @param listaOperands
	 *            Lista de los operadores u operandos a mostrar. <br>
	 */
	private void cargarOperands(final String titulo, final Set<String> listaOperands) {
		Operands operands = new Operands(titulo, listaOperands);
		operands.setVisible(true);
	}
}
