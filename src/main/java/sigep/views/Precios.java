package main.java.sigep.views;


//import main.java.sigep.controllers.Printer;
import main.java.sigep.model.dao.ProductosPOSDAO;
import main.java.sigep.model.entities.ProductosPOS;
import main.java.sigep.model.entities.Etiquetas;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Marito
 */
public class Precios extends javax.swing.JFrame {

    private PrintService printService;
    private ProductosPOS articuloSeleccionado;
    private DecimalFormat formatter = new DecimalFormat("#,##0.00");
    private Ayuda ayuda;
    private static String[] savedArgs;

    public Precios() {
      
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initListeners();
        URL url = getClass().getResource("/main/java/sigep/views/icons/zebra.png");
        setIconImage(new ImageIcon(url).getImage());
        
        
        try {
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            int item = 0;
            
            for (PrintService service : services) {
                if (service.getName().toLowerCase().contains("zebra")) {
                    cbImpresora.addItem(service);
                    item++;
                    //break;
                }
            }
            if (cbImpresora.getItemCount() == 1) {
                lbImpresora.setVisible(false);
                cbImpresora.setVisible(false);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el servicio de impresión",
                    "Alerta!", JOptionPane.ERROR_MESSAGE);
        }
        
        final List<JRadioButton> buttons = new ArrayList<>();
        buttons.add(rbGondolas);
        buttons.add(rbDeposito);
        buttons.add(rbFraccionadaGondolas);
        buttons.add(rbCodigoBarrasPrecio);
        
        pLabels.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int selected = 0;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        for (JRadioButton button : buttons) {
                            if (button.isSelected()) {
                                if ((selected + 1) == buttons.size()) {
                                    buttons.get(0).doClick();
                                } else {
                                    buttons.get(selected + 1).doClick();
                                }
                            } else {
                                selected++;
                            }
                        }
                        break;
                    case KeyEvent.VK_UP:
                        for (JRadioButton button : buttons) {
                            if (button.isSelected()) {
                                if ((selected - 1) < 0) {
                                    buttons.get(buttons.size() - 1).doClick();
                                    break;
                                } else {
                                    buttons.get(selected - 1).doClick();
                                }
                            } else {
                                selected++;
                            }
                        }
                        break;
                }
            }
        });
        
        List<Component> components = new ArrayList<>();
        components.add(tfCodigo);
        components.add(pLabels);
        components.add(((DefaultEditor) spFraccion.getEditor()).getTextField());
        components.add(((DefaultEditor) spCantidad.getEditor()).getTextField());
        components.add(btImprimir);
        
        FocusPolicy focusPolicy = new FocusPolicy(components);
        setFocusTraversalPolicy(focusPolicy);
        
        ayuda = new Ayuda(this, true);
        ayuda.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgLabels = new javax.swing.ButtonGroup();
        tfCodigo = new javax.swing.JTextField();
        lbCodigo = new javax.swing.JLabel();
        lbDescripcion = new javax.swing.JLabel();
        lbCodigoBarras = new javax.swing.JLabel();
        tfDescripcion = new javax.swing.JTextField();
        tfCodigoBarras = new javax.swing.JTextField();
        lbExistencia = new javax.swing.JLabel();
        lbPrecio = new javax.swing.JLabel();
        lbFraccion = new javax.swing.JLabel();
        lbImporte = new javax.swing.JLabel();
        lbCantidad = new javax.swing.JLabel();
        tfExistencia = new javax.swing.JTextField();
        tfPrecio = new javax.swing.JTextField();
        spFraccion = new javax.swing.JSpinner();
        spCantidad = new javax.swing.JSpinner();
        tfImporte = new javax.swing.JTextField();
        btImprimir = new javax.swing.JButton();
        cbPrecio = new javax.swing.JComboBox();
        pLabels = new javax.swing.JPanel();
        rbDeposito = new javax.swing.JRadioButton();
        rbFraccionadaGondolas = new javax.swing.JRadioButton();
        rbGondolas = new javax.swing.JRadioButton();
        rbCodigoBarrasPrecio = new javax.swing.JRadioButton();
        lbSeleccionarEtiqueta = new javax.swing.JLabel();
        cbImpresora = new javax.swing.JComboBox();
        lbImpresora = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Etiquetas"); // NOI18N
        setResizable(false);

        tfCodigo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tfCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfCodigo.setToolTipText("Presione <Enter> para confirmar");
        tfCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodigoFocusLost(evt);
            }
        });
        tfCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCodigoActionPerformed(evt);
            }
        });
        tfCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCodigoKeyReleased(evt);
            }
        });

        lbCodigo.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbCodigo.setText("Ingrese el Código");

        lbDescripcion.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbDescripcion.setText("Descripción");

        lbCodigoBarras.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbCodigoBarras.setText("Código de Barras");

        tfDescripcion.setEditable(false);
        tfDescripcion.setFocusable(false);

        tfCodigoBarras.setEditable(false);
        tfCodigoBarras.setFocusable(false);

        lbExistencia.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbExistencia.setText("Existencia");

        lbPrecio.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbPrecio.setText("Precio $");

        lbFraccion.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbFraccion.setText("Fracción");

        lbImporte.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbImporte.setText("Importe Total $");

        lbCantidad.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbCantidad.setText("Cantidad");

        tfExistencia.setEditable(false);
        tfExistencia.setFocusable(false);

        tfPrecio.setEditable(false);
        tfPrecio.setFocusable(false);
        tfPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPrecioActionPerformed(evt);
            }
        });

        spFraccion.setModel(new javax.swing.SpinnerNumberModel(1.0d, 0.0d, null, 1.0d));
        spFraccion.setEnabled(false);
        spFraccion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spFraccionStateChanged(evt);
            }
        });

        spCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        tfImporte.setEditable(false);
        tfImporte.setEnabled(false);
        tfImporte.setFocusable(false);

        btImprimir.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/sigep/views/icons/print.png"))); // NOI18N
        btImprimir.setText("Imprimir");
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });

        cbPrecio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Precio Minorista" }));
        cbPrecio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbPrecioItemStateChanged(evt);
            }
        });

        pLabels.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pLabels.setFocusCycleRoot(true);
        pLabels.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pLabelsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pLabelsFocusLost(evt);
            }
        });

        bgLabels.add(rbDeposito);
        rbDeposito.setText("Depósito");
        rbDeposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDepositoActionPerformed(evt);
            }
        });

        bgLabels.add(rbFraccionadaGondolas);
        rbFraccionadaGondolas.setText("Fracc. Góndolas");
        rbFraccionadaGondolas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFraccionadaGondolasActionPerformed(evt);
            }
        });

        bgLabels.add(rbGondolas);
        rbGondolas.setSelected(true);
        rbGondolas.setText("Góndolas");
        rbGondolas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbGondolasActionPerformed(evt);
            }
        });

        bgLabels.add(rbCodigoBarrasPrecio);
        rbCodigoBarrasPrecio.setText("Cod. Barras y Precio");
        rbCodigoBarrasPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCodigoBarrasPrecioActionPerformed(evt);
            }
        });

        lbSeleccionarEtiqueta.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbSeleccionarEtiqueta.setText(" Seleccionar el tipo de etiqueta");

        javax.swing.GroupLayout pLabelsLayout = new javax.swing.GroupLayout(pLabels);
        pLabels.setLayout(pLabelsLayout);
        pLabelsLayout.setHorizontalGroup(
            pLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLabelsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSeleccionarEtiqueta)
                    .addGroup(pLabelsLayout.createSequentialGroup()
                        .addGroup(pLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbFraccionadaGondolas)
                            .addComponent(rbGondolas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbDeposito)
                            .addComponent(rbCodigoBarrasPrecio))))
                .addGap(16, 16, 16))
        );
        pLabelsLayout.setVerticalGroup(
            pLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLabelsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSeleccionarEtiqueta)
                .addGap(7, 7, 7)
                .addGroup(pLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbGondolas)
                    .addComponent(rbDeposito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbFraccionadaGondolas)
                    .addComponent(rbCodigoBarrasPrecio))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbImpresora.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbImpresora.setText("Impresora");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbImpresora)
                            .addComponent(lbDescripcion)
                            .addComponent(lbCodigoBarras)
                            .addComponent(lbExistencia)
                            .addComponent(lbPrecio)
                            .addComponent(lbFraccion)
                            .addComponent(lbImporte)
                            .addComponent(lbCantidad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(spFraccion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbImpresora, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbPrecio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(tfCodigoBarras))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btImprimir))
                            .addComponent(tfDescripcion)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pLabels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(163, 163, 163)
                                .addComponent(lbCodigo)))))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pLabels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDescripcion)
                    .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodigoBarras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbExistencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPrecio)
                    .addComponent(cbPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spFraccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFraccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbImporte))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCantidad))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbImpresora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbImpresora))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed
