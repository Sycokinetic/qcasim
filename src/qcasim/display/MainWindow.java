package qcasim.display;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class MainWindow extends JFrame
{
    private static final long serialVersionUID = 1L;

    private int width = 1500;
    private int height = width / 16 * 9;
    private Rectangle winSize;

    private MainPanel mainPanel;

    public MainWindow()
    {
	this.mainPanel = new MainPanel();

	this.setResizable(false);

	this.setContentPane(mainPanel);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setTitle("QCA Simuator");

	this.winSize = new Rectangle(width, height);
	this.setUndecorated(false);
	this.setSize(width, height);
	this.setVisible(true);
    }

    public void cycle()
    {
	this.mainPanel.cycle();
    }
}
