package modelo.transformacoes;

import java.util.ArrayList;
import java.util.List;

import modelo.Coordenada;
import modelo.Window;
import modelo.objetos.ObjetoGeometrico;

public class Normalizador {

	Window window;
	double fatorDeEscalaX;
	double fatorDeEscalaY;
	public static final int AJUSTE = 1;
	List<ObjetoGeometrico> objetos;

	public Normalizador(Window window) {
		this.window = window;

	}

	public void gerarDescricaoEmSCN(List<ObjetoGeometrico> objetos) throws CloneNotSupportedException {
		fatorDeEscalaX = (window.getMaximo().getX() - window.getMinimo().getX()) / 2;
		fatorDeEscalaY = (window.getMaximo().getY() - window.getMinimo().getY()) / 2;
		double xNormalizado, yNormalizado;
		List<Coordenada> coordenadasNormalizadas;

		for (ObjetoGeometrico objeto : objetos) {
			ObjetoGeometrico clone = objeto.clone();
			coordenadasNormalizadas = new ArrayList<Coordenada>();

			for (Coordenada coordenada : clone.getCoordenadas()) {

				xNormalizado = (coordenada.getX() - clone.getCentroGeometrico().getX()) * fatorDeEscalaX
						+ clone.getCentroGeometrico().getX() - AJUSTE;

				yNormalizado = (coordenada.getY() - clone.getCentroGeometrico().getY()) * fatorDeEscalaY
						+ clone.getCentroGeometrico().getY() - AJUSTE;

				coordenadasNormalizadas.add(new Coordenada(xNormalizado, yNormalizado));
			}
			clone.setCoordenadas(coordenadasNormalizadas);
		}

	}

}
