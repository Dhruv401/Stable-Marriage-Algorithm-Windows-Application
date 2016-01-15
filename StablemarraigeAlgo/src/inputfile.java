
// Generation of Input File


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;


public class inputfile{
	
	public inputfile(int N)
	{
	
		
		System.out.println("hello");
						try{
						
			
						System.out.println("Number of men");
						
						// the number of men 
						int m = N;
						
						System.out.println("Number of women");
						
						// the number of women 
						int w = N;
						
						// Create a File temp.txt in a given path
						// I put file inside my eclipse workspace project
						
						
						
						File writefile = new File("src/input.txt");
						
						
						//write bytes into file
						FileOutputStream fs = new FileOutputStream(writefile);
						
						//writes text to a Character Output Stream
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs));
						
						bw.write(m+":"+w);
						
						// Used for New Line5
						bw.newLine();  
						
						// Create an Array List for men
						ArrayList<String> men = new ArrayList<String>();
						
						// Create an Array List for Women
						ArrayList<String> women = new ArrayList<String>();
						
						// Enter the number of men into array list
						for(int i=1;i<=m;i++)
						{
							String tempmen = "m"+i;
							
							men.add(tempmen);
						}
						
						// System.out.println(men);
						
						// Enter the number of Women into Array List
						for(int i=1;i<=w;i++)
						{
							String tempwomen = "w"+i;
							
							women.add(tempwomen);
						}
						
						//System.out.println(women);
						
						
						// array lists for shuffle all men elements
						
						ArrayList<String> tempmenlist = new ArrayList<String>();
						
						tempmenlist.addAll(men);
						
						// array lists for shuffle all women elements
						ArrayList<String> tempwomenlist = new ArrayList<String>();
						
						tempwomenlist.addAll(women);
						
						// This Functionality is used for Generating a Preference List for men
						for(int i=0;i<tempwomenlist.size();i++)
						{
							
							// Collection.shuffle function is used to randomly permute the specified list using a default source of randomness
							Collections.shuffle(tempwomenlist);
							
							
							// assigned preference list to each women
							bw.write(men.get(i)+":");
							
							
							// Generate comma Seperated preference list for each women
							for(int j=0;j<tempwomenlist.size();j++)
							{
								String m1 = tempwomenlist.get(j);
								if(j==tempwomenlist.size()-1)
								{
									bw.write(m1);
								}
								else
								{
								bw.write(m1+",");
								}
							}
							bw.newLine();
							
						}
						
						// This Functionality is used for Generating Preference List for Women
						for(int i=0;i<tempmenlist.size();i++)
						{
							
							
							// Collection.shuffle function is used to randomly permute the specified list using a default source of randomness
							Collections.shuffle(tempmenlist);
					
							
							// assigned preference list to each men
							bw.write(women.get(i)+":");
							
							// Generate comma Seperated preference list for each men
							for(int j=0;j<tempmenlist.size();j++)
							{
								String m2 = tempmenlist.get(j);
							
								if(j==tempmenlist.size()-1)
								{
									bw.write(m2);
								}
								else
								{
									bw.write(m2+",");
								}
							
							}
							
							bw.newLine();
							
						}
						
						// Close the Buffered Writer Stream
						
						System.out.println("File Generated successfully in a given directory");
						
						bw.close();
						
						
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
	}
}
