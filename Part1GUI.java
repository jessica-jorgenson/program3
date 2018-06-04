package pro3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class Part1GUI {

	private static JFrame frame;
	private JTextField valueToAdd;
	private static JTable table;
	private static myHashTable hash;
	private static JScrollPane scrollPane;
	static boolean start = true;
	static String[] columnNames = { "Index", "Value" };
	static Object[][] data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		hash = new myHashTable(10);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Part1GUI window = new Part1GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Part1GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 878, 682);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		valueToAdd = new JTextField();
		valueToAdd.setBounds(269, 49, 316, 26);
		frame.getContentPane().add(valueToAdd);
		valueToAdd.setColumns(10);

		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int value = Integer.parseInt(valueToAdd.getText());
				HashNode node = hash.add(value);
				if (node == null) {
					JOptionPane.showMessageDialog(frame, "Unable to insert value into hashtable");
				}
				if (start) {
					start = false;
				}
				if (needsUpdating()) {
					updateTable();
				} else if (node != null) {
					data[node.key][1] = node.value;
				}
				table.repaint();
			}
		});
		addButton.setBounds(269, 91, 115, 29);
		frame.getContentPane().add(addButton);

		JButton delButton = new JButton("Delete");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int value = Integer.parseInt(valueToAdd.getText());
				int ind = hash.delete(value);
				if (ind == -1) {
					JOptionPane.showMessageDialog(frame, "Value not found");
				} else {
					data[ind][1] = null;
					table.repaint();
				}

			}

		});
		delButton.setBounds(470, 93, 115, 29);
		frame.getContentPane().add(delButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(115, 188, 638, 395);
		frame.getContentPane().add(scrollPane);

		data = makeTable();
		table = new JTable(data, columnNames);
		scrollPane.setViewportView(table);
		table.setRowHeight(40);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));

		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		table.setFocusable(false);

	}

	public static Object[][] makeTable() {
		data = new Object[myHashTable.max][2];

		for (int i = 0; i < myHashTable.max; i++) {
			data[i][0] = i;
			if (!start) {
				HashNode node = hash.leArray.get(i);
				if (node != null) {
					data[i][1] = node.value;
				} else {
					data[i][1] = null;
				}
			}
		}

		return data;
	}

	public static void updateTable() {
		JOptionPane.showMessageDialog(frame, "Fill ratio reached, updating table size and rehashing...");
		hash.size = 0;
		myHashTable.max *= 2;
		ArrayList<HashNode> tempTabl = new ArrayList<HashNode>(myHashTable.max);
		for (int i = 0; i < myHashTable.max; i++) {
			tempTabl.add(null);
		}
		for (HashNode node : hash.leArray) {
			if (node != null) {
				int leData = node.value;
				hash.add(tempTabl, leData);
			}
		}
		hash.leArray = tempTabl;

		table = new JTable(makeTable(), columnNames);
		scrollPane.setViewportView(table);
		table.setRowHeight(40);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));

		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		table.setFocusable(false);
	}

	public boolean needsUpdating() {
		float check = (float) hash.size / myHashTable.max;
		if (check == hash.percent) {
			return true;
		}
		return false;
	}
}
