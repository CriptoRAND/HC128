package ar.edu.unlam.cripto.cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ar.edu.unlam.cripto.cliente.hilos.Escuchador;

public class Cliente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JFileChooser fc;
	private ServicioTransmision servicio;
	private JLabel lblNewLabel;
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
		try {
			servicio=new ServicioTransmision(this);
			new Escuchador(this).start();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
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
						} catch (IOException | InterruptedException e1) {
							JOptionPane.showMessageDialog(null,"Error no se pudo enviar el archivo","ERROR",JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
			}
		});
		btnSubirImagen.setBounds(152, 197, 116, 23);
		frame.getContentPane().add(btnSubirImagen);
		
		 lblNewLabel = null;
		  BufferedImage img;
			try {
				img = ImageIO.read(new File("Imagenes/000.jpg"));
				ImageIcon icon = new ImageIcon(img);
		          lblNewLabel = new JLabel(icon);
		          lblNewLabel.setBounds(43, 11, 330, 166);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		frame.getContentPane().add(lblNewLabel); 
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFileChooser getFc() {
		return fc;
	}

	public void setFc(JFileChooser fc) {
		this.fc = fc;
	}

	public ServicioTransmision getServicio() {
		return servicio;
	}

	public void setServicio(ServicioTransmision servicio) {
		this.servicio = servicio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public void setLabelText(String string) {
		this.lblNewLabel.setIcon(null);
		this.lblNewLabel.setText(string);		
	}

	public void setLabelText(File file) {
		BufferedImage img;
		try {
			img = ImageIO.read(file);
			ImageIcon icon = new ImageIcon(img);
	        lblNewLabel.setIcon(icon); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
