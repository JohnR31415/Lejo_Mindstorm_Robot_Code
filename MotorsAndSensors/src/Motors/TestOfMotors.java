package Motors;

import java.io.File;

import lejos.nxt.*;
import lejos.util.Delay;

public class TestOfMotors
{

	//Button.readButtons() 1 = Enter
	//Button.readButtons() 2 = Left
	//Button.readButtons() 4 = Right
	//Button.readButtons() 8 = Back
	boolean buttonPressed = false;

	public static void main(String[] args)
	{
		new TestOfMotors(true);
	}
	
	public TestOfMotors(boolean test)
	{
		try
		{
			boolean lol = true;
			boolean running = false;
			SensorPort.S1.setType(SensorPort.TYPE_SWITCH);
			LCD.drawString("Starting", 0, 0);
			while(lol)
			{
				if(buttonPressed && !running)
				{
					LCD.clear();
					LCD.drawString("Starting to move", 0, 0);
					Motor.C.forward();
					running = true;
					Thread.sleep(1000);
					buttonPressed = SensorPort.S1.readBooleanValue();
				}
				else if(!buttonPressed && running)
				{
					LCD.clear();
					LCD.drawString("Stopping", 0, 0);
					Motor.C.stop();
					running = false;
					Thread.sleep(1000);
					buttonPressed = SensorPort.S1.readBooleanValue();
				}
				else
				{
					LCD.clear();
					if(running)
						LCD.drawString("Moving", 0, 0);
					else
						LCD.drawString("Stopped", 0, 0);
					buttonPressed = SensorPort.S1.readBooleanValue();
				}
				Thread.sleep(100);	
			}
			
		}
		catch(Exception e)
		{
			
		}
			
	}

	public TestOfMotors()
	{
		int distance = 0;
		
		UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S2);
		//sensorCheck();
		do
		{
			moveForward(360);
			distance = sensor.getDistance();
			System.out.println("Distance: " + distance);
			 //motorFunction(180);
			sensorCheck();
		} while (!buttonPressed && distance > 30);

		System.out.println(buttonPressed);
		Sound.playSample(new File("Casino4.wav"), 8011);
		Button.waitForAnyPress();
	}

	private void moveForward(int rpm)
	{
		// TODO Auto-generated method stub
		Motor.B.setSpeed(rpm);
		Motor.B.forward();
		
		Motor.C.setSpeed(rpm);
		Motor.C.forward();
		Delay.msDelay(600);
		Motor.B.stop();
		Motor.C.stop();
	}

	private void motorFunction(int rpm)
	{
		// TODO Auto-generated method stub
		Motor.B.setSpeed(rpm);
		if (rpm > 0)
			Motor.B.backward();
		else
			Motor.B.forward();
		Delay.msDelay(3000);
		Motor.B.stop();
		Motor.C.setSpeed(rpm);
		if (rpm > 0)
			Motor.C.forward();
		else
			Motor.C.backward();
		Delay.msDelay(3000);
		Motor.C.stop();

	}

	private void sensorCheck()
	{
		// TODO Auto-generated method stub
		SensorPort.S1.setType(SensorPort.TYPE_SWITCH);
		if (!buttonPressed)
			buttonPressed = SensorPort.S1.readBooleanValue();
		
//		SensorPort.S1.addSensorPortListener(new SensorPortListener()
//		{
//
//			@Override
//			public void stateChanged(SensorPort aSource, int aOldValue,
//					int aNewValue)
//			{
//				// TODO Auto-generated method stub
//				System.out.println("s1 id: " + aSource.getId());
//				System.out.println("s1 type:" + aSource.getType());
//				System.out.println("Old Value: " + aOldValue + ", New Value: "
//						+ aNewValue);
//
//				
//			}
//		});
		// SensorPort.S2.addSensorPortListener(new SensorPortListener() {
		//
		// @Override
		// public void stateChanged(SensorPort aSource, int aOldValue, int
		// aNewValue) {
		// // TODO Auto-generated method stub
		// //System.out.println("s2 id: " + aSource.getId());
		// //System.out.println("s2 type:" + aSource.getType());
		// System.out.println("Mode: " + aSource. );
		// }
		// });
		// SensorPort.S3.setType(SensorPort.TYPE_SOUND_DB);
		// SensorPort.S3.addSensorPortListener(new SensorPortListener() {
		//
		// @Override
		// public void stateChanged(SensorPort aSource, int aOldValue, int
		// aNewValue) {
		// // TODO Auto-generated method stub
		// System.out.println("s3 id: " + aSource.getId());
		// System.out.println("s3 type:" + aSource.getType());
		// System.out.println("Old Value: " + aOldValue + ", New Value: " +
		// aNewValue);
		// }
		// });
	}
}
