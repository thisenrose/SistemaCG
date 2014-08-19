/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import modelo.Coordenada;
import modelo.DisplayFile;
import modelo.ObjetoGeometrico;
import modelo.Poligono;
import modelo.Ponto;
import modelo.Reta;

/**
 *
 * @author thiago
 */
public class AdicionarFormaDialog extends javax.swing.JDialog {

	/**
	 * A return status code - returned if Cancel button has been pressed
	 */
	public static final int RET_CANCEL = 0;
	/**
	 * A return status code - returned if OK button has been pressed
	 */
	public static final int RET_OK = 1;

	public static final int TAB_PONTO = 0;
	public static final int TAB_RETA = 1;
	public static final int TAB_POLIGONO = 2;
	public static final String REGEX_PARA_INTEIRO = "\\d+";

	private List<Coordenada> coordenadasDoPoligono;

	/**
	 * Creates new form AdicionarFormaDialog
	 */
	public AdicionarFormaDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		coordenadasDoPoligono = new ArrayList<Coordenada>();
		initComponents();

		// Close the dialog when Esc is pressed
		String cancelName = "cancel";
		InputMap inputMap = getRootPane().getInputMap(
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		nomeLabel = new javax.swing.JLabel();
		painelFormas = new javax.swing.JTabbedPane();
		painelPonto = new javax.swing.JPanel();
		painelCoordenadasPonto = new javax.swing.JPanel();
		coordenadaPontoXLabel = new javax.swing.JLabel();
		coordenadaPontoXTextField = new javax.swing.JTextField();
		coordenadaPontoYLabel = new javax.swing.JLabel();
		coordenadaPontoYTextField = new javax.swing.JTextField();
		painelReta = new javax.swing.JPanel();
		painelCoordenadasFinalReta = new javax.swing.JPanel();
		coordenadaFInalRetaXLabel = new javax.swing.JLabel();
		coordenadaFinalRetaXTextField = new javax.swing.JTextField();
		coordenadaFinalRetaYLabel = new javax.swing.JLabel();
		coordenadaFinalRetaYTextField = new javax.swing.JTextField();
		painelCoordenadasInicialReta = new javax.swing.JPanel();
		coordenadaInicialRetaXLabel = new javax.swing.JLabel();
		coordenadaInicialRetaXTextField = new javax.swing.JTextField();
		coordenadaInicialRetaYLabel = new javax.swing.JLabel();
		coordenadaInicialRetaYTextField = new javax.swing.JTextField();
		painelPoligono = new javax.swing.JPanel();
		painelDeRolagemDoPoligono = new javax.swing.JScrollPane();
		listaDeCoordenadasDoPoligono = new java.awt.List();
		botaoAdicionarPonto = new javax.swing.JButton();
		botaoRemoverPonto = new javax.swing.JButton();
		nomeTextField = new javax.swing.JTextField();

		jLabel1.setText("X:");

		setTitle("Adicionar Forma");
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				closeDialog(evt);
			}
		});

		okButton.setText("OK");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});

		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		nomeLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		nomeLabel.setText("Nome:");

		painelCoordenadasPonto.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Coordenadas"));

		coordenadaPontoXLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		coordenadaPontoXLabel.setText("X:");

		coordenadaPontoYLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		coordenadaPontoYLabel.setText("Y:");

		javax.swing.GroupLayout painelCoordenadasPontoLayout = new javax.swing.GroupLayout(
				painelCoordenadasPonto);
		painelCoordenadasPonto.setLayout(painelCoordenadasPontoLayout);
		painelCoordenadasPontoLayout
				.setHorizontalGroup(painelCoordenadasPontoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								painelCoordenadasPontoLayout
										.createSequentialGroup()
										.addGap(56, 56, 56)
										.addComponent(coordenadaPontoXLabel)
										.addGap(18, 18, 18)
										.addComponent(
												coordenadaPontoXTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												45,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(64, 64, 64)
										.addComponent(coordenadaPontoYLabel)
										.addGap(18, 18, 18)
										.addComponent(
												coordenadaPontoYTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												45,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(59, Short.MAX_VALUE)));
		painelCoordenadasPontoLayout
				.setVerticalGroup(painelCoordenadasPontoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								painelCoordenadasPontoLayout
										.createSequentialGroup()
										.addGap(57, 57, 57)
										.addGroup(
												painelCoordenadasPontoLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																coordenadaPontoXLabel)
														.addComponent(
																coordenadaPontoXTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																coordenadaPontoYLabel)
														.addComponent(
																coordenadaPontoYTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(57, Short.MAX_VALUE)));

		javax.swing.GroupLayout painelPontoLayout = new javax.swing.GroupLayout(
				painelPonto);
		painelPonto.setLayout(painelPontoLayout);
		painelPontoLayout.setHorizontalGroup(painelPontoLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						painelPontoLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(painelCoordenadasPonto,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addContainerGap()));
		painelPontoLayout.setVerticalGroup(painelPontoLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						painelPontoLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(painelCoordenadasPonto,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addContainerGap()));

		painelFormas.addTab("Ponto", painelPonto);

		painelCoordenadasFinalReta.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Coordenadas do Ponto Final"));

		coordenadaFInalRetaXLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		coordenadaFInalRetaXLabel.setText("X:");

		coordenadaFinalRetaYLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		coordenadaFinalRetaYLabel.setText("Y:");

		javax.swing.GroupLayout painelCoordenadasFinalRetaLayout = new javax.swing.GroupLayout(
				painelCoordenadasFinalReta);
		painelCoordenadasFinalReta.setLayout(painelCoordenadasFinalRetaLayout);
		painelCoordenadasFinalRetaLayout
				.setHorizontalGroup(painelCoordenadasFinalRetaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								painelCoordenadasFinalRetaLayout
										.createSequentialGroup()
										.addGap(25, 25, 25)
										.addComponent(coordenadaFInalRetaXLabel)
										.addGap(33, 33, 33)
										.addComponent(
												coordenadaFinalRetaXTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												45,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(53, 53, 53)
										.addComponent(coordenadaFinalRetaYLabel)
										.addGap(18, 18, 18)
										.addComponent(
												coordenadaFinalRetaYTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												45,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		painelCoordenadasFinalRetaLayout
				.setVerticalGroup(painelCoordenadasFinalRetaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								painelCoordenadasFinalRetaLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												painelCoordenadasFinalRetaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																coordenadaFInalRetaXLabel)
														.addComponent(
																coordenadaFinalRetaXTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																coordenadaFinalRetaYLabel)
														.addComponent(
																coordenadaFinalRetaYTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(45, 45, 45)));

		painelCoordenadasInicialReta.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Coordenadas do Ponto Inicial"));

		coordenadaInicialRetaXLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		coordenadaInicialRetaXLabel.setText("X:");

		coordenadaInicialRetaYLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		coordenadaInicialRetaYLabel.setText("Y:");

		javax.swing.GroupLayout painelCoordenadasInicialRetaLayout = new javax.swing.GroupLayout(
				painelCoordenadasInicialReta);
		painelCoordenadasInicialReta
				.setLayout(painelCoordenadasInicialRetaLayout);
		painelCoordenadasInicialRetaLayout
				.setHorizontalGroup(painelCoordenadasInicialRetaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								painelCoordenadasInicialRetaLayout
										.createSequentialGroup()
										.addGap(25, 25, 25)
										.addComponent(
												coordenadaInicialRetaXLabel)
										.addGap(33, 33, 33)
										.addComponent(
												coordenadaInicialRetaXTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												45,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(53, 53, 53)
										.addComponent(
												coordenadaInicialRetaYLabel)
										.addGap(18, 18, 18)
										.addComponent(
												coordenadaInicialRetaYTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												45,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(86, Short.MAX_VALUE)));
		painelCoordenadasInicialRetaLayout
				.setVerticalGroup(painelCoordenadasInicialRetaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								painelCoordenadasInicialRetaLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												painelCoordenadasInicialRetaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																coordenadaInicialRetaXLabel)
														.addComponent(
																coordenadaInicialRetaXTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																coordenadaInicialRetaYLabel)
														.addComponent(
																coordenadaInicialRetaYTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(45, 45, 45)));

		javax.swing.GroupLayout painelRetaLayout = new javax.swing.GroupLayout(
				painelReta);
		painelReta.setLayout(painelRetaLayout);
		painelRetaLayout
				.setHorizontalGroup(painelRetaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								painelRetaLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												painelRetaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																painelCoordenadasInicialReta,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																painelCoordenadasFinalReta,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));
		painelRetaLayout
				.setVerticalGroup(painelRetaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								painelRetaLayout
										.createSequentialGroup()
										.addGap(0, 12, Short.MAX_VALUE)
										.addComponent(
												painelCoordenadasInicialReta,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												80,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												painelCoordenadasFinalReta,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												80,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		painelFormas.addTab("Reta", painelReta);

		painelDeRolagemDoPoligono.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Pontos"));
		painelDeRolagemDoPoligono.setViewportView(listaDeCoordenadasDoPoligono);

		botaoAdicionarPonto.setText("Adicionar");
		botaoAdicionarPonto.setToolTipText("Clique para adicionar ponto.");
		botaoAdicionarPonto
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						botaoAdicionarPontoActionPerformed(evt);
					}
				});

		botaoRemoverPonto.setText("Remover");
		botaoRemoverPonto
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						botaoRemoverPontoActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout painelPoligonoLayout = new javax.swing.GroupLayout(
				painelPoligono);
		painelPoligono.setLayout(painelPoligonoLayout);
		painelPoligonoLayout
				.setHorizontalGroup(painelPoligonoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								painelPoligonoLayout
										.createSequentialGroup()
										.addContainerGap(60, Short.MAX_VALUE)
										.addGroup(
												painelPoligonoLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																painelPoligonoLayout
																		.createSequentialGroup()
																		.addComponent(
																				botaoAdicionarPonto)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				botaoRemoverPonto))
														.addComponent(
																painelDeRolagemDoPoligono,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																244,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(67, Short.MAX_VALUE)));
		painelPoligonoLayout
				.setVerticalGroup(painelPoligonoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								painelPoligonoLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												painelDeRolagemDoPoligono,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												133,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												painelPoligonoLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																botaoAdicionarPonto)
														.addComponent(
																botaoRemoverPonto))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		painelFormas.addTab("Polígono", painelPoligono);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(painelFormas)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGap(0,
																		0,
																		Short.MAX_VALUE)
																.addComponent(
																		okButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		67,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		cancelButton))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(47, 47,
																		47)
																.addComponent(
																		nomeLabel)
																.addGap(18, 18,
																		18)
																.addComponent(
																		nomeTextField,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		186,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(0,
																		0,
																		Short.MAX_VALUE)))
								.addContainerGap()));

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { cancelButton, okButton });

		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(nomeLabel)
												.addComponent(
														nomeTextField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(painelFormas)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(cancelButton)
												.addComponent(okButton))
								.addContainerGap()));

		getRootPane().setDefaultButton(okButton);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void botaoRemoverPontoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoRemoverPontoActionPerformed
		int indiceSelecionado = this.listaDeCoordenadasDoPoligono
				.getSelectedIndex();
		if (indiceSelecionado != -1) {
			this.listaDeCoordenadasDoPoligono.remove(indiceSelecionado);
			this.coordenadasDoPoligono.remove(indiceSelecionado);
		}
		;
	}// GEN-LAST:event_botaoRemoverPontoActionPerformed

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okButtonActionPerformed
		boolean semErros = true;
		switch (this.painelFormas.getSelectedIndex()) {
		case TAB_PONTO:
			semErros = criarPonto();
			break;
		case TAB_RETA:
			semErros = criarReta();
			break;
		case TAB_POLIGONO:
			semErros = criarPoligono();
			break;
		}
		if (semErros) {
			List<ObjetoGeometrico> objetos = DisplayFile.obterInstancia()
					.getObjetos();
			int utlimo = objetos.size() - 1;
			((TelaPrincipal) this.getParent()).adicionarObjeto(objetos
					.get(utlimo));
			((TelaPrincipal) this.getParent()).atualizarPainelDeDesenho();
			doClose(RET_OK);
		}
	}// GEN-LAST:event_okButtonActionPerformed

	private boolean criarPonto() {
		boolean semErros = false;

		semErros = Validador.validarInteiro(this.coordenadaPontoXTextField,
				this.coordenadaPontoXLabel);
		semErros &= Validador.validarInteiro(this.coordenadaPontoYTextField,
				this.coordenadaPontoYLabel);
		semErros &= Validador.validarString(this.nomeTextField, this.nomeLabel);

		if (semErros) {
			String nome = this.nomeTextField.getText();
			Integer pontoX = Integer.parseInt(this.coordenadaPontoXTextField
					.getText());
			Integer pontoY = Integer.parseInt(this.coordenadaPontoYTextField
					.getText());
			Coordenada c = new Coordenada(pontoX, pontoY);
			DisplayFile.obterInstancia().adicionar(new Ponto(nome, c));
		}
		return semErros;
	}

	public boolean criarReta() {
		boolean semErros = false;
		semErros = Validador.validarInteiro(
				this.coordenadaInicialRetaXTextField,
				this.coordenadaInicialRetaXLabel);
		semErros &= Validador.validarInteiro(
				this.coordenadaInicialRetaYTextField,
				this.coordenadaInicialRetaYLabel);
		semErros &= Validador.validarInteiro(
				this.coordenadaFinalRetaXTextField,
				this.coordenadaFInalRetaXLabel);
		semErros &= Validador.validarInteiro(
				this.coordenadaFinalRetaYTextField,
				this.coordenadaFinalRetaYLabel);
		semErros &= Validador.validarString(this.nomeTextField, this.nomeLabel);

		if (semErros) {
			String nome = this.nomeTextField.getText();
			Integer xInicial = Integer
					.parseInt(this.coordenadaInicialRetaXTextField.getText());
			Integer yInicial = Integer
					.parseInt(this.coordenadaInicialRetaYTextField.getText());
			Integer xFinal = Integer
					.parseInt(this.coordenadaFinalRetaXTextField.getText());
			Integer yFinal = Integer
					.parseInt(this.coordenadaFinalRetaYTextField.getText());
			Coordenada coordenadaDeA = new Coordenada(xInicial, yInicial);
			Coordenada coordenadaDeB = new Coordenada(xFinal, yFinal);
			Reta reta = new Reta(nome, coordenadaDeA, coordenadaDeB);
			DisplayFile.obterInstancia().adicionar(reta);
		}
		return semErros;

	}

	private boolean criarPoligono() {
		boolean semErro = Validador.validarString(nomeTextField, nomeLabel);
		semErro &= coordenadasDoPoligono.size() >= 3;
		if (semErro) {
			DisplayFile.obterInstancia()
					.adicionar(
							new Poligono(nomeTextField.getText(),
									coordenadasDoPoligono));
		}
		return semErro;
	}

	public void adicionarCoordenadaAoPoligono(Coordenada coordenada) {
		coordenadasDoPoligono.add(coordenada);
		String descricao = "Ponto ".concat(String.valueOf(
				coordenadasDoPoligono.size()).concat(
				" X:" + coordenada.getX() + " Y:" + coordenada.getY()));
		this.listaDeCoordenadasDoPoligono.add(descricao);
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
		doClose(RET_CANCEL);
	}// GEN-LAST:event_cancelButtonActionPerformed

	/**
	 * Closes the dialog
	 */
	private void closeDialog(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_closeDialog
		doClose(RET_CANCEL);
	}// GEN-LAST:event_closeDialog

	private void botaoAdicionarPontoActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoAdicionarPontoActionPerformed
		AdicionarPontoDialog adicionarPontoDialog = new AdicionarPontoDialog(
				null, this, rootPaneCheckingEnabled);
		adicionarPontoDialog.setLocationRelativeTo(this);
		adicionarPontoDialog.setVisible(true);
	}// GEN-LAST:event_botaoAdicionarPontoActionPerformed

	private void doClose(int retStatus) {
		returnStatus = retStatus;
		setVisible(false);
		dispose();
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton botaoAdicionarPonto;
	private javax.swing.JButton botaoRemoverPonto;
	private javax.swing.JButton cancelButton;
	private javax.swing.JLabel coordenadaFInalRetaXLabel;
	private javax.swing.JTextField coordenadaFinalRetaXTextField;
	private javax.swing.JLabel coordenadaFinalRetaYLabel;
	private javax.swing.JTextField coordenadaFinalRetaYTextField;
	private javax.swing.JLabel coordenadaInicialRetaXLabel;
	private javax.swing.JTextField coordenadaInicialRetaXTextField;
	private javax.swing.JLabel coordenadaInicialRetaYLabel;
	private javax.swing.JTextField coordenadaInicialRetaYTextField;
	private javax.swing.JLabel coordenadaPontoXLabel;
	private javax.swing.JTextField coordenadaPontoXTextField;
	private javax.swing.JLabel coordenadaPontoYLabel;
	private javax.swing.JTextField coordenadaPontoYTextField;
	private javax.swing.JLabel jLabel1;
	private java.awt.List listaDeCoordenadasDoPoligono;
	private javax.swing.JLabel nomeLabel;
	private javax.swing.JTextField nomeTextField;
	private javax.swing.JButton okButton;
	private javax.swing.JPanel painelCoordenadasFinalReta;
	private javax.swing.JPanel painelCoordenadasInicialReta;
	private javax.swing.JPanel painelCoordenadasPonto;
	private javax.swing.JScrollPane painelDeRolagemDoPoligono;
	private javax.swing.JTabbedPane painelFormas;
	private javax.swing.JPanel painelPoligono;
	private javax.swing.JPanel painelPonto;
	private javax.swing.JPanel painelReta;
	// End of variables declaration//GEN-END:variables

	private int returnStatus = RET_CANCEL;

}
