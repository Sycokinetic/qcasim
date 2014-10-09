package qcasim.display.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import qcasim.Manager;
import qcasim.Simulator;
import qcasim.display.panel.RenderPanel;

public class RenderWindow extends Manager<JFrame>
{
    protected int width = 1500;
    protected int height = width / 16 * 9;
    protected boolean mayOpen = false;

    public RenderWindow()
    {
	this.element = new JFrame();
	this.children.add(new RenderPanel());

	this.element.setTitle("QCA Simuator: Render Window");
	this.element.setSize(width, height);
	this.element.setLocationRelativeTo(null);
	
	this.element.setContentPane((JPanel) this.children.get(0).getElement());
	this.element.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	this.element.addWindowListener(new WindowListener()
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
		RenderWindow.this.element.setVisible(false);
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
    
    @Override
    public void init()
    {
	this.mayOpen = false;
    }

    @Override
    public void cycle()
    {
	if (!this.element.isVisible() && this.mayOpen) this.element.setVisible(true);
    }

    @Override
    public void revert()
    {
	this.mayOpen = false;
    }

    @Override
    protected void stop()
    {
	this.mayOpen = false;
    }

    @Override
    protected void start()
    {
	this.mayOpen = true;
    }
}
