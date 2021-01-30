package rgbledring.mqtt;

import static org.assertj.core.api.Assertions.assertThat;
import static rgbledring.mqtt.MqttSender.Message.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rgbledring.LedRing;
import rgbledring.mqtt.MqttSender.Message;

public class MqttLedRingIT {

	static final String MQTT_HOST = "test.mosquitto.org";
	static final int MQTT_PORT = 1883;

	boolean[] leds;
	LedRing ledRing;
	MqttSender mqttSender;

	List<Message> messagesSent = new ArrayList<>();
	MqttSender secondClient;

	@SuppressWarnings("resource")
	@BeforeEach
	void setup() throws IOException {
		this.secondClient = new MqttSender(MQTT_HOST, MQTT_PORT).subscribe("some/led/#", messagesSent::add);
	}

	@AfterEach
	void tearDown() throws IOException {
		this.mqttSender.close();
		this.secondClient.close();
	}

	@Test
	void whenTheLevelIsOneTheFirstLedIsOn() throws Exception {
		givenRingOfLeds(8);
		whenLevelIsSetTo(1);
		thenMessagesWereSent( //
				message("some/led/0/rgb", "#FFFFFF"), //
				message("some/led/1/rgb", "#000000"), //
				message("some/led/2/rgb", "#000000"), //
				message("some/led/3/rgb", "#000000"), //
				message("some/led/4/rgb", "#000000"), //
				message("some/led/5/rgb", "#000000"), //
				message("some/led/6/rgb", "#000000"), //
				message("some/led/7/rgb", "#000000") //
		);
	}

	private void givenRingOfLeds(int leds) throws IOException {
		this.leds = new boolean[leds];
		this.ledRing = new LedRing(this.leds);
		this.mqttSender = new MqttSender(MQTT_HOST, MQTT_PORT);
	}

	private void whenLevelIsSetTo(int level) {
		ledRing.setLevel(level);
		for (int i = 0; i < this.leds.length; i++) {
			mqttSender.publish(message("some/led/" + i + "/rgb", color(this.leds[i])));
		}
	}

	private String color(boolean state) {
		return state ? "#FFFFFF" : "#000000";
	}

	private void thenMessagesWereSent(Message... messages) throws InterruptedException {
		assertThat(messagesSent).containsExactly(messages);
	}

}
