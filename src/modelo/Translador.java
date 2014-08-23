package modelo;

import java.util.ArrayList;
import java.util.List;

public class Translador extends Movimentador {

	public Translador(ObjetoGeometrico objeto, Coordenada vetor) {
		super(objeto, vetor);
	}

	@Override
	public void movimentar() {
		int dx = coordenadaDeReferencia.getX();
		int dy = coordenadaDeReferencia.getY();
		List<Coordenada> novasCoordenadas = new ArrayList<Coordenada>();

		for (Coordenada coordenada : objeto.getCoordenadas()) {
			novasCoordenadas.add(new Coordenada(coordenada.getX() + dx, 
												coordenada.getY() + dy ));

		}

		objeto.setCoordenadas(novasCoordenadas);

	}

}