//        if (articuloSeleccionado != null) {
//            Etiquetas etiqueta = new Etiquetas(articuloSeleccionado);
//            //if (retail) {
//            if (cbPrecio.getSelectedIndex() == 0) {
//                etiqueta.setPrecio(articuloSeleccionado.getPrecioMinorista());
//            } else {
//                etiqueta.setPrecio(articuloSeleccionado.getPrecioMayorista());
//            }
//            etiqueta.setFraccion((Double) spFraccion.getValue());
//            etiqueta.setTotal(Double.parseDouble(tfPrecio.getText().replace(",", ".")));
//            printService    = (PrintService)cbImpresora.getSelectedItem();
//            if (printService != null) {
//                Printer printer = new Printer(printService);
//                int cantidad = (Integer) spCantidad.getValue();
//                if (rbBlisters.isSelected()) {
//                    printer.print(etiqueta, cantidad, Printer.LabelType.BLISTERS);
//                } else if (rbDeposito.isSelected()) {
//                    printer.print(etiqueta, cantidad, Printer.LabelType.DEPOSIT);
//                } else if (rbDobleDescripcion.isSelected()) {
//                    printer.print(etiqueta, cantidad, Printer.LabelType.DOUBLE_DESCRIPTION);
//                } else if (rbPisos.isSelected()) {
//                    printer.print(etiqueta, cantidad, Printer.LabelType.FLOORS);
//                } else if (rbFraccionada.isSelected()) {
//                    printer.print(etiqueta, cantidad, Printer.LabelType.FRACTIONAL);
//                } else if (rbFraccionadaGondolas.isSelected()) {
//                    printer.print(etiqueta, cantidad, Printer.LabelType.FRACTIONAL_STANDS);
//                } else if (rbMini.isSelected()) {
//                    printer.print(etiqueta, cantidad, Printer.LabelType.MINI);
//                } else if (rbGondolas.isSelected()) {
//                    printer.print(etiqueta, cantidad, Printer.LabelType.STANDS);
//                } else if (rbCodigoBarrasPrecio.isSelected()) {
//                    printer.print(etiqueta, cantidad, Printer.LabelType.BARCODE_PRICE);
//                }
//                spCantidad.setValue(1);
//                tfCodigo.selectAll();
//                tfCodigo.requestFocus();
//            } else {
//                JOptionPane.showMessageDialog(null, "No se pudo encontrar la impresora",
//                        "Alerta!", JOptionPane.ERROR_MESSAGE);
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Nada para imprimir",
//                    "Alerta!", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_btImprimirActionPerformed

    private void rbGondolasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbGondolasActionPerformed
        bgLabelsRules(false);
    }//GEN-LAST:event_rbGondolasActionPerformed

    private void rbFraccionadaGondolasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFraccionadaGondolasActionPerformed
        bgLabelsRules(true);
        calcularImporte();
    }//GEN-LAST:event_rbFraccionadaGondolasActionPerformed

    private void rbDepositoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDepositoActionPerformed
        bgLabelsRules(false);
    }//GEN-LAST:event_rbDepositoActionPerformed

    private void tfCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCodigoKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                if (!tfCodigo.getText().isEmpty()) {
                    tfCodeKeyEnter();
                    if (rbFraccionadaGondolas.isSelected()) {
                        calcularImporte();
                    }
                    tfCodigo.selectAll();
                }
                break;
            case KeyEvent.VK_F1:
                ayuda.setVisible(true);
                if (!ayuda.getResult().isEmpty()) {
                    tfCodigo.setText(ayuda.getResult());
                    tfCodeKeyEnter();
                    if (rbFraccionadaGondolas.isSelected()) {
                        calcularImporte();
                    }
                }
                break;
            case KeyEvent.VK_F2:
                btImprimirActionPerformed(null);
                break;
        }
    }//GEN-LAST:event_tfCodigoKeyReleased

    private void rbCodigoBarrasPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodigoBarrasPrecioActionPerformed
        bgLabelsRules(false);
    }//GEN-LAST:event_rbCodigoBarrasPrecioActionPerformed

    private void spFraccionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spFraccionStateChanged
        calcularImporte();
}//GEN-LAST:event_spFraccionStateChanged

    private void cbPrecioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbPrecioItemStateChanged
        if (!tfCodigo.getText().isEmpty()) {
            tfCodeKeyEnter();
        }
        if (rbFraccionadaGondolas.isSelected()) {
            calcularImporte();
        }
    }//GEN-LAST:event_cbPrecioItemStateChanged

    private void tfCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodigoFocusLost
        if (!tfCodigo.getText().isEmpty()) {
            tfCodeKeyEnter();
            if ( rbFraccionadaGondolas.isSelected()) {
                calcularImporte();
            }
            tfCodigo.selectAll();
        }
    }//GEN-LAST:event_tfCodigoFocusLost

    private void pLabelsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pLabelsFocusGained
        lbSeleccionarEtiqueta.setText("[Seleccionar el tipo de etiqueta]");
    }//GEN-LAST:event_pLabelsFocusGained

    private void pLabelsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pLabelsFocusLost
        lbSeleccionarEtiqueta.setText(" Seleccionar el tipo de etiqueta");
    }//GEN-LAST:event_pLabelsFocusLost

