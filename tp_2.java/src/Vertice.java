
import java.util.ArrayList;
import java.util.List;

public class Vertice<T> {

    private ArrayList<Arco> adyacente;
    private int id;
    private String name;


    public Vertice(String nombre) {
        this.name = nombre;
        this.adyacente = new ArrayList();
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addAdyacente(Vertice verticeId2, Integer etiqueta) {
        if(this.contiene(verticeId2) == false) {
            this.adyacente.add(new Arco(this, verticeId2,etiqueta));
        }
    }


	public boolean contiene(Vertice id2) {
		for(int i=0; i<this.adyacente.size(); i++){
			if(this.adyacente.get(i).getVerticeDestino().getName().equals(id2.getName())){
				return true;
			}
		}
		return false;
	}

    public Arco<T> getArco(Vertice id) {
        for(int i=0; i < this.adyacentesSize(); i++) {
            if(this.adyacente.get(i).getVerticeDestino() == id) {
                return this.adyacente.get(i);
            }
        }
        return null;
    }


    public ArrayList<Vertice> getIdAdyacentes() {
        ArrayList<Vertice> aux = new ArrayList<Vertice>();
        for(int i = 0; i < adyacentesSize(); i++) {
            aux.add(this.adyacente.get(i).getVerticeDestino());
        }
        return aux;
    }

    public int adyacentesSize(){
        return this.adyacente.size();
    }

    //Tengo el listado de todos los arcos
    public ArrayList<Arco> getListaArco(){
        return this.adyacente;
    }
    
	@Override
	public boolean equals(Object obj) {
		Vertice o =(Vertice) obj;
		if(this.name == o.getName()){
			return true;
		} else {
			return false;
		}
	}
}