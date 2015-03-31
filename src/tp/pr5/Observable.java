package tp.pr5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
//import java.util.Vector;

/**
 * Esta clase es una reimplementacion de la clase Obserbable de Java
 * acoplada a nuestro etilo, representa un objeto observable, o "datos" 
 * Un objeto observable puede tener uno o más observadores. 
 * Des esta manera podemos desde un modelo y un controlador manipular n vistas 
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 * @param <T>
 */
public class Observable<T> implements Iterable<T>{

	private Collection<T> obs = new ArrayList<T>();
	//private Vector<T> obs = new Vector<T>();
	
	/**
	 * Añadimos un observador "vista de tipo T" al vector de vistas
	 * @param o : objeto de tipo t
	 */
	public void addObserver(T o){
		if (o == null) System.err.print("objeto null");
		else if(!this.obs.contains(o)) obs.add(o);
		//else if(!this.obs.contains(o)) obs.addElement(o);
	}
	
	/**
	 * Motodo no utilizado, se puede implantar para futuros casos
	 * en el caso q se dsee elimnar un vista "obserbable"de la lista, por ejemplo
	 */
	public void removeObserver(T o){
		if(this.obs.contains(o)) obs.remove(o);
		//if(this.obs.contains(o)) obs.removeElement(o);
	}

	@Override
	public Iterator<T> iterator() {
		return obs.iterator();
	}
	
		
	
}
