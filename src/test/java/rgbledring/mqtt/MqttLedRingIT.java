package rgbledring.mqtt;

import static org.assertj.core.api.Assertions.assertThat;
import static rgbledring.mqtt.MqttSender.Message.message;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import rgbledring.mqtt.MqttSender.Message;

public class MqttLedRingIT {

	List<Message> messagesSent = new ArrayList<>();

	@Test
	void whenTheLevelIsOneTheFirstLedIsOn() throws Exception {
		givenRingOfLeds(8);
		whenLevelIsSetTo(1);
		thenMessagesWereSent( //
				message("some/led/0/rgb", "#FFFFFF"), //
				message("some/led/0/rgb", "#000000"), //
				message("some/led/0/rgb", "#000000"), //
				message("some/led/0/rgb", "#000000") //
		);
	}

	private void givenRingOfLeds(int leds) {
		// TODO Auto-generated method stub

	}

	private void whenLevelIsSetTo(int level) {
		// TODO Auto-generated method stub

	}

	private void thenMessagesWereSent(Message... messages) {
		assertThat(messagesSent).containsExactly(messages);
	}

}
