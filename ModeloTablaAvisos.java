/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import javax.swing.table.*;
/**
 *
 * @author basesdatos
 */
public class ModeloTablaAvisos extends AbstractTableModel{
    private ArrayList<Aviso> avisos;

    public ModeloTablaAvisos(){
        this.avisos=new java.util.ArrayList<>();
    }

    public int getColumnCount (){
        return 3;
    }

    public int getRowCount(){
        return avisos.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Empresa"; break;
            case 1: nombre= "Umbral"; break;
            case 2: nombre="Operacion"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.Double.class; break;
            case 2: clase=java.lang.String.class; break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    public Object getValueAt(int row, int col){
        Object resultado=null;
        if(row<this.avisos.size()){
            switch (col){
                case 0: resultado= avisos.get(row).getEmpresa(); break;
                case 1: resultado= avisos.get(row).getValor(); break;
                case 2: resultado=avisos.get(row).getTipo();break;
            }
        }
        return resultado;
    }

    public void setFilas(ArrayList<Aviso> avisos){
        this.avisos.clear();
        for(Aviso avisox : avisos){
            this.avisos.add(avisox);
        }
        fireTableDataChanged();
    }

    public void borrarFila(int indice){
        this.avisos.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }
    
    public void nuevaFila(Aviso a){
        this.avisos.add(a);
        fireTableRowsInserted(this.avisos.size()-1, this.avisos.size()-1);
    }
}
