import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// create a class for panel which differentiate Question1:A
class Achoice extends JPanel implements ActionListener{
	
	// initialize all the variable
	JLabel l1,l2,l3,l4;
	JTextField textField;
	JTextArea textarea, textarea1;
	JButton button1,button2,button3,choose;
	Font font;
	JFileChooser chooser;
	
	String path;
	
	//create a constructor 
	public Achoice() {
		// TODO Auto-generated constructor stub
		
		font = new Font(Font.SANS_SERIF, Font.BOLD, 12);
		
		l1 = new JLabel("Enter the number of N :");
		
		setLayout(null);
		
		l1.setBounds(15, 25, 150, 15);
		
		add(l1);
		
		textField = new JTextField();
		textField.setBounds(170, 25, 120, 20);
		add(textField);
		
		JLabel l6 = new JLabel("Run the Algorithm for any N and check stable matching");
		l6.setBounds(350, 25, 350, 15);
		add(l6);
		
		button1 = new JButton("Generate Input File for N :");
		button1.setBounds(15, 100 , 200, 50);
		add(button1);
		button1.addActionListener(this);
		
		l2 = new JLabel("Input File Text:");
		l2.setBounds(240,100,100,50);
		add(l2);
		
		textarea = new JTextArea(300,300);
		JScrollPane jp = new JScrollPane(textarea);
		jp.setBounds(370, 100, 500, 300);
		add(jp);
	//	textarea.setEnabled(false);
		
		button2 = new JButton("Calculate Stable Matching Pairs");
		button2.setBounds(15, 425 , 220, 50);
		add(button2);
		button2.addActionListener(this);
		
		button3 = new JButton("Clear All");
		button3.setBounds(15, 500 , 220, 50);
		add(button3);
		button3.addActionListener(this);
		
		l3 = new JLabel("Output :");
		l3.setBounds(240,425,100,50);
		add(l3);
		
		textarea1 = new JTextArea(300,300);
		JScrollPane jp1 = new JScrollPane(textarea1);
		jp1.setBounds(370, 425, 300, 250);
		add(jp1);
	//	textarea1.setEnabled(false);
		
		
		l4 = new JLabel();
		l4.setBounds(720, 500, 250, 50);
		add(l4);
		
		
		chooser = new JFileChooser();
		chooser.setDialogTitle("choose a file");
		add(chooser);
		choose = new JButton("Browse File");
		choose.setBounds(750,25,200,50);
		add(choose);
		choose.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object src = e.getSource();
		
		//click here to display input text
		if(src == button1)
		{
			// enter the size of N
			int nvalue = Integer.parseInt(textField.getText());
			
			// call inputfile class to generate an input file
			inputfile i = new inputfile(nvalue);
			
			File input = new File("src/input.txt");
			
			textarea.setText(null);
			
			// Create an Array List to store all lines from file
			ArrayList<String> lines = new ArrayList<String>();
			
			try {
				// read from the file 
				Scanner o = new Scanner(input);
				
				while(o.hasNextLine())
				{
					
					String eachline = o.nextLine();
					lines.add(eachline);
					
				}
				
				for(int k=0;k<lines.size();k++)
				{
					
					textarea.append(lines.get(k)+"\n");
					
				}
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			textarea1.setText(null);
			
			l4.setText(null);
			
		}
		else if(src == button3)
		{
			
			textarea.setText(null);
			textarea1.setText(null);
			
			textField.setText(null);
			
			l4.setText(null);
			
		}
		else if(src == choose)
		{
			textarea1.setText(null);
			l4.setText(null);
			
			int value = chooser.showOpenDialog(new JFrame());
			
			if(value == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();
				
				path = file.getPath();
				
				System.out.println(path);
				
				
				// Create an Array List to store all lines from file
				ArrayList<String> lines = new ArrayList<String>();
				
				try {
					// read from the file 
					Scanner o = new Scanner(file);
					
					while(o.hasNextLine())
					{
						
						String eachline = o.nextLine();
						lines.add(eachline);
						
					}
					
					for(int k=0;k<lines.size();k++)
					{
						
						textarea.append(lines.get(k)+"\n");
						
					}
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			
		}
		// click here to calculate stable matching algorithm
		else
		{
				
				try {
					
					// call MainAlgo class to calculate algorithm and generate stable matching
					
					if(path==null)
					{
					MainAlgo m1 = new MainAlgo();
					}
					else
					{
					
					MainAlgo m2 = new MainAlgo(path);
					
					}
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				// read from the output file
				File output = new File("src/output.txt");
				
				textarea1.setText(null);
				
				// Create an Array List to store all lines from file
				ArrayList<String> linesoutput = new ArrayList<String>();
				
				try {
					
					Scanner o1 = new Scanner(output);
					
					while(o1.hasNextLine())
					{
						
						String eachline = o1.nextLine();
						linesoutput.add(eachline);
						
					}
					
					for(int k=0;k<linesoutput.size()-1;k++)
					{
						
						textarea1.append(linesoutput.get(k)+"\n");
						
					}
					
					
					
					if(constructalgo.checkstable==true)
					{
						
						l4.setText("This Output is Stable");
						
					}
					else
					{
						
						l4.setText("This output is not stable");
						
					} 
					
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			
		}
		
		
	}
	
	
	
	
}

//create a class for panel which differentiate Question1:B
class Bchoice extends JPanel implements ActionListener{
	
	// initialize all the variable
	JLabel l1,l2,l3,l4;
	
	JTextField textField;
	
	JButton button1,button2,button3,button4;
	
	JTextArea textarea,textarea1,textarea2;
	
	int count = 0;
	
	//create a constructor 
	public Bchoice()
	{
		
		setLayout(null);
		
		l1 = new JLabel("Specifically for Input Size N =");
		
		l1.setBounds(15, 25, 180, 15);
		
		add(l1);
		
		textField = new JTextField();
		textField.setBounds(190, 25, 120, 20);
		textField.setText("10");
		textField.setEnabled(false);
		add(textField);
		
		JLabel l6 = new JLabel("Run the Algorithm for N=10 with different Input Files");
		l6.setBounds(350, 25, 300, 15);
		add(l6);
		
		button1 = new JButton("Generate Input File for N = "+textField.getText());
		button1.setBounds(15, 100 , 200, 50);
		add(button1);
		
		button1.addActionListener(this);
		
		l2 = new JLabel("Input File Text:");
		l2.setBounds(240,100,100,50);
		add(l2);
		
		textarea = new JTextArea(300,300);
		JScrollPane jp = new JScrollPane(textarea);
		jp.setBounds(370, 100, 300, 300);
		add(jp);
	//	textarea.setEnabled(false);
		
		l4 = new JLabel("Running Time Of an Algorithm :");
		l4.setBounds(720,100,180,50);
		add(l4);
		
		textarea2 = new JTextArea(15,15);
		textarea2.setBounds(720, 140, 150, 300);
		add(textarea2);
	//	textarea2.setEnabled(false);
		
		button2 = new JButton("Calculate Stable Matching Pairs");
		button2.setBounds(15, 425 , 220, 50);
		add(button2);
		
		button2.addActionListener(this);
		
		button4 = new JButton("Clear All");
		button4.setBounds(15, 500 , 220, 50);
		add(button4);
		button4.addActionListener(this);
		
		l3 = new JLabel("Output :");
		l3.setBounds(240,425,100,50);
		add(l3);
		
		textarea1 = new JTextArea(300,300);
		JScrollPane jp1 = new JScrollPane(textarea1);
		jp1.setBounds(370, 425, 300, 250);
		add(jp1);
	//	textarea1.setEnabled(false);
		
		button3 = new JButton("Reset Running Time");
		button3.setBounds(720, 460 , 220, 50);
		add(button3);
		
		button3.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object src = e.getSource();
		
		//Enter the Input size N
		int nvalue = Integer.parseInt(textField.getText());
		
		
		// click here to generate input file
		if(src == button1)
		{
			// call this class to generate an input file
			inputfile i = new inputfile(nvalue);
			
			// Read from the input file
			File input = new File("src/input.txt");
			
			textarea.setText(null);
			
			// Create an Array List to store all lines from file
			ArrayList<String> lines = new ArrayList<String>();
			
			
			try {
				
				Scanner o = new Scanner(input);
				
				while(o.hasNextLine())
				{
					
					String eachline = o.nextLine();
					lines.add(eachline);
					
				}
				
				for(int k=0;k<lines.size();k++)
				{
					
					textarea.append(lines.get(k)+"\n");
					
				}
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			textarea1.setText(null);
			
			
		}
		// click here to reset running time 
		else if(src == button3)
		{
			textarea2.setText(null);
			
			count=0;
		}
		else if(src == button4)
		{
			textarea.setText(null);
			textarea1.setText(null);
			textarea2.setText(null);
			
			
		}
		// click here to generate an calculate stable matching algorithm
		else
		{
				
				try {
					
					//call class MainAlgo to generate a stable matching pair
					MainAlgo m1 = new MainAlgo();
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				// read from the output file
				File output = new File("src/output.txt");
				
				textarea1.setText(null);
				
				// Create an Array List to store all lines from file
				ArrayList<String> linesoutput = new ArrayList<String>();
				
				try {
					
					Scanner o1 = new Scanner(output);
					
					while(o1.hasNextLine())
					{
						
						String eachline = o1.nextLine();
						linesoutput.add(eachline);
						
					}
					
					for(int k=0;k<linesoutput.size()-1;k++)
					{
						
						textarea1.append(linesoutput.get(k)+"\n");
						
					}
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				textarea2.append(count+" time : "+linesoutput.get(nvalue)+" milliseconds"+"\n");
				
				System.out.println("hey");
		
				
				count++;
			
		}
		
		
		
	}
	
	
	
	
}

//create a class for panel which differentiate Question1:C
class Cchoice extends JPanel implements ActionListener{
	
	// initialize all the variables 
	JLabel l1,l2,l3,l4;
	
	JTextField textField;
	
	JTextArea textarea,textarea1,textarea2;
	
	JButton button1,button2,button3,button4;
	
	int count =0;
	
	boolean success = false;
	
	// create a constructor 
	public Cchoice()
	{
		setLayout(null);
		
		l1 = new JLabel("Specifically for Input Size N =");
		
		l1.setBounds(15, 25, 180, 15);
		
		add(l1);
		
		textField = new JTextField();
		textField.setBounds(190, 25, 120, 20);
		textField.setText("10");
		textField.setEnabled(false);
		add(textField);
		
		JLabel l6 = new JLabel("Run the Algorithm for N=10 with same Input Files");
		l6.setBounds(350, 25, 300, 15);
		add(l6);
	
		
		button1 = new JButton("Generate Input File for N ");
		button1.setBounds(15, 100 , 200, 50);
		add(button1);
		
		button1.addActionListener(this);

		
		
		l2 = new JLabel("Input File Text:");
		l2.setBounds(240,100,100,50);
		add(l2);
		
		textarea = new JTextArea(300,300);
		JScrollPane jp = new JScrollPane(textarea);
		jp.setBounds(370, 100, 300, 300);
		add(jp);
	//	textarea.setEnabled(false);
		
		l4 = new JLabel("Running Time Of an Algorithm :");
		l4.setBounds(720,100,180,50);
		add(l4);
		
		textarea2 = new JTextArea(15,15);
		textarea2.setBounds(720, 140, 150, 300);
		add(textarea2);
	//	textarea2.setEnabled(false);
		
		button2 = new JButton("Calculate Stable Matching Pairs");
		button2.setBounds(15, 425 , 220, 50);
		add(button2);
		
		button2.addActionListener(this);
		
		button4 = new JButton("Clear All");
		button4.setBounds(15, 500 , 220, 50);
		add(button4);
		button4.addActionListener(this);
		
		l3 = new JLabel("Output :");
		l3.setBounds(240,425,100,50);
		add(l3);
		
		textarea1 = new JTextArea(300,300);
		JScrollPane jp1 = new JScrollPane(textarea1);
		jp1.setBounds(370, 425, 300, 250);
		add(jp1);
	//	textarea1.setEnabled(false);
		
		button3 = new JButton("Reset Running Time");
		button3.setBounds(720, 460 , 220, 50);
		add(button3);
		
		button3.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object src = e.getSource();
		
		// Enter the Input Size N
		int nvalue = Integer.parseInt(textField.getText());
		
		
		
		// Click here to generate an Input file
		if(src == button1)
		{
			
			// call this class to generate an input file
			inputfile i = new inputfile(nvalue);
			
			File input = new File("src/input.txt");
			
			textarea.setText(null);
			
			// Create an Array List to store all lines from file
			ArrayList<String> lines = new ArrayList<String>();
			
			try {
				
				Scanner o = new Scanner(input);
				
				while(o.hasNextLine())
				{
					
					String eachline = o.nextLine();
					lines.add(eachline);
					
				}
				
				for(int k=0;k<lines.size();k++)
				{
					
					textarea.append(lines.get(k)+"\n");
					
				}
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			textarea1.setText(null);
			
		}
		// click here to reset running time of an algorithm
		else if(src == button3)
		{
			textarea2.setText(null);
			
			count =0;
			
			success = false;
		}
		else if(src == button4)
		{
			textarea.setText(null);
			textarea1.setText(null);
			textarea2.setText(null);
			
			success = false;
		}
		// click here to generate a stable matching pair
		else
		{
			if(success == false)
			{
			
			for(int count=0;count<11;count++)
			{
			
				
				try {
					
					// call MainAlgo to generate a stable matching pair
					MainAlgo m1 = new MainAlgo();
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				// read from the output file
				File output = new File("src/output.txt");
				
				textarea1.setText(null);
				
				// Create an Array List to store all lines from file
				ArrayList<String> linesoutput = new ArrayList<String>();
				
				try {
					
					Scanner o1 = new Scanner(output);
					
					while(o1.hasNextLine())
					{
						
						String eachline = o1.nextLine();
						linesoutput.add(eachline);
						
					}
					
					for(int k=0;k<linesoutput.size()-1;k++)
					{
						
						textarea1.append(linesoutput.get(k)+"\n");
						
					}
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				textarea2.append(count+" time : "+linesoutput.get(nvalue)+" milliseconds"+"\n");
				
				System.out.println("hey");
		
				
			}
			
			
	
			
		}
		
			success = true;
			
		}
		
	}
}

//create a class for panel which differentiate Question1:D
class Dchoice extends JPanel implements ActionListener{
	
	// initialize all the variable
	JLabel l1,l2,l3,l4;
	JTextField textField;
	JTextArea textarea,textarea1,textarea2;
	JButton button1,button2,button3,button4;
	

	
	// constructor 
	public Dchoice()
	{
		setLayout(null);
		
		l1 = new JLabel("Enter the number of N :");
		
		l1.setBounds(15, 25, 150, 15);
		
		add(l1);
		
		textField = new JTextField();
		textField.setBounds(170, 25, 120, 20);
		add(textField);
		
		JLabel l6 = new JLabel("Run the Algorithm for N=1,5,10,15,20,50,100 ");
		l6.setBounds(350, 25, 400, 15);
		add(l6);
		
		button1 = new JButton("Generate Input File for N ");
		button1.setBounds(15, 100 , 200, 50);
		add(button1);
		
		button1.addActionListener(this);
		
		l2 = new JLabel("Input File Text:");
		l2.setBounds(240,100,100,50);
		add(l2);
		
		textarea = new JTextArea(300,300);
		JScrollPane jp = new JScrollPane(textarea);
		jp.setBounds(370, 100, 300, 300);
		add(jp);
	//	textarea.setEnabled(false);
		
		l4 = new JLabel("Running Time Of an Algorithm :");
		l4.setBounds(720,100,180,50);
		add(l4);
		
		textarea2 = new JTextArea(15,15);
		textarea2.setBounds(720, 140, 150, 300);
		add(textarea2);
	//	textarea2.setEnabled(false);
		
		button2 = new JButton("Calculate Stable Matching Pairs");
		button2.setBounds(15, 425 , 220, 50);
		add(button2);
		
		button2.addActionListener(this);
		
		button4 = new JButton("Clear All");
		button4.setBounds(15, 500 , 220, 50);
		add(button4);
		button4.addActionListener(this);
		
		l3 = new JLabel("Output :");
		l3.setBounds(240,425,100,50);
		add(l3);
		
		textarea1 = new JTextArea(300,300);
		JScrollPane jp1 = new JScrollPane(textarea1);
		jp1.setBounds(370, 425, 300, 250);
		add(jp1);
	//	textarea1.setEnabled(false);
		
		button3 = new JButton("Reset Running Time");
		button3.setBounds(720, 460 , 220, 50);
		add(button3);
		
		button3.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		

		Object src = e.getSource();
		
		int nvalue = 0;
		
		//Enter the Input size of N 
		nvalue = Integer.parseInt(textField.getText());
		
		// click here to generate an Input File
		if(src == button1)
		{
			
			// call this class to generate an input file
			inputfile i = new inputfile(nvalue);
			
			// read from the input file
			File input = new File("src/input.txt");
			
			textarea.setText(null);
			
			// Create an Array List to store all lines from file
			ArrayList<String> lines = new ArrayList<String>();
			
			try {
				
				Scanner o = new Scanner(input);
				
				while(o.hasNextLine())
				{
					
					String eachline = o.nextLine();
					lines.add(eachline);
					
				}
				
				for(int k=0;k<lines.size();k++)
				{
					
					textarea.append(lines.get(k)+"\n");
					
				}
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			textarea1.setText(null);
			
			
		}
		else if(src == button4)
		{
			textarea.setText(null);
			textarea1.setText(null);
			textarea2.setText(null);
			
			textField.setText(null);
		}
		// click here to reset running time of an algorithm
		else if(src == button3)
		{
			
			
			textarea2.setText(null);
			
			
		}
		// click here to generate output file of an algorithm
		else
		{
			
			
				
				try {
					
					// call class MainAlgo to generate stable matching pair
					MainAlgo m1 = new MainAlgo();
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				// read from the output file
				File output = new File("src/output.txt");
				
				textarea1.setText(null);
				
				// Create an Array List to store all lines from file
				ArrayList<String> linesoutput = new ArrayList<String>();
				
				try {
					
					Scanner o1 = new Scanner(output);
					
					while(o1.hasNextLine())
					{
						
						String eachline = o1.nextLine();
						linesoutput.add(eachline);
						
					}
					
					for(int k=0;k<linesoutput.size()-1;k++)
					{
						
						textarea1.append(linesoutput.get(k)+"\n");
						
					}
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				textarea2.append("N = "+nvalue+" : "+linesoutput.get(nvalue)+" milliseconds"+"\n");
				
			
				
		
		
		}
	}
	
}

// Main GUI class
public class GUI {
	
	JFrame jf;
	
	GUI()
	{
		jf = new JFrame("Stable Marraige Algorithm Applicattion");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1000,1000);
		
		
		addchoices();
	}
	
	public void addchoices()
	{
		// create a tabbed based gui structure using JTabbedPane 
		
		JTabbedPane jt = new JTabbedPane();
		jt.addTab("Question1:A", new Achoice());
		jt.addTab("Question1:B", new Bchoice());
		jt.addTab("Question1:C", new Cchoice());
		jt.addTab("Question1:D", new Dchoice());
		
		// content pane added into container
		jf.getContentPane().add(jt);
		
		jf.setVisible(true);
		
		
	}
	
	
	public static void main(String args[])
	{
		new GUI();
	}
			

}