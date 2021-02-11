package rgbledring;

public class LedRing {

	private boolean[] leds;

	public LedRing(boolean[] leds) {
		this.leds = leds;
	}

	public void setLevel(int level) {
		for (int i = 0; i < leds.length; i++) {
			final float ledActivationLevel = ((100f / leds.length) * i);
			leds[i] = level > ledActivationLevel;
		}
	}

}
