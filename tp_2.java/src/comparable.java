public class comparable implements Ordenamiento{

    @Override
    public int compare(Arco o,Arco t1) {
        return o.getEtiqueta()-t1.getEtiqueta();
    }
}

