package rgbledring;

import java.util.ArrayList;
import java.util.List;

public class LedRing {

	public static class Led
	{
		enum Status
		{
			On,
			Off,
		}

		enum Color
		{
			Green,
			Yellow,
			Red,
		}
	}

	private boolean[] leds;
	private List<Led> ledList;

	public LedRing(boolean[] leds) {
		this.leds = leds;
	}

	public LedRing(int size)
	{
		this.ledList = new ArrayList<Led>(size);
	}

	public void setLevel(int level) {
		float percentPerLed = (100f / leds.length);
		for (int i = 0; i < leds.length; i++) {
			final float ledActivationLevel = (percentPerLed * i);
			leds[i] = level > ledActivationLevel;
		}
	}

}
