package UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.MainController;

public class MainPanel extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JButton b_filechooser;
	private JLabel l_file;
	
	private MainController controller;

	public MainPanel() {
		b_filechooser = new JButton("Select an arff file");
		l_file = new JLabel("No file selected");
		controller = MainController.getInstance();
		
		init();
	}

	private void init() {
		// Initialisation de la fenêtre
		setLayout( new BoxLayout( getContentPane(), BoxLayout.Y_AXIS ) );
		setTitle("Système Expert");
	    setSize(600, 400);
	    setLocationRelativeTo(null);  

		// Ouverture d'un selecteur de fichier arff au clique
		b_filechooser.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser( new File(System.getProperty("user.home")) );
				filechooser.setFileFilter( new FileNameExtensionFilter("Arff Files", "arff") );
			    int returnVal = filechooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String absolutePath = filechooser.getSelectedFile().getAbsolutePath();
			       l_file.setText( absolutePath );
			       controller.fileSelected( absolutePath );
			    }
			}
		});
		
	    // Mise en place des composants
		add(b_filechooser);
		add(l_file);
		
	    // Show Panel
	    setVisible(true);
	}
	
	
}
