package unidad.dos.aprendizaje.no.supervisado.cluster;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 34567542342346L;
	
	private Kmeans kMeans;
	private JPanel mainPanel;
	private JPanel headerPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel footerPanel;
	private Graphic graphic;
	private JPanel buttonsPanel;
	private JButton iterateButton;
	private JButton updateColorsButton;
	private String[] scenarioList = {"Escenario 1", "Escenario 2", 
			"Escenario 3", "Escenario 4", "Escenario 5" };
	private JComboBox<String> scenariosComboBox;
	
	private final int HEADER_PANEL_HEIGHT = 60;
	private final int LEFT_PANEL_WIDTH = 20;
	private final int RIGHT_PANEL_WIDTH = 200;
	private final int FOOTER_PANEL_HEIGHT = 20;
	

	public MainWindow(){
	
		this.setTitle("K-means");
		int fullWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int fullHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		// Dimension maxDimension = new Dimension(fullWidth, fullHeight);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.setLocation((fullWidth - this.getWidth()) / 2, (fullHeight - this.getHeight()) / 2);
		
		this.initializeComponents();
		this.scenariosComboBox.setSelectedIndex(0);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void initializeComponents(){
		this.setMainPanel();
		this.setHeaderPanel();
		this.setLeftPanel();
		this.setRightPanel();
		this.setFooterPanel();
		this.setButtons();
	}
	
	private void setMainPanel(){
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(null);
		this.add(this.mainPanel, BorderLayout.CENTER);
		
		this.setgraphic();
	}
	
	private void setHeaderPanel(){
		this.headerPanel = new JPanel();
		this.headerPanel.setLayout(null);
		this.headerPanel.setPreferredSize(new Dimension(this.headerPanel.getWidth(), HEADER_PANEL_HEIGHT));
		this.headerPanel.setBackground(Color.LIGHT_GRAY);
		this.add(this.headerPanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("Simulación del algoritmo K-means");
		titleLabel.setBounds(20, 5, 250, 30);
		this.headerPanel.add(titleLabel);
		JLabel authorsLabel = new JLabel("MMED17027");
		authorsLabel.setBounds(20, 25, 250, 30);
		this.headerPanel.add(authorsLabel);
	}
	
	private void setLeftPanel(){
		this.leftPanel = new JPanel();
		this.leftPanel.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, this.leftPanel.getHeight()));
		this.add(this.leftPanel, BorderLayout.WEST);
	}
	
	private void setRightPanel(){
		this.rightPanel = new JPanel();
		this.rightPanel.setLayout(null);
		this.rightPanel.setPreferredSize(new Dimension(RIGHT_PANEL_WIDTH, this.rightPanel.getHeight()));
		this.add(this.rightPanel, BorderLayout.EAST);
	}
	
	private void setFooterPanel(){
		this.footerPanel = new JPanel();
		this.footerPanel.setPreferredSize(new Dimension(this.footerPanel.getWidth(), FOOTER_PANEL_HEIGHT));
		this.footerPanel.setBackground(Color.LIGHT_GRAY);
		this.add(this.footerPanel, BorderLayout.SOUTH);
	}
	
	private void setgraphic(){
		this.graphic = new Graphic();
		this.graphic.setBounds(0, 20, 1100, 580);
		this.mainPanel.add(this.graphic);
	}
	
	public void setButtons()
	{
		this.buttonsPanel = new JPanel();
		this.buttonsPanel.setLayout(new GridLayout(5, 1));
		
		this.scenariosComboBox = new JComboBox(scenarioList);
		
		this.scenariosComboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				graphic.removeAll();
				graphic.repaint();
				
				switch(scenariosComboBox.getSelectedIndex()){
				case 0 : trySampleScenario1();
					break;
					
				case 1 : trySampleScenario2();
					break;
					
				case 2 : trySampleScenario3();
					break;
					
				case 3 : trySampleScenario4();
					break;
				
				case 4 : trySampleScenario5();
				break;
					
				default : trySampleScenario1();
				}
			}
		});
		
		//this.scenariosComboBox.setBounds(this.graphic.getWidth() + 20, 120, 120, 30);
		this.buttonsPanel.add(this.scenariosComboBox);
		
		this.iterateButton = new JButton("Iteración");
		//this.iterateButton.setBounds(this.graphic.getWidth() + 20, 10, 120, 30);
		this.iterateButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				kMeans.iterate();
				graphic.repaint();
			}
		});
		this.buttonsPanel.add(this.iterateButton);
		
		this.updateColorsButton = new JButton("Actualizar");
		//this.updateColorsButton.setBounds(this.graphic.getWidth() + 20, 45, 120, 30);
		this.updateColorsButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				kMeans.updateColors();
				graphic.repaint();
			}
		});
		this.buttonsPanel.add(this.updateColorsButton);
		
		this.buttonsPanel.setBounds(0, 20, RIGHT_PANEL_WIDTH - 30, 120);
		this.rightPanel.add(this.buttonsPanel);
	}
	
	public void trySampleScenario1(){
		Cluster redCluster = ClusterGenerator.generate(60, Color.RED, 10, 30, 15, 30);
		Cluster greenCluster = ClusterGenerator.generate(70, Color.GREEN, 15, 40, 15, 25);
		Cluster blueCluster = ClusterGenerator.generate(50, Color.BLUE, 25, 45, 15, 35);
		Cluster magentaCluster = ClusterGenerator.generate(40, Color.MAGENTA, 10, 25, 25, 40);
		
		this.graphic.AddCluster(redCluster);
		this.graphic.AddCluster(greenCluster);
		this.graphic.AddCluster(blueCluster);
		this.graphic.AddCluster(magentaCluster);
		
		Cluster[] clusters = { redCluster, greenCluster, blueCluster, magentaCluster };
		this.kMeans = new Kmeans(clusters);
	}
	
	public void trySampleScenario2(){
		Cluster redCluster = ClusterGenerator.generate(90, Color.RED, 5, 40, 5, 30);
		Cluster greenCluster = ClusterGenerator.generate(120, Color.GREEN, 5, 30, 15, 45);
		Cluster blueCluster = ClusterGenerator.generate(150, Color.BLUE, 25, 45, 15, 35);
		
		this.graphic.AddCluster(redCluster);
		this.graphic.AddCluster(greenCluster);
		this.graphic.AddCluster(blueCluster);
		
		Cluster[] clusters = { redCluster, greenCluster, blueCluster };
		this.kMeans = new Kmeans(clusters);
	}
	
	public void trySampleScenario3(){
		Cluster redCluster = ClusterGenerator.generate(150, Color.RED, 5, 45, 0, 40);
		Cluster blueCluster = ClusterGenerator.generate(150, Color.BLUE, 5, 45, 15, 50);
		
		this.graphic.AddCluster(redCluster);
		this.graphic.AddCluster(blueCluster);
		
		Cluster[] clusters = { redCluster, blueCluster };
		this.kMeans = new Kmeans(clusters);
	}
	
	public void trySampleScenario4(){
		Cluster redCluster = ClusterGenerator.generate(150, Color.RED, 5, 45, 0, 50);
		Cluster blueCluster = ClusterGenerator.generate(150, Color.BLUE, 5, 45, 0, 50);
		Cluster greenCluster = ClusterGenerator.generate(150, Color.GREEN, 5, 45, 0, 40);
		Cluster magentaCluster = ClusterGenerator.generate(150, Color.MAGENTA, 5, 45, 5, 50);
		
		this.graphic.AddCluster(redCluster);
		this.graphic.AddCluster(blueCluster);
		this.graphic.AddCluster(greenCluster);
		this.graphic.AddCluster(magentaCluster);
		
		Cluster[] clusters = { redCluster, blueCluster, greenCluster, magentaCluster };
		this.kMeans = new Kmeans(clusters);
	}
	
	public void trySampleScenario5(){
		Cluster redCluster = ClusterGenerator.generate(60, Color.RED, 10, 30, 15, 30);
		Cluster pink = ClusterGenerator.generate(40, Color.PINK, 12, 55, 25, 40);
		Cluster greenCluster = ClusterGenerator.generate(165, Color.GREEN, 15, 40, 20, 41);
		Cluster blueCluster = ClusterGenerator.generate(50, Color.BLUE, 25, 45, 15, 35);
		Cluster magentaCluster = ClusterGenerator.generate(40, Color.MAGENTA, 10, 25, 25, 40);
		
		this.graphic.AddCluster(redCluster);
		this.graphic.AddCluster(greenCluster);
		this.graphic.AddCluster(blueCluster);
		this.graphic.AddCluster(magentaCluster);
		this.graphic.AddCluster(pink);
		
		Cluster[] clusters = { redCluster, greenCluster, blueCluster, magentaCluster, pink };
		this.kMeans = new Kmeans(clusters);
	}
}