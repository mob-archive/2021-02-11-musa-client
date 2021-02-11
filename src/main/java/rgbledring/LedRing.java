package rgbledring;

import java.util.ArrayList;
import java.util.List;

public class LedRing {

    private boolean[] leds;

	public LedRing(boolean[] leds) {
		this.leds = leds;
	}

	public void setLevel(int level) {
		float percentPerLed = (100f / leds.length);
		for (int i = 0; i < leds.length; i++) {
			final float ledActivationLevel = (percentPerLed * i);
            leds[i] = level > ledActivationLevel;
		}
	}
}
