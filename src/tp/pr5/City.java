package tp.pr5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Esta clase represnta la ciudad donde el robot esta explorando Contiene
 * informacion sobre las calles y los lugares de la ciudad
 * 
 * @author Eddy Cuizaguana Cerpa
 */
public class City {

	private Collection<Street> cityMap = new ArrayList<Street>();

	/**
	 * Por constructor defecto. Se necesita para propositos de prueba
	 */
	public City() {}

	/**
	 * Crea una ciudad con una serie de calles
	 * 
	 * @param cityMap : el array de calles
	 */
	public City(Street[] cityMap) {
		this.cityMap = Arrays.asList(cityMap);
	}

	/**
	 * Busca la calle que se inicia desde el lugar determinado en la direccion
	 * dada.
	 * 
	 * @param currentPlace
	 *            : El lugar donde buscar la calle
	 * @param currentHeading
	 *            : La direccion para buscar la calle
	 * @return : la calle que empieza desde el lugar indicado en la direccion
	 *         dada. Se devuelve un valor nulo si no hay ninguna calle en esta
	 *         direccion desde el lugar dado
	 */
	public Street lookForStreet(Place currentPlace, Direction currentHeading) {
		boolean encontrado = false;
		Street calle = null;
		
		if (this.cityMap != null){
			Iterator<Street> it = cityMap.iterator();
			while (!encontrado && it.hasNext()){
				Street aux = it.next();
				if (aux.comeOutFrom(currentPlace, currentHeading)){
					encontrado = true;
					calle = aux;
				}
			}
		}
		return calle;
	}
	/**
	 * Agrega una calle de la ciudad
	 * 
	 * @param street
	 *            : La calle que se quiere agreagar
	 */
	public void addStreet(Street street) {
		this.cityMap.add(street);
	}

}
