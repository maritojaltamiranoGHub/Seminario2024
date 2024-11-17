
package main.java.sigep.views;


import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.java.sigep.model.dao.UsuariosDAO;
import main.java.sigep.model.dao.impl.UsuariosDAOImpl;
import main.java.sigep.model.entities.Usuarios;

/**
 *
 * @author Marito
 */
public class UsuariosABM extends javax.swing.JFrame {

    /**
     * Creates new form UsuariosABM
     */
    public UsuariosABM() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
        URL url = getClass().getResource("/main/java/sigep/views/icons/zebra.png");
        setIconImage(new ImageIcon(url).getImage());
        buscarUsuarios();
    }

   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbResultado = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonAgregar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de Usuarios");

        tbResultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Rol", "Clave"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbResultado);

        jLabel1.setText("USUARIOS");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAgregar)
                .addGap(33, 33, 33)
                .addComponent(jButtonModificar)
                .addGap(32, 32, 32)
                .addComponent(jButtonEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSalir)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAgregar)
                    .addComponent(jButtonModificar)
                    .addComponent(jButtonEliminar)
                    .addComponent(jButtonSalir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        


        // Abrir ventana de edicion
        UsuariosDatos dialog = new UsuariosDatos(this, true);

        dialog.configurarAlta();
        // Mostrar el diálogo
        dialog.setVisible(true);
        
        //luego de la operacion refresco la pantalla
        buscarUsuarios();
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        // Verificar que se haya seleccionado una fila
        int selectedRow = tbResultado.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener valores de la fila seleccionada
        int usuarioId = Integer.parseInt(tbResultado.getValueAt(selectedRow, 0).toString());
        String usuarioNombre = tbResultado.getValueAt(selectedRow, 1).toString();
        String usuarioRol = tbResultado.getValueAt(selectedRow, 2).toString();
        String usuarioClave = tbResultado.getValueAt(selectedRow, 3).toString();

        // Abrir ventana de edicion
        UsuariosDatos dialog = new UsuariosDatos(this, true);

        // Pasar los valores a los campos
        dialog.setDatos(usuarioId, usuarioNombre, usuarioRol, usuarioClave);

        // Mostrar el diálogo
        dialog.setVisible(true);
        
        //luego de la operacion refresco la pantalla
        buscarUsuarios();
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        UsuariosDAO usuariosDAO = new UsuariosDAOImpl();
        int selectedRow = tbResultado.getSelectedRow();

        if (selectedRow == -1) {
            // Si no hay ninguna fila seleccionada
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener el ID del usuario seleccionado (asumiendo que está en la primera columna)
        int userId = Integer.parseInt(tbResultado.getValueAt(selectedRow, 0).toString());

        // Confirmación de eliminación
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar este usuario?", 
            "Confirmación", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                
                usuariosDAO.eliminar(userId);
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                // Actualizar la vista de la tabla
                buscarUsuarios();
            } catch (Exception e) {
                // Mostrar mensaje en caso de error
                JOptionPane.showMessageDialog(this, "Error al eliminar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButtonSalirActionPerformed
    private void buscarUsuarios(){
        UsuariosDAO usuariosDAO = new UsuariosDAOImpl();
        List<Usuarios> usuarios = usuariosDAO.obtenerTodos();
        DefaultTableModel model = (DefaultTableModel) tbResultado.getModel();
        model.setRowCount(0); // Limpio los resultados

        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay resultados para mostrar", "Alerta!", JOptionPane.ERROR_MESSAGE);
        } else {
            for (Usuarios usuario : usuarios) {
                model.addRow(usuario.toObject());
            }
            tbResultado.requestFocus();
            tbResultado.setRowSelectionInterval(0, 0);
        }
    
    };
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbResultado;
    // End of variables declaration//GEN-END:variables
}
