
package Vista;

import Modelo.Prioridad;
import Modelo.Tarea;
import Modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MainView extends JFrame {
    private final Usuario usuario;
    private final JTabbedPane tabbedPane;
    private final DefaultTableModel modeloTareasActivas;
    private final DefaultTableModel modeloTareasFinalizadas;
    private final JComboBox<Prioridad> comboPrioridad;
    private final JTextArea descripcionArea;

    public MainView(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Sistema de Tareas - " + usuario.getNombre());
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes principales
        tabbedPane = new JTabbedPane();
        modeloTareasActivas = new DefaultTableModel(new String[]{"Descripción", "Prioridad"}, 0);
        modeloTareasFinalizadas = new DefaultTableModel(new String[]{"Descripción", "Prioridad"}, 0);
        comboPrioridad = new JComboBox<>(Prioridad.values());
        descripcionArea = new JTextArea(3, 20);

        // Configurar paneles
        tabbedPane.addTab("Tareas Activas", crearPanelTareasActivas());
        tabbedPane.addTab("Tareas Finalizadas", crearPanelTareasFinalizadas());
        tabbedPane.addTab("Nueva Tarea", crearPanelNuevaTarea());
        tabbedPane.addTab("Perfil", crearPanelPerfil());

        // Agregar a la ventana
        add(tabbedPane);

        // Cargar datos iniciales
        actualizarTablas();
    }

    private JPanel crearPanelTareasActivas() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable tabla = new JTable(modeloTareasActivas);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton btnFinalizar = new JButton("Finalizar Tarea Seleccionada");
        btnFinalizar.addActionListener(e -> finalizarTareaSeleccionada(tabla));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnFinalizar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelTareasFinalizadas() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable tabla = new JTable(modeloTareasFinalizadas);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelNuevaTarea() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Descripción
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Descripción:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(new JScrollPane(descripcionArea), gbc);

        // Prioridad
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Prioridad:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(comboPrioridad, gbc);

        // Botón Agregar
        JButton btnAgregar = new JButton("Agregar Tarea");
        btnAgregar.addActionListener(e -> agregarNuevaTarea());

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(btnAgregar, gbc);

        return panel;
    }

    private JPanel crearPanelPerfil() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea perfilArea = new JTextArea(usuario.getPerfil().toString());
        perfilArea.setEditable(false);
        panel.add(new JScrollPane(perfilArea), BorderLayout.CENTER);
        return panel;
    }

    private void actualizarTablas() {
        // Limpiar tablas
        modeloTareasActivas.setRowCount(0);
        modeloTareasFinalizadas.setRowCount(0);

        // Actualizar tareas activas
        for (Tarea tarea : usuario.getTareasActivas()) {
            modeloTareasActivas.addRow(new Object[]{
                    tarea.getDescripcion(),
                    tarea.getPrioridad()
            });
        }

        // Actualizar tareas finalizadas
        for (Tarea tarea : usuario.getTareasFinalizadas()) {
            modeloTareasFinalizadas.addRow(new Object[]{
                    tarea.getDescripcion(),
                    tarea.getPrioridad()
            });
        }
    }

    private void finalizarTareaSeleccionada(JTable tabla) {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada >= 0) {
            if (usuario.finalizarTarea(filaSeleccionada)) {
                actualizarTablas();
                JOptionPane.showMessageDialog(this,
                        "Tarea finalizada exitosamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Por favor, seleccione una tarea para finalizar",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarNuevaTarea() {
        String descripcion = descripcionArea.getText().trim();
        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese una descripción para la tarea",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Prioridad prioridad = (Prioridad) comboPrioridad.getSelectedItem();
        Tarea nuevaTarea = new Tarea(descripcion, prioridad, false);
        usuario.agregarTarea(nuevaTarea);

        // Limpiar campos
        descripcionArea.setText("");
        comboPrioridad.setSelectedIndex(0);

        // Actualizar vista
        actualizarTablas();
        JOptionPane.showMessageDialog(this,
                "Tarea agregada exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
    }
}