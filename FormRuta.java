
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Nico
 */
public class FormRuta extends javax.swing.JFrame {
    
    public FormRuta() {
        initComponents();
    }
    public FormRuta(InterfazControlador _app){
        initComponents();
        app = _app;
        cargarOpciones();
    }

    private void initComponents() {

        bConfirmarForm = new javax.swing.JButton();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        bCancelarForm = new javax.swing.JButton();
        selectorOrigen = new javax.swing.JComboBox<>();
        selectorDestino = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulario de Ruta");
        setMinimumSize(new java.awt.Dimension(400, 400));

        bConfirmarForm.setText("Confirmar");
        bConfirmarForm.addActionListener(this::bConfirmarFormActionPerformed);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Formulario de Ruta");

        jLabel2.setText("Origen");
        jLabel2.setMaximumSize(new java.awt.Dimension(200, 50));
        jLabel2.setMinimumSize(new java.awt.Dimension(100, 20));
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 20));
        jLabel2.setRequestFocusEnabled(false);

        jLabel3.setText("Destino");
        jLabel3.setMaximumSize(new java.awt.Dimension(200, 50));
        jLabel3.setMinimumSize(new java.awt.Dimension(100, 20));
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 20));
        jLabel3.setRequestFocusEnabled(false);

        bCancelarForm.setText("Cancelar");
        bCancelarForm.addActionListener(this::bCancelarFormActionPerformed);

        selectorOrigen.setMinimumSize(new java.awt.Dimension(150, 20));
        selectorOrigen.setPreferredSize(new java.awt.Dimension(200, 20));

        selectorDestino.setMinimumSize(new java.awt.Dimension(150, 20));
        selectorDestino.setPreferredSize(new java.awt.Dimension(200, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selectorOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selectorDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bCancelarForm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bConfirmarForm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectorOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectorDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bConfirmarForm)
                    .addComponent(bCancelarForm))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void cargarOpciones(){
        Vector<String> opciones = app.getOpciones();

        selectorDestino.setModel(new DefaultComboBoxModel<>(opciones));
        selectorOrigen.setModel(new DefaultComboBoxModel<>(opciones));


    }

    private void bConfirmarFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfirmarFormActionPerformed
        //cuando se presiona confirmar
        String origen = selectorOrigen.getSelectedItem().toString();
        String destino = selectorDestino.getSelectedItem().toString();

        if(origen.equals(destino)){
            JOptionPane.showMessageDialog(this, "La ruta debe tener un origen y destino distintos");
            return;
        }
        app.agregarRuta(origen, destino);
        setVisible(false);

    }//GEN-LAST:event_bConfirmarFormActionPerformed

    private void bCancelarFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarFormActionPerformed
        setVisible(false);
    }//GEN-LAST:event_bCancelarFormActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelarForm;
    private javax.swing.JButton bConfirmarForm;
    private javax.swing.JComboBox<String> selectorDestino;
    private javax.swing.JComboBox<String> selectorOrigen;
    // End of variables declaration//GEN-END:variables
    private InterfazControlador app;
}
