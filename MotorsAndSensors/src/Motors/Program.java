package Motors;

import java.io.File;
import java.util.Random;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.robotics.LightScanner;
import lejos.util.Delay;

public class Program
{
	public boolean returnPressed = false;
	public boolean depositPressed = false;
	public boolean isDone = false;
	private boolean run = true;
	public static void main(String[] args)
	{
		SensorPort.S3.setType(SensorPort.TYPE_SWITCH);
		SensorPort.S4.setType(SensorPort.TYPE_SWITCH);
		new Program();
		//Button.waitForAnyPress();
	}
	
	public Program(boolean move)
	{
		//MoveBucketArm();
	}
	public Program()
	{
		LightSensor light = new LightSensor(SensorPort.S1, true);
		try
		{
			
			int lightValue = light.getLightValue();
			System.out.println("Place a quarter into the slot");
			boolean isFirstCall = false;
			while(run)
			{
				boolean quarterInSlot = coinInSlot(light.getLightValue(),lightValue);
				if(quarterInSlot && !isFirstCall)
				{
					isFirstCall = true;
					informCoinPlacement(quarterInSlot);
				}
				else if(!quarterInSlot && isFirstCall)
				{
					isFirstCall = false;
					informCoinPlacement(quarterInSlot);
				}
				
				if(returnPressed && quarterInSlot)
				{
					MoveCoinArm(false);
					Delay.msDelay(1000);
					returnPressed = SensorPort.S4.readBooleanValue();
				}
				if(depositPressed  && quarterInSlot) //&& coinInSlot(newLightValue,lightValue)
				{
					MoveCoinArm(true);
					play();
					depositPressed = SensorPort.S3.readBooleanValue();
				}
				else
				{
					returnPressed = SensorPort.S4.readBooleanValue();
					depositPressed = SensorPort.S3.readBooleanValue();
				}
				
				CheckUserDebug();
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	private void informCoinPlacement(boolean quarter) {
		// TODO Auto-generated method stub
		LCD.clear();
		String gameOutput = quarter? "Procced with game play" : "Place a quarter into the slot";
		System.out.println(gameOutput);
	}

	private void CheckUserDebug() {
		
		if(Button.readButtons() == Button.ID_RIGHT)
			moveTray();
		if(Button.readButtons() == Button.ID_LEFT)
			payOut();
		if(Button.readButtons() == Button.ID_ESCAPE)
			run = false;
	}

	private void moveTray() {
		Motor.C.rotate(-75);
		Delay.msDelay(1000);
		Motor.C.rotate(75);
		
	}

	public void MoveCoinArm(boolean deposit)
	{
		try
		{
			if(deposit)
			{
				Motor.B.rotate(-60);
				Delay.msDelay(1000);
				Motor.B.rotate(60);
			}
			else
			{
				Motor.B.rotate(120);
				Delay.msDelay(1000);
				Motor.B.rotate(-120);
			}
			
			
		}
		catch(Exception e)
		{
			
		}
		
	}
	
//	public void MoveBucketArm()
//	{
//		try
//		{
//			Motor.A.rotate(18);
//			Delay.msDelay(1000);
//			Motor.A.rotate(-18);
//		}
//		catch(Exception e)
//		{
//			
//		}
//	}
	private boolean isWin()
	{
		Random ran = new Random();
		int randomNumber = ran.nextInt(9);
		return randomNumber > 6;
			
	}
	private void play()
	{
//		int errorSound = Sound.playSample(new File("Casino4.wav"), 80);
//		if(errorSound < 0)
//			System.out.println("Problem playing sound file");
		Delay.msDelay(1000);
		if(isWin())
		{
			payOut();
		}
		else
			System.out.println("LOSER!!!");
		
		Delay.msDelay(3000);
	}

	private void payOut() {
		System.out.println("Jackpot!");
		flashLights();
		//MoveBucketArm();
		moveTray();
		flashLights();
	}
	private void flashLights() {
		
		flashLight();
		flashLight();
		flashLight();
		
	}

	private void flashLight() {
		MotorPort.A.controlMotor(50, MotorPort.FORWARD);
		Delay.msDelay(500);
		MotorPort.A.controlMotor(0, MotorPort.STOP);

		Delay.msDelay(500);
	}

	private boolean coinInSlot(int lightValue, int initalValue)
	{
		//System.out.println("Coin in slot: old " + initalValue + ", new " + lightValue);
		boolean inSlot = false;
			if(lightValue - initalValue > 10)
				inSlot = lightValue > initalValue;
		return inSlot;
	}
	

}
