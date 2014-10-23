package qcasim.display.panel;

import java.awt.Component;
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
    protected JTextField ruleField;
    protected JLabel curTickLabel;
    protected JTextField cycleField;
    protected JButton drawButton;
    protected JButton runButton;
    protected JButton initButton;
    protected JButton revertButton;

    protected int menuLength = 18;
    protected JPanel[] panelList = new JPanel[this.menuLength];
    protected int curIndex = 0;

    public OptionPanel()
    {
	this.element = new JPanel();

	this.element.setLayout(new GridLayout(this.menuLength / 6, 6));

	for (int i = 0; i < this.panelList.length; i++)
	{
	    this.panelList[i] = new JPanel();
	    this.element.add(this.panelList[i]);
	}

	this.addPanel(new JLabel("Rule:"));
	this.ruleField = new JTextField(10);
	this.ruleField.setText("B3/S23");
	this.addPanel(this.ruleField);

	this.addPanel(new JLabel("Number of Cycles:"));

	this.cycleField = new JTextField(10);
	this.addPanel(this.cycleField);

	this.curIndex = 6;
	this.drawButton = new JButton("Draw");
	this.drawButton.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent arg0)
	    {
		Simulator.getDisplay().getRenderWindow().getElement().setVisible(true);
	    }
	});
	this.addPanel(this.drawButton);

	this.curIndex = 12;
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
			Simulator.setRule(OptionPanel.this.ruleField.getText());
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
	this.addPanel(this.runButton);

	this.initButton = new JButton("New");
	this.initButton.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent evt)
	    {
		Simulator.stop();
		Simulator.init();
	    }
	});
	this.addPanel(this.initButton);

	this.revertButton = new JButton("Revert");
	this.revertButton.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent evt)
	    {
		Simulator.stop();
		Simulator.revert();
	    }
	});
	this.addPanel(this.revertButton);

	this.curTickLabel = new JLabel("Current Tick: " + String.valueOf(this.curTick));
	this.addPanel(this.curTickLabel);
    }

    protected void addPanel(Component p)
    {
	if (this.curIndex < this.panelList.length)
	{
	    this.panelList[this.curIndex].add(p);
	    this.curIndex++;
	}
	else
	{
	    throw new IndexOutOfBoundsException();
	}
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
