package SlotMachine_TEST;

import SlotMachine.SlotMachine;

public class SlotMachineTest extends SlotMachine{

	public static void main(String[] args)
	{
		SlotMachineTest testSlot = new SlotMachineTest();
		
		System.out.println("Begin Test");
		System.out.println("hasQuarter() returns : "+ testSlot.hasQuarter());
		testSlot.checkWinPercent();
		testSlot.coinSlot.deposit();
		testSlot.coinSlot.returnCoin();
		testSlot.coinSlot.hasCoin();
		testSlot.coinSlot.informCoinPlacement();
		testSlot.coinSlot.isReturnButtonPressed();
		testSlot.coinTray.empty();
		
	}
	
	public SlotMachineTest() {
		// TODO Auto-generated constructor stub
		coinSlot = new CoinSlot_test(null, null, null);
		coinTray = new coinTray_Test(null);
	}
	@Override
	public boolean hasQuarter() {
		System.out.println("hasQuarter()");
		boolean quarterPresent = true;
		if(quarterPresent && !firstQuarterCall)
		{
			firstQuarterCall = true;
			System.out.println("Quarter precent");
		}
		else if(!quarterPresent && firstQuarterCall)
		{
			firstQuarterCall = false;
			System.out.println("No quarter precent");
		}
		return quarterPresent;
	}

	public void checkWinPercent()
	{
		int wins = 0;
		for(int i =0; i < 100; i++)
		{
			if(isWin())
				wins++;
		}
		System.out.println("Win Percentage: " + wins + "%");
	}
	@Override
	protected boolean isPlayButtonPressed() {
		// TODO Auto-generated method stub
		return true;
	}

}
