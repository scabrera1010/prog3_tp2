import java.util.ArrayList;
import java.util.Iterator;

public class main {
    public static void main(String[] args) {
      
      CSVReader reader=new CSVReader();
      GrafoUser grafoUser=new GrafoUser<>();
      reader.read(grafoUser);

    GrafoUser g= (GrafoUser) grafoUser.dfs("viajes");
    System.out.println("Nuevo grafo con generos a fine "+g.getV());

    }

}
