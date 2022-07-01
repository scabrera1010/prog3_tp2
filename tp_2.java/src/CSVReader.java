import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void read(GrafoUser grafoUser) {
        String csvFile = "tp_2.java/data/dataset1.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] items = line.split(cvsSplitBy);
                for(int j=0;j<items.length-1;j++){
                    grafoUser.agregarVertice(items[j],items[j+1]);                              
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}