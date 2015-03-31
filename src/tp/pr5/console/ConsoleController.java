package tp.pr5.console;

import java.util.Scanner;

import tp.pr5.Interpreter;
import tp.pr5.RobotEngine;
import tp.pr5.controller.Controller;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

/**
 * El controlador emplea cuando la aplicaci�n est� configurada como una
 * aplicacion de consola. Contiene el bucle de simulaci�n que ejecuta las
 * instrucciones escritas por el usuario en la consola.
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public class ConsoleController extends Controller{

	/**
	 * Constructor del controlador. Recibe la clase principal modelo.
	 * @param game : juego que se est� jugando.
	 */
	public ConsoleController(RobotEngine game){
		super(game);
	}
	
	/**
	 * Incial la simulacin del robot en modo consola
	 */
	public void starRobotConsole (){
		robot.requestStart();
		Scanner sc = new Scanner(System.in);
		while (!robot.fin()/*robot.isOver()*/){
			Instruction inte;
			System.out.print("WALL·E> ");
			String line = sc.nextLine(); 
			try {
				inte = Interpreter.generateInstruction(line);
				robot.communicateRobot(inte);
			} catch (WrongInstructionFormatException e){ 
				robot.informaObservadoresMensajeError(e.getMessage());
			}
		}
	}
}
