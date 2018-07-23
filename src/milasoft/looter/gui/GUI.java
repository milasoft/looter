package milasoft.looter.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.stream.IntStream;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.map.Area;

import milasoft.looter.config.Config;
import milasoft.looter.context.ContextProvider;

public class GUI {
	/**
	 * Get an instance of our config class.
	 */
	Config config = Config.getConfig();
	
	/**
	 * Get an instance of MethodContext from our ContextProvider.
	 */
	MethodContext c = ContextProvider.getInstance().getContext();
	
	/**
	 * Declare our components.
	 */
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel startButton;
	private JLabel startButtonLabel;
	private JLabel areaLabel;
	private JPanel areaButton;
	private JLabel areaButtonLabel;
	private JComboBox<Integer> radiusDropdown;
	private JLabel radiusLabel;
	private JLabel coordinatesLabel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JTextField itemInput;
	private JComboBox<BankLocation> bankLocationDropdown;
	private JLabel bankLocationLabel;
	private JLabel itemsLabel;
	private JPanel addButton;
	private JLabel addButtonLabel;
	private DefaultListModel<String> itemListModel;
	private JList<String> itemList;
	private JPanel removeButton;
	private JLabel removeButtonLabel;
	
	/**
	 * Declare an Integer array to populate our radius dropdown.
	 */
	private Integer[] radius;
	
	
	/**
	 * Construct our GUI.
	 */
	public GUI() {
		frame = new JFrame("Milasoft Looter");
		frame.setSize(new Dimension(440, 290));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		createMainPanel();
		addActionListeners();
	}
	
	/**
	 * Shows our GUI.
	 */
	public void show() {
		frame.setVisible(true);
	}
	
