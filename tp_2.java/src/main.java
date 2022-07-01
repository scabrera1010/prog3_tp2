import java.util.ArrayList;
import java.util.Iterator;

public class main {
    public static void main(String[] args) {
      
      CSVReader reader=new CSVReader();
      GrafoUser grafoUser=new GrafoUser<>();
      reader.read(grafoUser);

    /***********************/
    /*       Servicio 1    */
    /***********************/
    System.out.println("Los generos mas buscados son " + grafoUser.getMasBuscado("cine", 4));

    /***********************/
    /*       Servicio 2    */
    /***********************/
    System.out.println("La secuencia de generos a partir del genero cine es: "+grafoUser.secuenciaDeGeneros("cine"));


    /***********************/
    /*       Servicio 3    */
    /***********************/
    GrafoUser g= (GrafoUser) grafoUser.dfs("viajes");
    System.out.println("Nuevo grafo con generos a fines "+g.getV());
    
  }

}
