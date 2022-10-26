package ar.unrn.tp.ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.jpa.servicios.CacheServiceJedis;
import ar.unrn.tp.jpa.servicios.ServicioCliente;
import ar.unrn.tp.jpa.servicios.ServicioVenta;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Venta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaDeVentas extends JFrame {

	private JPanel contentPane;
	private JTable tablaVentas;
	private MyTableModel modelo;
	private JScrollPane panel_tabla;
	private List<Venta> ventas;
	private JLabel lblCliente;
	private List<Cliente> clientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String emf = "ORM";
					VistaDeVentas frame = new VistaDeVentas(new ServicioVenta(emf, new CacheServiceJedis("localhost", 6379, "ventas")),
							new ServicioCliente(emf));
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
	public VistaDeVentas(VentaService vs, ClienteService cs) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] titulos = { "Codigo", "Fecha", "Monto Total"};
		@SuppressWarnings("rawtypes")
		Class[] columTypes = new Class[] {String.class, String.class, float.class};
		
		tablaVentas = new JTable();
		tablaVentas.setBounds(10, 11, 304, 220);
		
		modelo = new MyTableModel(new Object[][] {}, titulos);
		modelo.setColumnTypes(columTypes);
		
		tablaVentas.setModel(modelo);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		tablaVentas.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tablaVentas.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tablaVentas.getColumnModel().getColumn(2).setCellRenderer(tcr);
		
		panel_tabla = new JScrollPane(tablaVentas);
		panel_tabla.setBounds(10, 64, 529, 133);
		contentPane.add(panel_tabla);
		
		lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(106, 25, 70, 14);
		contentPane.add(lblCliente);
		
		JComboBox<String> comboClientes = new JComboBox<String>();
		comboClientes.setBounds(174, 23, 92, 18);
		contentPane.add(comboClientes);
		
		try {
			
			clientes = cs.obtenerClientes();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				
				modelo.setRowCount(0);
				long idCliente = clientes.get(comboClientes.getSelectedIndex()).getId();
				ventas = vs.ultimasTresVentas(idCliente);
				for(Venta v : ventas) {
					Object fila[] = {v.getCodigo(), v.getFecha(), v.getMontoTotal()};
					modelo.addRow(fila);
				}
				
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnBuscar.setBounds(290, 21, 89, 23);
		contentPane.add(btnBuscar);
		
		for (Cliente c : this.clientes) {
			comboClientes.addItem(c.getNombre());
		}
		
	}
}
