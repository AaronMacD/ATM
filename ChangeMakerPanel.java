package Project1;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class ChangeMakerPanel extends JPanel implements ActionListener {

    private JLabel titleLabel = new JLabel("Change Maker", JLabel.CENTER);
    private JLabel amountLabel = new JLabel("Amount:");
    private JLabel transferLabel = new JLabel("Transfer Amount: ");
    private JLabel quartersLabel = new JLabel("Quarters: ");
    private JLabel dimesLabel = new JLabel("Dimes: ");
    private JLabel nickelsLabel = new JLabel("Nickels: ");
    private JLabel penniesLabel = new JLabel("Pennies: ");
    private JLabel amountValueLabel = new JLabel();
    private JLabel errorValueLabel = new JLabel();
    private JLabel quartersValueLabel = new JLabel();
    private JLabel dimesValueLabel = new JLabel();
    private JLabel nickelsValueLabel = new JLabel();
    private JLabel penniesValueLabel = new JLabel();

    private JButton loadButton = new JButton("Load");
    private JButton takeButton = new JButton("Take Out!");
    private JButton loadFileButton = new JButton("Load from File");
    private JButton saveFileButton = new JButton("Save to File");

    private JTextField amountEntryField = new JTextField(10);

    private Border borderLines = BorderFactory.createLineBorder(Color.black);

    private ChangeMaker changeMaker = new ChangeMaker(1000.00);

    GridBagConstraints c = new GridBagConstraints(); //box of black magic right here. Even java is like "good luck bitch"
    public ChangeMakerPanel(){
        super(); //more black magic. Just makes UI work somehow.

        this.setLayout(new GridBagLayout());
        //this.setPreferredSize(new Dimension(400, 300));
        this.setBorder(borderLines);

        /* width = 2, height = 9
              0          1
        0 Title--------------
        1 AmntLabel   AmntAct
        2 TrsfLabel   TrsfAct
        3 errorMessage-------
        4 LoadBtn     TakeBtn
        5 QrtLabel    QrtAct
        6 DmeLabel    DmeAct
        7 NckLabel    NckAct
        8 PenLabel    PenAct
        9 LdFlBtn     SvFlBt
         */

        titleLabel.setForeground(Color.blue);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        this.add(titleLabel, c);

        c.insets = new Insets(10,0,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.add(amountLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        this.add(amountValueLabel, c);
        setAmountEntryLabel(1000.00);


        c.gridx = 0;
        c.gridy = 2;
        this.add(transferLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        this.add(amountEntryField, c);
        amountEntryField.setText("0.00");

        c.insets = new Insets(10,0,10,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;
        this.add(errorValueLabel, c);
        errorValueLabel.setText(" "); //if left blank, resizes whole application when text added.

        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0,0,0,0);
        c.weightx = 0.5; //magic number that just... does stuff?
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        loadButton.setPreferredSize(new Dimension(150, 20));
        this.add(loadButton, c);
        this.loadButton.addActionListener(this);

        c.gridx = 1;
        c.gridy = 4;
        takeButton.setPreferredSize(new Dimension(150, 20));
        this.add(takeButton, c);
        this.takeButton.addActionListener(this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        this.add(quartersLabel, c);

        c.gridx = 1;
        c.gridy = 5;
        this.add(quartersValueLabel, c);

        c.gridx = 0;
        c.gridy = 6;
        this.add(dimesLabel, c);

        c.gridx = 1;
        c.gridy = 6;
        this.add(dimesValueLabel, c);

        c.gridx = 0;
        c.gridy = 7;
        this.add(nickelsLabel, c);

        c.gridx = 1;
        c.gridy = 7;
        this.add(nickelsValueLabel, c);

        c.gridx = 0;
        c.gridy = 8;
        this.add(penniesLabel, c);

        c.gridx = 1;
        c.gridy = 8;
        this.add(penniesValueLabel, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 9;
        loadFileButton.setPreferredSize(new Dimension(150, 20));
        this.add(loadFileButton, c);
        this.loadFileButton.addActionListener(this);

        c.gridx = 1;
        c.gridy = 9;
        saveFileButton.setPreferredSize(new Dimension(150, 20));
        this.add(saveFileButton, c);
        this.saveFileButton.addActionListener(this);

    }
    private void setAmountEntryLabel(double amount){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String entry = formatter.format(amount);
        amountValueLabel.setText(entry);
    }


    public void actionPerformed(ActionEvent event){
        JComponent buttonPressed = (JComponent) event.getSource();
        if (buttonPressed == loadButton){
            double newAmount = Double.parseDouble(amountEntryField.getText());
            try{
                changeMaker.loadMachine(newAmount);
                errorValueLabel.setText(amountEntryField.getText() + " Added");
                setAmountEntryLabel(changeMaker.getAmount());
            }
            catch (Exception e){
                errorValueLabel.setText(e.getMessage());
            }
        }
        if (buttonPressed == takeButton){
            double newAmount = Double.parseDouble(amountEntryField.getText());
            try{
                ChangeBag cb = changeMaker.takeOut(newAmount);
                errorValueLabel.setText(amountEntryField.getText() + " removed and distributed below");
                quartersValueLabel.setText(String.valueOf(cb.getQuarters()));
                dimesValueLabel.setText(String.valueOf(cb.getDimes()));
                nickelsValueLabel.setText(String.valueOf(cb.getNickels()));
                penniesValueLabel.setText(String.valueOf(cb.getPennies()));
                setAmountEntryLabel(changeMaker.getAmount());
            }
            catch (Exception e){
                errorValueLabel.setText(e.getMessage());
            }

        }
        //JFileChooser is fancy. Way better than opening a new window
        if (buttonPressed == loadFileButton){
            JFileChooser fc = new JFileChooser("./");
            int value = fc.showOpenDialog(null);
            if (value == JFileChooser.APPROVE_OPTION){
                String fileName = fc.getSelectedFile().getName();
                try {
                    changeMaker.load(fileName);
                    errorValueLabel.setText(fileName + " loaded.");
                    setAmountEntryLabel(changeMaker.getAmount());
                }
                catch (Exception e){ //file name has to exist
                    errorValueLabel.setText(e.getMessage());
                }
            }
        }
        if (buttonPressed == saveFileButton){
            JFileChooser fc = new JFileChooser("./");
            int value = fc.showSaveDialog(null);
            if (value == JFileChooser.APPROVE_OPTION){
                String fileName = fc.getSelectedFile().getName();
                try {
                    changeMaker.save(fileName);
                    errorValueLabel.setText(fileName + " saved.");
                }
                catch (Exception e){
                    errorValueLabel.setText(e.getMessage());
                }
            }
        }
    }
}
