package UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import weka.core.Instances;
import Moteur.ConditionRegle;
import Moteur.JeuxDInstanceSimple;
import Solveur.ParseurArff;


public class MainPanel extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JButton b_filechooser;
	private JButton b_launch;
	private JLabel l_file;
	private JTextArea ta_regles;
	
	public MainPanel() {
		b_filechooser = new JButton("Select an arff file");
		b_launch = new JButton("Lancer");
		l_file = new JLabel("./docs/vote.arff");
		ta_regles = new JTextArea();
		
		init();
	}

	private void init() {
		// Initialisation de la fenêtre
		setLayout( new BoxLayout( getContentPane(), BoxLayout.Y_AXIS ) );
		setTitle("Apprentissage règles");
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
			    }
			}
		});

		b_launch.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ParseurArff ra = new ParseurArff();
				Instances data = ra.parse( l_file.getText() );
				JeuxDInstanceSimple vals = new JeuxDInstanceSimple();
				
				vals.setValues( data );

				ArrayList<ConditionRegle> regles = vals.apprendre();
				int nb_regles = regles.size();
				String txt_regles = "";
				for ( int i=0; i<nb_regles; i++ ) {
					txt_regles += "Regle n°"+i+":\n\t"+regles.get(i)+"\n";
				}
				
				ta_regles.setText( txt_regles );
								
			}
		});
		
	    // Mise en place des composants
		add(b_filechooser);
		add(l_file);
		add(b_launch);
		add(ta_regles);
		
	    // Show Panel
	    setVisible(true);
	}
	
	
}
