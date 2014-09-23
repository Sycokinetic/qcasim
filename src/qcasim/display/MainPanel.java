package qcasim.display;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private RenderPanel renderPanel;
    private MenuPanel menuPanel;

    public MainPanel()
    {
	this.renderPanel = new RenderPanel();
	this.menuPanel = new MenuPanel();

	this.setLayout(new BorderLayout());
	this.add(this.renderPanel, BorderLayout.CENTER);
	this.add(this.menuPanel, BorderLayout.SOUTH);
    }

    public void cycle()
    {
	this.renderPanel.repaint();
	this.menuPanel.cycle();
    }
}
