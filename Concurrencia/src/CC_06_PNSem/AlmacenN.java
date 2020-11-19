package CC_06_PNSem;

import es.upm.babel.cclib.Producto;
import es.upm.babel.cclib.Almacen;

// TODO: importar la clase de los semáforos.
import es.upm.babel.cclib.Semaphore;

/**
 * Implementación de la clase Almacen que permite el almacenamiento
 * FIFO de hasta un determinado número de productos y el uso
 * simultáneo del almacén por varios threads.
 */
class AlmacenN implements Almacen {
   private int capacidad = 0;
   private Producto[] almacenado = null;
   private int nDatos = 0;
   private int aExtraer = 0;
   private int aInsertar = 0;

   // TODO: declaración de los semáforos necesarios
   
   Semaphore mutex;
   Semaphore consumo;
   Semaphore productos;

   public AlmacenN(int n) {
      capacidad = n;
      almacenado = new Producto[capacidad];
      nDatos = 0;
      aExtraer = 0;
      aInsertar = 0;

      // TODO: inicialización de los semáforos
      mutex = new Semaphore(1);
      consumo = new Semaphore(0);
      productos = new Semaphore(capacidad);
   }

   public void almacenar(Producto producto) {
      // TODO: protocolo de acceso a la sección crítica y código de
      // sincronización para poder almacenar.
	  productos.await();
      // Sección crítica
	  mutex.await();
      almacenado[aInsertar] = producto;
      nDatos++;
      aInsertar++;
      aInsertar %= capacidad;
      mutex.signal();
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
      mutex.await();
      result = almacenado[aExtraer];
      almacenado[aExtraer] = null;
      nDatos--;
      aExtraer++;
      aExtraer %= capacidad;
      mutex.signal();
      // TODO: protocolo de salida de la sección crítica y código de
      // sincronización para poder almacenar.
      productos.signal();
      return result;
   }
}
