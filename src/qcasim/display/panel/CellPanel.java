package qcasim.display.panel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import qcasim.Simulator;

public class CellPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    protected int cellSize = 4;

    public CellPanel(int cellSize)
    {
	this.cellSize = cellSize;
    }
    
    public int getCellSize()
    {
	return this.cellSize;
    }
    
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
}
