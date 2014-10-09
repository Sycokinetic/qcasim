package qcasim.display.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import qcasim.Manager;
import qcasim.Simulator;

public class OptionPanel extends Manager<JPanel>
{
    protected int curTick;
    protected JLabel curTickLabel;
    protected JTextField cycleField;
    protected JButton runButton;
    protected JButton initButton;
    protected JButton revertButton;

    protected JPanel[] panelList;

    public OptionPanel()
    {
	this.element = new JPanel();

	this.element.setLayout(new GridLayout(1, 6));

	this.panelList = new JPanel[6];
	for (int i = 0; i < 6; i++)
	{
	    this.panelList[i] = new JPanel();
	    this.element.add(this.panelList[i]);
	}

	this.panelList[0].add(new JLabel("Number of Cycles to Run:"));

	this.cycleField = new JTextField(10);
	this.panelList[1].add(this.cycleField);

	this.curTickLabel = new JLabel("Current Tick: " + String.valueOf(this.curTick));
	this.panelList[2].add(this.curTickLabel);

	this.runButton = new JButton("Start");
	this.runButton.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent evt)
	    {
		if (OptionPanel.this.runButton.getText().equals("Start"))
		{
		    try
		    {
			int n = 0;
			if (!OptionPanel.this.cycleField.getText().equals(""))
			{
			    n = Integer.parseInt(OptionPanel.this.cycleField.getText());
			}
			Simulator.setTickCount(n);
			Simulator.start();

			OptionPanel.this.runButton.setText("Stop");
			OptionPanel.this.element.repaint();
		    }
		    catch (NumberFormatException e)
		    {
			Simulator.getDisplay().alert("Please enter the number of cycles to run, or leave empty to cycle indefinitely.");
		    }
		}
		else if (OptionPanel.this.runButton.getText().equals("Stop"))
		{
		    Simulator.stop();
		    OptionPanel.this.runButton.setText("Start");
		    OptionPanel.this.element.repaint();
		}
	    }
	});
	this.panelList[3].add(this.runButton);

	this.initButton = new JButton("New");
	this.initButton.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent evt)
	    {
		Simulator.stop();
		Simulator.init();
	    }
	});
	this.element.add(this.initButton);

	this.revertButton = new JButton("Revert");
	this.revertButton.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent evt)
	    {
		Simulator.stop();
		Simulator.revert();
	    }
	});
	this.element.add(this.revertButton);
    }

    @Override
    protected void init()
    {
	this.curTick = 0;
	this.curTickLabel.setText("Current Tick: " + String.valueOf(this.curTick));
	this.runButton.setText("Start");
    }

    @Override
    protected void cycle()
    {
	this.curTick++;
	this.curTickLabel.setText("Current Tick: " + String.valueOf(this.curTick));
    }

    @Override
    protected void revert()
    {
	this.curTick = 0;
	this.curTickLabel.setText("Current Tick: " + String.valueOf(this.curTick));
	this.runButton.setText("Start");
    }

    @Override
    protected void stop()
    {
	this.runButton.setText("Start");
    }

    @Override
    protected void start()
    {
	// TODO Auto-generated method stub
	
    }

}
