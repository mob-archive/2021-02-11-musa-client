package musa.stock;

import static java.awt.Font.ITALIC;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.BoxLayout.PAGE_AXIS;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.HORIZONTAL;

import java.awt.Font;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;

import musa.fwk.AbstractPanel;
import musa.fwk.ClientContext;
import musa.fwk.TitleProvider;
import rgbledring.mqtt.MqttLedRing;
import rgbledring.mqtt.MqttSender;

/**
 * MFW generated class. Do not change anything except dedicated user sections
 *
 */
public class StockPanel extends AbstractPanel implements TitleProvider {

	private static final long serialVersionUID = 6523015166272363631L;

	private static final int MIN = 0;
	private static final int MAX = 100;
	private static final int INIT = MIN;

	private JSlider profibilitySlider, availableAmountSlider;
	private JButton saveButton;

	private int profibility, availableAmount;
	private int ledCount1, ledCount2;
	private boolean direction1, direction2;

	private MqttLedRing mqttLedRing;

	public StockPanel(ClientContext clientContext) {
		StockContext ctx = (StockContext) clientContext;
		ledCount1 = ctx.getRingLedCount(0);
		ledCount2 = ctx.getRingLedCount(1);

		direction1 = ctx.getDirectionForRing(0);
		direction2 = ctx.getDirectionForRing(1);

		setLayout(new BoxLayout(this, PAGE_AXIS));

		add(makeLabel("Profibility"));
		profibilitySlider = makeSlider();
		add(profibilitySlider);

		add(makeLabel("AvailableAmount"));
		availableAmountSlider = makeSlider();
		add(availableAmountSlider);

		saveButton = new JButton("Save");
		add(saveButton);

		try {
			mqttLedRing = new MqttLedRing(ledCount1, new MqttSender("test.mosquitto.org", 1883));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/***
	 * Modifiable Method
	 */
	@Override
	protected void stateChanged(Object source) {
		// ########## GENERATED - USER SECTION BEGIN
		// PUT YOUR CODE HERE
		if (source == saveButton) {
			persist(new Object[] { profibility, availableAmount });
		}
		if (source == profibilitySlider) {
			this.profibility = ((JSlider) source).getValue();
			mqttLedRing.setLevel(profibility);
		}
		if (source == availableAmountSlider) {
			this.availableAmount = ((JSlider) source).getValue();
		}
		// ########## GENERATED - USER SECTION END
	}

	@Override
	public String getTitle() {
		return "Stock P/A";
	}

	private JSlider makeSlider() {
		JSlider slider = new JSlider(HORIZONTAL, MIN, MAX, INIT);
		configure(slider);
		return slider;
	}

	private JLabel makeLabel(String text) {
		JLabel label = new JLabel(text, CENTER);
		label.setAlignmentX(CENTER_ALIGNMENT);
		return label;
	}

	private void configure(JSlider slider) {
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBorder(createEmptyBorder(0, 0, 10, 0));
		slider.setFont(new Font("Serif", ITALIC, 15));
	}

}
