package rgbledring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LedRingTest {

	private boolean[] leds;
	private LedRing ledRing;

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
		assertThat(leds).isEqualTo(expected);
	}

	private void givenLEDRingOfSize(int size) {
		leds = new boolean[size];
		ledRing = new LedRing(leds);
	}

	private void whenLevelIsSetTo(int level) {
		ledRing.setLevel(level);
	}

}
