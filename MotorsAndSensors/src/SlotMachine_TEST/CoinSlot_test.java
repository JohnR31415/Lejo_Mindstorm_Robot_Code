package SlotMachine_TEST;

import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import SlotMachine.CoinSlot;

public class CoinSlot_test extends CoinSlot {

	public CoinSlot_test(NXTRegulatedMotor _coinMotor, SensorPort lightSensor,
			SensorPort _returnbutton) {
		super(_coinMotor, lightSensor, _returnbutton);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean hasCoin() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void deposit() {
		// TODO Auto-generated method stub
		System.out.println("Depositing coin");
	}
	
	@Override
	public void returnCoin() {
		// TODO Auto-generated method stub
		System.out.println("returning coin");
	}
	@Override
	public boolean isReturnButtonPressed() {
		// TODO Auto-generated method stub
		System.out.println("return button pressed");
		return true;
	}
	
	@Override
	public void informCoinPlacement() {
		// TODO Auto-generated method stub
		System.out.println("Coin placement");
		super.informCoinPlacement();
	}
}
