package SlotMachine;

import java.util.Random;

import lejos.nxt.*;
import lejos.util.Delay;

public class SlotMachine implements OurAction{

	public CoinSlot coinSlot;
	public CoinTray coinTray;
	protected MotorPort lights;
	protected SensorPort playButton;
	protected boolean running = true;
	protected boolean firstQuarterCall = false;
	
	public SlotMachine()
	{
		SensorPort.S3.setType(SensorPort.TYPE_SWITCH);
		SensorPort.S4.setType(SensorPort.TYPE_SWITCH);
		coinSlot = new CoinSlot(Motor.B, SensorPort.S1, SensorPort.S4);
		coinTray = new CoinTray(Motor.C);
		lights = MotorPort.A;
		playButton = SensorPort.S3;
	}
	public void run()
	{
		
		System.out.println("Please place a quarter in the slot.");
		while(running)
		{
			if(hasQuarter())
				checkButtons();
			CheckUserDebug();
		}
	}
	protected void checkButtons()
	{
		if(coinSlot.isReturnButtonPressed())
		{
			coinSlot.returnCoin();
		}
		if(isPlayButtonPressed())
		{
			coinSlot.deposit();
			play();
		}
	}
	protected void CheckUserDebug() {
	
	if(Button.readButtons() == Button.ID_RIGHT)
		coinTray.empty();
	if(Button.readButtons() == Button.ID_LEFT)
		payOut();
	if(Button.readButtons() == Button.ID_ESCAPE)
		running = false;
	}
	public boolean hasQuarter()
	{
		boolean quarterPresent = coinSlot.hasCoin();
		if(quarterPresent && !firstQuarterCall)
		{
			firstQuarterCall = true;
			coinSlot.informCoinPlacement();
		}
		else if(!quarterPresent && firstQuarterCall)
		{
			firstQuarterCall = false;
			coinSlot.informCoinPlacement();
		}
		return quarterPresent;
	}
	
	public void flashLights(int numberOfTimes)
	{
		do{
		lights.controlMotor(50, MotorPort.FORWARD);
		Delay.msDelay(500);
		lights.controlMotor(0, MotorPort.STOP);

		Delay.msDelay(500);
		}while(numberOfTimes-- >0 );
	}
	
	public void play()
	{
		Delay.msDelay(1000);
		if(isWin())
		{
			payOut();
		}
		else
			System.out.println("LOSER!!!");
		
		Delay.msDelay(3000);
	}
	protected boolean isPlayButtonPressed()
	{
		return playButton.readBooleanValue();
	}
	protected void payOut()
	{
		System.out.println("Jackpot!");
		flashLights(3);
		coinTray.empty();
		flashLights(3);
	}
	protected boolean isWin()
	{
		Random ran = new Random();
		int randomNumber = ran.nextInt(9);
		return randomNumber > 6;
	}
	@Override
	public void ActionPerform() {
		// TODO Auto-generated method stub
		checkButtons();
	}
}
