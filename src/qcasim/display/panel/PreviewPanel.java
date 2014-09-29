package qcasim.display.panel;

import java.awt.Color;
import java.awt.Graphics;

import qcasim.Simulator;
import qcasim.display.panel.SimPanel;

public class PreviewPanel extends SimPanel
{
    private static final long serialVersionUID = 1L;

    protected int cellSize;

    public PreviewPanel(int n)
    {
	this.cellSize = n;
    }

    @Override
    public void cycle()
    {
	this.repaint();
    }

    @Override
    public void reset()
    {
	this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
	super.paintComponent(g);

	for (int i = 0; i < Simulator.getInitField().length; i++)
	{
	    for (int j = 0; j < Simulator.getInitField()[i].length; j++)
	    {
		if (Simulator.getInitField()[i][j]) g.setColor(Color.WHITE);
		else g.setColor(Color.BLACK);

		g.fillRect(i * this.cellSize, j * this.cellSize, this.cellSize, this.cellSize);
	    }
	}
    }
}
