package Project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
	public static void main(String arg[]){

		JFrame gui = new JFrame("Change Maker");

		//file menu stuff
		JMenu fileMenu;
		JMenuItem quitItem;
		JCheckBoxMenuItem quartersItem;
		JCheckBoxMenuItem dimesItem;
		JCheckBoxMenuItem nickelsItem;
		JCheckBoxMenuItem penniesItem;
		JMenuBar menus;

		fileMenu = new JMenu("File");
        quitItem = new JMenuItem ("Quit!");
		quartersItem = new JCheckBoxMenuItem ("Suspend Quarters");
        dimesItem = new JCheckBoxMenuItem ("Suspend Dimes");
        nickelsItem = new JCheckBoxMenuItem ("Suspend Nickels");
        penniesItem = new JCheckBoxMenuItem ("Suspend Pennies");

		fileMenu.add(quitItem);
		fileMenu.add(quartersItem);
		fileMenu.add(dimesItem);
		fileMenu.add(nickelsItem);
		fileMenu.add(penniesItem);

		menus = new JMenuBar();
		menus.add(fileMenu);

		//GRID LAYOUT
		//Layout is in a grid, rows/columns
		GridLayout gl = new GridLayout();
		gl.setHgap(25);
		gl.setVgap(25);
		gl.setRows(1);
		gl.setColumns(3);
		gui.setLayout(gl);


		//Panel 1
		ChangeMakerPanel panel1 = new ChangeMakerPanel();
		gui.add(panel1);

		//Panel 2
		ChangeMakerPanel panel2 = new ChangeMakerPanel();
		gui.add(panel2);

		//Panel 3
		ChangeMakerPanel panel3 = new ChangeMakerPanel();
		gui.add(panel3);

		//gui.setSize(1200,400); //turn off .pack if using this. Looks a bit funny.
		gui.setJMenuBar(menus);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.pack();
		gui.setVisible(true);

		//action listener for the checkbox items of the menu. Easier to do this way than passing things to the
		//changemaker panel
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton checkBoxItem = (AbstractButton) e.getSource();
				if (checkBoxItem == quartersItem){
					ChangeMaker.setQuartersAvail(!checkBoxItem.isSelected());
				}
				if (checkBoxItem == dimesItem) {
					ChangeMaker.setDimesAvail(!checkBoxItem.isSelected());
				}
				if (checkBoxItem == nickelsItem){
					ChangeMaker.setNickelsAvail(!checkBoxItem.isSelected());
				}
				if (checkBoxItem == penniesItem) {
					ChangeMaker.setPenniesAvail(!checkBoxItem.isSelected());
				}
				if (checkBoxItem == quitItem){
					gui.dispose();
				}
			}
		};

		//Not sure how to add the ActionListener with 'this' in this context, so I made a new independent object above
		//Probably need to implement actionlistner interface. Test some other time.
		quartersItem.addActionListener(actionListener);
		dimesItem.addActionListener(actionListener);
		nickelsItem.addActionListener(actionListener);
		penniesItem.addActionListener(actionListener);
		quitItem.addActionListener(actionListener);

	}
}
