/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import modelo.Coordenada;
import modelo.movimentos.Translador;
import modelo.objetos.ObjetoGeometrico;
import view.PainelDeDesenho;
import view.Validador;

/**
 *
 * @author thiago
 */
public class TransladarDialog extends javax.swing.JDialog {

	/**
	 * A return status code - returned if Cancel button has been pressed
	 */
	public static final int RET_CANCEL = 0;
	/**
	 * A return status code - returned if OK button has been pressed
	 */
	public static final int RET_OK = 1;
	private ObjetoGeometrico objeto;
	private Translador translador;
	private PainelDeDesenho painelDeDesenho;

	/**
	 * Creates new form EscalonarDialog
	 * @param displayFile 
	 */
	public TransladarDialog(java.awt.Frame parent, boolean modal, ObjetoGeometrico objeto, PainelDeDesenho painelDeDesenho) {
		super(parent, modal);
		this.objeto = objeto;
		this.painelDeDesenho = painelDeDesenho;
		initComponents();

		// Close the dialog when Esc is pressed
		String cancelName = "cancel";
		InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
		ActionMap actionMap = getRootPane().getActionMap();
		actionMap.put(cancelName, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doClose(RET_CANCEL);
			}
		});
	}

	/**
	 * @return the return status of this dialog - one of RET_OK or RET_CANCEL
	 */
	public int getReturnStatus() {
		return returnStatus;
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

		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		painelVetor = new javax.swing.JPanel();
		xLabel = new javax.swing.JLabel();
		yLabel = new javax.swing.JLabel();
		vetorYTextField = new javax.swing.JTextField();
		vetorXTextField = new javax.swing.JTextField();

		setTitle("Transladar");
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				closeDialog(evt);
			}
		});

		okButton.setText("OK");
		okButton.setToolTipText("Clique para escalonar");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});

		cancelButton.setText("Cancel");
		cancelButton.setToolTipText("Clique para cancelar a rotação");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		painelVetor.setBorder(javax.swing.BorderFactory.createTitledBorder("Vetor"));

		xLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		xLabel.setText("X:");

		yLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		yLabel.setText("Y:");

		javax.swing.GroupLayout painelVetorLayout = new javax.swing.GroupLayout(painelVetor);
		painelVetor.setLayout(painelVetorLayout);
		painelVetorLayout.setHorizontalGroup(painelVetorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelVetorLayout.createSequentialGroup().addContainerGap().addComponent(xLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(vetorXTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
						.addComponent(yLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(vetorYTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		painelVetorLayout.setVerticalGroup(painelVetorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelVetorLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								painelVetorLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(xLabel)
										.addComponent(yLabel)
										.addComponent(vetorYTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(vetorXTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cancelButton).addContainerGap())
				.addComponent(painelVetor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] { cancelButton, okButton });

		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addComponent(painelVetor, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(okButton).addComponent(cancelButton))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		getRootPane().setDefaultButton(okButton);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okButtonActionPerformed
		boolean semErro = Validador.validarInteiro(vetorXTextField, xLabel);
		semErro &= Validador.validarInteiro(vetorYTextField, yLabel);
		if (semErro) {
			int x = Integer.valueOf(this.vetorXTextField.getText());
			int y = Integer.valueOf(this.vetorYTextField.getText());
			Coordenada fatorDeEscala = new Coordenada(x, y);
			translador = new Translador(objeto, fatorDeEscala);
			translador.movimentar();
			painelDeDesenho.atualizarObjeto(objeto);
			doClose(RET_OK);
		}

		doClose(RET_OK);
	}// GEN-LAST:event_okButtonActionPerformed

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
		doClose(RET_CANCEL);
	}// GEN-LAST:event_cancelButtonActionPerformed

	/**
	 * Closes the dialog
	 */
	private void closeDialog(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_closeDialog
		doClose(RET_CANCEL);
	}// GEN-LAST:event_closeDialog

	private void doClose(int retStatus) {
		returnStatus = retStatus;
		setVisible(false);
		dispose();
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton cancelButton;
	private javax.swing.JButton okButton;
	private javax.swing.JPanel painelVetor;
	private javax.swing.JTextField vetorXTextField;
	private javax.swing.JTextField vetorYTextField;
	private javax.swing.JLabel xLabel;
	private javax.swing.JLabel yLabel;
	// End of variables declaration//GEN-END:variables

	private int returnStatus = RET_CANCEL;
}
