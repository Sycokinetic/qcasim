package qcasim.display.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import qcasim.Simulator;

public class OptionPanel extends SimPanel
{
    private static final long serialVersionUID = 1L;
    private int curTick;

    private JPanel[] panelList;
    private JLabel curTickLabel;
    private JTextField cycleField;
    private JButton start;
    private JButton stop;
    private JButton restart;
    private JButton reset;

    public OptionPanel()
    {
	this.panelList = new JPanel[6];
	for (int i = 0; i < 6; i++)
	{
	    this.panelList[i] = new JPanel();
	    this.add(this.panelList[i]);
	}

	this.setLayout(new GridLayout(1, 6));
	this.panelList[0].add(new JLabel("Number of Cycles to Run:"));

	this.cycleField = new JTextField(10);
	this.panelList[1].add(this.cycleField);

	this.curTickLabel = new JLabel("Current Tick: " + String.valueOf(this.curTick));
	this.panelList[2].add(this.curTickLabel);

	this.start = new JButton("Start");
	this.start.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent evt)
	    {
		try
		{
		    int n;
		    if (OptionPanel.this.cycleField.getText().equals("")) n = 0;
		    else n = Integer.parseInt(OptionPanel.this.cycleField.getText());
		    Simulator.setTickCount(n);
		    Simulator.start();

		    OptionPanel.this.panelList[3].remove(OptionPanel.this.start);
		    OptionPanel.this.panelList[3].add(OptionPanel.this.stop);
		    OptionPanel.this.panelList[3].repaint();
		}
		catch (NumberFormatException e)
		{
		    Simulator.getDisplay().alert("Please enter the number of cycles to run, or 0 to cycle indefinitely.");
		}
	    }
	});
	this.panelList[3].add(this.start);

	this.stop = new JButton("Stop");
	this.stop.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent evt)
	    {
		Simulator.stop();
		OptionPanel.this.panelList[3].remove(OptionPanel.this.stop);
		OptionPanel.this.panelList[3].add(OptionPanel.this.start);
		OptionPanel.this.panelList[3].repaint();
	    }
	});

	this.restart = new JButton("Restart");
	this.restart.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent evt)
	    {
		OptionPanel.this.stop.doClick();
		Simulator.restart();
		OptionPanel.this.curTick = 0;
		OptionPanel.this.curTickLabel.setText("Current Tick: " + String.valueOf(OptionPanel.this.curTick));
		Simulator.getDisplay().getRenderWindow().reset();
		Simulator.getDisplay().reset();
	    }
	});
	this.panelList[4].add(this.restart);

	this.reset = new JButton("New");
	this.reset.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent evt)
	    {
		OptionPanel.this.stop.doClick();
		Simulator.reset();
		OptionPanel.this.curTick = 0;
		OptionPanel.this.curTickLabel.setText("Current Tick: " + String.valueOf(OptionPanel.this.curTick));
		Simulator.getDisplay().reset();
	    }
	});
	this.panelList[5].add(this.reset);
    }
    
    public void cycle()
    {
	this.curTick++;
	this.curTickLabel.setText("Current Tick: " + String.valueOf(this.curTick));
    }

    @Override
    public void reset()
    {
    }
}
