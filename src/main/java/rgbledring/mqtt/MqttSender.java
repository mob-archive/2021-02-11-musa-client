package rgbledring.mqtt;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Consumer;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSender implements Closeable {

	public static class Message {

		public static Message message(String topic, String payload) {
			return new Message(topic, payload);
		}

		private final String topic;
		private final String payload;

		private Message(String topic, String payload) {
			this.topic = topic;
			this.payload = payload;
		}

		@Override
		public String toString() {
			return "Message [topic=" + topic + ", payload=" + payload + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((payload == null) ? 0 : payload.hashCode());
			result = prime * result + ((topic == null) ? 0 : topic.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Message other = (Message) obj;
			if (payload == null) {
				if (other.payload != null)
					return false;
			} else if (!payload.equals(other.payload))
				return false;
			if (topic == null) {
				if (other.topic != null)
					return false;
			} else if (!topic.equals(other.topic))
				return false;
			return true;
		}

	}

	private final MqttClient mqttClient;

	public MqttSender(String host, int port) throws IOException {
		try {
			mqttClient = new MqttClient("tcp://" + host + ":" + port, clientId(), new MemoryPersistence());
			mqttClient.setTimeToWait(SECONDS.toMillis(1));
			mqttClient.connect(connectOptions());
		} catch (MqttException e) {
			throw new IOException(e);
		}
	}

	private String clientId() {
		return getClass().getName() + "-" + System.currentTimeMillis();
	}

	private static MqttConnectOptions connectOptions() {
		MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
		mqttConnectOptions.setAutomaticReconnect(true);
		return mqttConnectOptions;
	}

	public void publish(Message message) {
		try {
			mqttClient.publish(message.topic, message.payload.getBytes(), 0, false);
		} catch (MqttException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() throws IOException {
		try {
			if (mqttClient.isConnected()) {
				mqttClient.disconnect();
			}
			mqttClient.close();
		} catch (MqttException e) {
			throw new IOException(e);
		}
	}

	public boolean isConnected() {
		return mqttClient.isConnected();
	}

	public MqttSender subscribe(String topic, Consumer<Message> consumer) throws IOException {
		mqttClient.setCallback(callback(consumer));
		try {
			mqttClient.subscribe(topic);
			return this;
		} catch (MqttException e) {
			throw new IOException(e);
		}
	}

	private MqttCallback callback(Consumer<Message> c) {
		return new MqttCallback() {

			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				c.accept(Message.message(topic, new String(message.getPayload())));
			}

			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {
				// ignore
			}

			@Override
			public void connectionLost(Throwable cause) {
				// ignore
			}
		};
	}

}