package SlotMachine;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.util.Delay;

public class CoinSlot implements OurAction{

	protected OurEvent coinEvent;
	protected int initalLightValue;
	protected NXTRegulatedMotor coinMotor;
	protected LightSensor light;
	protected SensorPort returnButton;
	protected boolean coinInSlot;
	public CoinSlot(NXTRegulatedMotor _coinMotor,SensorPort lightSensor, SensorPort _returnbutton)
	{
		coinMotor = _coinMotor;
		light = new LightSensor(lightSensor, true);
		initalLightValue = light.getLightValue();
		returnButton = _returnbutton;
		coinEvent = new OurEvent();
		coinEvent.addListener(this);
		coinInSlot = false;
	}
	public void returnCoin()
	{
		coinMotor.rotate(120);
		Delay.msDelay(1000);
		coinMotor.rotate(-120);
	}
	public void deposit()
	{
		coinMotor.rotate(-60);
		Delay.msDelay(1000);
		coinMotor.rotate(60);
	}
	public void informCoinPlacement() {
		// TODO Auto-generated method stub
		LCD.clear();
		String gameOutput = coinInSlot? "Procced with game play" : "Place a quarter into the slot";
		System.out.println(gameOutput);
	}
	public boolean isReturnButtonPressed()
	{
		return returnButton.readBooleanValue();
	}
	public boolean hasCoin()
	{
		//Thread newThread = new Thread(new coinLightSensor(coinEvent));
		//System.out.println("started the thread");
		//newThread.run();
		boolean inSlot = false;
		int lightValue = light.getLightValue();
		if(lightValue - initalLightValue > 10)
		{
			inSlot = lightValue > initalLightValue;
		}
		return inSlot;
	}
	@Override
	public void ActionPerform() {
		// TODO Auto-generated method stub
		coinInSlot = true;
	}
}

class coinLightSensor implements Runnable
{
	protected OurEvent coinInSlot;
	LightSensor light = new LightSensor(SensorPort.S1, true);
	int initalLightValue = light.getLightValue();
	
	public coinLightSensor(OurEvent coinInSlot2) {
		// TODO Auto-generated constructor stub
		coinInSlot =  coinInSlot2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean checking = true;
		while(checking)
		{
			int lightValue = light.getLightValue();
			if(lightValue - initalLightValue > 10)
			{
				checking = !(lightValue > initalLightValue);
				System.out.println("checking: " + checking);
			}
		}
		coinInSlot.fireEvent();
	}
	
}
