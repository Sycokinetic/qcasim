package qcasim.display.panel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import qcasim.Simulator;
import qcasim.display.DisplayInterface;

public abstract class SimPanel extends JPanel implements DisplayInterface
{
    private static final long serialVersionUID = 1L;
    
    public SimPanel()
    {
	this.setKeyBinding(KeyEvent.VK_ENTER, "START", new AbstractAction()
	{
	    private static final long serialVersionUID = 1L;

	    public void actionPerformed(ActionEvent evt)
	    {
		Simulator.start();
	    }
	});
    }
    
    protected void setKeyBinding(int keyCode, String desc, Action a)
    {
	KeyStroke k = KeyStroke.getKeyStroke(keyCode, 0);
	this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(k, desc);
	this.getActionMap().put(desc, a);
    }
}
