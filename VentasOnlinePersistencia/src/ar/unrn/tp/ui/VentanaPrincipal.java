package ar.unrn.tp.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.jpa.servicios.ServicioCliente;
import ar.unrn.tp.jpa.servicios.ServicioDescuento;
import ar.unrn.tp.jpa.servicios.ServicioProducto;
import ar.unrn.tp.jpa.servicios.ServicioVenta;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.Promocion;
import ar.unrn.tp.modelo.TarjetaDeCredito;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCant;
	private JTable table;
	private MyTableModel modelo;
	private JScrollPane panel_tabla;
	private List<Producto> productos;
	private List<TarjetaDeCredito> tarjetas;
	private List<Promocion> promociones;
	private Map<Long, Integer> productosSeleccionados = new HashMap<Long, Integer>();
	private JTable tableDescuentos;
	private MyTableModel modeloDescuentos;
	private JScrollPane panel_tablaDescuento;
	private JTextField textFieldTotal;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String emf = "$objectdb/db/p2.odb";
					long idCliente = 4l;
					VentanaPrincipal frame = new VentanaPrincipal(new ServicioProducto(emf), new ServicioDescuento(emf), 
							new ServicioCliente(emf), new ServicioVenta(emf), idCliente);
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
	 
	public VentanaPrincipal(ProductoService ps, DescuentoService ds, ClienteService cs, VentaService vs, long idCliente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 409);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] titulos = { "Codigo", "Descripcion", "Precio", "Cantidad", "Marca"};
		@SuppressWarnings("rawtypes")
		Class[] columTypes = new Class[] { String.class, String.class, float.class, Integer.class, String.class};
		
		table = new JTable();
		table.setBounds(10, 11, 304, 220);
		
		modelo = new MyTableModel(new Object[][] {}, titulos);
		modelo.setColumnTypes(columTypes);
		
		table.setModel(modelo);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		table.getColumnModel().getColumn(4).setCellRenderer(tcr);
		
		panel_tabla = new JScrollPane(table);
		panel_tabla.setBounds(10, 41, 427, 133);
		contentPane.add(panel_tabla);
		
		try {
			
			productos = ps.listarProductos();
			tarjetas = cs.listarTarjetas(idCliente);
			promociones = ds.listarDescuentos();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		JLabel lbProducto = new JLabel("Producto:");
		lbProducto.setBounds(447, 59, 70, 14);
		contentPane.add(lbProducto);
		
		JLabel lbCantidad = new JLabel("Cantidad:");
		lbCantidad.setBounds(447, 91, 70, 14);
		contentPane.add(lbCantidad);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(527, 55, 156, 22);
		contentPane.add(comboBox);
		
		textFieldCant = new JTextField();
		textFieldCant.setBounds(527, 88, 122, 20);
		contentPane.add(textFieldCant);
		textFieldCant.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cant = textFieldCant.getText();
				if(!cant.equals("") && !cant.equals("0"))
				{
					Producto p = productos.get(comboBox.getSelectedIndex());
					agregarFila(p);
					textFieldCant.setText("");
					
				}
				else
					JOptionPane.showMessageDialog(null, "Ingrese una cantidad", "Error", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnAgregar.setBounds(526, 124, 91, 23);
		contentPane.add(btnAgregar);
		
		JComboBox<String> comboBoxTarjetas = new JComboBox<String>();
		comboBoxTarjetas.setBounds(447, 241, 156, 22);
		contentPane.add(comboBoxTarjetas);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					TarjetaDeCredito t = tarjetas.get(comboBoxTarjetas.getSelectedIndex());
					vs.realizarVenta(idCliente, productosSeleccionados, t.getId());
					JOptionPane.showMessageDialog(null, "Compra realizada con exito", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnComprar.setBounds(449, 335, 102, 23);
		contentPane.add(btnComprar);
		
		String[] titulos2 = { "Inicio", "Fin", "Descuento", "Marca"};
		@SuppressWarnings("rawtypes")
		Class[] columTypes2 = new Class[] { LocalDate.class, LocalDate.class, float.class, String.class};
		
		tableDescuentos = new JTable();
		tableDescuentos.setBounds(10, 331, 302, 172);
		
		modeloDescuentos = new MyTableModel(new Object[][] {}, titulos2);
		modeloDescuentos.setColumnTypes(columTypes2);
		
		for(Promocion promo : this.promociones) {
			Object fila[] = {promo.getDiaDesde(), promo.getDiaHasta(), promo.getPorcentajeDescuento(), promo.getMarca()};
			modeloDescuentos.addRow(fila);
		}
		
		tableDescuentos.setModel(modeloDescuentos);
		
		panel_tablaDescuento = new JScrollPane(tableDescuentos);
		panel_tablaDescuento.setBounds(10, 232, 429, 127);
		contentPane.add(panel_tablaDescuento);
		
		JButton btnCalcularMonto = new JButton("Precio total");
		btnCalcularMonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					TarjetaDeCredito t = tarjetas.get(comboBoxTarjetas.getSelectedIndex());
					float total = vs.calcularMonto(productosSeleccionados, t.getId());
					textFieldTotal.setText(String.valueOf(total));	
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCalcularMonto.setBounds(447, 286, 104, 23);
		contentPane.add(btnCalcularMonto);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setEditable(false);
		textFieldTotal.setBounds(562, 287, 100, 20);
		contentPane.add(textFieldTotal);
		textFieldTotal.setColumns(10);
		
		JLabel lblDescuento = new JLabel("DESCUENTOS");
		lblDescuento.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblDescuento.setBounds(176, 207, 122, 14);
		contentPane.add(lblDescuento);
		
		JLabel lblCarrito = new JLabel("CARRITO");
		lblCarrito.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblCarrito.setBounds(186, 16, 122, 14);
		contentPane.add(lblCarrito);
		
		for (TarjetaDeCredito t : tarjetas) {
			comboBoxTarjetas.addItem(t.getMarca());
		}
		
		for (Producto p : this.productos) {
			comboBox.addItem(p.getDescripcion());
		}
	}
	
	private void agregarFila(Producto p) {
		DefaultTableModel dm = (DefaultTableModel) modelo;
		Object fila[] = {p.getCodigo(), p.getDescripcion(), p.getPrecio(), textFieldCant.getText(), p.getMarca()};
		dm.addRow(fila);
		productosSeleccionados.put(p.getId(), Integer.valueOf(textFieldCant.getText()));
	}
}
