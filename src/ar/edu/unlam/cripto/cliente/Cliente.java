package ar.edu.unlam.cripto.cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.Icon;

public class Cliente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JFileChooser fc;
	private ServicioTransmision servicio;
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel label_1;
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
		frame.setBounds(100, 100, 800, 505);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		fc = new JFileChooser();
		try {
			servicio=new ServicioTransmision(this);
//			new Escuchador(this).start();
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
//							servicio.enviarArchivo(file);
							servicio.encriptarArchivo(file);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null,"Error no se pudo enviar el archivo","ERROR",JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
			}
		});
		btnSubirImagen.setBounds(235, 404, 116, 23);
		frame.getContentPane().add(btnSubirImagen);
		
		 lblNewLabel = null;
		  BufferedImage img;
			try {
				img = ImageIO.read(new File("Imagenes/000.jpg"));
				ImageIcon icon = new ImageIcon(img);
		          lblNewLabel = new JLabel(icon);
		          lblNewLabel.setBounds(23, 35, 237, 291);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		frame.getContentPane().add(lblNewLabel); 
		
		JButton btnNewButton = new JButton("Streaming");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					servicio.stremear();
				} catch (InterruptedException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		btnNewButton.setBounds(473, 404, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		label = new JLabel((Icon) null);
		label.setBounds(288, 35, 237, 291);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel((Icon) null);
		label_1.setBounds(537, 35, 237, 291);
		frame.getContentPane().add(label_1);
		
		 frame.addWindowListener(new WindowAdapter() {
	         @Override
	         public void windowClosing(WindowEvent e) {
	        	 try {
					servicio.cerrarCliente();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					System.exit(0);
				}
	         }
	      });
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

	public void setLabelText(BufferedImage img) {
			ImageIcon icon = new ImageIcon(img);
	        lblNewLabel.setIcon(icon); 
	}

	public void setLabelEncriptadoText(BufferedImage img) {
		ImageIcon icon = new ImageIcon(img);
        label.setIcon(icon); 
	}
	
	public void setLabelDesencriptadoText(BufferedImage img) {
		ImageIcon icon = new ImageIcon(img);
        label_1.setIcon(icon); 
	}
}
