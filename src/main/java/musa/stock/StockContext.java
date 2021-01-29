package musa.stock;

import musa.fwk.ClientContext;

public interface StockContext extends ClientContext {

	/**
	 * Returns the amount of leds of the ring.
	 * 
	 * @param ring number of ring
	 * @return amount of leds
	 */
	int getRingLedCount(int ring);

	/**
	 * When value on ring should be shown clockwise <code>true</code> is returned,
	 * otherwise <code>false</code>.
	 * 
	 * @param ring number of ring
	 * @return <code>true</code> for clockwise
	 */
	boolean getDirectionForRing(int ring);

	/**
	 * Returns the hostname of the mqtt broker.
	 * 
	 * @return hostname of the mqtt broker.
	 */
	String getMqttHost();
	
	/**
	 * Returns the port of the mqtt broker.
	 * 
	 * @return port of the mqtt broker.
	 */
	int getMqttHPortost();

}

