import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Interfaz extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JCheckBox direccionadoCheckBox;
    private JCheckBox conPonderacionCheckBox;
    private JButton crearGrafoButton;
    private JButton quemarButton;
    private JTextField textFieldVertice;
    private JButton insertarButton;
    private JComboBox comboBoxInicio;
    private JTextField textFieldPeso;
    private JButton insertarButton1;
    private JButton mostrarGrafoButton;
    private JComboBox comboBoxInicial;
    private JButton DFSButton;
    private JButton BFSButton;
    private JButton dijkstraButton;
    private JTextArea textArea1;
    private JComboBox comboBoxFin;
    Grafo grafo;

    public Interfaz(){
        setContentPane(panel1);
        crearGrafoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearGrafo();
            }
        });
        quemarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quemarDatos();
            }
        });
        mostrarGrafoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarGrafo();
            }
        });
        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarVertice();
            }
        });
        insertarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarArista();
            }
        });
        DFSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dfs();
            }
        });
        BFSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bsf();
            }
        });
        dijkstraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dijkstra();
            }
        });
    }



    public void quemarDatos(){
        grafo.addVertice("1");
        actualizarCombos("1");
        grafo.addVertice("2");
        actualizarCombos("2");
        grafo.addVertice("3");
        actualizarCombos("3");
        grafo.addVertice("4");
        actualizarCombos("4");
        grafo.addVertice("5");
        actualizarCombos("5");

        grafo.addEdge(grafo.getVertices().get(0).getData(), grafo.getVertices().get(1).getData(), 1);
        grafo.addEdge(grafo.getVertices().get(0).getData(), grafo.getVertices().get(2).getData(), 1);

        grafo.addEdge(grafo.getVertices().get(1).getData(), grafo.getVertices().get(2).getData(), 1);
        grafo.addEdge(grafo.getVertices().get(1).getData(), grafo.getVertices().get(3).getData(), 1);

        grafo.addEdge(grafo.getVertices().get(2).getData(), grafo.getVertices().get(4).getData(), 1);

        quemarButton.setEnabled(false);
        mostrarGrafo();
    }

    public void mostrarGrafo(){
        textArea1.setText(grafo.print());
    }

    public void actualizarCombos(String data){
        comboBoxInicio.addItem(data);
        comboBoxFin.addItem(data);
        comboBoxInicial.addItem(data);
    }

    public void insertarVertice(){

        if(!textFieldVertice.getText().isEmpty()){
            if(grafo.getVertexByValue(textFieldVertice.getText()) == null){
                grafo.addVertice(textFieldVertice.getText());
                actualizarCombos(textFieldVertice.getText());
                mostrarGrafo();
                JOptionPane.showMessageDialog(null, "El vertice fue insertado");
            }else{
                JOptionPane.showMessageDialog(null, "El vertice ya fue creado");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No puedes inggresar un vertice nulo");
        }

    }
    public void crearGrafo(){
        grafo = new Grafo(direccionadoCheckBox.isSelected(), conPonderacionCheckBox.isSelected());

        if(!conPonderacionCheckBox.isSelected()){
            textFieldPeso.setEnabled(false);
            textFieldPeso.setText("1");
        }else{
            textFieldPeso.setEnabled(true);
            textFieldPeso.setText("");
        }

        resetearApp();
        activarCampos();

        JOptionPane.showMessageDialog(null, "El grafo fue creado con exito");
    }
    public void insertarArista(){
        String inicio = comboBoxInicio.getSelectedItem().toString();
        String fin = comboBoxFin.getSelectedItem().toString();

        if(comboBoxInicio.getSelectedItem() != null && comboBoxFin.getSelectedItem() != null){
            if(!textFieldPeso.getText().isEmpty()){
                if(inicio.equals(fin)) {
                    JOptionPane.showMessageDialog(null, "No uede haber un bucle en el grafo");
                } else {
                    Vertice verticeInicio = grafo.getVertexByValue(inicio);
                    Vertice verticeFin = grafo.getVertexByValue(fin);

                    boolean edgeYaExiste = false;
                    for (Edge edge : verticeInicio.getAristas()) {
                        if (edge.getFinalVertice().equals(verticeFin)) {
                            edgeYaExiste = true;
                            break;
                        }
                    }

                    if (edgeYaExiste) {
                        JOptionPane.showMessageDialog(null, "La arista ya existe ");
                    } else {
                        grafo.addEdge(inicio, fin, Integer.parseInt(textFieldPeso.getText()));
                        mostrarGrafo();
                        JOptionPane.showMessageDialog(null, "El vertice fue insertado");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "No puedes ingresar una arista con peso nulo");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No puedes ingresar una arista con un vertice vacio");
        }
    }

    public void dfs(){
        if(comboBoxInicial.getSelectedItem() != null){
            ArrayList<Vertice> visitedVertices = new ArrayList<>();
            textArea1.setText(grafo.depthFirstTraversal(grafo.getVertexByValue(comboBoxInicial.getSelectedItem().toString()), visitedVertices));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que poner un vertice Inicial");
        }
    }

    public void bsf(){
        if(comboBoxInicial.getSelectedItem() != null){
            textArea1.setText(grafo.breadthFirstTraversal(grafo.getVertexByValue(comboBoxInicial.getSelectedItem().toString())));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que poner un vertice Inicial.");
        }
    }

    public void dijkstra(){
        if(comboBoxInicial.getSelectedItem() != null){
            textArea1.setText(grafo.dijkstra(grafo.getVertexByValue(comboBoxInicial.getSelectedItem().toString())));
        }else{
            JOptionPane.showMessageDialog(null, "Tienes que poner un vertice Inicial");
        }
    }

    public void resetearApp(){
        direccionadoCheckBox.setSelected(false);
        conPonderacionCheckBox.setSelected(false);
        textFieldVertice.setText("");
        comboBoxInicio.removeAllItems();
        comboBoxFin.removeAllItems();
        comboBoxInicial.removeAllItems();
        textArea1.setText("");
    }

    public void activarCampos(){
        quemarButton.setEnabled(true);
        textFieldVertice.setEnabled(true);
        insertarButton.setEnabled(true);
        comboBoxInicio.setEnabled(true);
        comboBoxFin.setEnabled(true);
        insertarButton1.setEnabled(true);
        mostrarGrafoButton.setEnabled(true);
        comboBoxInicial.setEnabled(true);
        DFSButton.setEnabled(true);
        BFSButton.setEnabled(true);
        dijkstraButton.setEnabled(true);
    }
}
