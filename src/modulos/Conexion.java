/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulos;



/**
 *
 * @author Geek
 */
public class Conexion extends Sujeto{
    
   
    private static Conexion instancia=null;
    public static Conexion getInstance() {//Aqui se define singleton
        if (instancia==null) {
            instancia= new Conexion();
        }
        return instancia;
    }

   
    

    @Override
    public void removerobservador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notificar() {
        for (Observador observador : observadoresLista) {
            observador.actualizar();
        }
    }

    @Override
    public void registrarObservador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void registrarObservador(Observador observador) {
        this.observadoresLista.add(observador);
    }
    
    
}
