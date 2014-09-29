package qcasim.display.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import qcasim.Simulator;
import qcasim.display.panel.RenderPanel;

public class RenderWindow extends SimWindow
{
    private static final long serialVersionUID = 1L;

    protected RenderPanel renderPanel;
    private int width = 1500;
    private int height = width / 16 * 9;

    public RenderWindow()
    {
	this.renderPanel = new RenderPanel(2);

	this.setContentPane(this.renderPanel);
	this.setLocationRelativeTo(null);
	this.setTitle("QCA Simuator: Render Window");

	this.setUndecorated(false);
	this.setSize(width, height);
	this.setVisible(false);

	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	this.addWindowListener(new WindowListener()
	{
	    @Override
	    public void windowActivated(WindowEvent arg0)
	    {
	    }

	    @Override
	    public void windowClosed(WindowEvent arg0)
	    {
	    }

	    @Override
	    public void windowClosing(WindowEvent arg0)
	    {
		Simulator.stop();
		RenderWindow.this.setVisible(false);
	    }

	    @Override
	    public void windowDeactivated(WindowEvent arg0)
	    {
	    }

	    @Override
	    public void windowDeiconified(WindowEvent arg0)
	    {
	    }

	    @Override
	    public void windowIconified(WindowEvent arg0)
	    {
	    }

	    @Override
	    public void windowOpened(WindowEvent arg0)
	    {
	    }
	});
    }

    public void cycle()
    {
	this.renderPanel.cycle();
    }
    
    public void reset()
    {
	this.repaint();
	this.renderPanel.repaint();
    }
}
