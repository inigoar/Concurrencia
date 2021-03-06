package CC_05_P1CSem.java;

import es.upm.babel.cclib.Semaphore;

import es.upm.babel.cclib.Producto;
import es.upm.babel.cclib.Almacen;

// TODO: importar la clase de los semáforos.

/**
 * Implementación de la clase Almacen que permite el almacenamiento
 * de producto y el uso simultáneo del almacen por varios threads.
 */
class Almacen1 implements Almacen {
   // Producto a almacenar: null representa que no hay producto
   private Producto almacenado = null;
   
   private static Semaphore consumo = new Semaphore (0);
   private static Semaphore productos = new Semaphore (1);

   // TODO: declaración e inicialización de los semáforos
   // necesarios

   public Almacen1() {
   }

   public void almacenar(Producto producto) {
      // TODO: protocolo de acceso a la sección crítica y código de
      // sincronización para poder almacenar.
	   
	  productos.await();
	  
      // Sección crítica
      almacenado = producto;

      // TODO: protocolo de salida de la sección crítica y código de
      // sincronización para poder extraer.
      
      consumo.signal();
   }

   public Producto extraer() {
      Producto result;

      // TODO: protocolo de acceso a la sección crítica y código de
      // sincronización para poder extraer.
      
      consumo.await();
      
      // Sección crítica
      result = almacenado;
      almacenado = null;

      // TODO: protocolo de salida de la sección crítica y código de
      // sincronización para poder almacenar.
      
      productos.signal();

      return result;
   }
}

