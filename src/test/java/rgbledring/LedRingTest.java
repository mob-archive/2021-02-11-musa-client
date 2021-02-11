package rgbledring;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

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
    void testGivenRingOfSize4_whenLevelIsSetTo50_thenOnlyLED1And2AreOn() {
    	givenLEDRingOfSize(4);
    	whenLevelIsSetTo(50);
    	thenLEDsAre(true, true, false, false);
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

    @DisplayName("test Given Ring of size 16 when Level is set to -> AllLEDsAre Off")
    @Test
    void testGivenRingOfSize16_whenLevelIsSetTo0_thenAllLEDsAreOff() {
        givenLEDRingOfSize(16);
        whenLevelIsSetTo(0);
        thenLEDsAreOnUntil(0);
    }
    
    @DisplayName("test Given Ring of size 8 when Level is set to 50 -> first 4 LEDs are on")
    @Test
    void testGivenRingOfSize8_whenLevelIsSetTo50_thenFirst4LEDsAreOn() {
    	givenLEDRingOfSize(8);
    	whenLevelIsSetTo(50);
    	thenLEDsAreOnUntil(4);
    }

    @Test
	void testGivenRingOfSize16_whenLevelIsSetTo50_then8LEDsAreOn() {
    	givenLEDRingOfSize(16);
    	whenLevelIsSetTo(50);
    	thenLEDsAreOnUntil(8);
	}

    private void thenLEDsAre(boolean... expected) {
        assertThat(leds).isEqualTo(expected);
    }

    private void thenLEDsAreOnUntil(int ledPosition) {
        boolean[] expected = new boolean[leds.length];
        Arrays.fill(expected, 0, ledPosition, true);
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
