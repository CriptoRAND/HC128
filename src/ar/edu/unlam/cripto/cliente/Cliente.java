package ar.edu.unlam.cripto.cliente;

import java.awt.Color;
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
	private JLabel lblTitulo;
	private JLabel lblTitulo2;

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
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		fc = new JFileChooser();
		try {
			servicio = new ServicioTransmision(this);
			new Escuchador(this).start();
		} catch (IOException e2) {
		}
		frame.setTitle("HC-128");
		lblTitulo = new JLabel();
		lblTitulo.setBounds(50, 20, 182, 22);
		lblTitulo.setText("Imagen encriptada");
		lblTitulo.setForeground(Color.WHITE);
//		lblTitulo.setBackground(new Color(34, 102, 102));
		lblTitulo2 = new JLabel();
		lblTitulo2.setBounds(517, 19, 169, 23);
//		lblTitulo2.setBackground(new Color(34, 102, 102));
		lblTitulo2.setText("Imagen desencriptada");
		lblTitulo2.setForeground(Color.WHITE);
		frame.getContentPane().add(lblTitulo);
		frame.getContentPane().add(lblTitulo2);
//		frame.getContentPane().setBackground(new Color(34, 102, 102));
		frame.getContentPane().setBackground(new Color(102, 153, 153));
		JButton btnNewButton = new JButton("Streaming");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					servicio.stremear();
				} catch (Exception e) {
					System.out.println("Error en cliente: " + e.getMessage());
				}
			}
		});
		
				lblNewLabel = new JLabel();
				lblNewLabel.setBounds(374, 53, 400, 493);
				frame.getContentPane().add(lblNewLabel);
		
				label = new JLabel((Icon) null);
				label.setBounds(10, 53, 400, 493);
				frame.getContentPane().add(label);
		
				JButton btnSubirImagen = new JButton("Subir Imagen");
				//		btnSubirImagen.setBackground(new Color(102, 153, 153));
						btnSubirImagen.setBackground(new Color(34, 102, 102));
						btnSubirImagen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								if (e.getSource() == btnSubirImagen) {
									int returnVal = fc.showOpenDialog(Cliente.this);
									if (returnVal == JFileChooser.APPROVE_OPTION) {
										File file = fc.getSelectedFile();
										JOptionPane.showMessageDialog(null, "El archivo cargado es: " + file.getName(), "Exito",
												JOptionPane.INFORMATION_MESSAGE);
										try {
											servicio.enviarArchivo(file);
										} catch (IOException | InterruptedException e1) {
											JOptionPane.showMessageDialog(null, "Error no se pudo enviar el archivo", "ERROR",
													JOptionPane.ERROR_MESSAGE);
										}
									}

								}
							}
						});
						btnSubirImagen.setBounds(26, 515, 116, 23);
						btnSubirImagen.setForeground(Color.WHITE);
						frame.getContentPane().add(btnSubirImagen);
		btnNewButton.setBounds(290, 515, 116, 23);
//		btnNewButton.setBackground(new Color(102, 153, 153));
		btnNewButton.setBackground(new Color(34, 102, 102));
		btnNewButton.setForeground(Color.WHITE);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnImagenRota = new JButton("Imagen Rota");
		btnImagenRota.setBackground(new Color(34, 102, 102));
		btnImagenRota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnImagenRota) {
					int returnVal = fc.showOpenDialog(Cliente.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						JOptionPane.showMessageDialog(null, "El archivo cargado es: " + file.getName(), "Exito",
								JOptionPane.INFORMATION_MESSAGE);
						try {
							servicio.enviarArchivoRoto(file);
						} catch (IOException | InterruptedException e1) {
							JOptionPane.showMessageDialog(null, "Error no se pudo enviar el archivo", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				}
				
			}
		});
		btnImagenRota.setForeground(Color.WHITE);
		btnImagenRota.setBackground(new Color(34, 102, 102));
		btnImagenRota.setBounds(152, 515, 116, 23);
		frame.getContentPane().add(btnImagenRota);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					servicio.cerrarCliente();
				} catch (IOException e1) {
					System.out.println("Error en cliente: " + e1.getMessage());
				} finally {
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
		}

	}

	public void setLabelText(BufferedImage img) {
		ImageIcon icon = new ImageIcon(img);
		lblNewLabel.setIcon(icon);
	}

	public void setLabelEncriptadoText(File file) {
		BufferedImage img;
		try {
			img = ImageIO.read(file);
			ImageIcon icon = new ImageIcon(img);
			label.setIcon(icon);
		} catch (IOException e) {
		}

	}

	public void setLabelEncriptadoText(BufferedImage img) {
		ImageIcon icon = new ImageIcon(img);
		label.setIcon(icon);
	}
}
