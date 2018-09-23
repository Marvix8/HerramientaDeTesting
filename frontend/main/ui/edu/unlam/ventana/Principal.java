package edu.unlam.ventana;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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

import edu.unlam.herramienta_testing.Directory;
import edu.unlam.herramienta_testing.HerramientaTesting;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JList<String> listClase;
	private JList<String> listMetodo;
	private JTextArea textAreaCodigo;
	private JTextField textFieldDirectorioSeleccionado;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		setResizable(false);
		setTitle("Herramienta de Testing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(25, 25, 750, 700);
		
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
		
		JList<String> listArchivo = new JList<String>();
		listArchivo.setFont(new Font("Tahoma", Font.PLAIN, 11));

		listClase = new JList<String>();
		listClase.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		listMetodo = new JList<String>();
		listMetodo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		textAreaCodigo = new JTextArea();
		textAreaCodigo.setEditable(false);
		textAreaCodigo.setColumns(10);
		
		JScrollPane scrollBar = new JScrollPane(textAreaCodigo);
		
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDirectorioSeleccionado)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldDirectorioSeleccionado, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
						.addComponent(textAreaCodigo, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
						.addComponent(lblCodigoMetodoSeleccionado)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblseleccionArchivo)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSeleccionClase)
										.addComponent(listClase, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
									.addGap(35)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSeleccionMetodo)
										.addComponent(listMetodo, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)))
								.addComponent(listArchivo, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(81)
									.addComponent(lblAnalisisMetodo, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDirectorioSeleccionado, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldDirectorioSeleccionado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblseleccionArchivo)
						.addComponent(lblAnalisisMetodo))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(listArchivo, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSeleccionClase)
								.addComponent(lblSeleccionMetodo))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(listMetodo, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
								.addComponent(listClase, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblCodigoMetodoSeleccionado)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textAreaCodigo, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
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
		
		JLabel labelCantHalsteadVolument = new JLabel("");
		labelCantHalsteadVolument.setHorizontalAlignment(SwingConstants.CENTER);
		labelCantHalsteadVolument.setForeground(Color.ORANGE);
		labelCantHalsteadVolument.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelCantHalsteadVolument.setBounds(0, 296, 266, 14);
		layeredPane.add(labelCantHalsteadVolument);
		contentPane.setLayout(gl_contentPane);
		
		DefaultListModel<String> listaModelArchivo = new DefaultListModel<String>();
		listaModelArchivo.addElement( "Seleccione una carpeta con archivos .java desde el menú Análisis -> Elegir Carpeta...");
		listArchivo.setModel(listaModelArchivo);
		
		JMenuItem opcionElegir = new JMenuItem("Elegir Carpeta");
		opcionElegir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String directorio = obtenerDirectorio();
				Directory directory = new Directory(directorio);
				ArrayList<String> archivo = directory.getFilesList();
				textFieldDirectorioSeleccionado.setText(directorio);
				if(archivo != null) {
					listaModelArchivo.removeAllElements();
					for (String str : archivo) {
						listaModelArchivo.addElement(str);
					}
				}
			}
		});
		menuAnalisis.add(opcionElegir);
		
		DefaultListModel<String> listaModelClase = new DefaultListModel<String>();
		listClase.setModel(listaModelClase);
		
		listArchivo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String dirArchivo = textFieldDirectorioSeleccionado.getText() + "\\" + listArchivo.getSelectedValue();
				HerramientaTesting ht = new HerramientaTesting(dirArchivo, "Triangulo");
				ht.obtenerClasesArchivo();
				ArrayList<String> clasesArchivo = ht.getClasesArchivo();
				listaModelClase.removeAllElements();
				for(String str : clasesArchivo) {
					listaModelClase.addElement(str);
				}
			}
		});
		
		DefaultListModel<String> listaModelMetodo = new DefaultListModel<String>();
		listMetodo.setModel(listaModelMetodo);
		
		listClase.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String dirArchivo = textFieldDirectorioSeleccionado.getText() + "\\" + listArchivo.getSelectedValue();
				HerramientaTesting ht = new HerramientaTesting(dirArchivo, "Triangulo");
				ht.obtenerMetodosClase();
				ArrayList<String> metodosClase = ht.getMetodosClase();
				listaModelMetodo.removeAllElements();
				for(String str : metodosClase) {
					listaModelMetodo.addElement(str);
				}
			}
		});
		
		listMetodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String dirArchivo = textFieldDirectorioSeleccionado.getText() + "\\" + listArchivo.getSelectedValue();
				HerramientaTesting ht = new HerramientaTesting(dirArchivo, "Triangulo");
				ht.setMethod(listMetodo.getSelectedValue());
				ht.resolver();
				ArrayList<String> lineasMetodoProcesado = ht.getLineasMetodoProcesado();
				textAreaCodigo.setText("");
				for(String str : lineasMetodoProcesado) {
					textAreaCodigo.append(str + "\n");
				}
				
				labelCantLineasCodigo.setText(String.valueOf(ht.getCantidadLineas()));
				labelCantComentadas.setText(String.valueOf(ht.getCantidadComentarios()));
				labelPorcentajeComentadas.setText(String.valueOf(ht.getPorcentajeComentarios()));
				labelCantComplejidadCiclomatica.setText(String.valueOf(ht.getComplejidadCiclomatica()));
				
			}
		});
		
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
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Seleccione el directorio");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    	directorioObtenido = chooser.getSelectedFile().toString();
	    	return directorioObtenido;
	    } else {
	    	return null;
	    }
	}
}
