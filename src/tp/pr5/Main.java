package tp.pr5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.console.ConsoleController;

import tp.pr5.gui.GUIController;
import tp.pr5.gui.MainWindow;



/**
 * Aplicación de punto de entrada. La aplicación admite un parámetro -m | - mapa
 * con el nombre del archivo de mapa para su uso y un parámetro -i | - Interfaz
 * con el tipo de interfaz (consola o swing) Si no se especifica ningún
 * argumento (o se da más de un archivo), se muestra un mensaje de error (en
 * System.err) y la aplicación termina con un código de error (-1). Si el
 * archivo de mapa no puede ser leído (o no existe), la aplicación termina con
 * un código de error diferente (-2). Si el arg interfaz no es correcta ( la
 * consola o media vuelta ) la aplicación imprime un mensaje y la aplicación
 * termina con un código de error (-3). Si no se incluye el argumento de
 * interfaz que se inicia la aplicaion en modo consola. De lo contrario, la
 * simulación se inicia y, finalmente, la aplicación terminará normalmente
 * (código de retorno 0).
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */

public class Main {
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	static CityLoaderFromTxtFile loader = new CityLoaderFromTxtFile();
	static City cityMap = new City();
	static boolean mapa = true;

	public static void main(String[] args) {

		Options options = LoadOption();
		// metodo que evita que el parse lance exepciones
		comprobarLinea(args);
		CommandLineParser parser = new PosixParser();
		try {
			CommandLine line = parser.parse(options, args);
			evaluaMapa(line);
			if (line.hasOption("i") || line.hasOption("interface")) 
				evaluaInterface(line);
			else if (line.hasOption("h") || line.hasOption("help") )
				evaluaHelp(options);	
			else {
				System.err.println("Interface not specified");
				System.exit(1);
			}
			// nunca llega aqui!, por que evitamos el lanzamiento de las exepciones mediante el metodo comprobarLinea			
		} catch (ParseException exp)  {
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
		}
	}
	
	/**
	 * Metodo que muestra la opcion de ayuda
	 * @param options : opcion introducida
	 */
	private static void evaluaHelp(Options options) {
		System.out.println("Execute this assignment with these parameters:");
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("tp.pr5.Main [-h] [-i <type>] [-m <mapfile>]", options);
		System.exit(0);	
	}

	/**
	 * Metodo que se encarga de la interfaz que el usuario quiere ver, swing,console,both
	 * @param line : la linea de comando
	 */
	private static void evaluaInterface(CommandLine line) {
		reconocerOptionValue(line);
		RobotEngine robot = new RobotEngine(cityMap,loader.getInitialPlace(), Direction.NORTH);
		//-i swing -m madrid.txt
		if (line.getOptionValue("interface").equalsIgnoreCase("swing")) {
			//creamos el controlador de la swin y le pasamos el engine 
			GUIController guiController = new GUIController(robot);
			//creamos la SWING y mandamos la refencia del controlador para que cada ventana sea la 
			//responsable de añadir su observador
			MainWindow ventaPrincipal = new MainWindow(guiController);
			// hacemos visible el Frame
			ventaPrincipal.arranca();
			// arrancamos el robot desde el controlador
			guiController.starRobotSwing();
		}
		//-i console -m madrid.txt
		else if (line.getOptionValue("interface").equalsIgnoreCase("console")) {
			//creamos el controlador de la consola y le pasamos el engine 
			ConsoleController consoleControlles = new ConsoleController(robot);
			// creamos la consola
			tp.pr5.console.Console console = new tp.pr5.console.Console();
			// La consola se comporta como vista observando al robot, navigationModule y Itemcontainer
			consoleControlles.addRobotEngineObserver(console);
			consoleControlles.addNavigationModuleObserver(console);
			consoleControlles.addInventarioObserver(console);
			consoleControlles.starRobotConsole();
		}
		//-i both -m madrid.txt
		else if (line.getOptionValue("interface").equalsIgnoreCase("both")) {
			// creamos los controladores de la consola y de la swing
			GUIController guiController = new GUIController(robot);
			ConsoleController consoleControlles2 = new ConsoleController(robot);
			
			// creamos la consola
			tp.pr5.console.Console console = new tp.pr5.console.Console();
			// La consola se comporta como vista observando al robot, navigationModule y Itemcontainer
			consoleControlles2.addRobotEngineObserver(console);
			consoleControlles2.addNavigationModuleObserver(console);
			consoleControlles2.addInventarioObserver(console);
			
			//creamos la SWING y mandamos la refencia del controlador para que cada ventana sea la 
			//responsable de añadir su observador
			MainWindow ventaPrincipal = new MainWindow(guiController);
			ventaPrincipal.arranca();
			//---- segunda ventana
				//MainWindow ventaPrincipal2 = new MainWindow(guiController);
				//ventaPrincipal2.arranca();
			//----- hasta aqui segunda ventana
			guiController.starRobotSwing();
			//-------
			
		}	
	}


