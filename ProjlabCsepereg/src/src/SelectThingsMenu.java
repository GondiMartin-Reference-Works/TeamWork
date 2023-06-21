package src;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * inherits from JFrame.
 * Class that is responsible for displaying the choices the player has, when he invokes a method.
 * @author csizm
 */
public class SelectThingsMenu extends JFrame{


	private static final long serialVersionUID = -3144941765010925027L;
	
	private JButton btOk;
	private GameMenu previous;
	private JTable table;
	@SuppressWarnings("rawtypes")
	private ThingData data;
	
	private JPanel panel;
	
	//private static Thread thread;
	
	//private static Object lock = new Object();
	
	/**
	 * constructor
	 * @param t the thing we want to display informations about
	 * @param info the specifing information we want displayed as thingdata
	 * @param p the previous jframe
	 */
	public SelectThingsMenu(Thing t, String info, GameMenu p){
		synchronized (p.getLock()) {
			previous = p;
			btOk = new JButton("OK");
			table = new JTable();
			
			Virologist player = (Virologist)t;
			//if info is genCode, we display the agents the virologist has learnt as thingdata
			if(info.equals("genCode")) {
				data = new ThingData<Agent>(player.GetGenCodeCollection().GetAgents());
			}
			//if info is Virologists, we display the Things on the player's current field,
			//which are Virologist objects
			else if(info.equals("Virologists")) {
				ArrayList<Virologist> lista = new ArrayList<>();
				for(Thing item: player.GetField().GetThings()) {
					if(item.toString().contains("Virologist"))
						lista.add((Virologist)item);
				}
				data = new ThingData<Virologist>(lista);
			}
			//if info is Crafts, we display the agents the virologist has crafted as thingdata
			else if(info.equals("Crafts")) {
				data = new ThingData<Agent>(player.GetCraftedACollection().GetAgents());
			}
			//if info is Equipments from Field, we display the equipments on the virologist's current field as thingdata
			else if(info.equals("Equipments from Field")) {
				ArrayList<Equipment> lista = new ArrayList<>();
				for(Thing item: player.GetField().GetThings())
					if(item.toString().contains("Equipment"))
						lista.add((Equipment) item);
				data = new ThingData<Equipment>(lista);
			}
			//if info is Equipments from Virologist, we display the equipments the virologist has as thingdata
			else if(info.equals("Equipments from Virologist")) {
				data = new ThingData<Equipment>(player.GetEquipmentCollection().GetEquipments());
			}
			
			table = new JTable(data);
			
			View();
		}
	}
	
	/**
	 * Makes the menu visible.
	 */
	private void View() {
		this.setSize(1120, 1020);
		
		panel = new JPanel(new BorderLayout());
		JScrollPane scrollPanel;
		
		// Table
		table.setRowHeight(150);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		scrollPanel = new JScrollPane(table);
		panel.add(scrollPanel, "Center");
		
		// Button, if it's pressed in a valid row, it will call CallOk method
		btOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				Thing t = null;
				if(row == -1) {
					// Do nothing
				}else {
					t = (Thing) table.getValueAt(row, 0);
				}
				
				CallOk(t);
			}
		});
		panel.add(btOk, "South");
		
		this.add(panel);
		
		this.setVisible(true);
	}
	
	/**
	 * sets the selected item of the previous jframe
	 * @param t thing at a certain row and column of the table
	 */
	public void CallOk(Thing t) {
		synchronized (previous.getLock()) {
			previous.SetSelectedItem(t);
			this.setVisible(false);
			previous.getLock().notify();
		}
	}
}
