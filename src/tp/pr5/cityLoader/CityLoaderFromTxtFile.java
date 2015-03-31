package tp.pr5.cityLoader;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import tp.pr5.City;
import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.Street;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.items.CodeCard;
import tp.pr5.items.Fuel;
import tp.pr5.items.Garbage;


/**
 * Cargador City desde un archivo txt El formato obligatorio debe ser:
 *BeginCity
 *BeginPlaces
 *place 0 Entrada Estamos_en_la_entrada._Comienza_la_aventura noSpaceShip
 *place 1 Callao In_this_square... spaceShip
 *...
 *EndPlaces
 *BeginStreets
 *street 0 place 0 south place 1 open
 *street 1 place 1 east place 2 open
 *street 2 place 2 north place 3 closed onetwothreefourfive
 *...
 *EndStreets
 *BeginItems
 *fuel 0 Petrol from_olds_heatings 10 3 place 0
 *fuel 1 Battery to_get_cracking -50 1 place 0
 *codecard 2 Card The_key_is_too_easy onetwothreefourfive place 1
 *garbage 3 Newspapers News_on_sport 30 place 2
 *...
 *EndItems
 *EndCity
 *
 * @author Eddy Cuizaguana Cerpa
 *
 */
public class CityLoaderFromTxtFile {
	
	private ArrayList <Street> calles;
	private ArrayList <Place> lugares;
	private boolean esNum = true; // se utiliza para el perce
	//---------------
	private String cad = ""; // cadena de string que represnta la descripcion de un item
	
	/**
	 * Creates the different array lists
	 * file - The name of the file
	 */
	public CityLoaderFromTxtFile(){
		calles = new ArrayList <Street> ();
		lugares = new ArrayList <Place> ();
	}
	
	/**
	 * Cargamos la ciudad de un txt
	 * @param file : El flujo de entrada, donde se almacena la ciudad
	 * @return : The city
	 * @throws java.io.IOException : Cuando hay algún error en el archivo de formato (WrongCityFormatException) o algunos errores en las operaciones de IO.
	 */
	public City loadCity(InputStream file) throws IOException {
		try{	
			 BufferedReader br = new BufferedReader(new InputStreamReader(file));
			 if (br.readLine().equalsIgnoreCase("BeginCity")){
			    cargarLugares(br);
			    if (this.esNum) {
			    	cargarCalles (br);
			    	if (this.esNum) {
			    		caragarItem (br);
			    		if (!this.esNum)
			    			System.err.print("Problemas con los numeros");
			    	}
			    	else System.err.print("Problemas con los numeros");
			    }
			    else System.err.print("Problemas con los numeros");
			 }
			 else throw new WrongCityFormatException("No se encuenta la linea que indica el inicio de la ciudad BeginCity"); 
			 if (br.readLine().equalsIgnoreCase("EndCity")) 
				 br.close();
			 else 
				 throw new WrongCityFormatException("No se encuenta la linea que indica el final de la ciudad EndCity"); 
			 Street [] street = calles.toArray(new Street[calles.size()]); // pasamos un arrayList a un array normal
			 return (new City (street));// retornamos City
		     }
		  
		 catch(Exception e){
				throw new WrongCityFormatException("Error: en el formato del archivo ;" + e.getMessage(),e);
		}
	}
	
	/**
	 * Cargamos Los Places en un array
	 * @param b : BufferedReader de entrada
	 * @throws IOException 
	 */
	public void cargarLugares (BufferedReader b)throws IOException{
		
		 String [] words;
		 String palabras;
		 int cont = 0;
		 
		 if (b.readLine().equalsIgnoreCase("BeginPlaces")){
			while(!(palabras = b.readLine()).equalsIgnoreCase("EndPlaces")){
				words = palabras.split(" ");			   
			   if (!esEntero(words[1]))
				   this.esNum = false;			   
			   else if (words.length >= 5){
				   int i = 0;
				   if (words.length == 5)
					    cad = words[3].replace("_", " "); 
				   else 
					   i = descripcionConEspacios (words);
				   int num = Integer.parseInt(words[1]);
			       if (words[0].equalsIgnoreCase("place") && cont == num){ 		 
					      Place p;
				          if (words[4 + i].equalsIgnoreCase("spaceShip")) 
				        	  p = new Place(words[2],true,cad);
				          else if (words[4 + i].equalsIgnoreCase("noSpaceShip")) 
				        	  p = new Place(words[2],false,cad);
				          else throw new WrongCityFormatException("Error , no se sabe el lugar de la nave espacial"); 
				          this.lugares.add(p);
			       }
			       else throw new WrongCityFormatException("Error la linea, puede ser un place o el numero no va de forma creciente");
			   }
			   else 
				   throw new WrongCityFormatException("la longitud de la cadena no corresponde con la de un place"); 
			   cont++;
			}
		 }
		 else throw new WrongCityFormatException("Error cabecera de no empeiza con BeginPlaces"); 
	 }
	
