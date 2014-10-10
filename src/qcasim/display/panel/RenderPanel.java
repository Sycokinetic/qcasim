package qcasim.display.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import qcasim.Manager;
import qcasim.Simulator;

public class RenderPanel extends Manager<JPanel>
{
    public RenderPanel()
    {
	this.element = new JPanel()
	{
	    private static final long serialVersionUID = 1L;
	    protected int cellSize = 2;

	    @Override
	    protected void paintComponent(Graphics g)
	    {
		super.paintComponent(g);

		for (int i = 0; i < Simulator.getCellGrid().length; i++)
		{
		    for (int j = 0; j < Simulator.getCellGrid()[i].length; j++)
		    {
			if (Simulator.getCellGrid()[i][j].getCurState()) g.setColor(Color.WHITE);
			else g.setColor(Color.BLACK);

			g.fillRect(i * this.cellSize, j * this.cellSize, this.cellSize, this.cellSize);
		    }
		}
	    }
	};

	InputMap inputMap = this.element.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
	ActionMap actionMap = this.element.getActionMap();

	inputMap.put(KeyStroke.getKeyStroke("SPACE"), "TEST");
	actionMap.put("TEST", new AbstractAction()
	{
	    private static final long serialVersionUID = 1L;

	    public void actionPerformed(ActionEvent evt)
	    {
		if (Simulator.isRunning()) Simulator.stop();
		else Simulator.start();
	    }
	});
    }

    @Override
    protected void init()
    {
	this.element.repaint();
    }

    @Override
    protected void cycle()
    {
	this.element.repaint();
    }

    @Override
    protected void revert()
    {
	this.element.repaint();
    }

    @Override
    protected void stop()
    {
	// TODO Auto-generated method stub

    }

    @Override
    protected void start()
    {
	// TODO Auto-generated method stub

    }

}
