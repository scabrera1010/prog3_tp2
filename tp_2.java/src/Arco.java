
/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice destino y una etiqueta.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
public class Arco<T> {

	private Vertice verticeOrigen;
	private Vertice verticeDestino;
	private Integer etiqueta;

	public Arco(Vertice verticeOrigen, Vertice verticeDestino, Integer etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}
	
	public Vertice getVerticeOrigen() {
		return verticeOrigen;
	}

	public void setEtiqueta(Integer e){
		this.etiqueta =+e;
	}
	
	public Vertice getVerticeDestino() {
		return verticeDestino;
	}

	public Integer getEtiqueta() {
		return etiqueta;
	}


	@Override
	public boolean equals(Object obj) {
		Arco o =(Arco) obj;
		if(this.verticeOrigen == o.verticeOrigen && this.verticeDestino == o.verticeDestino){
			return true;
		} else {
			return false;
		}
	}
	
	

}
