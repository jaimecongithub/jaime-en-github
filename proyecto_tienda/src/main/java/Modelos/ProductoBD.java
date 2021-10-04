/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author escritorio
 */
public class ProductoBD extends ConexionBD {
    public ProductoBD(){
    
    }
    public ArrayList<Producto> listar(){
        
        String sentencia = "SELECT * FROM productos;";
        ArrayList<Producto> productos = new ArrayList<>();
        ResultSet rst = consultarBD(sentencia);
        
        try {
            while(rst.next()){
                Producto prod = new Producto();
            
                prod. setId(rst.getInt("id"));
                prod. setNombre(rst.getString("nombre"));
                prod. setCantidad(rst.getInt("cantidad"));
                prod.setPrecio(rst.getDouble("precio"));
                prod. setCategoria(rst.getString("categoria"));
            
                productos.add(prod);
            }
        }catch (SQLException ex){
        Logger.getLogger(ProductoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    //return productos;
        public boolean insertar(Producto prod){
        String sentencia = "INSERT INTO productos (nombre,cantidad, precio, categoria) VALUES ";
        sentencia += "('"+prod.getNombre()+ "', ";
        sentencia += prod.getCantidad()+ ", ";
        sentencia += prod.getPrecio()+ ", ";
        sentencia += "'"+prod.getCategoria()+ "'); ";
        
        System.out.println(sentencia);
        return insertarBD(sentencia);
    }
        public boolean actualizar(Producto prod, int id){
        String sentencia = "UPDATE productos SET ";
        sentencia += "nombre= '"+prod.getNombre()+ "', ";
        sentencia += "cantidad="+prod.getCantidad()+ ", ";
        sentencia += "precio="+prod.getPrecio()+ ", ";
        sentencia += "categoria='"+prod.getCategoria();
        sentencia += "' WHERE id="+id+";";
        
        System.out.println(sentencia);
        return insertarBD(sentencia);
    }
        
        public boolean borrar(int id){
            String sentencia="DELETE FROM productos WHERE id="+id+";";
            return borrarBD(sentencia);
    }
        
        public ArrayList<Producto> listarPorCategoria(String categoria){
        String sentencia = "SELECT * FROM productos WHERE categoria='"+categoria+"';";
        ArrayList<Producto> productos = new ArrayList<>();
        ResultSet rst = consultarBD(sentencia);
        try {
            while(rst.next()){
                Producto prod = new Producto();
            
                prod. setId(rst.getInt("id"));
                prod. setNombre(rst.getString("nombre"));
                prod. setCantidad(rst.getInt("cantidad"));
                prod.setPrecio(rst.getDouble("precio"));
                prod. setCategoria(rst.getString("categoria"));
            
                productos.add(prod);
            }
        }catch (SQLException ex){
        Logger.getLogger(ProductoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    
}
