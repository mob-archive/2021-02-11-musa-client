package musa.fwk;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class AbstractPanel extends JPanel implements ChangeListener, ActionListener {

	private static final long serialVersionUID = -7711620983909025162L;

	public AbstractPanel() {
		super(false);
	}

	@Override
	public Component add(Component comp) {
		addListener(comp);
		return super.add(comp);
	}

	@Override
	public Component add(Component comp, int index) {
		addListener(comp);
		return super.add(comp, index);
	}

	@Override
	public void add(Component comp, Object constraints) {
		addListener(comp);
		super.add(comp, constraints);
	}

	@Override
	public void add(Component comp, Object constraints, int index) {
		addListener(comp);
		super.add(comp, constraints, index);
	}

	@Override
	public Component add(String name, Component comp) {
		addListener(comp);
		return super.add(name, comp);
	}

	private void addListener(Component comp) {
		if (comp instanceof JSlider) {
			((JSlider) comp).addChangeListener(this);
		}
		if (comp instanceof JButton) {
			((JButton) comp).addActionListener(this);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		stateChanged(e.getSource());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		stateChanged(e.getSource());
	}

	protected abstract void stateChanged(Object source);

	protected void persist(Object[] objects) {
		System.out.println("Persisting " + Arrays.toString(objects));
	}

}