	/**
	 * Metodo q se encarga de coger la descripcion de los item y pasarlos como una sola cadena, la misma es utilizada si biene con espacios
	 * @param words : array de String
	 * @return : un entero i que nos inica desde donde debe seguir procesando la linea de string
	 * @throws WrongCityFormatException : se produce una exepcion si solo se encuentra la descripcion de un item y no los otros parametros para construir el objeto
	 */
	private int descripcionConEspacios(String[] words) throws WrongCityFormatException {
		int i = 3;
		cad = "";
		while (words[i].charAt(words[i].length() - 1)!= '"' && i < words.length){
			cad +=  words[i] + " ";
			 i++;
			 if (i == words.length) 
				   throw new WrongCityFormatException("Error en la cadena"); 
		}
		cad +=  words[i];
		i++;
		cad = cad.replace('"',' ');
		   i -= 4;
		return i;
	}


	/**
	 * Cargamos las calles
	 * @param b : BufferedReader de entrada
	 * @throws IOException : 
	 */
	public void cargarCalles(BufferedReader b)throws IOException{
		 
		 String [] words;
		 int cont=0;
		 String palabra;
		 
		 if (b.readLine().equalsIgnoreCase("BeginStreets")){
	        while(!(palabra = b.readLine()).equalsIgnoreCase("EndStreets")){
	        	words = palabra.split(" ");
			   if (words.length == 9 || words.length == 8){
				   if (!(esEntero(words[1]) && esEntero(words[3]) && esEntero(words[6])))
					   this.esNum = false;
				   else{
				   int num = Integer.parseInt(words[1]);
			       if (words[0].equalsIgnoreCase("street") && cont == num){
				           if ((words[7].equalsIgnoreCase("closed")||words[7].equalsIgnoreCase("open")) && 
				               (words[2].equalsIgnoreCase("place") && words[5].equalsIgnoreCase("place"))){
				   		         int source = Integer.parseInt(words[3]);
						         int target = Integer.parseInt(words[6]); 
						         //Direction dir = direccion (words[4]);	
						         Direction dir = Direction.direccion(words[4]);
						         if (dir == Direction.UNKNOWN)
						        	 throw new WrongCityFormatException("Error la direccion no se corresponde");
						         if (words[7].equalsIgnoreCase("open")) 
						        	 this.calles.add(new Street(this.lugares.get(source), dir, this.lugares.get(target)));
						         else if (words[7].equalsIgnoreCase("closed")) 
						        	this.calles.add(new Street(this.lugares.get(source), dir, this.lugares.get(target), false,words[8]));
						         else 
						        	 throw new WrongCityFormatException("Erro no se sabe el estado de la puerta");
						        }
					          else throw new WrongCityFormatException("Erro (place) y (close) or (open) no aparecen en la linea");				        			  
			        }
			        else throw new WrongCityFormatException("Eroor la linea no empieza con street, o los numeros no estan ordenados en forma creciente ");
			   }
			   }
			   else throw new WrongCityFormatException("Eroor en una linea, o no se termina de leer bien el EndStreets ");
			   cont++;
			}
		 }
		 else throw new WrongCityFormatException("Error cabecera de no empieza con BeginStreets"); 	 
	 }
	
	/**
	 * Leemos todos los item del txt
	 * @param b : BufferedReader de entrada
	 * @throws IOException : Cuando la cadena no es la esperada
	 */
	public void caragarItem (BufferedReader b)throws IOException{
		
	 	 String [] words;
		 int cont=0;
		 String aux; // en ella se guarada la lectura
		
		 if (b.readLine().equalsIgnoreCase("BeginItems")){
		    while(!(aux = b.readLine()).equalsIgnoreCase("EndItems")){
		    	words = aux.split(" ");
		    	if (!esEntero(words[1]))
					  this.esNum = false;
			    else if (cont == Integer.parseInt(words[1])){
			        if (words.length >= 8 && words[0].equalsIgnoreCase("fuel") )
			        	cargarFuel (words);		        
			        else if (words.length >= 7 && words[0].equalsIgnoreCase("codecard"))
			        	cargarCar (words);
			        else if ( words.length >= 7 && words[0].equalsIgnoreCase("garbage"))
			        	cargarGarbage (words);
			        else throw new WrongCityFormatException("No se reconoce la linea de un item, bien sea por su longitud o por el tipo de item"); 
			    }
			    else throw new WrongCityFormatException("Error los numeros de Items no estan hordenados de forma creciente");
			    cont++;
		    }
		 
		 }
		 else throw new WrongCityFormatException("Error cabecera no empieza con BeginItems"); 
	 }
	
