import java.util.ArrayList;

public class Vertice {
    private String data;
    private ArrayList<Edge> aristas;

    public Vertice(String inputData){
        this.data = inputData;
        this.aristas = new ArrayList<Edge>();
    }

    public void addEdge(Vertice verticeFinal, int weight){
        this.aristas.add(new Edge(this, verticeFinal, weight));
    }

    public void removeEdge(Vertice verticeFinal){
        this.aristas.removeIf(edge -> edge.getFinalVertice().equals(verticeFinal));
    }

    public String getData(){
        return this.data;
    }

    public Vertice getVertex(String data){
        if(this.data.equals(data)){
            return this;
        }
        return null;
    }

    public ArrayList<Edge> getAristas(){
        return this.aristas;
    }

    public String print(boolean showWeight){
        String message = "";

        if(this.aristas.size() == 0){
            message += this.data + " -->";
            return message;
        }

        for(int i = 0; i < this.aristas.size(); i++){
            if(i == 0){
                message += this.aristas.get(i).getInitialVertice().data + " --> ";
            }

            message += this.aristas.get(i).getFinalVertice().data;

            if(showWeight){
                message += "' (" + this.aristas.get(i).getWeight() + ")";
            }

            if(i != this.aristas.size() -1){
                message += ", ";
            }
        }
        return message;
    }
}
