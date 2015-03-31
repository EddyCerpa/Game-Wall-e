package tp.pr5.gui;

import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.controller.Controller;
import tp.pr5.instructions.DropInstruction;
import tp.pr5.instructions.MoveInstruction;
import tp.pr5.instructions.OperateInstruction;
import tp.pr5.instructions.PickInstruction;
import tp.pr5.instructions.QuitInstruction;
import tp.pr5.instructions.TurnInstruction;

/**
 * El controlador emplea cuando la aplicaci�n est� configurada como una
 * aplicaci�n de oscilacion. Es responsable de solicitar el arranque del motor
 * del robot y se vuelve a dirigir las acciones realizadas por el usuario en la
 * ventana del robot.
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public class GUIController extends Controller {
	/**
	 * Constructor que utiliza el modelo
	 * @param robot: La referencia al modelo
	 */
	public GUIController(RobotEngine robot){
		super(robot);
	}

	/**
	 * Inical la aplicacion en modo swing 
	 */
	public void starRobotSwing() {
		robot.requestStart();
	}
	
	/**
	 * Informa al modelo que tiene que realizar la accion de giro, para ello le enviamos 
	 * una nueva isntrucion
	 * @param rotacion : direcion del giro 
	 */
	public void turnAction (Rotation rotacion){
		TurnInstruction turnInstruction = new TurnInstruction(rotacion);
		robot.communicateRobot(turnInstruction);
	}

	/**
	 * Informa al modelo que tiene que realizar la accion move , para ello le enviamos 
	 * una nueva isntrucion
	 */
	public void moveAction() {
		MoveInstruction moveInstruction = new MoveInstruction();
		robot.communicateRobot(moveInstruction);
	}
	
	/**
	 * Informa al modelo que tiene que realizar la accion para de coger un elemento,  
	 * para ello le enviamos una nueva isntrucion
	 * @param id : nombre del elemnto (id)
	 */
	public void pickAction(String id){
		PickInstruction pickInstruction = new PickInstruction(id);
		robot.communicateRobot(pickInstruction);
	}
	
	/**
	 * Informa al modelo que tiene que realizar la accion para de soltar un elemento,  
	 * para ello le enviamos una nueva isntrucion 
	 * @param id : nombre del elemnto (id)
	 */
	public void dropAction(String id){
		DropInstruction dropInstruction = new DropInstruction(id);
		robot.communicateRobot(dropInstruction);
	}
	/**
	 * Informa al modelo que tiene que realizar la accion operar con un elemento,
	 * para ello le enviamos una nueva isntrucion 
	 * @param id : nombre del elemnto (id)
	 */
	public void operateAction(String id){
		OperateInstruction operateInstruction = new OperateInstruction(id);
		robot.communicateRobot(operateInstruction);
	}
	
	/**
	 * Informa al modelo que tiene que realizar la accion de de soltar 
	 * un elemento, para ello le enviamos  una nueva isntrucion 
	 * @param id : nombre del elemnto (id)
	 */
	public void quitAction(){
		QuitInstruction quitInstruction = new QuitInstruction();
		robot.communicateRobot(quitInstruction);
	}

	
	/**
	 * Otra forma de realizar eslta clase seria poner todas las instrucciones en un solo metodo
	 * y desde la calse InstructionPanel llamar unicamente a este metodo enviando la instruccion corrspondiente
	 * de cada boton
	 * @param ins
	 */
	/*public void enviarInstruction(Instruction ins) {
		robot.communicateRobot(ins);
	}*/
}