	/**
	 * Metodo que se encarga de cargar un elemento recicable,i hace referncia a la cantidad de palabras que hay en la cadena que describe un item
	 * @param words : la linea leida
	 * @throws WrongCityFormatException si el formato de del .txt no es el adecuado saltara una expcion
	 */
	private void cargarGarbage(String[] words) throws WrongCityFormatException {
		cad = "";
		int indice,cantMatrial,i = 0;
		
		if (words.length > 7) 
			i = descripcionConEspacios (words);	
		else
			cad = words[3].replaceAll("_", " ");
		if (!(esEntero(words[6 + i]) && esEntero(words[4])))
				this.esNum = false;
		else if (words[5 + i].equalsIgnoreCase("place")){
			indice = Integer.parseInt(words[6 + i]);
			cantMatrial = Integer.parseInt(words[4 + i]); 
			this.lugares.get(indice).addItem(new Garbage(words[2], cad, cantMatrial)); 
		}
		else throw new WrongCityFormatException("Error no se encuentra place"); 
		
	}

/**
 * Metodo que se encarga de cargar las tarjetas,i hace referncia a la cantidad de palabras que hay en la cadena que describe un item
 * @param words : la linea leida
 * @throws WrongCityFormatException si el formato de del .txt no es el adecuado saltara una expcion
 */
	private void cargarCar(String[] words) throws WrongCityFormatException {
		int num,i = 0;
		if (words.length > 7) 
			i = descripcionConEspacios (words);
		else 
		 cad=words[3].replaceAll("_", " ");
   		if (!esEntero(words[6]))
   			this.esNum = false;
    	else if (words[5+i].equalsIgnoreCase("place")){
    		num = Integer.parseInt(words[6+i]);
		    this.lugares.get(num).addItem(new CodeCard(words[2], cad, words[4+i])); 
		 }  
		 else throw new WrongCityFormatException("Error no se encuentra el place de la targeta");	
	}


	/**
	 * Cargamos el los items de tipo fuel , i hace referncia a la cantidad de palabras que hay en la cadena que describe un item
	 * @param words : una cadena de string
	 * @throws WrongCityFormatException
	 */
	private void cargarFuel(String[] words) throws WrongCityFormatException {
		int indice,power,time;
		int i = 0;
		if (words.length > 8) 
			i = descripcionConEspacios (words);
		else 
		 cad = words[3].replaceAll("_", " ");
         if (!(esEntero(words[7 + i]) && esEntero(words[4 + i]) && esEntero(words[5 + i])))
				   this.esNum = false;
         else if (words[6 + i].equalsIgnoreCase("place") && words[0].equalsIgnoreCase("fuel") ){ 	    
		           indice = Integer.parseInt(words[7 + i]);
			       power = Integer.parseInt(words[4 + i]);
			       time =Integer.parseInt(words[5 + i]);
			       this.lugares.get(indice).addItem(new Fuel(words[2], cad, power, time)); 
	        }
         else throw new WrongCityFormatException("Error en el (place)or(fuel)");
	}

	/**
	 * Devuelve el lugar en el que el robot inicia la simulacion
	 * @return : Lugar de inicio.
	 */
	public Place getInitialPlace() {
		return this.lugares.get(0);
	}
	/**
	 * Metodo que se utiliza para evitar que el parse lance exeptiones
	 * Verificamos que el numero a parsear sea un entero	
	 * @param ent : entero en forma de string
	 * @return : true si es una cadena que se puede transformar a entero.
	 * fasle si es una cadena q no corresponde a ningun numero
	 */
	boolean esEntero (String ent){
			char [] words = ent.toCharArray();
			int  i = words.length - 1;
			boolean salir = true;
			while (i >= 0 &&  salir){
				if (words [i] < '0' && words[i] > '9' ) salir = false;
				i--;
			}
			return salir;
	}
}