private void tfCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodigoActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tfCodigoActionPerformed

private void tfPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPrecioActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tfPrecioActionPerformed

    private void bgLabelsRules(boolean valor) {
        tfImporte.setEnabled(valor);
        spFraccion.setEnabled(valor);
    }

    private void calcularImporte() {
        if (spFraccion.getValue() != null && articuloSeleccionado != null) {
            Double fraccion = (Double) spFraccion.getValue();
            Double importe;
            importe = fraccion * articuloSeleccionado.getPrecioMinorista();
            tfImporte.setText(formatter.format(importe));
        }
    }

    private void tfCodeKeyEnter() {
        try {
            articuloSeleccionado = ProductosPOSDAO.getArticulo(tfCodigo.getText());
            if (articuloSeleccionado != null) {
                tfCodigo.setText(articuloSeleccionado.getCodigoInterno());
                tfCodigoBarras.setText(articuloSeleccionado.getCodigoBarra());
                tfDescripcion.setText(articuloSeleccionado.getDescripcion());
                
                if (articuloSeleccionado.getPrecioMinorista() != null) {
                    tfPrecio.setText(formatter.format(articuloSeleccionado.getPrecioMinorista()));
                } else {
                    tfExistencia.setText("0.00"); // O algún valor predeterminado
                }
                if (articuloSeleccionado.getExistencia() != null) {
                    tfExistencia.setText(formatter.format(articuloSeleccionado.getExistencia()));
                } else {
                    tfExistencia.setText("0.00"); // O algún valor predeterminado
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Artículo Inexistente",
                        "Alerta!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println("Error! " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "No hay conexión con la base de datos",
                    "Alerta!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initListeners() {
        ((DefaultEditor) spFraccion.getEditor()).getTextField().addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent evt) {
                final JFormattedTextField textField = (JFormattedTextField) evt.getSource();
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        textField.selectAll();
                    }
                });
            }
        });
        ((DefaultEditor) spFraccion.getEditor()).getTextField().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent evt) {
                if (evt.getKeyChar() == '.') {
                    evt.setKeyChar(',');
                }
            }

            @Override
            public void keyPressed(KeyEvent evt) {
            }

            @Override
            public void keyReleased(KeyEvent evt) {
            }
        });
        ((DefaultEditor) spCantidad.getEditor()).getTextField().addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent evt) {
                final JFormattedTextField textField = (JFormattedTextField) evt.getSource();
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        textField.selectAll();
                    }
                });
            }
        });
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgLabels;
    private javax.swing.JButton btImprimir;
    private javax.swing.JComboBox cbImpresora;
    private javax.swing.JComboBox cbPrecio;
    private javax.swing.JLabel lbCantidad;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbCodigoBarras;
    private javax.swing.JLabel lbDescripcion;
    private javax.swing.JLabel lbExistencia;
    private javax.swing.JLabel lbFraccion;
    private javax.swing.JLabel lbImporte;
    private javax.swing.JLabel lbImpresora;
    private javax.swing.JLabel lbPrecio;
    private javax.swing.JLabel lbSeleccionarEtiqueta;
    private javax.swing.JPanel pLabels;
    private javax.swing.JRadioButton rbCodigoBarrasPrecio;
    private javax.swing.JRadioButton rbDeposito;
    private javax.swing.JRadioButton rbFraccionadaGondolas;
    private javax.swing.JRadioButton rbGondolas;
    private javax.swing.JSpinner spCantidad;
    private javax.swing.JSpinner spFraccion;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfCodigoBarras;
    private javax.swing.JTextField tfDescripcion;
    private javax.swing.JTextField tfExistencia;
    private javax.swing.JTextField tfImporte;
    private javax.swing.JTextField tfPrecio;
    // End of variables declaration//GEN-END:variables

}
