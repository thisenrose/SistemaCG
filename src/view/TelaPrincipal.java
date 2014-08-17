/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

/**
 *
 * @author thiago
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        painelPrincipal = new javax.swing.JPanel();
        painelDeAcoes = new javax.swing.JPanel();
        painelDeObjetos = new javax.swing.JScrollPane();
        painelWindow = new javax.swing.JPanel();
        painelDeDesenho = new javax.swing.JPanel();
        barraDeMenu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        sairMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        adionarFormaMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        sobreMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Atividade de Computação Gráfica");
        setResizable(false);

        painelDeAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu de Funções"));

        painelDeObjetos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        painelDeObjetos.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Objetos")));

        painelWindow.setBorder(javax.swing.BorderFactory.createTitledBorder("Window"));

        javax.swing.GroupLayout painelWindowLayout = new javax.swing.GroupLayout(painelWindow);
        painelWindow.setLayout(painelWindowLayout);
        painelWindowLayout.setHorizontalGroup(
            painelWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelWindowLayout.setVerticalGroup(
            painelWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 504, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout painelDeAcoesLayout = new javax.swing.GroupLayout(painelDeAcoes);
        painelDeAcoes.setLayout(painelDeAcoesLayout);
        painelDeAcoesLayout.setHorizontalGroup(
            painelDeAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelDeObjetos, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
            .addComponent(painelWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        painelDeAcoesLayout.setVerticalGroup(
            painelDeAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDeAcoesLayout.createSequentialGroup()
                .addComponent(painelDeObjetos, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelDeDesenho.setBorder(javax.swing.BorderFactory.createTitledBorder("Viewport"));

        javax.swing.GroupLayout painelDeDesenhoLayout = new javax.swing.GroupLayout(painelDeDesenho);
        painelDeDesenho.setLayout(painelDeDesenhoLayout);
        painelDeDesenhoLayout.setHorizontalGroup(
            painelDeDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 871, Short.MAX_VALUE)
        );
        painelDeDesenhoLayout.setVerticalGroup(
            painelDeDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addComponent(painelDeAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelDeDesenho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelDeAcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(painelDeDesenho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        fileMenu.setMnemonic('f');
        fileMenu.setText("Arquivo");

        sairMenuItem.setMnemonic('x');
        sairMenuItem.setText("Sair");
        sairMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(sairMenuItem);

        barraDeMenu.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Editar");

        adionarFormaMenuItem.setMnemonic('t');
        adionarFormaMenuItem.setText("Adicionar Forma");
        adionarFormaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adionarFormaMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(adionarFormaMenuItem);

        barraDeMenu.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Ajuda");

        sobreMenuItem.setMnemonic('a');
        sobreMenuItem.setText("Sobre");
        sobreMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sobreMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(sobreMenuItem);

        barraDeMenu.add(helpMenu);

        setJMenuBar(barraDeMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void sairMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
        System.exit(0);
    }                                            

    private void sobreMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                              
        SobreDialog sobreDialog = new SobreDialog(this, rootPaneCheckingEnabled);
        sobreDialog.setLocationRelativeTo(this);
        sobreDialog.setVisible(true);
    }                                             

    private void adionarFormaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                     
       AdicionarFormaDialog adicionarFormDialog = new AdicionarFormaDialog(this, rootPaneCheckingEnabled);
       adicionarFormDialog.setLocationRelativeTo(this);
       adicionarFormDialog.setVisible(true);
    }                                                    

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JMenuItem adionarFormaMenuItem;
    private javax.swing.JMenuBar barraDeMenu;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel painelDeAcoes;
    private javax.swing.JPanel painelDeDesenho;
    private javax.swing.JScrollPane painelDeObjetos;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JPanel painelWindow;
    private javax.swing.JMenuItem sairMenuItem;
    private javax.swing.JMenuItem sobreMenuItem;
    // End of variables declaration                   

}
