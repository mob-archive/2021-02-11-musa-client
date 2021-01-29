package musa.app;

import static java.awt.BorderLayout.CENTER;
import static java.lang.Boolean.FALSE;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import musa.fwk.TitleProvider;
import musa.stock.StockContext;
import musa.stock.StockPanel;

public class MusaClient extends JPanel {

	private static final List<String> avail = Arrays.asList(
			"advantage advertisement advice agenda apology authorization bill brand budget commission comparison competition competitor confirmation costs creditor customer deadline debt debtor decision decrease deficit delivery department description difference disadvantage distribution employee employer enquiry environment equipment estimate experience explanation facilities factory feedback goal goods growth guarantee improvement increase industry instructions interest inventory invoice knowledge limit loss margin market message mistake objective offer opinion option order output payment penalty permission possibility product production profit promotion purchase reduction refund reminder repairs report responsibility result retailer rise risk salary sales schedule share signature stock success suggestion supply support target transport turnover wholesaler"
					.split("\\s"));
	private static final List<String> words = new ArrayList<>();

	private static final long serialVersionUID = -7743313663988155973L;

	public MusaClient() {
		super(new GridLayout(1, 1));
		add(tabbedPane());
	}

	private JTabbedPane tabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		for (int i = 0; i < 32; i++) {
			JComponent panel = i == 3 ? new StockPanel(stockContext()) : makeTextPanel();
			String label = panel instanceof TitleProvider ? ((TitleProvider) panel).getTitle() : label();
			tabbedPane.addTab(label, panel);
		}
		return tabbedPane;
	}

	private StockContext stockContext() {
		return new StockContext() {

			@Override
			public int getRingLedCount(int ring) {
				return 16;
			}

			@Override
			public boolean getDirectionForRing(int ring) {
				return ring == 0 ? false : true;
			}

			@Override
			public String getMqttHost() {
				return "localhost";
			}

			@Override
			public int getMqttHPortost() {
				return 1833;
			}};
	}

	private String label() {
		SecureRandom random = new SecureRandom();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < random.nextInt(2) + 1; i++) {
			if (sb.length() > 0) {
				sb.append(" ");
			}
			sb.append(word(random.nextInt(getWords().size())));
		}
		return sb.toString();
	}

	public static List<String> getWords() {
		if (words.isEmpty()) {
			words.addAll(avail);
		}
		return words;
	}

	private String word(int num) {
		String word = getWords().remove(num);
		return Character.toUpperCase(word.charAt(0)) + word.substring(1);
	}

	protected JComponent makeTextPanel() {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel("some content");
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("MUSA V42.0");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.add(new MusaClient(), CENTER);
		frame.pack();
		frame.setSize(new Dimension(800, 600));
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UIManager.put("swing.boldMetal", FALSE);
				createAndShowGUI();
			}
		});
	}
}
