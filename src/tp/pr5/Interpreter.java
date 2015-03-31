package tp.pr5;


import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.instructions.*;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

/**
 * El interprete se encarga de la conversión de la entrada del usuario en una instrucción 
 * para el robot. Las instrucciones validads son:
 * 
 *MOVER
 *{TURN LEFT | RIGHT}
 *RECOGIDA <ITEM>
 *SCAN [<ITEM>]
 *OPERAR <ITEM>
 *RADAR
 *DROP <ITEM>
 *AYUDA
 *SALIR
 * @author Eddy Cuizaguana Cerpa
 *
 */
public class Interpreter {

	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static ArrayList<Instruction> instrucciones = incializaArray ();
	
	/**
	 * Inicializamos nuestro arrayList
	 * @return
	 */
	public static ArrayList<Instruction> incializaArray (){
		 ArrayList<Instruction> aux = new  ArrayList<Instruction>();
		 aux.add(new DropInstruction());
		 aux.add(new HelpInstruction());
		 aux.add(new MoveInstruction());
		 aux.add(new OperateInstruction());
		 aux.add(new RadarInstruction());
		 aux.add(new ScanInstruction());
		 aux.add(new TurnInstruction());
		 aux.add(new PickInstruction());
		 aux.add(new DropInstruction());
		 aux.add(new QuitInstruction());
		return aux;
	}
	
	/**
	 * Genera una nueva instrucción de acuerdo con la entrada de usuario
	 * @param line : Una cadena con la entrada del usuario
	 * @return : La instrucción leida desde la línea dada. Si la instrucción no es 
	 * la correcta, entonces se produce una excepción.
	 * @throws WrongInstructionFormatException
	 */
	public static Instruction generateInstruction(String line)
            throws WrongInstructionFormatException {
		Iterator<Instruction> it = instrucciones.iterator();
		Instruction aux;  
		
		while (it.hasNext()){
			try{
			aux = it.next().parse(line);
			return aux;
			}
			
			catch(Exception e){ // se capura la exeption por q se comprueba instruccion por instruccion
			 }
		}
		throw new WrongInstructionFormatException("WALL·E says: I do not understand. Please repeat");
	}
	
	/**
	 * Se devuelve informacion acerca de todas las instrucciones que el robot entiende.
	 * @return : Una cadena con la informacion acerca de todas las instrucciones disponibles
	 */
	public static String interpreterHelp(){
		Instruction aux;  
		Iterator<Instruction> it = instrucciones.iterator();
		 String LINE_SEPARATOR = System.getProperty("line.separator");
		 String help="";
		 
		 while (it.hasNext()){
			 aux = it.next();
			 help = help + LINE_SEPARATOR +"   "+ aux.getHelp();
		 }
		 return help;
	 }
		
}
