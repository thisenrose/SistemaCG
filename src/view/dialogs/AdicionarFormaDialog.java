/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.dialogs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import modelo.Coordenada;
import modelo.objetos.Curva;
import modelo.objetos.CurvaBSpline;
import modelo.objetos.ObjetoGeometrico;
import modelo.objetos.Poligono;
import modelo.objetos.Ponto;
import modelo.objetos.Reta;
import view.PainelDeDesenho;
import view.Validador;

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
	public static final int TAB_CURVA = 3;
	private static final int TAB_BSPLINE = 4;
	public static final String REGEX_PARA_INTEIRO = "\\d+";

	private List<Coordenada> coordenadasDoPoligono;
	private List<Coordenada> coordenadasDaBSpline;
	private List<Coordenada> coordenadasDeControleDaCurva;
	private List<Coordenada> coordenadasNormaisDaCurva;
	private boolean isEdicao;
	private int indiceSelecionado;
	private Color corSelecionada;
	private PainelDeDesenho painelDeDesenho;

	/**
	 * Creates new form AdicionarFormaDialog
	 * 
	 * @param indiceSelecionado
	 * @param isEdicao
	 */
	public AdicionarFormaDialog(java.awt.Frame parent, boolean modal, boolean isEdicao, int indiceSelecionado,
			PainelDeDesenho painelDeDesenho) {
		super(parent, modal);
		this.isEdicao = isEdicao;
		this.indiceSelecionado = indiceSelecionado;
		this.painelDeDesenho = painelDeDesenho;
		coordenadasDoPoligono = new ArrayList<Coordenada>();
		coordenadasDaBSpline = new ArrayList<Coordenada>();
		coordenadasDeControleDaCurva = new ArrayList<Coordenada>();
		coordenadasNormaisDaCurva = new ArrayList<Coordenada>();
		initComponents();
		if (isEdicao) {
			ObjetoGeometrico objetoGeometrico = painelDeDesenho.getObjetos().get(indiceSelecionado);
			List<Coordenada> coordenadas = objetoGeometrico.getCoordenadas();
			this.nomeTextField.setText(objetoGeometrico.getNome());
			this.botaoAlterarCor.setBackground(objetoGeometrico.getCor());

			if (objetoGeometrico instanceof Ponto) {
				this.painelFormas.setSelectedIndex(TAB_PONTO);
				this.coordenadaPontoXTextField.setText(String.valueOf(coordenadas.get(0).getX()));
				this.coordenadaPontoYTextField.setText(String.valueOf(coordenadas.get(0).getY()));
			} else if (objetoGeometrico instanceof Reta) {
				this.painelFormas.setSelectedIndex(TAB_RETA);
				this.coordenadaInicialRetaXTextField.setText(String.valueOf(coordenadas.get(0).getX()));
				this.coordenadaInicialRetaYTextField.setText(String.valueOf(coordenadas.get(0).getY()));
				this.coordenadaFinalRetaXTextField.setText(String.valueOf(coordenadas.get(1).getX()));
				this.coordenadaFinalRetaYTextField.setText(String.valueOf(coordenadas.get(1).getY()));
			} else if (objetoGeometrico instanceof Poligono) {
				this.painelFormas.setSelectedIndex(TAB_POLIGONO);
				int contador = 1;
				this.coordenadasDoPoligono = coordenadas;
				for (Coordenada coordenada : coordenadas) {
					String descricao = "Ponto ".concat(String.valueOf(contador++).concat(
							" X:" + coordenada.getX() + " Y:" + coordenada.getY()));
					this.listaDeCoordenadasDoPoligono.add(descricao);
				}
			} else if (objetoGeometrico instanceof Curva) {
				this.painelFormas.setSelectedIndex(TAB_CURVA);
				this.coordenadasDeControleDaCurva.add(0, coordenadas.get(0));
				this.coordenadasDeControleDaCurva.add(1, coordenadas.get(3));
				this.coordenadasNormaisDaCurva.add(0, coordenadas.get(1));
				this.coordenadasNormaisDaCurva.add(1, coordenadas.get(2));
				for (Coordenada coordenadaDeControle : coordenadasDeControleDaCurva) {
					String descricao = "Ponto ";
					descricao = descricao.concat(
							String.valueOf(coordenadasDeControleDaCurva.size()).concat(
									" X:" + coordenadaDeControle.getX() + " Y:" + coordenadaDeControle.getY())).concat(
							" [CONTROLE]");
					this.listaDeCoordenadasDaCurva.add(descricao);
				}

				for (Coordenada coordenadaNormal : coordenadasNormaisDaCurva) {
					String descricao = "Ponto ";
					descricao = descricao.concat(String.valueOf(coordenadasNormaisDaCurva.size()).concat(
							" X:" + coordenadaNormal.getX() + " Y:" + coordenadaNormal.getY()));
					this.listaDeCoordenadasDaCurva.add(descricao);
				}
				revisarBotoesDeAdiciaoDeCurva();
			} else if (objetoGeometrico instanceof CurvaBSpline) {
				this.painelFormas.setSelectedIndex(TAB_BSPLINE);
				int contador = 1;
				this.coordenadasDaBSpline = coordenadas;
				for (Coordenada coordenada : coordenadas) {
					String descricao = "Ponto ".concat(String.valueOf(contador++).concat(
							" X:" + coordenada.getX() + " Y:" + coordenada.getY()));
					this.listaDeCoordenadasDaCurvaBSpline.add(descricao);
				}
			}
		}
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
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
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
		preencherPoligonoCheckBox = new javax.swing.JCheckBox();
		painelCurva = new javax.swing.JPanel();
		painelDeRolagemDoPoligono1 = new javax.swing.JScrollPane();
		listaDeCoordenadasDaCurva = new java.awt.List();
		botaoAdicionarPontoCurva = new javax.swing.JButton();
		botaoRemoverPontoCurva = new javax.swing.JButton();
		painelCurvaBSpline = new javax.swing.JPanel();
		painelDeRolagemDaCurvaBSpline = new javax.swing.JScrollPane();
		listaDeCoordenadasDaCurvaBSpline = new java.awt.List();
		botaoAdicionarPontoBSpline = new javax.swing.JButton();
		botaoRemoverPontoBSpline = new javax.swing.JButton();
		nomeTextField = new javax.swing.JTextField();
		corLabel = new javax.swing.JLabel();
		botaoAlterarCor = new javax.swing.JButton();

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
		okButton.setToolTipText("Confirmar adição de objeto");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});

		cancelButton.setText("Cancel");
		cancelButton.setToolTipText("Cancelar adição de objeto");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		nomeLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		nomeLabel.setText("Nome:");

		painelCoordenadasPonto.setBorder(javax.swing.BorderFactory.createTitledBorder("Coordenadas"));

		coordenadaPontoXLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		coordenadaPontoXLabel.setText("X:");

		coordenadaPontoYLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		coordenadaPontoYLabel.setText("Y:");

		javax.swing.GroupLayout painelCoordenadasPontoLayout = new javax.swing.GroupLayout(painelCoordenadasPonto);
		painelCoordenadasPonto.setLayout(painelCoordenadasPontoLayout);
		painelCoordenadasPontoLayout.setHorizontalGroup(painelCoordenadasPontoLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelCoordenadasPontoLayout
						.createSequentialGroup()
						.addGap(56, 56, 56)
						.addComponent(coordenadaPontoXLabel)
						.addGap(18, 18, 18)
						.addComponent(coordenadaPontoXTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(45, 45, 45)
						.addComponent(coordenadaPontoYLabel)
						.addGap(18, 18, 18)
						.addComponent(coordenadaPontoYTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
								javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(81, Short.MAX_VALUE)));
		painelCoordenadasPontoLayout.setVerticalGroup(painelCoordenadasPontoLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelCoordenadasPontoLayout
						.createSequentialGroup()
						.addGap(57, 57, 57)
						.addGroup(
								painelCoordenadasPontoLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(coordenadaPontoXLabel)
										.addComponent(coordenadaPontoXTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(coordenadaPontoYLabel)
										.addComponent(coordenadaPontoYTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(59, Short.MAX_VALUE)));

		javax.swing.GroupLayout painelPontoLayout = new javax.swing.GroupLayout(painelPonto);
		painelPonto.setLayout(painelPontoLayout);
		painelPontoLayout.setHorizontalGroup(painelPontoLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelPontoLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(painelCoordenadasPonto, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));
		painelPontoLayout.setVerticalGroup(painelPontoLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelPontoLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(painelCoordenadasPonto, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));

		painelFormas.addTab("Ponto", painelPonto);

		painelCoordenadasFinalReta
				.setBorder(javax.swing.BorderFactory.createTitledBorder("Coordenadas do Ponto Final"));

		coordenadaFInalRetaXLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		coordenadaFInalRetaXLabel.setText("X:");

		coordenadaFinalRetaYLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		coordenadaFinalRetaYLabel.setText("Y:");

		javax.swing.GroupLayout painelCoordenadasFinalRetaLayout = new javax.swing.GroupLayout(
				painelCoordenadasFinalReta);
		painelCoordenadasFinalReta.setLayout(painelCoordenadasFinalRetaLayout);
		painelCoordenadasFinalRetaLayout.setHorizontalGroup(painelCoordenadasFinalRetaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelCoordenadasFinalRetaLayout
						.createSequentialGroup()
						.addGap(25, 25, 25)
						.addComponent(coordenadaFInalRetaXLabel)
						.addGap(33, 33, 33)
						.addComponent(coordenadaFinalRetaXTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(41, 41, 41)
						.addComponent(coordenadaFinalRetaYLabel)
						.addGap(18, 18, 18)
						.addComponent(coordenadaFinalRetaYTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
								javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(101, Short.MAX_VALUE)));
		painelCoordenadasFinalRetaLayout.setVerticalGroup(painelCoordenadasFinalRetaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelCoordenadasFinalRetaLayout
						.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(
								painelCoordenadasFinalRetaLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(coordenadaFInalRetaXLabel)
										.addComponent(coordenadaFinalRetaXTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(coordenadaFinalRetaYLabel)
										.addComponent(coordenadaFinalRetaYTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(45, 45, 45)));

		painelCoordenadasInicialReta.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Coordenadas do Ponto Inicial"));

		coordenadaInicialRetaXLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		coordenadaInicialRetaXLabel.setText("X:");

		coordenadaInicialRetaYLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		coordenadaInicialRetaYLabel.setText("Y:");

		javax.swing.GroupLayout painelCoordenadasInicialRetaLayout = new javax.swing.GroupLayout(
				painelCoordenadasInicialReta);
		painelCoordenadasInicialReta.setLayout(painelCoordenadasInicialRetaLayout);
		painelCoordenadasInicialRetaLayout.setHorizontalGroup(painelCoordenadasInicialRetaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelCoordenadasInicialRetaLayout
						.createSequentialGroup()
						.addGap(25, 25, 25)
						.addComponent(coordenadaInicialRetaXLabel)
						.addGap(33, 33, 33)
						.addComponent(coordenadaInicialRetaXTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(38, 38, 38)
						.addComponent(coordenadaInicialRetaYLabel)
						.addGap(18, 18, 18)
						.addComponent(coordenadaInicialRetaYTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		painelCoordenadasInicialRetaLayout.setVerticalGroup(painelCoordenadasInicialRetaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelCoordenadasInicialRetaLayout
						.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(
								painelCoordenadasInicialRetaLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(coordenadaInicialRetaXLabel)
										.addComponent(coordenadaInicialRetaXTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(coordenadaInicialRetaYLabel)
										.addComponent(coordenadaInicialRetaYTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(45, 45, 45)));

		javax.swing.GroupLayout painelRetaLayout = new javax.swing.GroupLayout(painelReta);
		painelReta.setLayout(painelRetaLayout);
		painelRetaLayout.setHorizontalGroup(painelRetaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelRetaLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								painelRetaLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(painelCoordenadasInicialReta,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(painelCoordenadasFinalReta,
												javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		painelRetaLayout.setVerticalGroup(painelRetaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				painelRetaLayout
						.createSequentialGroup()
						.addGap(0, 14, Short.MAX_VALUE)
						.addComponent(painelCoordenadasInicialReta, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(painelCoordenadasFinalReta, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
								javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));

		painelFormas.addTab("Reta", painelReta);

		painelDeRolagemDoPoligono.setBorder(javax.swing.BorderFactory.createTitledBorder("Pontos"));
		painelDeRolagemDoPoligono.setViewportView(listaDeCoordenadasDoPoligono);

		botaoAdicionarPonto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagens/add.png"))); // NOI18N
		botaoAdicionarPonto.setToolTipText("Clique para adicionar ponto.");
		botaoAdicionarPonto.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botaoAdicionarPontoActionPerformed(evt);
			}
		});

		botaoRemoverPonto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagens/remove.png"))); // NOI18N
		botaoRemoverPonto.setToolTipText("Clique para remover o ponto selecionado");
		botaoRemoverPonto.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botaoRemoverPontoActionPerformed(evt);
			}
		});

		preencherPoligonoCheckBox.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		preencherPoligonoCheckBox.setSelected(true);
		preencherPoligonoCheckBox.setText("Preencher");
		preencherPoligonoCheckBox.setToolTipText("Marque para preencher espaço interno do polígono");

		javax.swing.GroupLayout painelPoligonoLayout = new javax.swing.GroupLayout(painelPoligono);
		painelPoligono.setLayout(painelPoligonoLayout);
		painelPoligonoLayout.setHorizontalGroup(painelPoligonoLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelPoligonoLayout
						.createSequentialGroup()
						.addContainerGap(77, Short.MAX_VALUE)
						.addGroup(
								painelPoligonoLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(
												painelPoligonoLayout
														.createSequentialGroup()
														.addComponent(preencherPoligonoCheckBox)
														.addGap(18, 18, 18)
														.addComponent(botaoAdicionarPonto)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(botaoRemoverPonto).addGap(33, 33, 33))
										.addComponent(painelDeRolagemDoPoligono,
												javax.swing.GroupLayout.PREFERRED_SIZE, 244,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(91, Short.MAX_VALUE)));
		painelPoligonoLayout.setVerticalGroup(painelPoligonoLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelPoligonoLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(painelDeRolagemDoPoligono, javax.swing.GroupLayout.PREFERRED_SIZE, 133,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								painelPoligonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(botaoAdicionarPonto).addComponent(botaoRemoverPonto)
										.addComponent(preencherPoligonoCheckBox))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		painelFormas.addTab("Polígono", painelPoligono);

		painelDeRolagemDoPoligono1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pontos"));
		painelDeRolagemDoPoligono1.setViewportView(listaDeCoordenadasDaCurva);

		botaoAdicionarPontoCurva
				.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagens/add.png"))); // NOI18N
		botaoAdicionarPontoCurva.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botaoAdicionarPontoCurvaActionPerformed(evt);
			}
		});

		botaoRemoverPontoCurva.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/resources/imagens/remove.png"))); // NOI18N
		botaoRemoverPontoCurva.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botaoRemoverPontoCurvaActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout painelCurvaLayout = new javax.swing.GroupLayout(painelCurva);
		painelCurva.setLayout(painelCurvaLayout);
		painelCurvaLayout.setHorizontalGroup(painelCurvaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				painelCurvaLayout
						.createSequentialGroup()
						.addContainerGap(90, Short.MAX_VALUE)
						.addGroup(
								painelCurvaLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(painelDeRolagemDoPoligono1,
												javax.swing.GroupLayout.PREFERRED_SIZE, 244,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(
												painelCurvaLayout.createSequentialGroup()
														.addComponent(botaoAdicionarPontoCurva).addGap(18, 18, 18)
														.addComponent(botaoRemoverPontoCurva))).addGap(78, 78, 78)));
		painelCurvaLayout.setVerticalGroup(painelCurvaLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelCurvaLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(painelDeRolagemDoPoligono1, javax.swing.GroupLayout.PREFERRED_SIZE, 133,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								painelCurvaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(botaoAdicionarPontoCurva).addComponent(botaoRemoverPontoCurva))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		painelFormas.addTab("Curva", painelCurva);

		painelDeRolagemDaCurvaBSpline.setBorder(javax.swing.BorderFactory.createTitledBorder("Pontos"));
		painelDeRolagemDaCurvaBSpline.setViewportView(listaDeCoordenadasDaCurvaBSpline);

		botaoAdicionarPontoBSpline.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/resources/imagens/add.png"))); // NOI18N
		botaoAdicionarPontoBSpline.setToolTipText("Clique para adicionar ponto.");
		botaoAdicionarPontoBSpline.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botaoAdicionarPontoBSplineActionPerformed(evt);
			}
		});

		botaoRemoverPontoBSpline.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/resources/imagens/remove.png"))); // NOI18N
		botaoRemoverPontoBSpline.setToolTipText("Clique para remover o ponto selecionado");
		botaoRemoverPontoBSpline.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botaoRemoverPontoBSplineActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout painelCurvaBSplineLayout = new javax.swing.GroupLayout(painelCurvaBSpline);
		painelCurvaBSpline.setLayout(painelCurvaBSplineLayout);
		painelCurvaBSplineLayout.setHorizontalGroup(painelCurvaBSplineLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelCurvaBSplineLayout
						.createSequentialGroup()
						.addContainerGap(77, Short.MAX_VALUE)
						.addGroup(
								painelCurvaBSplineLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(
												painelCurvaBSplineLayout
														.createSequentialGroup()
														.addComponent(botaoAdicionarPontoBSpline)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(botaoRemoverPontoBSpline).addGap(33, 33, 33))
										.addComponent(painelDeRolagemDaCurvaBSpline,
												javax.swing.GroupLayout.PREFERRED_SIZE, 244,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(91, Short.MAX_VALUE)));
		painelCurvaBSplineLayout.setVerticalGroup(painelCurvaBSplineLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				painelCurvaBSplineLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(painelDeRolagemDaCurvaBSpline, javax.swing.GroupLayout.PREFERRED_SIZE, 133,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								painelCurvaBSplineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(botaoAdicionarPontoBSpline)
										.addComponent(botaoRemoverPontoBSpline))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		painelFormas.addTab("BSpline", painelCurvaBSpline);

		corLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
		corLabel.setText("Cor:");

		botaoAlterarCor.setBackground(new java.awt.Color(1, 1, 1));
		botaoAlterarCor.setForeground(new java.awt.Color(1, 1, 1));
		botaoAlterarCor.setToolTipText("Clique para alterar a cor.");
		botaoAlterarCor.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botaoAlterarCorActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(painelFormas)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGap(0, 0, Short.MAX_VALUE)
																.addComponent(okButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 67,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(cancelButton))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(44, 44, 44)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(corLabel)
																				.addComponent(nomeLabel))
																.addGap(18, 18, 18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						nomeTextField,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						186, Short.MAX_VALUE)
																				.addComponent(
																						botaoAlterarCor,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))
																.addGap(0, 0, Short.MAX_VALUE))).addContainerGap()));

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] { cancelButton, okButton });

		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(nomeLabel)
										.addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(botaoAlterarCor, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
												javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(corLabel))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
						.addComponent(painelFormas, javax.swing.GroupLayout.PREFERRED_SIZE, 229,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(cancelButton).addComponent(okButton)).addContainerGap()));

		getRootPane().setDefaultButton(okButton);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void botaoAdicionarPontoBSplineActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoAdicionarPontoBSplineActionPerformed
		AdicionarPontoDialog adicionarPontoDialog = new AdicionarPontoDialog(null, true,
				this.listaDeCoordenadasDaCurvaBSpline, this.coordenadasDaBSpline);
		adicionarPontoDialog.setLocationRelativeTo(this);
		adicionarPontoDialog.setVisible(true);
	}// GEN-LAST:event_botaoAdicionarPontoBSplineActionPerformed

	private void botaoRemoverPontoBSplineActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoRemoverPontoBSplineActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_botaoRemoverPontoBSplineActionPerformed

	private void botaoAdicionarPontoCurvaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoAdicionarPontoCurvaActionPerformed
		AdicionarPontoCurvaDialog adicionarPontoDialog = new AdicionarPontoCurvaDialog(null, this,
				rootPaneCheckingEnabled, coordenadasDeControleDaCurva.size(), coordenadasNormaisDaCurva.size());
		adicionarPontoDialog.setLocationRelativeTo(this);
		adicionarPontoDialog.setVisible(true);
	}// GEN-LAST:event_botaoAdicionarPontoCurvaActionPerformed

	private void botaoRemoverPontoCurvaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoRemoverPontoCurvaActionPerformed
		// TODO: Implementar
		int indiceSelecionado = this.listaDeCoordenadasDaCurva.getSelectedIndex();
		if (indiceSelecionado != -1) {
			this.listaDeCoordenadasDaCurva.remove(indiceSelecionado);
		}
		revisarBotoesDeAdiciaoDeCurva();
	}// GEN-LAST:event_botaoRemoverPontoCurvaActionPerformed

	private void botaoAlterarCorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoAlterarCorActionPerformed
		JColorChooser selecionadorDeCor = new JColorChooser(this.botaoAlterarCor.getBackground());
		final AbstractColorChooserPanel[] paineis = selecionadorDeCor.getChooserPanels();
		for (final AbstractColorChooserPanel painel : paineis) {
			if (!painel.getDisplayName().equals("RGB")) {
				selecionadorDeCor.removeChooserPanel(painel);
			}
		}
		removerAlfa(selecionadorDeCor);
		ColorPicker okListener = new ColorPicker(selecionadorDeCor);
		JDialog alterarCorDialog = JColorChooser.createDialog(this, "Selecionador de Cor", true, selecionadorDeCor,
				okListener, null);
		alterarCorDialog.setLocationRelativeTo(this);
		alterarCorDialog.setVisible(true);
		corSelecionada = okListener.getColor();
		if (corSelecionada != null) {
			this.botaoAlterarCor.setBackground(corSelecionada);
		}
	}// GEN-LAST:event_botaoAlterarCorActionPerformed

	public void removerAlfa(JColorChooser selecionadorDeCor) {
		Field f = null;
		AbstractColorChooserPanel[] paineis = selecionadorDeCor.getChooserPanels();
		AbstractColorChooserPanel painelDeCor = paineis[0];
		try {
			f = painelDeCor.getClass().getDeclaredField("panel");
			f.setAccessible(true);

			Object colorPanel = null;
			colorPanel = f.get(painelDeCor);

			Field f2 = null;
			f2 = colorPanel.getClass().getDeclaredField("spinners");
			f2.setAccessible(true);
			Object rows = null;
			rows = f2.get(colorPanel);

			final Object transpSlispinner = Array.get(rows, 3);
			Field f3 = null;
			f3 = transpSlispinner.getClass().getDeclaredField("slider");
			f3.setAccessible(true);
			JSlider slider = null;
			slider = (JSlider) f3.get(transpSlispinner);
			slider.setVisible(false);
			Field f4 = null;
			f4 = transpSlispinner.getClass().getDeclaredField("spinner");
			f4.setAccessible(true);
			JSpinner spinner = null;
			spinner = (JSpinner) f4.get(transpSlispinner);
			spinner.setVisible(false);
			Field f5 = null;
			f5 = transpSlispinner.getClass().getDeclaredField("label");
			f5.setAccessible(true);
			JLabel label = null;
			label = (JLabel) f5.get(transpSlispinner);
			label.setVisible(false);

			Field f6 = null;
			f6 = transpSlispinner.getClass().getDeclaredField("value");
			f6.setAccessible(true);
			float value = 0;
			value = (float) f6.get(transpSlispinner);
		} catch (Exception e) {
			System.out.println("Erro ao remover alpha do Painel de Seleção de Cores!");
		}
	}

	class ColorPicker implements ActionListener {

		JColorChooser selecionadorDeCor;
		Color color;

		public ColorPicker(JColorChooser selecionadorDeCor) {
			this.selecionadorDeCor = selecionadorDeCor;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			color = selecionadorDeCor.getColor();
		}

		public Color getColor() {
			return color;
		}

	}

	private void botaoRemoverPontoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoRemoverPontoActionPerformed
		int indiceSelecionado = this.listaDeCoordenadasDoPoligono.getSelectedIndex();
		if (indiceSelecionado != -1) {
			this.listaDeCoordenadasDoPoligono.remove(indiceSelecionado);
			this.coordenadasDoPoligono.remove(indiceSelecionado);
		}
	}// GEN-LAST:event_botaoRemoverPontoActionPerformed

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okButtonActionPerformed
		boolean semErros = true;
		this.corSelecionada = this.botaoAlterarCor.getBackground();
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
		case TAB_CURVA:
			semErros = criarCurva();
			break;
		case TAB_BSPLINE:
			semErros = criarCurvaBSpline();
			break;
		}
		if (semErros) {
			doClose(RET_OK);
		}
	}// GEN-LAST:event_okButtonActionPerformed

	private boolean criarCurvaBSpline() {
		boolean semErro = Validador.validarString(nomeTextField, nomeLabel);
		semErro &= coordenadasDaBSpline.size() >= 3;
		if (semErro) {
			CurvaBSpline bspline = new CurvaBSpline(nomeTextField.getText(), corSelecionada, coordenadasDaBSpline, 10);
			if (isEdicao) {
				painelDeDesenho.trocarObjetoDoIndice(bspline, indiceSelecionado);
			} else {
				painelDeDesenho.adicionarObjeto(bspline);
			}
		}
		return semErro;
	}

	private boolean criarCurva() {
		boolean semErro = Validador.validarString(nomeTextField, nomeLabel);
		semErro &= coordenadasDeControleDaCurva.size() == 2;
		semErro &= coordenadasNormaisDaCurva.size() == 2;
		if (semErro) {
			Curva curva = new Curva(nomeTextField.getText(), corSelecionada, coordenadasDeControleDaCurva,
					coordenadasNormaisDaCurva);
			if (isEdicao) {
				painelDeDesenho.trocarObjetoDoIndice(curva, indiceSelecionado);
			} else {
				painelDeDesenho.adicionarObjeto(curva);
			}
		}
		return semErro;
	}

	private boolean criarPonto() {
		boolean semErros = false;

		semErros = Validador.validarInteiro(this.coordenadaPontoXTextField, this.coordenadaPontoXLabel);
		semErros &= Validador.validarInteiro(this.coordenadaPontoYTextField, this.coordenadaPontoYLabel);
		semErros &= Validador.validarString(this.nomeTextField, this.nomeLabel);

		if (semErros) {
			String nome = this.nomeTextField.getText();
			Integer pontoX = Integer.parseInt(this.coordenadaPontoXTextField.getText());
			Integer pontoY = Integer.parseInt(this.coordenadaPontoYTextField.getText());
			Coordenada c = new Coordenada(pontoX, pontoY);
			Ponto ponto = new Ponto(nome, corSelecionada, c);
			if (isEdicao) {
				painelDeDesenho.trocarObjetoDoIndice(ponto, indiceSelecionado);
			} else {
				painelDeDesenho.adicionarObjeto(ponto);
			}
		}
		return semErros;
	}

	public boolean criarReta() {
		boolean semErros = false;
		semErros = Validador.validarInteiro(this.coordenadaInicialRetaXTextField, this.coordenadaInicialRetaXLabel);
		semErros &= Validador.validarInteiro(this.coordenadaInicialRetaYTextField, this.coordenadaInicialRetaYLabel);
		semErros &= Validador.validarInteiro(this.coordenadaFinalRetaXTextField, this.coordenadaFInalRetaXLabel);
		semErros &= Validador.validarInteiro(this.coordenadaFinalRetaYTextField, this.coordenadaFinalRetaYLabel);
		semErros &= Validador.validarString(this.nomeTextField, this.nomeLabel);

		if (semErros) {
			String nome = this.nomeTextField.getText();
			Integer xInicial = Integer.parseInt(this.coordenadaInicialRetaXTextField.getText());
			Integer yInicial = Integer.parseInt(this.coordenadaInicialRetaYTextField.getText());
			Integer xFinal = Integer.parseInt(this.coordenadaFinalRetaXTextField.getText());
			Integer yFinal = Integer.parseInt(this.coordenadaFinalRetaYTextField.getText());
			Coordenada coordenadaDeA = new Coordenada(xInicial, yInicial);
			Coordenada coordenadaDeB = new Coordenada(xFinal, yFinal);
			Reta reta = new Reta(nome, corSelecionada, coordenadaDeA, coordenadaDeB);

			if (isEdicao) {
				painelDeDesenho.trocarObjetoDoIndice(reta, indiceSelecionado);
			} else {
				painelDeDesenho.adicionarObjeto(reta);
			}
		}
		return semErros;

	}

	private boolean criarPoligono() {
		boolean semErro = Validador.validarString(nomeTextField, nomeLabel);
		semErro &= coordenadasDoPoligono.size() >= 3;
		if (semErro) {
			Poligono poligono = new Poligono(nomeTextField.getText(), corSelecionada, coordenadasDoPoligono,
					this.preencherPoligonoCheckBox.isSelected());
			if (isEdicao) {
				painelDeDesenho.trocarObjetoDoIndice(poligono, indiceSelecionado);
			} else {
				painelDeDesenho.adicionarObjeto(poligono);
			}
		}
		return semErro;
	}

	public void adicionarCoordenadaAoPoligono(Coordenada coordenada) {
		coordenadasDoPoligono.add(coordenada);
		String descricao = "Ponto ".concat(String.valueOf(coordenadasDoPoligono.size()).concat(
				" X:" + coordenada.getX() + " Y:" + coordenada.getY()));
		this.listaDeCoordenadasDoPoligono.add(descricao);
	}

	public void adicionarCoordenadaACurva(Coordenada coordenada, boolean isControle) {
		String descricao = "Ponto ";
		if (isControle) {
			coordenadasDeControleDaCurva.add(coordenada);
			descricao = descricao.concat(
					String.valueOf(coordenadasDeControleDaCurva.size()).concat(
							" X:" + coordenada.getX() + " Y:" + coordenada.getY())).concat(" [CONTROLE]");
		} else {
			coordenadasNormaisDaCurva.add(coordenada);
			descricao = descricao.concat(String.valueOf(coordenadasNormaisDaCurva.size()).concat(
					" X:" + coordenada.getX() + " Y:" + coordenada.getY()));
		}
		if (coordenadasDeControleDaCurva.size() + coordenadasNormaisDaCurva.size() == 4) {
			this.botaoAdicionarPonto.setEnabled(false);
		}
		this.listaDeCoordenadasDaCurva.add(descricao);
		revisarBotoesDeAdiciaoDeCurva();
	}

	private void revisarBotoesDeAdiciaoDeCurva() {
		if (coordenadasDeControleDaCurva.size() + coordenadasNormaisDaCurva.size() == 4) {
			this.botaoAdicionarPontoCurva.setEnabled(false);
			this.botaoAdicionarPontoCurva.setToolTipText("O número máximo de pontos já foi atingido.");
		} else {
			this.botaoAdicionarPontoCurva.setEnabled(true);
			this.botaoAdicionarPontoCurva.setToolTipText("Adicionar ponto.");
		}

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

	private void botaoAdicionarPontoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botaoAdicionarPontoActionPerformed
		AdicionarPontoDialog adicionarPontoDialog = new AdicionarPontoDialog(null, true,
				this.listaDeCoordenadasDoPoligono, this.coordenadasDoPoligono);
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
	private javax.swing.JButton botaoAdicionarPontoBSpline;
	private javax.swing.JButton botaoAdicionarPontoCurva;
	private javax.swing.JButton botaoAlterarCor;
	private javax.swing.JButton botaoRemoverPonto;
	private javax.swing.JButton botaoRemoverPontoBSpline;
	private javax.swing.JButton botaoRemoverPontoCurva;
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
	private javax.swing.JLabel corLabel;
	private javax.swing.JLabel jLabel1;
	private java.awt.List listaDeCoordenadasDaCurva;
	private java.awt.List listaDeCoordenadasDaCurvaBSpline;
	private java.awt.List listaDeCoordenadasDoPoligono;
	private javax.swing.JLabel nomeLabel;
	private javax.swing.JTextField nomeTextField;
	private javax.swing.JButton okButton;
	private javax.swing.JPanel painelCoordenadasFinalReta;
	private javax.swing.JPanel painelCoordenadasInicialReta;
	private javax.swing.JPanel painelCoordenadasPonto;
	private javax.swing.JPanel painelCurva;
	private javax.swing.JPanel painelCurvaBSpline;
	private javax.swing.JScrollPane painelDeRolagemDaCurvaBSpline;
	private javax.swing.JScrollPane painelDeRolagemDoPoligono;
	private javax.swing.JScrollPane painelDeRolagemDoPoligono1;
	private javax.swing.JTabbedPane painelFormas;
	private javax.swing.JPanel painelPoligono;
	private javax.swing.JPanel painelPonto;
	private javax.swing.JPanel painelReta;
	private javax.swing.JCheckBox preencherPoligonoCheckBox;
	// End of variables declaration//GEN-END:variables

	private int returnStatus = RET_CANCEL;

}
