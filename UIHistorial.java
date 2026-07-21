import java.util.Vector;

import javax.swing.DefaultListModel;

/**
 *
 * @author Nico
 */
public class UIHistorial extends javax.swing.JFrame {
    
    /**
     * Creates new form UIHistorial
     */
    public UIHistorial() {
        initComponents();
    }

    public UIHistorial(Vector<String> _datos){
        initComponents();
        DefaultListModel modelo =new DefaultListModel<String>();
        modelo.addAll(_datos);
        listaHistorial.setModel(modelo);
        
    }

    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        listaHistorial = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Historial");

        jScrollPane2.setViewportView(listaHistorial);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HISTORIAL DE PAQUETES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaHistorial;
    // End of variables declaration//GEN-END:variables
}
