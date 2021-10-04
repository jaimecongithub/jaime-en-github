/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Producto;
import Modelos.ProductoBD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import vistas.VistaPrincipal;

/**
 *
 * @author escritorio
 */
public class Controlador implements ActionListener {
     private VistaPrincipal vista;
    private ProductoBD modeloProducto;
    
    public Controlador (VistaPrincipal vista, ProductoBD productoDB){
        this.vista = vista;
        this.modeloProducto = productoDB;
        
        this.vista.btnguardar.addActionListener(this);
        this.vista.btnactualizar.addActionListener(this);
        this.vista.btneliminar.addActionListener(this);
        this.vista.btnconsultar.addActionListener(this);
         
    }
    
    public Controlador(){
    
    }
    
    public void clearConsultar(){
        vista.txtidconsultar.setText("");
        vista.txtnombreconsultar.setText("");
        vista.txtcantidadconsultar.setText("");
        vista.txtprecioconsultar.setText("");
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
//verificar nombre tabla
        DefaultTableModel model = (DefaultTableModel) vista.tableProductos.getModel();
        
        
        
        if (e.getSource() == (vista.btnguardar)){
            System.out.println("Guardar");
            Producto p = new Producto();
            p.setNombre(vista.txtnombreregistrar.getText());
            p.setCantidad(Integer.valueOf(vista.txtcantidadregistrar.getText()));
            p.setPrecio(Double.valueOf(vista.txtprecioregistrar.getText()));
            p.setCategoria(String.valueOf(vista.combocategoriaregistrar.getSelectedItem()));
            
            modeloProducto.insertar(p);
            
            vista.txtnombreregistrar.setText("");
            vista.txtcantidadregistrar.setText("");
            vista.txtprecioregistrar.setText("");
            vista.combocategoriaregistrar.setSelectedIndex(0);
            
        }else if (e.getSource() == vista.btnconsultar){
            model.setRowCount(0);
            for (Producto producto : this.modeloProducto.listar()){
            System.out.println(producto);
            model.addRow(new Object[]{producto.getId(), producto.getNombre(), producto.getCantidad(), producto.getPrecio(), producto.getCategoria()});
            
            }
            vista.tableProductos.setModel(model);
            System.out.println("Consultar");
        }
        else if(e.getSource() == vista.btnactualizar){
            int numRow = vista.tableProductos.getSelectedRow();
            System.out.println(numRow);
            System.out.println(vista.tableProductos.getSelectedRow());
            
            if (numRow >= 0){
            int id = Integer.parseInt(String.valueOf(vista.tableProductos.getValueAt(numRow, 0)));
            Producto p = new Producto();
            
            p.setId(id);
            p.setNombre(vista.txtnombreconsultar.getText());
            p.setCantidad(Integer.valueOf(vista.txtcantidadconsultar.getText()));
            p.setPrecio(Double.valueOf(vista.txtprecioconsultar.getText()));
            p.setCategoria(String.valueOf(vista.combocategoriaconsultar.getSelectedItem()));
            
            modeloProducto.actualizar(p, id);
            model.setRowCount(0);
            for (Producto producto : this.modeloProducto.listar()){
            System.out.println(producto);
            model.addRow(new Object[]{producto.getId(), producto.getNombre(), producto.getCantidad(), producto.getPrecio(), producto.getCategoria()});
            }
            vista.tableProductos.setModel(model);
            clearConsultar();
            System.out.println("Actualizar");
            
            }
        }else if (e.getSource() == vista.btneliminar){
                int numRow = vista.tableProductos.getSelectedRow();
                
                if (numRow >= 0){
                int id = Integer.parseInt(String.valueOf(vista.tableProductos.getValueAt(numRow, 0)));
                modeloProducto.borrar(id);
                model.setRowCount(0);
                for (Producto producto: this.modeloProducto.listar()){
                    System.out.println(producto);
                    model.addRow(new Object[]{producto.getId(), producto.getNombre(), producto.getCantidad(), producto.getPrecio(), producto.getCategoria()});
                    
                }
                vista.tableProductos.setModel(model);
                    System.out.println("Eliminar");
                }
            }
    }
    
}
