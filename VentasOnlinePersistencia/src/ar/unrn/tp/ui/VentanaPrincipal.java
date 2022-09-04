package ar.unrn.tp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 11, 304, 236);
		contentPane.add(table);
		
		JLabel lbProducto = new JLabel("Producto:");
		lbProducto.setBounds(322, 67, 70, 14);
		contentPane.add(lbProducto);
		
		JLabel lbCantidad = new JLabel("Cantidad:");
		lbCantidad.setBounds(324, 114, 70, 14);
		contentPane.add(lbCantidad);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(402, 63, 156, 22);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(404, 111, 122, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(402, 157, 89, 23);
		contentPane.add(btnAgregar);
	}
}
