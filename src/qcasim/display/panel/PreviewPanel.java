package qcasim.display.panel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import qcasim.Manager;
import qcasim.Simulator;

public class PreviewPanel extends Manager<JPanel>
{   
    public PreviewPanel()
    {
	this.element = new JPanel() {
	    private static final long serialVersionUID = 1L;
	    protected int cellSize = 1;
	    
	    @Override
	    protected void paintComponent(Graphics g)
	    {
		super.paintComponent(g);

		for (int i = 0; i < Simulator.getInitGrid().length; i++)
		{
		    for (int j = 0; j < Simulator.getInitGrid()[i].length; j++)
		    {
			if (Simulator.getInitGrid()[i][j]) g.setColor(Color.WHITE);
			else g.setColor(Color.BLACK);

			g.fillRect(i * this.cellSize, j * this.cellSize, this.cellSize, this.cellSize);
		    }
		}
	    }
	};
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
