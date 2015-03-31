package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * Esta clase es utilizada para hacer una busqueda binaria, ya que hay metodos que reciben un ide
 * @author eddy
 *
 */
public class OtherItem extends Item {

	public OtherItem(String id, String description) {
		super(id, description);
	}

	@Override
	public boolean canBeUsed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean use(RobotEngine r, NavigationModule nav) {
		// TODO Auto-generated method stub
		return false;
	}

}
