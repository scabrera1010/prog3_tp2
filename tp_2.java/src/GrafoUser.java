
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class GrafoUser<T> implements Grafo<T> {
	private ArrayList<Vertice> vertice;
	private HashMap map;
	private HashMap<String, String> colores;
	private boolean primeraVez;
	
	public GrafoUser(){
		this.vertice = new ArrayList<Vertice>();
		this.map = new HashMap<Vertice, String>();
	}


	public void agregarVertice(String vertice1, String vertice2) {
		Vertice v1 = new Vertice<>(vertice1);
		Vertice v2 = new Vertice<>(vertice2);

		if(this.contieneVertice(v1) == false){
			this.vertice.add(v1);
		}	
		if(this.contieneVertice(v2) == false){
			this.vertice.add(v2);
		}

		int pos1 = this.getPosVertice(v1);
		int pos2 = this.getPosVertice(v2);

		if(this.existeArco(this.vertice.get(pos1), this.vertice.get(pos2))){
			Arco a = this.obtenerArco(this.vertice.get(pos1), this.vertice.get(pos2));
			a.setEtiqueta(a.getEtiqueta()+1);
			//System.out.println("Arco modif " + a.getVerticeOrigen() + " - " + a.getVerticeDestino() + "--" + a.getEtiqueta());
		} else {
			this.agregarArco(this.vertice.get(pos1), this.vertice.get(pos2), 1);
			//System.out.println("Arco agrega " + v1 + " - " + v2+ "--" + 1);

		} 
	}

	
	public ArrayList<Vertice> getV(){
		return this.vertice;
	}
	//Controlla si existe el vertice con el id pasado por parametro
	public boolean contieneVertice(Vertice verticeName) {
		for(int i=0; i<this.vertice.size(); i++){
			if(this.vertice.get(i).getName().equals(verticeName.getName())){
				return true; //Tiene vertice
			}
		}
		return false; //No tiene vertice
	}
		
	//Agregar Arista
	@Override
	public void agregarArco(Vertice genero1, Vertice genero2, Integer etiqueta) {
		int pos = this.getPosVertice(genero1);
		if(pos >=0){
			if(this.contieneVertice(genero2) == true){
				this.vertice.get(pos).addAdyacente(genero2, etiqueta);
				genero1.addAdyacente(genero2,etiqueta);
			}
		}

	}

	
	private int getPosVertice(Vertice genero){
		for(int i = 0; i < vertice.size();i++){
			if(this.vertice.get(i).getName().equals(genero.getName())){
				return i;
			}
		}
		return -1;
	}


	@Override
	public Arco<T> obtenerArco(Vertice verticeId1, Vertice verticeId2) {
		int pos = this.getPosVertice(verticeId1);
		if(pos >= 0) {
			if(this.contieneVertice(verticeId2) == true){
				return this.vertice.get(pos).getArco(verticeId2);
			}
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return this.vertice.size();
	}

	


	/**Creado */
	private ArrayList getIdVertice(){
        ArrayList aux = new ArrayList<>();
        for(int i = 0; i < vertice.size(); i++){
            aux.add(vertice.get(i).getName());
        }
		return aux;
    }
    
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco>aux = new ArrayList<>();
		for(int i =0; i<this.vertice.size();i++){
			aux.addAll(this.vertice.get(i).getListaArco());
		}
		Iterator it = aux.iterator();
		while(it.hasNext()){
			it.next();
		}
		return it;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(Vertice verticeId) {
		int pos = this.getPosVertice(verticeId);
		if(pos > 0){
			Iterator it = this.vertice.get(pos).getListaArco().iterator();
			while(it.hasNext()){
				it.next();
			}
			return it;
		}
		return null;
	}

	//Servicio nro 1
	public ArrayList<String> getMasBuscado(String v,int cantidad){
		int cant = 0;
		ArrayList<String>aux=new ArrayList<>();
		int pos =this.getVertice(v);
		if(pos>=0){
			Iterator temp=OrdenarPorEtiqueta(this.vertice.get(pos).getListaArco()).iterator();
			while(temp.hasNext() && cantidad>0){
				//cant++;
			//System.out.println(cant);
				Arco a=(Arco) temp.next();
				aux.add(a.getVerticeDestino().getName());
				cantidad--;
			}
		}
		return aux;
	}

	private ArrayList<Arco> OrdenarPorEtiqueta(ArrayList listaArco) {
		ArrayList <Arco> t=listaArco;
		Collections.sort(t,new comparable());
		Collections.reverse(t);
		return t;
	}

	private int getVertice(String v) {
		for(int i=0;i<this.vertice.size();i++){
			if(this.vertice.get(i).getName().equals(v)){
				return i;
			}
		}
		return -1;
	}

	@Override
	public Iterator<String> obtenerVertices() {
		Iterator<String> it= this.getIdVertice().iterator();
		while(it.hasNext()){
			it.next();
		}

		return it;
	}

	@Override
	public Iterator<String> obtenerAdyacentes(String verticeId) {
		Integer pos = getVertice(verticeId);
		Iterator<String> it = this.obtenerVertices();
		if(pos > 0) {
			Iterator<String> it2 = this.getIdVertice().iterator();
			while(it2.hasNext()){
				it2.next();
			}
			return it2;
		}

		return null;
		
	}


	private boolean existeVertice(Vertice genero) {
		int pos = this.getPosVertice(genero);
		if(pos >= 0) {
			return this.vertice.get(pos).contiene(genero);
		}
		return false;
	}


	public boolean existeArco(Vertice verticeId1, Vertice verticeId2) {
		int pos = this.getPosVertice(verticeId1);
		int pos2 = this.getPosVertice(verticeId2);

		if(pos >= 0 && pos2 >= 0) {
			
				return this.vertice.get(pos).contiene(this.vertice.get(pos2));
			
		}
		return false;

	}


	//Servicio nro 2
	public ArrayList<String> secuenciaDeGeneros(String genero){
		ArrayList aux=new ArrayList();
		int pos =this.getVertice(genero);
		Iterator it=this.vertice.get(pos).getListaArco().iterator();
		while(it.hasNext()){
			Arco arco=(Arco) it.next();
			Vertice v=arco.getVerticeDestino();
            Vertice a=seleccion(this.vertice.get(this.getVertice(v.getName())).getListaArco());
			if(!aux.contains(a)){
				aux.add(a);
			}

		}
		return aux;
	}
	private Vertice seleccion(ArrayList array) {
		Integer max=0;
		Vertice verTemp = null;
		for(int i=0;i<array.size();i++){
			Arco v=(Arco)array.get(i);
			if(v.getEtiqueta()>max){
				max=v.getEtiqueta();
				verTemp=v.getVerticeDestino();
			}
		}
		return verTemp;
	}




	//Servicio nro 3
	public GrafoUser dfs(String genero){
		int pos =this.getVertice(genero);
		if(pos>=0){
			Vertice v=this.vertice.get(pos);
			Iterator it=this.vertice.iterator();
			while(it.hasNext()){
				Vertice a=(Vertice) it.next();
				int posicion=this.getVertice(a.getName());
				this.map.put(this.vertice.get(posicion),"blanco");
			}

			ArrayList<String> aux=new ArrayList();
			aux.add(genero);
			Iterator it2=v.getListaArco().iterator();
			while(it2.hasNext()){
				Arco arco=(Arco)it2.next();
				if(this.map.get(arco.getVerticeDestino()).equals("blanco")) {
					return dfs_visit(arco.getVerticeDestino().getName(),genero,aux);
				}
			}
		}
		return null;
	}

	private GrafoUser dfs_visit(String origen, String destino, ArrayList<String> aux) {
		int posOrigen=this.getVertice(origen);
		this.map.replace(this.vertice.get(posOrigen),"amarillo");
		if(origen.equals(destino)){
			aux.add(destino);
			return subGrafo(aux);
		}else{
			Iterator it=this.vertice.get(posOrigen).getListaArco().iterator();

			while(it.hasNext()){

				Arco a=(Arco)it.next();

				int posicion=this.getVertice(a.getVerticeDestino().getName());

				if(this.map.get(this.vertice.get(posicion)).equals("blanco")){

					aux.add(this.vertice.get(posOrigen).getName());
					return dfs_visit(a.getVerticeDestino().getName(),destino,aux);
				}
			}
		}
		this.map.replace(this.vertice.get(posOrigen),"negro");
		return null;
	}

	private GrafoUser subGrafo(ArrayList<String> lista) {
		GrafoUser g=new GrafoUser();
		for(int i=0;i<lista.size()-1;i++) {
			System.out.println(lista.get(i)+"-"+lista.get(i+1));
			g.agregarVertice(lista.get(i),lista.get(i+1));
		}
		return g;
	}
	
	

}
