package SlotMachine_TEST;

import lejos.nxt.NXTRegulatedMotor;
import SlotMachine.CoinTray;

public class coinTray_Test extends CoinTray {

	public coinTray_Test(NXTRegulatedMotor _trayMotor) {
		super(_trayMotor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void empty() {
		// TODO Auto-generated method stub
		System.out.println("Emptying bucket");
	}

}
