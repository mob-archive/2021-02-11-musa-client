package rgbledring;

public class LedRing {

    private boolean[] leds;

    private boolean flipped;

	public LedRing(boolean[] leds, boolean flipped) {
		this.leds = leds;
		this.flipped = flipped;
	}

	public void setLevel(int level) {
		float percentPerLed = (100f / leds.length);
		for (int i = 0; i < leds.length; i++) {
			final float ledActivationLevel = (percentPerLed * i);
            leds[i] = level > ledActivationLevel;
		}
		if (flipped) {
			for (int i = 0; i < leds.length; i++) {
				final float ledActivationLevel = (percentPerLed * i);
				leds[leds.length - 1 - i] = level > ledActivationLevel;
			}

		}

	}

}
