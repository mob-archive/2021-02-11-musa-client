package rgbledring;

public class LedRing {

	private boolean[] leds;

	public LedRing(boolean[] leds) {
		this.leds = leds;
	}

	public void setLevel(int level) {
		for (int i = 0; i < leds.length; i++) {
			leds[i] = level > 100 / leds.length * i;
		}
	}

}
