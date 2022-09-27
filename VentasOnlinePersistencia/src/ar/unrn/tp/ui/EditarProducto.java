package ar.unrn.tp.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.jpa.servicios.ServicioProducto;
import ar.unrn.tp.modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.OptimisticLockException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class EditarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfNombre;
	private JTextField tfPrecio;
	private JTextField tfMarca;
	private List<Producto> productos;
	private Producto product;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					String emf = "ORM";
					EditarProducto frame = new EditarProducto(new ServicioProducto(emf), 7L);
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
	public EditarProducto(ProductoService ps, long idProducto) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(115, 66, 67, 14);
		contentPane.add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(115, 97, 67, 14);
		contentPane.add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(115, 125, 67, 14);
		contentPane.add(lblPrecio);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(115, 156, 67, 14);
		contentPane.add(lblMarca);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(222, 63, 86, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(222, 94, 86, 20);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfPrecio = new JTextField();
		tfPrecio.setBounds(222, 122, 86, 20);
		contentPane.add(tfPrecio);
		tfPrecio.setColumns(10);
		
		tfMarca = new JTextField();
		tfMarca.setBounds(222, 153, 86, 20);
		contentPane.add(tfMarca);
		tfMarca.setColumns(10);
		
		try {
			productos = ps.listarProductos();
		
			for (Producto p : productos) {
				if(p.getId() == idProducto)
					product = p;
			}
			
			tfId.setText(String.valueOf(product.getId()));
			tfMarca.setText(product.getMarca());
			tfNombre.setText(product.getDescripcion());
			tfPrecio.setText(String.valueOf(product.getPrecio()));
			
		  } catch (RuntimeException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		  } catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		  }
			
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					ps.modificarProducto(idProducto, product.getCodigo(), tfNombre.getText(), Float.valueOf(tfPrecio.getText()), 
							product.getCategoria(), tfMarca.getText(), product.getVersion());
					JOptionPane.showMessageDialog(null, "Producto modificado", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModificar.setBounds(219, 190, 89, 23);
		contentPane.add(btnModificar);
		
		JLabel lblTitulo = new JLabel("MODIFICAR PRODUCTO");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(127, 22, 161, 14);
		contentPane.add(lblTitulo);
	}
}
