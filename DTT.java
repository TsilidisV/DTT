import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class DTT extends JFrame
{
	JTextField path = new JTextField(50);
	JTextField fileName = new JTextField(50);
	JRadioButton r1 = new JRadioButton("Yes");
	JRadioButton r2 = new JRadioButton("No");
	ButtonGroup bg = new ButtonGroup();
	
	
	public void scan(String targetFolder, String outputFile)
	{
		PrintWriter outputStream = null;
		
		File folder = new File(targetFolder);
		File[] listOfFiles = folder.listFiles();
	
		try
		{
			if (r2.isSelected())
			{
				outputStream = new PrintWriter(new FileOutputStream(outputFile),true);
				for (File file : listOfFiles)
				{
					if (file.isFile())
					{
						outputStream.println(file.getName());
					}
				}
				JOptionPane.showMessageDialog(null, "File Saved in: " + path.getText());
			}
			if (r1.isSelected())
			{
				outputStream = new PrintWriter(new FileOutputStream(outputFile),true);
				for (File file : listOfFiles)
				{
						outputStream.println(file.getName());
				}
				JOptionPane.showMessageDialog(null, "File Saved in: " + path.getText());
			}
			if (fileName.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Please choose a folder.");
			}
			if (!r1.isSelected() && !r2.isSelected() && !fileName.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Please choose whether you want to save the names of the folders in your directory.");
			}

		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Error creating file.");
			System.out.println("Error creating " + outputFile);
			System.exit(0);
		}
		
		outputStream.close();
		
	}
	
	public DTT()
	{
		createView();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(670,120);
		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("DTT");
		
	}
	
	private void createView()
	{
		JPanel panelMain = new JPanel();
		getContentPane().add(panelMain);
		
		JPanel panelForm = new JPanel(new GridBagLayout());
		panelMain.add(panelForm);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;	
		c.anchor = GridBagConstraints.LINE_END;
		panelForm.add(new JLabel("Folder path: "), c);
		
		c.gridy++;
		panelForm.add(new JLabel("File name: "), c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		//JTextField path = new JTextField(50);
		panelForm.add(path, c);
		
		c.gridy++;
		//JTextField fileName = new JTextField(50);
		panelForm.add(fileName, c);
		
		c.gridy++;
		c.anchor = GridBagConstraints.LINE_END;
		JButton go = new JButton("Go!");
		go.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e) {
				scan(path.getText(), path.getText() + "/" + fileName.getText() + ".txt");
			}
			
				});
		panelForm.add(go, c);
		
		c.gridx = 2;
		c.gridy = 0;
		JButton openFileChooserN = new JButton("Browse...");
		JFileChooser fc = new JFileChooser();
        openFileChooserN.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                fc.setCurrentDirectory(new java.io.File("user.home"));
                fc.setDialogTitle("Please choose the desired directory");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (fc.showOpenDialog(openFileChooserN) == JFileChooser.APPROVE_OPTION)
                {
            		path.setText(fc.getSelectedFile().getAbsolutePath());
            		fileName.setText(fc.getSelectedFile().getName());
                }
            }
            
        });
        panelForm.add(openFileChooserN, c);
        
		c.gridx = 0;
		c.gridy = 2;
		panelForm.add(new JLabel("Include folder names: "), c);
		
        bg.add(r1);
        bg.add(r2);
		
		c.gridx++;
		c.anchor = GridBagConstraints.LINE_START;
		panelForm.add(r1, c);
		
		c.anchor = GridBagConstraints.CENTER;
		panelForm.add(r2, c);
	}

	
	public static void main(String args[])throws ClassNotFoundException, InstantiationException,
	                                              IllegalAccessException, UnsupportedLookAndFeelException
	{
		SwingUtilities.invokeLater( () -> { new DTT().setVisible(true);});
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
       

	}
}