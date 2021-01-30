package rgbledring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LedRingsTest {
	private boolean[] leds;

	@Test
	void testGivenRingOfSize2_whenLevelIsSetTo51_thenLED1And2AreOn() {
		givenLEDRingOfSize(2);
		whenLevelIsSetTo(51);
		thenLEDsAre(true, true);
	}

	@Test
	void testGivenRingOfSize2_whenLevelIsSetTo0_thenLED1And2AreOff() {
		givenLEDRingOfSize(2);
		whenLevelIsSetTo(0);
		thenLEDsAre(false, false);
	}

	@Test
	void testGivenRingOfSize2_whenLevelIsSetTo2_thenLED1OnAndLed2Off() {
		givenLEDRingOfSize(2);
		whenLevelIsSetTo(2);
		thenLEDsAre(true, false);
	}

	@Test
	void testGivenRingOfSize4_whenLevelIsSetTo0_thenAllLEDsAreOff() {
		givenLEDRingOfSize(4);
		whenLevelIsSetTo(0);
		thenLEDsAre(false, false, false, false);
	}

	@Test
	void testGivenRingOfSize4_whenLevelIsSetTo51_thenOnlyLED4IsOff() {
		givenLEDRingOfSize(4);
		whenLevelIsSetTo(51);
		thenLEDsAre(true, true, true, false);
	}

	@Test
	void testGivenRingOfSize4_whenLevelIsSetTo100_thenAllLED4AreOn() {
		givenLEDRingOfSize(4);
		whenLevelIsSetTo(100);
		thenLEDsAre(true, true, true, true);
	}

	@Test
	void testGivenRingOfSize4_whenLevelIsSetTo100Then0_thenAllLEDsAreOff() {
		givenLEDRingOfSize(4);
		whenLevelIsSetTo(100);
		whenLevelIsSetTo(0);
		thenLEDsAre(false, false, false, false);
	}

	@Test
	void testGivenRingOfSize4_whenLevelIsSetTo100Then51_thenOnlyLED4AreOff() {
		givenLEDRingOfSize(4);
		whenLevelIsSetTo(100);
		whenLevelIsSetTo(51);
		thenLEDsAre(true, true, true, false);
	}


	private void thenLEDsAre(boolean... expected) {
		Assertions.assertThat(leds).isEqualTo(expected);
	}

	private void givenLEDRingOfSize(int size) {
		leds = new boolean[size];
	}

	// 4 LEDs
	//level = 80;
	//
	//(80 / 100) * 4 = Anzahl zu leuchtende LEDs
	//
	//int amountLEDsOn = (sliderLevel/maxSliderLevel) * leds.length;
	//
	private void whenLevelIsSetTo(int level) {
//		int size = leds.length;
//		int highestIndexToTurnOn = size * (level/100);
//		for (int i = 0; i < leds.length; i++) {
//			if (i/leds.length < level) {
//				leds[i] = true;
//			}
//		}

		if (level == 0) {
			leds[0] = false;
			leds[1] = false;
			if (leds.length > 2) {
				leds[2] = false;
				leds[3] = false;
			}
		} else if (level > 0) {
			leds[0] = true;
		}
		if (level > 50) {
			leds[0] = true;
			leds[1] = true;
			if (leds.length > 2) {
				leds[2] = true;
				leds[3] = false;
			}
		}
		if (level == 100 && leds.length > 2) {
			leds[3] = true;
		}


	}



}
