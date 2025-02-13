/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package game;

/**
 *
 * @author hoang
 */
public class Sceen extends javax.swing.JFrame {

    /**
     * Creates new form Sceen
     */
    public Sceen() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NEWGAME = new javax.swing.JButton();
        CONTINUE = new javax.swing.JButton();
        SETTING = new javax.swing.JButton();
        QUIT = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(500, 500));

        NEWGAME.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        NEWGAME.setText("New Game");
        NEWGAME.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NEWGAME.setPreferredSize(new java.awt.Dimension(150, 40));
        NEWGAME.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NEWGAMEMouseClicked(evt);
            }
        });
        NEWGAME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NEWGAMEActionPerformed(evt);
            }
        });

        CONTINUE.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        CONTINUE.setText("Continue");
        CONTINUE.setPreferredSize(new java.awt.Dimension(150, 40));
        CONTINUE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONTINUEActionPerformed(evt);
            }
        });

        SETTING.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SETTING.setText("Setting");
        SETTING.setPreferredSize(new java.awt.Dimension(150, 40));
        SETTING.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SETTINGActionPerformed(evt);
            }
        });

        QUIT.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        QUIT.setText("Quit");
        QUIT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QUITMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REQUIEM DREAM");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(62, 492, Short.MAX_VALUE)
                        .addComponent(CONTINUE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(QUIT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SETTING, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NEWGAME, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(NEWGAME, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CONTINUE, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SETTING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NEWGAMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NEWGAMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NEWGAMEActionPerformed

    private void SETTINGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SETTINGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SETTINGActionPerformed

    private void NEWGAMEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NEWGAMEMouseClicked
        dispose();
        InGameScreen IGS = new InGameScreen();
        IGS.setVisible(true);
    }//GEN-LAST:event_NEWGAMEMouseClicked

    private void QUITMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QUITMouseClicked
        System.exit(0);
    }//GEN-LAST:event_QUITMouseClicked

    private void CONTINUEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONTINUEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CONTINUEActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sceen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sceen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sceen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sceen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sceen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CONTINUE;
    private javax.swing.JButton NEWGAME;
    private javax.swing.JButton QUIT;
    private javax.swing.JButton SETTING;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
