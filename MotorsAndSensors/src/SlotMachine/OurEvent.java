package SlotMachine;

import java.util.*;

public class OurEvent implements OurListener {

	List<OurAction> listeners = new ArrayList<OurAction>();
	public void addListener(OurAction o)
	{
		listeners.add(o);
	}
	@Override
	public void fireEvent() {
		// TODO Auto-generated method stub
		System.out.println("EVEN FIRED!!!");
		for(OurAction a : listeners)
		{
			a.ActionPerform();
		}
	}
}