	/**
	 * Dispose of our GUI.
	 */
	public void dispose() {
		frame.dispose();
	}
	/**
	 * Adds all of the action listeners for our buttons.
	 */
	public void addActionListeners() {
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/**
				 * If the script is already running, stop the script and change the text on the button.
				 * If the script isn't running, start the script and change the text on the button.
				 */
				if(config.isScriptRunning()) {
					config.setScriptRunning(false);
					startButtonLabel.setText("Start Script");
				} else {
					config.setScriptRunning(true);
					startButtonLabel.setText("Stop Script");
				}
			}
		});
		areaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/**
				 * Generate an area around the player with a radius of our radius dropdown.
				 */
				int radiusLocal = (int)radiusDropdown.getSelectedItem();
				Area a = c.getLocalPlayer().getSurroundingArea(radiusLocal);
				config.setLootArea(a);
				/**
				 * Change our coordinates label to our current area.
				 */
				coordinatesLabel.setForeground(Color.GREEN);
				coordinatesLabel.setText("Yes");				
			}
		});
		bankLocationDropdown.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				/**
				 * Sets our bank location when the dropdown is changed.
				 */
				config.setBankLocation(bankLocationDropdown.getItemAt(bankLocationDropdown.getSelectedIndex()));
			}
		});
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/**
				 * Adds the item into the list of items in our config file and our gui.
				 */
				if(!itemInput.getText().isEmpty()) {
					config.getItems().add(itemInput.getText());
					itemListModel.addElement(itemInput.getText());
					/**
					 * Reset our input box text.
					 */
					itemInput.setText("");
				}
			}
		});
		removeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/**
				 * Removes the selected item from the list.
				 */
				config.getItems().remove(itemList.getSelectedIndex());
				itemListModel.remove(itemList.getSelectedIndex());
			}
		});
	}
	/**
	 * Instantiates the main panel.
	 */
	public void createMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(52, 73, 94));
		mainPanel.setSize(434, 261);
		mainPanel.setBounds(0, 0, 434, 261);
		createLeftPanel();
		mainPanel.add(leftPanel);
		createRightPanel();
		mainPanel.add(rightPanel);
		createStartButton();
		mainPanel.add(startButton);
		frame.add(mainPanel);
	}
	
	/**
	 * Creates our start button.
	 */
	public void createStartButton() {
		startButton = new JPanel();
		startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		startButton.setBackground(new Color(52, 152, 219));
		startButton.setBounds(169, 215, 96, 34);
		startButton.setLayout(null);
		startButtonLabel = new JLabel("Start Script");
		startButtonLabel.setBounds(15, 9, 65, 16);
		startButtonLabel.setForeground(new Color(255, 255, 255));
		startButton.add(startButtonLabel);
	}
	
	public void createLeftPanel() {
		leftPanel = new JPanel();
		leftPanel.setBorder(null);
		leftPanel.setBackground(new Color(52, 73, 94));
		leftPanel.setBounds(12, 12, 200, 200);
		leftPanel.setLayout(null);
		
		areaLabel = new JLabel("Area Set");
		areaLabel.setBounds(74, 0, 52, 16);
		leftPanel.add(areaLabel);
		areaLabel.setForeground(new Color(255, 255, 255));
		
		bankLocationDropdown = new JComboBox<BankLocation>(BankLocation.values());
		bankLocationDropdown.setBounds(21, 163, 157, 25);
		leftPanel.add(bankLocationDropdown);
		bankLocationDropdown.setBackground(new Color(255, 255, 255));
		
		bankLocationLabel = new JLabel("Bank Location");
		bankLocationLabel.setBounds(59, 141, 81, 16);
		leftPanel.add(bankLocationLabel);
		bankLocationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		bankLocationLabel.setForeground(new Color(255, 255, 255));
		
		radius = IntStream.rangeClosed(1, 25).boxed().toArray(Integer[]::new);
		radiusDropdown = new JComboBox<Integer>(radius);
		radiusDropdown.setBounds(74, 60, 52, 25);
		leftPanel.add(radiusDropdown);
		
		radiusLabel = new JLabel("Radius");
		radiusLabel.setBounds(80, 40, 39, 16);
		leftPanel.add(radiusLabel);
		radiusLabel.setForeground(new Color(255, 255, 255));
		
		coordinatesLabel = new JLabel("No");
		coordinatesLabel.setBounds(85, 15, 29, 16);
		leftPanel.add(coordinatesLabel);
		coordinatesLabel.setForeground(Color.RED);
		
		areaButton = new JPanel();
		areaButton.setBounds(65, 98, 70, 33);
		leftPanel.add(areaButton);
		areaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		areaButton.setBackground(new Color(52, 152, 219));
		areaButton.setLayout(null);
		
		areaButtonLabel = new JLabel("Set Area");
		areaButtonLabel.setBounds(10, 8, 50, 16);
		areaButton.add(areaButtonLabel);
		areaButtonLabel.setForeground(new Color(255, 255, 255));
	}
	
	public void createRightPanel() {
		rightPanel = new JPanel();
		rightPanel.setBackground(new Color(52, 73, 94));
		rightPanel.setBounds(224, 12, 198, 200);
		rightPanel.setLayout(null);
		
		itemInput = new JTextField();
		itemInput.setBorder(null);
		itemInput.setBounds(0, 20, 143, 21);
		rightPanel.add(itemInput);
		itemInput.setColumns(10);
		
		itemsLabel = new JLabel("Items to Loot");
		itemsLabel.setForeground(Color.WHITE);
		itemsLabel.setBounds(62, 0, 74, 16);
		rightPanel.add(itemsLabel);
		
		addButton = new JPanel();

		addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addButton.setBackground(new Color(52, 152, 219));
		addButton.setBounds(155, 20, 43, 21);
		rightPanel.add(addButton);
		addButton.setLayout(null);
		
		addButtonLabel = new JLabel("Add");
		addButtonLabel.setForeground(Color.WHITE);
		addButtonLabel.setBounds(10, 2, 22, 16);
		addButton.add(addButtonLabel);
		
		itemListModel = new DefaultListModel<String>();
		itemList = new JList<String>(itemListModel);
		itemList.setBounds(0, 51, 198, 116);
		rightPanel.add(itemList);
		
		removeButton = new JPanel();
		removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		removeButton.setBackground(new Color(52, 152, 219));
		removeButton.setBounds(130, 173, 68, 21);
		rightPanel.add(removeButton);
		removeButton.setLayout(null);
		
		removeButtonLabel = new JLabel("Remove");
		removeButtonLabel.setForeground(Color.WHITE);
		removeButtonLabel.setBounds(11, 2, 46, 16);
		removeButton.add(removeButtonLabel);
	}
}
