package rgbledring.mqtt;

import static rgbledring.mqtt.MqttSender.Message.message;

import java.io.Closeable;
import java.io.IOException;

import rgbledring.LedRing;

public class MqttLedRing implements Closeable {

	private final boolean[] leds;
	private final LedRing ledRing;
	private final MqttSender mqttSender;

	public MqttLedRing(int leds, MqttSender mqttSender) {
		this.leds = new boolean[leds];
		this.ledRing = new LedRing(this.leds);
		this.mqttSender = mqttSender;
	}

	public void setLevel(int level) {
		ledRing.setLevel(level);
		for (int i = 0; i < leds.length; i++) {
			mqttSender.publish(message("some/led/" + i + "/rgb", color(leds[i])));
		}
	}

	private String color(boolean state) {
		return state ? "#FFFFFF" : "#000000";
	}

	@Override
	public void close() throws IOException {
		this.mqttSender.close();
	}

}