	/**
	 * Reconocemos la opcion introducidad en caso de no ser reconocida, salimos mostrando un mensaje
	 * de error
	 * @param line : linea de con¡mando a ser reconocida
	 */
	private static void reconocerOptionValue(CommandLine line) {
		if (!line.getOptionValue("interface").equalsIgnoreCase("console")
				&& !line.getOptionValue("interface").equalsIgnoreCase("swing")
				&& !line.getOptionValue("interface").equalsIgnoreCase("both")) {
			System.err.println("Wrong type of interface");
			System.exit(3);

		} 
		else if (!mapa) {
			System.err.println("Map file not specified");
			System.exit(1);
		}
	}

	private static void evaluaMapa(CommandLine line) {
		if (line.hasOption("m") || line.hasOption("map")) {
			String ed = line.getOptionValue("map");
			if (ed.length() > 4 && ed.charAt(ed.length() - 1) == 't'
					&& ed.charAt(ed.length() - 2) == 'x'
					&& ed.charAt(ed.length() - 3) == 't'
					&& ed.charAt(ed.length() - 4) == '.')

				try {
					InputStream is = new FileInputStream(line.getOptionValue("map"));
					cityMap = loader.loadCity(is);
				} catch (FileNotFoundException e) {
					System.err.print("Error reading the map file: "
							+ line.getOptionValue("map")
							+ " (No existe el fichero o el directorio)");
					System.exit(2);
				} catch (IOException e) {
					System.err.println(e.getMessage());
					System.exit(2);
				}
			else mapa = false;
		}

		else mapa = false;
	}


	/**
	 * Cargamos las opaciones disponibles
	 * @return : las opciones que permiten al usuario intriducir
	 */
	private static Options LoadOption(){
		Option help = new Option("h", "help", false, "Shows this help message");

		@SuppressWarnings("static-access")
		Option interfac = OptionBuilder.withLongOpt("interface")
				.withArgName("type").hasArg()
				.withDescription("The type of interface: console, swing or both")
				.create("i");

		@SuppressWarnings("static-access")
		Option map = OptionBuilder.withLongOpt("map").withArgName("mapfile")
				.hasArg()
				.withDescription("File with the description of the city")
				.create("m");

		Options options = new Options();
		options.addOption(help);
		options.addOption(interfac);
		options.addOption(map);
		
		return options;
	}

	/**
	 * 
	 * el metodo comprobarLinea es para que el metodo parse ( options, args) no
	 * lance exepciones mostrandose en ese caso un mensaje de error,
	 * Comprobasmos si la linea cumple con la longiud
	 * @param args
	 *            : argumento de entrada
	 */
	private static void comprobarLinea(String[] args) {
		String Linea = null;
		// entra en el caso de que no se haya introducido ninguna cadena 
		if (args.length == 0) {
			System.err.println("Map file not specified");
			System.exit(1);
		}
		else if (args.length > 0 && args.length < 5) {
			if (args.length == 1 && !cadPermitida(args[0]))
				Linea = ("Interface not specified");
			else if (args.length == 2) {
				if (!cadPermitida(args[0]) || comprobarGionMedio(args[1]))
					Linea = ("Interface not specified");
			} else if (args.length == 4) {
				if (!cadPermitida(args[0]) || comprobarGionMedio(args[1]))
					Linea = ("Interface not specified");
				else if (!cadPermitida(args[2]))
					Linea = ("Interface not specified");
			}
			// es para ver q no se ha introducido -h
			else if (args.length != 1)
				Linea = ("Interface not specified");
		} else
			Linea = ("Interface not specified");
		
		if (Linea != null) {
			System.err.println(Linea);
			System.exit(-3);
		}
	}

	/**
	 * Comprobamos  la "-" de la cadena
	 * @param string : cadena q se comprueba si tiene una "-"
	 * @return true si la cadena inicia con "-", false en caso contrario
	 */
	private static boolean comprobarGionMedio(String string) {
		boolean salida = false;
		if (string.charAt(0) == '-')
			salida = true;
		return salida;
	}

	/**
	 * Vemos si la cadena es permitida o no, es decir si es -i,-m,-h,--interface,--map,--help
	 * @param args : cadena a comprobar
	 * @return :true si la dena pertenece a una de ellas, fasle en caso contrario
	 */
	private static boolean cadPermitida(String args) {
		boolean salida = false;
		if (args.equals("-i") || args.equals("--interface")
				|| args.equals("-m") || args.equals("--map")
				|| args.equals("-h") || args.equals("--help"))
			salida = true;
		return salida;

	}

}