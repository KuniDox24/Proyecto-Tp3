import javax.swing.JOptionPane;

public class FormEntidad extends javax.swing.JFrame {
    
    public FormEntidad() {
        initComponents();
    }

    public FormEntidad(InterfazControlador _app){
        app = _app;
        initComponents();
    }

    private void initComponents() {

        bConfirmarFormEntidad = new javax.swing.JButton();
        entradaNombreEntidad = new javax.swing.JTextField();
        entradaContactoEntidad = new javax.swing.JTextField();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        ubicacionEntidadX = new javax.swing.JSpinner();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        bCancelarForm = new javax.swing.JButton();
        ubicacionEntidadY = new javax.swing.JSpinner();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        entradaRolEntidad = new javax.swing.JComboBox<>();

        setTitle("Formulario de Entidad");

        bConfirmarFormEntidad.setText("Confirmar");
        bConfirmarFormEntidad.addActionListener(this::bConfirmarFormEntidadActionPerformed);

        entradaNombreEntidad.setMinimumSize(new java.awt.Dimension(100, 30));
        entradaNombreEntidad.setPreferredSize(new java.awt.Dimension(150, 30));

        entradaContactoEntidad.setMinimumSize(new java.awt.Dimension(100, 30));
        entradaContactoEntidad.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Formulario de Entidad");

        ubicacionEntidadX.setMinimumSize(new java.awt.Dimension(70, 30));
        ubicacionEntidadX.setPreferredSize(new java.awt.Dimension(70, 30));

        jLabel2.setText("Nombre");
        jLabel2.setMaximumSize(new java.awt.Dimension(150, 20));
        jLabel2.setMinimumSize(new java.awt.Dimension(80, 20));
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 20));
        jLabel2.setRequestFocusEnabled(false);

        jLabel3.setText("Contacto");
        jLabel3.setMaximumSize(new java.awt.Dimension(150, 20));
        jLabel3.setMinimumSize(new java.awt.Dimension(80, 20));
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 20));
        jLabel3.setRequestFocusEnabled(false);

        jLabel4.setText("Ubicacion");
        jLabel4.setMaximumSize(new java.awt.Dimension(150, 20));
        jLabel4.setMinimumSize(new java.awt.Dimension(80, 20));
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 20));
        jLabel4.setRequestFocusEnabled(false);

        jLabel5.setText("Rol");
        jLabel5.setMaximumSize(new java.awt.Dimension(150, 20));
        jLabel5.setMinimumSize(new java.awt.Dimension(80, 20));
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 20));
        jLabel5.setRequestFocusEnabled(false);

        bCancelarForm.setText("Cancelar");
        bCancelarForm.addActionListener(this::bCancelarFormActionPerformed);

        ubicacionEntidadY.setMinimumSize(new java.awt.Dimension(70, 30));
        ubicacionEntidadY.setPreferredSize(new java.awt.Dimension(70, 30));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("X");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Y");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        entradaRolEntidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PROVEEDOR", "DISTRIBUIDOR", "ALMACEN", "FABRICANTE", "MINORISTA" }));
        entradaRolEntidad.setMinimumSize(new java.awt.Dimension(100, 30));
        entradaRolEntidad.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(entradaContactoEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(entradaRolEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(entradaNombreEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ubicacionEntidadX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(ubicacionEntidadY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(bCancelarForm)
                        .addGap(18, 18, 18)
                        .addComponent(bConfirmarFormEntidad)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entradaNombreEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entradaContactoEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entradaRolEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubicacionEntidadX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubicacionEntidadY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bConfirmarFormEntidad)
                    .addComponent(bCancelarForm))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bConfirmarFormEntidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfirmarFormEntidadActionPerformed
        //cuando se presiona confirmar

        //obtener todos los datos
        String nombre = entradaNombreEntidad.getText().trim();
        String contacto = entradaContactoEntidad.getText().trim();

        if(nombre.isEmpty() || contacto.isEmpty()){
            JOptionPane.showMessageDialog(this, "Debes llenar todos los campos");
            return;
        }

        String rol = entradaRolEntidad.getSelectedItem().toString();
        int[] ubicacion = {(Integer)ubicacionEntidadX.getValue(),(Integer)ubicacionEntidadY.getValue()};
        if(!app.agregarNodo(nombre, ubicacion, contacto, rol)){
            JOptionPane.showMessageDialog(this, "Ya existe una entidad en esa ubicación.", "Ubicación duplicada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        setVisible(false);


    }//GEN-LAST:event_bConfirmarFormEntidadActionPerformed

    private void bCancelarFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarFormActionPerformed
        setVisible(false);
    }//GEN-LAST:event_bCancelarFormActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelarForm;
    private javax.swing.JButton bConfirmarFormEntidad;
    private javax.swing.JTextField entradaContactoEntidad;
    private javax.swing.JTextField entradaNombreEntidad;
    private javax.swing.JComboBox<String> entradaRolEntidad;
    private javax.swing.JSpinner ubicacionEntidadX;
    private javax.swing.JSpinner ubicacionEntidadY;
    // End of variables declaration//GEN-END:variables
    private InterfazControlador app;
}
