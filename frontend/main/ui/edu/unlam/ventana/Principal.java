package edu.unlam.ventana;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldArchivo;
	private JTextField textFieldClase;
	private JTextField textFieldMetodo;
	private JTextField textFieldCodigo;
	private static Principal frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Principal();
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
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Herramienta de Testing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(25, 25, 750, 700);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu menuAnalisis = new JMenu("Análisis");
		menuAnalisis.setBackground(Color.BLACK);
		menuAnalisis.setForeground(Color.WHITE);
		menuBar.add(menuAnalisis);
		
		JMenuItem opcionElegir = new JMenuItem("Elegir Carpeta");
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
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		
		setContentPane(contentPane);
		
		JLabel lblseleccionArchivo = new JLabel("Seleccione un archivo de la lista:");
		lblseleccionArchivo.setForeground(Color.WHITE);
		lblseleccionArchivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldArchivo = new JTextField();
		textFieldArchivo.setEditable(false);
		textFieldArchivo.setColumns(10);
		
		JLabel lblSeleccionClase = new JLabel("Seleccione una clase de la lista: ");
		lblSeleccionClase.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccionClase.setForeground(Color.WHITE);
		
		JLabel lblSeleccionMetodo = new JLabel("Seleccione un método de la lista:");
		lblSeleccionMetodo.setForeground(Color.WHITE);
		lblSeleccionMetodo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldClase = new JTextField();
		textFieldClase.setEditable(false);
		textFieldClase.setColumns(10);
		
		textFieldMetodo = new JTextField();
		textFieldMetodo.setEditable(false);
		textFieldMetodo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Código del método seleccionado:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setForeground(Color.WHITE);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setEditable(false);
		textFieldCodigo.setColumns(10);
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		JLabel lblNewLabel = new JLabel("Análisis del Método");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldCodigo, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblseleccionArchivo)
								.addComponent(textFieldArchivo)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSeleccionClase)
										.addComponent(textFieldClase, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
									.addGap(35)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSeleccionMetodo)
										.addComponent(textFieldMetodo, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(81)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(lblseleccionArchivo)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textFieldArchivo, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSeleccionClase)
								.addComponent(lblSeleccionMetodo))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldMetodo, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
								.addComponent(textFieldClase, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))
						.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
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
		
		JLabel label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.ORANGE);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(0, 139, 266, 14);
		layeredPane.add(label_4);
		
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

	}
}
