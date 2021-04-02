/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author grifiun
 */
public class Tabla {
    private List<ArrayList<String>> datosTablaListado;
    private String[] titulosTabla;
    private String[][] infoTabla;
    private String tituloGeneral = "";
    private int anchoPanel;
    
    /**
     * Creamos una instancia de tabla con los datos
     * @param datosTablaListado 
     */
    public Tabla (InformacionTabla infoTab){
        this.datosTablaListado = infoTab.getInformacionTabla();
        this.tituloGeneral = infoTab.getTituloGeneral();
        //Generamos datos
        agregarTitulosTabla();
        agregarInfoTabla();
        this.anchoPanel = 1600 - 30;
    }
    
    public JPanel generarTabla(){
        JTable tablaAux;
        //scroll.setLayout(null);
        //scroll.setVisible(true);
        //scroll.setBounds(0, 0, anchoPanel, 30*infoTabla.length+50);
        JPanel panelParaTabla = new JPanel();
        panelParaTabla.setLayout(null);
        panelParaTabla.setBounds(10, 5, anchoPanel, 30*infoTabla.length+50);
        ///Agregamoos los titulos
        Label lblTitulo = new Label(tituloGeneral);
        lblTitulo.setBounds(10, 5,anchoPanel,15);             
        
        TableModel model = new DefaultTableModel(infoTabla, titulosTabla)//creamos un modelo para agregar especificaciones
            {
              public boolean isCellEditable(int row, int column)
              {
                return false;//dehabilitamos la edicion
              }
            };
            
        tablaAux = new JTable(model);  
        tablaAux.setLayout(null);
        tablaAux.setBounds(10 , 5 + 20+60, anchoPanel, 30*infoTabla.length);
        
        tablaAux.setAutoCreateRowSorter(true); //Agregamos el ordenamiento por columnas
        /*
        //AGREGAMOS BUSQUEDAS
        buscador = new TableRowSorter<>(tablaAux.getModel());//creamos el buscador
        tablaAux.setRowSorter(buscador);//Agregamos el filtro buscador a la tabla            
        
        txtFiltro.getDocument().addDocumentListener(new DocumentListener(){//Le agregamos el getDocument al JTextField
            @Override
            public void insertUpdate(DocumentEvent e) {//Cuando se digitan letras al jtextField
                prepararFiltro();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {//Cuando se remueven letras al jtextField
                prepararFiltro();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Sin funcion de momento"); //Cambios extras
            }
        });*/
        
        panelParaTabla.setLayout(new BorderLayout());
        panelParaTabla.add(lblTitulo,BorderLayout.PAGE_END); 
        panelParaTabla.add(tablaAux.getTableHeader(), BorderLayout.BEFORE_FIRST_LINE);//Generamos los titulos de las columnas
        panelParaTabla.add(tablaAux, BorderLayout.CENTER);  
        
        //scroll.setViewportView(panelParaTabla);
        //return panelParaTabla;
        return panelParaTabla;
    }
    
    public void agregarTitulosTabla(){
        try{
            if(datosTablaListado != null & datosTablaListado.size() > 0){
                titulosTabla = datosTablaListado.get(0).toArray(new String[0]);
                datosTablaListado.remove(0);
            }else{
                System.out.println("Tabla nula");
            }
        }catch(Exception ex){
            System.out.println("Error al agregar titulos a la tabla: "+ex.getMessage());
        }
    }
    
    public void agregarInfoTabla(){
        
        try{  
            if(datosTablaListado != null & datosTablaListado.size() > 0){
                //////////////////////////////// CANTIDAD DE REGISTROS (X); CANTIDAD COMPOENTES EN Y
                String[][] infoAux = new String[datosTablaListado.size()][datosTablaListado.get(0).size()];
                //recorremos toda la tabla
                //Recorremos los registros de cada componente, empezando por los reg 1 de cada uno        

                for(int i = 0; i < datosTablaListado.size(); i++){
                    //por numero de registros                
                    for(int j = 0; j < datosTablaListado.get(i).size(); j++){
                        //por registro N de componente M
                        infoAux[i][j] = datosTablaListado.get(i).get(j);
                    }
                }
                //Agregamos el valor
                infoTabla = infoAux;
            }else{
                System.out.println("Tabla sin datos");
            }
        }catch(Exception ex){
            System.out.println("Error al agregar info a la tabla: "+ex.getMessage());
        }
       
    }
    
    public void agregarTituloGeneral(String tituloGeneral){
        this.tituloGeneral = tituloGeneral;
    };

    public String getTituloGeneral() {
        return tituloGeneral;
    }
    
    
    
}
