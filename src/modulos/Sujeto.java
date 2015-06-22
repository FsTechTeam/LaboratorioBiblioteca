/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulos;

import java.util.List;

/**
 *
 * @author Geek
 */
public abstract class Sujeto {
    
    List<Observador> observadoresLista;
    public abstract void registrarObservador();
    public abstract void removerobservador();
    public abstract void notificar();
    
    
}
