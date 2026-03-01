package view.client;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLlogin = new javax.swing.JLabel();
        jLusuarioLogin = new javax.swing.JLabel();
        jLsenhaLogin = new javax.swing.JLabel();
        jTusuarioLogin = new javax.swing.JTextField();
        jPsenhaLogin = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLcadastre_se = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLlogin.setFont(new java.awt.Font("Showcard Gothic", 0, 24)); // NOI18N
        jLlogin.setText("LOGIN");

        jLusuarioLogin.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLusuarioLogin.setText("usuário");

        jLsenhaLogin.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLsenhaLogin.setText("senha");

        jButton1.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jButton1.setText("Entrar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLcadastre_se.setFont(new java.awt.Font("Segoe UI Variable", 0, 12)); // NOI18N
        jLcadastre_se.setText("Não tem uma conta? Cadastre-se");
        jLcadastre_se.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLusuarioLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLsenhaLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTusuarioLogin)
                                    .addComponent(jPsenhaLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(jLlogin))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jLcadastre_se, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(212, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLlogin)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTusuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLusuarioLogin))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLsenhaLogin)
                    .addComponent(jPsenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLcadastre_se)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLcadastre_se;
    private javax.swing.JLabel jLlogin;
    private javax.swing.JLabel jLsenhaLogin;
    private javax.swing.JLabel jLusuarioLogin;
    private javax.swing.JPasswordField jPsenhaLogin;
    private javax.swing.JTextField jTusuarioLogin;
    // End of variables declaration//GEN-END:variables
}
