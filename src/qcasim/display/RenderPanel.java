package qcasim.display;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import qcasim.Driver;
import qcasim.cell.Cell;

public class RenderPanel extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	
	for (int i = 0; i < Driver.sim.cellList.length; i++)
	{
	    for (int j = 0; j < Driver.sim.cellList[i].length; j++) {
		if (Driver.sim.cellList[i][j].getCurState()) g.setColor(Color.WHITE);
		else g.setColor(Color.BLACK);
		
		g.fillRect(i * 2, j * 2, 2, 2);
	    }
	}
    }
}
