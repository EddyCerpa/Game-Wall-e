package tp.pr5;

/**
 * Un tipo enumerado que representa los puntos cardinales (norte, este, sur y
 * oeste), además de un valor que representa una dirección desconocida.
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public enum Direction {
	EAST, NORTH, SOUTH, UNKNOWN, WEST;

	/**
	 * Calcula la direccion opuesta a la dada
	 * 
	 * @param a
	 *            : direccion dada
	 * @return : la direccion opuesta
	 */
	public static Direction opposite(Direction a) {
		Direction direccion;

		if (a == EAST)
			direccion = WEST;
		else if (a == NORTH)
			direccion = SOUTH;
		else if (a == SOUTH)
			direccion = NORTH;
		else if (a == WEST)
			direccion = EAST;
		else
			direccion = UNKNOWN;
		return direccion;
	}
	/**
	 * Realiza un giro dependiendo de la direccion a la q este y de la rotacion establecida
	 * @param direction : direccion a la que esta walle
	 * @param rotation : giro que se quiere realizar
	 * @return : la nueva direccion a la que esta mirando walee
	 */
	public static Direction giroDerechaIzquierda(Direction direction, Rotation rotation) {
		Direction dir = UNKNOWN;

		if (direction == Direction.NORTH) {
			if (rotation == Rotation.LEFT)
				dir = Direction.WEST;
			else if (rotation == Rotation.RIGHT)
				dir = Direction.EAST;
		} else if (direction == Direction.WEST) {
			if (rotation == Rotation.LEFT)
				dir = Direction.SOUTH;
			else if (rotation == Rotation.RIGHT)
				dir = Direction.NORTH;
		} else if (direction == Direction.SOUTH) {
			if (rotation == Rotation.LEFT)
				dir = Direction.EAST;
			else if (rotation == Rotation.RIGHT)
				dir = Direction.WEST;
		} else if (direction == Direction.EAST) {
			if (rotation == Rotation.LEFT)
				dir = Direction.NORTH;
			else if (rotation == Rotation.RIGHT)
				dir = Direction.SOUTH;
		}
		return dir;
	}

	/**
	 * Metodo al que introducimos un string y nos debuelve la direccion a la que se corresponde
	 * @param word : cadena de entrada
	 * @return : La direccion correspondiente
	 * @throws IOException : Cuando la cadena no se corresponde con ninguna cadena
	 */
	public static Direction direccion(String word) {
		Direction dir = UNKNOWN;
		if (word.equalsIgnoreCase("south")) dir=Direction.SOUTH;
        else if(word.equalsIgnoreCase("north"))dir=Direction.NORTH;
        else if(word.equalsIgnoreCase("east"))dir=Direction.EAST;
        else if(word.equalsIgnoreCase("west"))dir=Direction.WEST;
		return dir;
	}
		
	
}
