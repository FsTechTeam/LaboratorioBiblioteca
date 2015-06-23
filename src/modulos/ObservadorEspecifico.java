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
public class ObservadorEspecifico implements Observador{
    
    Mostrar muestra;
    
    List<Object> lista;
    @Override
    public void actualizar() {
        this.lista=muestra.obtenerDatos();
    }

    public Mostrar getMuestra() {
        return muestra;
    }

    public void setMuestra(Mostrar muestra) {
        this.muestra = muestra;
    }

    public List<Object> getLista() {
        return lista;
    }

    public void setLista(List<Object> lista) {
        this.lista = lista;
    }
    
    
    
}
