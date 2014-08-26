/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import modelo.ObjetoGeometrico;

/**
 *
 * @author thiago
 */
public class ListaDeAcoesDeMovimentosDialog extends javax.swing.JDialog {

	private ObjetoGeometrico objeto;

	/**
	 * Creates new form ListaDeMovimentosDialog
	 */
	public ListaDeAcoesDeMovimentosDialog(java.awt.Frame parent, boolean modal, ObjetoGeometrico objeto) {
		super(parent, modal);
		this.objeto = objeto;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		botaoAbrirDialogEscalonar = new javax.swing.JButton();
		botaoAbrirDialogRotacionar = new javax.swing.JButton();
		botaoAbrirDialogTransladar = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Ações sobre o Objeto");
		setResizable(false);

		botaoAbrirDialogEscalonar.setText("Escalonar");
		botaoAbrirDialogEscalonar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botaoAbrirDialogEscalonarActionPerformed(evt);
			}
		});

		botaoAbrirDialogRotacionar.setText("Rotacionar");
		botaoAbrirDialogRotacionar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botaoAbrirDialogRotacionarActionPerformed(evt);
			}
		});

		botaoAbrirDialogTransladar.setText("Transladar");
		botaoAbrirDialogTransladar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botaoAbrirDialogTransladarActionPerformed(evt);
			}
		});

		jButton4.setText("Cancelar");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(botaoAbrirDialogEscalonar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(botaoAbrirDialogRotacionar, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
				.addComponent(botaoAbrirDialogTransladar, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jButton4).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addComponent(botaoAbrirDialogEscalonar).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(botaoAbrirDialogRotacionar).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(botaoAbrirDialogTransladar).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButton4)
						.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void botaoAbrirDialogEscalonarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoAbrirDialogEscalonarActionPerformed
		EscalonarDialog escalonarDialog = new EscalonarDialog(null, true, objeto);
		escalonarDialog.setLocationRelativeTo(this);
		this.setVisible(false);
		escalonarDialog.setVisible(true);
		((TelaPrincipal) this.getParent()).atualizarPainelDeDesenho();
	}// GEN-LAST:event_botaoAbrirDialogEscalonarActionPerformed

	private void botaoAbrirDialogRotacionarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoAbrirDialogRotacionarActionPerformed
		RotacionarDialog rotacionarDialog = new RotacionarDialog(null, true, objeto);
		rotacionarDialog.setLocationRelativeTo(this);
		this.setVisible(false);
		rotacionarDialog.setVisible(true);
		((TelaPrincipal) this.getParent()).atualizarPainelDeDesenho();
	}// GEN-LAST:event_botaoAbrirDialogRotacionarActionPerformed

	private void botaoAbrirDialogTransladarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoAbrirDialogTransladarActionPerformed
		TransladarDialog transladarDialog = new TransladarDialog(null, true, objeto);
		transladarDialog.setLocationRelativeTo(this);
		this.setVisible(false);
		transladarDialog.setVisible(true);
		((TelaPrincipal) this.getParent()).atualizarPainelDeDesenho();
	}// GEN-LAST:event_botaoAbrirDialogTransladarActionPerformed

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton4ActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton botaoAbrirDialogEscalonar;
	private javax.swing.JButton botaoAbrirDialogRotacionar;
	private javax.swing.JButton botaoAbrirDialogTransladar;
	private javax.swing.JButton jButton4;
	// End of variables declaration//GEN-END:variables
}