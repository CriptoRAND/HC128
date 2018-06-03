package ar.edu.unlam.cripto.cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Cliente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JFileChooser fc;
	private ServicioTransmision servicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente window = new Cliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		fc = new JFileChooser();
		servicio=new ServicioTransmision();
		JButton btnSubirImagen = new JButton("Subir Imagen");
		btnSubirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == btnSubirImagen) {
					int returnVal = fc.showOpenDialog(Cliente.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						JOptionPane.showMessageDialog(null,"El archivo cargado es: " + file.getName(),"Exito",JOptionPane.INFORMATION_MESSAGE);
						try {
							servicio.enviarArchivo(file);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null,"Error no se pudo enviar el archivo","ERROR",JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
			}
		});
		btnSubirImagen.setBounds(21, 201, 116, 23);
		frame.getContentPane().add(btnSubirImagen);
	}
}
