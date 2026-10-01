package view;

import controller.EnvioController;
import model.Envio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaEnvios extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtCliente;
    private JTextField txtPeso;
    private JTextField txtDistancia;
    private JComboBox<String> cbTipo;

    private JTable tabla;
    private DefaultTableModel modelo;

    private EnvioController controller;

    public VentanaEnvios() {
        controller = new EnvioController();

        setTitle("Operador Logístico - Parcial POO");
        setSize(700, 470);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        crearInterfaz();
    }

    private void crearInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));
        add(panel);

        JLabel titulo = new JLabel("Registro de Envíos");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(20, 10, 250, 30);
        panel.add(titulo);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 55, 70, 25);
        panel.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(80, 55, 120, 25);
        panel.add(txtCodigo);

        JLabel lblTipo = new JLabel("Medio:");
        lblTipo.setBounds(220, 55, 60, 25);
        panel.add(lblTipo);

        cbTipo = new JComboBox<>(new String[]{"Terrestre", "Aereo", "Fluvial"});
        cbTipo.setBounds(275, 55, 120, 25);
        panel.add(cbTipo);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(410, 55, 60, 25);
        panel.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(465, 55, 180, 25);
        panel.add(txtCliente);

        JLabel lblPeso = new JLabel("Peso (Kg):");
        lblPeso.setBounds(20, 95, 70, 25);
        panel.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(90, 95, 110, 25);
        panel.add(txtPeso);

        JLabel lblDistancia = new JLabel("Distancia (Km):");
        lblDistancia.setBounds(220, 95, 100, 25);
        panel.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(320, 95, 110, 25);
        panel.add(txtDistancia);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(450, 92, 100, 30);
        panel.add(btnAgregar);

        JButton btnRetirar = new JButton("Retirar");
        btnRetirar.setBounds(555, 92, 100, 30);
        panel.add(btnRetirar);

        modelo = new DefaultTableModel(
                new Object[]{"Medio", "Código", "Cliente", "Peso", "Distancia", "Tarifa"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 145, 635, 240);
        panel.add(scroll);

        btnAgregar.addActionListener(e -> agregarEnvio());
        btnRetirar.addActionListener(e -> retirarEnvio());
    }

    private void agregarEnvio() {
        try {
            String codigo = txtCodigo.getText().trim();
            String cliente = txtCliente.getText().trim();
            String tipo = cbTipo.getSelectedItem().toString();

            if (codigo.isEmpty() || cliente.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Complete el código y el cliente.");
                return;
            }

            double peso = Double.parseDouble(txtPeso.getText());
            double distancia = Double.parseDouble(txtDistancia.getText());

            if (peso <= 0 || distancia <= 0) {
                JOptionPane.showMessageDialog(this,
                        "El peso y la distancia deben ser mayores que 0.");
                return;
            }

            controller.agregarEnvio(codigo, cliente, tipo, peso, distancia);
            actualizarTabla();
            limpiarCampos();

            JOptionPane.showMessageDialog(this, "Envío agregado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Peso y distancia deben ser números.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void retirarEnvio() {
        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un envío de la tabla.");
            return;
        }

        controller.retirarEnvio(fila);
        actualizarTabla();

        JOptionPane.showMessageDialog(this, "Envío retirado.");
    }

    private void actualizarTabla() {
        modelo.setRowCount(0);

        for (Envio envio : controller.listarEnvios()) {
            // Aquí se observa el polimorfismo:
            // todos son Envio, pero cada uno calcula su propia tarifa.
            modelo.addRow(new Object[]{
                    envio.getTipo(),
                    envio.getCodigo(),
                    envio.getCliente(),
                    envio.getPeso(),
                    envio.getDistancia(),
                    String.format("$ %.0f", envio.calcularTarifa())
            });
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cbTipo.setSelectedIndex(0);
    }
}
