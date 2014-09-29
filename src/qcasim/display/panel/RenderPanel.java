package qcasim.display.panel;

import java.awt.Color;
import java.awt.Graphics;

import qcasim.Simulator;

public class RenderPanel extends SimPanel
{
    private static final long serialVersionUID = 1L;
    
    protected int cellSize;

    public RenderPanel(int n)
    {
	this.cellSize = n;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	
	for (int i = 0; i < Simulator.getCellField().length; i++)
	{
	    for (int j = 0; j < Simulator.getCellField()[i].length; j++) {
		if (Simulator.getCellField()[i][j].getCurState()) g.setColor(Color.WHITE);
		else g.setColor(Color.BLACK);
		
		g.fillRect(i * this.cellSize, j * this.cellSize, this.cellSize, this.cellSize);
	    }
	}
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
}
