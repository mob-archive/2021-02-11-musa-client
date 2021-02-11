package rgbledring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LedRingTest {

	private boolean[] leds;
	private LedRing ledRing;

	@Test
	void testGivenRing1OfSize2_whenLevelIsSetTo51_thenLED1And2AreOn() {
		givenLEDRing1OfSize(2);
		whenLevelIsSetTo(51);
		thenLEDsAre(true, true);
	}

	@Test
	void testGivenRing1OfSize2_whenLevelIsSetTo0_thenLED1And2AreOff() {
		givenLEDRing1OfSize(2);
		whenLevelIsSetTo(0);
		thenLEDsAre(false, false);
	}

	@Test
	void testGivenRing1OfSize2_whenLevelIsSetTo2_thenLED1OnAndLed2Off() {
		givenLEDRing1OfSize(2);
		whenLevelIsSetTo(2);
		thenLEDsAre(true, false);
	}

	@Test
	void testGivenRing1OfSize4_whenLevelIsSetTo0_thenAllLEDsAreOff() {
		givenLEDRing1OfSize(4);
		whenLevelIsSetTo(0);
		thenLEDsAre(false, false, false, false);
	}

	@Test
	void testGivenRing1OfSize4_whenLevelIsSetTo51_thenOnlyLED4IsOff() {
		givenLEDRing1OfSize(4);
		whenLevelIsSetTo(51);
		thenLEDsAre(true, true, true, false);
	}

	@Test
	void testGivenRing1OfSize4_whenLevelIsSetTo50_thenOnlyLED1And2AreOn() {
		givenLEDRing1OfSize(4);
		whenLevelIsSetTo(50);
		thenLEDsAre(true, true, false, false);
	}

	@Test
	void testGivenRing1OfSize4_whenLevelIsSetTo100_thenAllLED4AreOn() {
		givenLEDRing1OfSize(4);
		whenLevelIsSetTo(100);
		thenLEDsAre(true, true, true, true);
	}

	@Test
	void testGivenRing1OfSize4_whenLevelIsSetTo100Then0_thenAllLEDsAreOff() {
		givenLEDRing1OfSize(4);
		whenLevelIsSetTo(100);
		whenLevelIsSetTo(0);
		thenLEDsAre(false, false, false, false);
	}

	@Test
	void testGivenRing1OfSize4_whenLevelIsSetTo100Then51_thenOnlyLED4AreOff() {
		givenLEDRing1OfSize(4);
		whenLevelIsSetTo(100);
		whenLevelIsSetTo(51);
		thenLEDsAre(true, true, true, false);
	}

	@DisplayName("Given Ring of size 16 when Level is set to 0 -> AllLEDsAre Off")
	@Test
	void testGivenRing1OfSize16_whenLevelIsSetTo0_thenAllLEDsAreOff() {
		givenLEDRing1OfSize(16);
		whenLevelIsSetTo(0);
		thenLEDsAreOnUntil(0);
	}

	@DisplayName("Given Ring of size 16 when Level is set to 50")
	@Test
	void testGivenRing1OfSize16_whenLevelIsSetTo50_then8LEDsAreOn() {
		givenLEDRing1OfSize(16);
		whenLevelIsSetTo(50);
		thenLEDsAreOnUntil(8);
	}

	@DisplayName("Given Ring of size 16 when Level is set to -> AllLEDsAre On")
	@Test
	void testGivenRing1OfSize16_whenLevelIsSetTo100_thenAllLEDsAreOn() {
		givenLEDRing1OfSize(16);
		whenLevelIsSetTo(100);
		thenLEDsAreOnUntil(16);
	}

	@DisplayName("Given Ring of size 8 when Level is set to 50 -> first 4 LEDs are on")
	@Test
	void testGivenRing1OfSize8_whenLevelIsSetTo50_thenFirst4LEDsAreOn() {
		givenLEDRing1OfSize(8);
		whenLevelIsSetTo(50);
		thenLEDsAreOnUntil(4);
	}


    private void thenLEDsAre(boolean... expected) {
		assertThat(leds).isEqualTo(expected);
	}

	private void thenLEDsAreOnUntil(int ledPosition) {
		boolean[] expected = new boolean[leds.length];
		Arrays.fill(expected, 0, ledPosition, true);
		assertThat(leds).isEqualTo(expected);

	}

	private void givenLEDRing1OfSize(int size) {
		leds = new boolean[size];
		ledRing = new LedRing(leds);

	}

	private void whenLevelIsSetTo(int level) {
		ledRing.setLevel(level);
	}

}
