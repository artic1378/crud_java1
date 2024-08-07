/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;

/**
 *
 * @author Artur
 * @param <T>
 */
public interface IGestorDatos<T> {
   void creacion (T objeto);
   T lectura (int id);
   void actualizar (T objeto, int  id);
   void eliminar (int id);
   List<T> lista();
}
