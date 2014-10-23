package qcasim.display.panel;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import qcasim.Manager;
import qcasim.Simulator;
import qcasim.cell.Cell;

public class RenderPanel extends Manager<JPanel>
{
    public RenderPanel(int cellSize)
    {
	this.element = new CellPanel(cellSize);

	InputMap inputMap = this.element.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
	ActionMap actionMap = this.element.getActionMap();

	inputMap.put(KeyStroke.getKeyStroke("SPACE"), "RUN");
	actionMap.put("RUN", new AbstractAction()
	{
	    private static final long serialVersionUID = 1L;

	    public void actionPerformed(ActionEvent evt)
	    {
		if (Simulator.isRunning()) Simulator.stop();
		else
		{
		    ((OptionPanel) Simulator.getDisplay().getMenuWindow().getChildren().get(0).getChildren().get(0)).runButton.doClick();
		    Simulator.start();
		}
	    }
	});

	inputMap.put(KeyStroke.getKeyStroke("1"), "SET1");
	actionMap.put("SET1", new AbstractAction()
	{
	    private static final long serialVersionUID = 1L;

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		if (!Simulator.isRunning())
		{
		    for (Cell[] row : Simulator.getCellGrid())
		    {
			for (Cell c : row)
			{
			    c.setState(true);
			}
		    }

		    RenderPanel.this.element.repaint();
		}

	    }
	});

	inputMap.put(KeyStroke.getKeyStroke("0"), "SET0");
	actionMap.put("SET0", new AbstractAction()
	{
	    private static final long serialVersionUID = 1L;

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		if (!Simulator.isRunning())
		{
		    for (Cell[] row : Simulator.getCellGrid())
		    {
			for (Cell c : row)
			{
			    c.setState(false);
			}
		    }

		    RenderPanel.this.element.repaint();
		}

	    }
	});

	inputMap.put(KeyStroke.getKeyStroke("N"), "NEW");
	actionMap.put("NEW", new AbstractAction()
	{
	    private static final long serialVersionUID = 1L;

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		if (!Simulator.isRunning())
		{
		    ((OptionPanel) Simulator.getDisplay().getMenuWindow().getChildren().get(0).getChildren().get(0)).initButton.doClick();
		    RenderPanel.this.element.repaint();
		}
	    }
	});

	inputMap.put(KeyStroke.getKeyStroke("R"), "REVERT");
	actionMap.put("REVERT", new AbstractAction()
	{
	    private static final long serialVersionUID = 1L;

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		if (!Simulator.isRunning())
		{
		    ((OptionPanel) Simulator.getDisplay().getMenuWindow().getChildren().get(0).getChildren().get(0)).revertButton.doClick();
		    RenderPanel.this.element.repaint();
		}
	    }
	});

	this.element.addMouseListener(new MouseListener()
	{
	    @Override
	    public void mouseClicked(MouseEvent evt)
	    {
	    }

	    @Override
	    public void mouseEntered(MouseEvent evt)
	    {
	    }

	    @Override
	    public void mouseExited(MouseEvent evt)
	    {
	    }

	    @Override
	    public void mousePressed(MouseEvent evt)
	    {
		if (!Simulator.isRunning())
		{
		    int i = evt.getX() / ((CellPanel) RenderPanel.this.element).cellSize;
		    int j = evt.getY() / ((CellPanel) RenderPanel.this.element).cellSize;
		    if (i < Simulator.getCellGrid().length && j < Simulator.getCellGrid()[i].length) Simulator.getCellGrid()[i][j].toggleState();

		    RenderPanel.this.element.repaint();
		}
	    }

	    @Override
	    public void mouseReleased(MouseEvent evt)
	    {
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
    }

    @Override
    protected void start()
    {
    }

}
