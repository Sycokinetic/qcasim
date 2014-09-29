package qcasim.display.window;

import qcasim.display.panel.MenuPanel;

public class MenuWindow extends SimWindow
{
    private static final long serialVersionUID = 1L;

    private int width = 900;
    private int height = width / 16 * 9;

    private MenuPanel menuPanel;

    public MenuWindow()
    {
	this.menuPanel = new MenuPanel();

	this.setContentPane(this.menuPanel);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setTitle("QCA Simuator");

	this.setUndecorated(false);
	this.setSize(width, height);
	this.setVisible(true);
    }

    public void cycle()
    {
	this.menuPanel.cycle();
    }

    @Override
    public void reset()
    {
	this.menuPanel.reset();
    }
}
