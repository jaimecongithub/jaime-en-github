/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;
import Controladores.Controlador;
import Modelos.Producto;
import Modelos.ProductoBD;
import java.sql.SQLException;
import java.util.ArrayList;
import vistas.VistaPrincipal;

/**
 *
 * @author escritorio
 */
public class RunAplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductoBD prodBD = new ProductoBD();
        VistaPrincipal vista = new VistaPrincipal();
        vista.setVisible(true);
        Controlador productcontroller = new Controlador(vista, prodBD);
    }
    
}
