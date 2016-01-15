import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;



class constructalgo{
	
	
		static boolean checkstable;
	
		// default constructor
	
		public constructalgo() {
			// TODO Auto-generated constructor stub
		}
	
		// Start time when the algorithm started 
		long starttime = System.currentTimeMillis();
	
		// Create array list to store list of men
		ArrayList<String> men = new ArrayList<String>();
		
		// create array list to store list of women
		ArrayList<String> women = new ArrayList<String>();
		
		// create array list to store all matched men
		ArrayList<String> menmatchinglist = new ArrayList<String>();
		
		// create array list to store all matched women
		ArrayList<String> womenmatchinlist = new ArrayList<String>();
		
		// create an array list to store unmatched/free men 
		ArrayList<String> freeman = new ArrayList<String>();
		
	
		// two dimensional array to store men preference list
		String menpreflist[][];
		
		// two dimensional array to store women preference list
		String womenpreflist[][];
		
		// number of men and women
		int numberofmen,numberofwomen;
		
		// counter used to check women preference list
		// howmany women are visited in the particular men preference list 
		int counter[] ;
		
			// declare a Constructor to intialize all the required values
		
			constructalgo(ArrayList<String> m,ArrayList<String> w,String mpref[][],String wpref[][]) throws IOException
			{
				
				men = m;
				women = w;
				
				menpreflist = mpref;
				
				womenpreflist = wpref;
				
				// calculate a size of men Array List
				numberofmen = men.size();
				
				// calculate a size of women Array List
				numberofwomen = women.size();
				
				// Initially all the men are free so add them into freeman array list
				freeman.addAll(men);
				
			//	System.out.println(freeman);
				
			//	System.out.println("Inside Constructor");
				
				// Call calculatematches for follow Gale shapley Algorithm
				calculatematches();
			
				
			}
			
			
			// follow Gale-Shapley Alorithm
			
			public void calculatematches() throws IOException
			{
		//		System.out.println("inside calculate matches");
				
				// Check current time when the algorithm start
				
				
			//	System.out.println(starttime);
				
				// All the men and Women are free
				// Run while loop until all the men are engaged
				// so run loop up to array list freeman become empty 
				 while(freeman.isEmpty()==false)
				 {
					 
					 String freemanname = null;
					 
		//			 System.out.println("inside freeman is empty or not");
					 
					 // get the freemanname from list
					 	freemanname = freeman.get(0);
						 
			//			 System.out.println(freemanname);
						 
						 // remove this man from freeman list
						 freeman.remove(freemanname);
						 
					 	// get the index of this man from men list					 
					 	 int indexoffreeman =  men.indexOf(freemanname); 
					 	 
			//			 System.out.println("freeman :"+freemanname);
						 
						 // check preference list for this man
						 
						 //take variable to check counter in women preference list two dimensional array
						 int l = 0;
						 
						 // check free man preference list in two dimensional array coresponding to that index  
						 for(int j=0;j<numberofwomen;j++)
						 {
						 
							// in iwo dimensional array row index become indexoffreeman and check according to j
							String firstwomen = menpreflist[indexoffreeman][j];
							
							// if firstwomen visited then initialize counter to l
							
							counter = new int[numberofmen]; 
									
							counter[j] = l;
							
							// increment l
							l++;
							
				//			System.out.println(firstwomen);
							
							String newpartner = null;
							
							// Check whether the women is free or not?
							// check into womenmatchinlist
							
							if(womenmatchinlist.contains(firstwomen)==false)
							{
								
				//				System.out.println("Inside men preference list");
								// them make a matching pair of this man and woman
								
								// if women is free than make matching pair of this women and men
								
								// store men into menmatchinglist with the same index which is women store into womenmatchinlist
								menmatchinglist.add(j,freemanname);
								
					//			System.out.println("menmatching list:"+menmatchinglist.get(j));
								
								// store women into womenmatchinglist with the same index which is men store into menmatchinlist
								womenmatchinlist.add(j,firstwomen);
								
					//			System.out.println("womenmatching list:"+womenmatchinlist.get(j));
								
								// if the pair has been established then break the loop
								break;
								
								
							}
							// if the women is not free then
							// check women prefer new partner over current partner
							else if(womenmatchinlist.contains(firstwomen)==true)
							{
								
								// get the women index from women matching list
								int index = womenmatchinlist.indexOf(firstwomen);
								
					//			System.out.println(index);
								
								// we get the current woman partner from men matching list corresponding to women matching list index 
								String currentpartner = menmatchinglist.get(index);
								
					//			System.out.println("currentpartner :"+currentpartner);
								
								// new partner is the freeman that we want to match with this woman
								newpartner = freemanname;
								
								// now take the women index from initial woman list to find his preference list in two dimensional array
								int womenindex = women.indexOf(firstwomen);
								
					//			System.out.println(womenindex);
								
								
								
								// call womenpreferencecheck function
								// passed row index, current partner and new partner
								if(womenpreferencecheck(currentpartner, newpartner, womenindex))
								{
									// if it returns true then remove current partner and becomes free add current partner into freeman list
									
									freeman.add(currentpartner);
									
									// remove previous pair and make new pair 
									// replace new partner with current partner in menmatchinglist
									menmatchinglist.set(index, newpartner);
									
				//					System.out.println(menmatchinglist);
									
				//					System.out.println("hello guys how r u?");
									
									// matching done then break the loop
									break;
								}
								
								
							}
						 
							else
							{
								
								// if it returns false then women rejects new partner 
								// new partner is not matched so add them into freeman list 
								
								freeman.add(newpartner);
								
								
								// check if counter visit all the element in the women preference list then set them back to zero and 
								// break the loop
								if(l>numberofmen)
								{
									l=0;
									break;
								}
									
							}
						 
						
						 } 
						 
					 }
				 
				 // call matching couple into console as a output
				 matchingcouple();
				
				 checkstable = checkstability();
				 
				 System.out.println("check stability :"+checkstable);
				 
				
				
				 // create a file and store into output.txt file
				 File output = new File("src/output.txt");
				 
				 //write bytes into file
				 FileOutputStream fs;
				 
				try {
					
					fs = new FileOutputStream(output);
					
					 //writes text to a Character Output Stream
					 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs));
					 
					 for(int z=0;z<womenmatchinlist.size();z++)
						{
						 
						// print the matching pair from same index of men and women matching list
						try {
							
							bw.write(menmatchinglist.get(z)+" is engaged with "+womenmatchinlist.get(z));
							
							bw.newLine();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
							
						
						}
					 
					 long stoptime = System.currentTimeMillis();
					 // current time when the algorithm stop
					
					 
				//	 System.out.println(stoptime);
					 
					 // difference between stop time and start time
					 
					 long elapsedtime = stoptime - starttime;
					 
					 System.out.println("Time");
					 
					 System.out.println("execution time of an algorithm in milliseconds is :"+elapsedtime);
					 
					 bw.write(""+elapsedtime);
					 
					 bw.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				
				 
					 
		} 
		
		// call this method when stability checking required
		public boolean checkstability()
		{
			ArrayList<String> previouselement = null;
			
			System.out.println(womenmatchinlist);
			
			for(int l=0;l<womenmatchinlist.size();l++)
			{
				
				String matchedman = menmatchinglist.get(l);
				
				String matchedwoman = womenmatchinlist.get(l);
				
				int matchedwomanindex = women.indexOf(matchedwoman);
				
				for(int z=0;z<women.size();z++)
				{
				
					if(womenpreflist[matchedwomanindex][z].equals(matchedman))
					{
							
						if(z>0)
						{
							for(int s=0;s<=z;s++)
							{
								previouselement = new ArrayList<String>();
								
								previouselement.add(womenpreflist[matchedwomanindex][s]);
							}
						}
						else
						{
							return true;
						}
						
					}
				
				}
				
				if(previouselement.isEmpty()==false)
				{

				for(int t=0;t<previouselement.size();t++)
				{
					
					String higherpreferman = previouselement.get(t);
					
					// find current partner of higherpreferman
					
					int higherprefmenindex = menmatchinglist.indexOf(higherpreferman);
					
					String currentpartnerofhigher = womenmatchinlist.get(higherprefmenindex);
					
					
					int tempindex = men.indexOf(higherpreferman);
					
					
					for(int i=0;i<numberofwomen;i++)
					{
						
						// if new partner comes before current partner then return true
						if(menpreflist[tempindex][i].equals(currentpartnerofhigher))
						
							return true;
							
						// otherwise return false
						if(womenpreflist[tempindex][i].equals(matchedwoman))
						
							return false;
							
						
					}
					
					
					
				}
				
				}
				else
				{
					return true;
				}
			}
			
			
			
			
			return false;
			
		}
				
				
			
		// create a method to display matching pair from men and women matching list	
			public void matchingcouple()
			{
				System.out.println("Output :");
				
				System.out.println("Following are the matching pairs ");
				
				for(int i=0;i<womenmatchinlist.size();i++)
				{
				
				// print the matching pair from same index of men and women matching list
				System.out.println(menmatchinglist.get(i)+" is engaged with "+womenmatchinlist.get(i));
				
				
				}
			
			}
			
			
			// check the priority of two men in women preference list array 
			
			public boolean womenpreferencecheck(String current,String newpartner,int defaultindex)
			{
				
				// loop used to check the priority of newpartner and current in women preference list 
				
				for(int i=0;i<numberofwomen;i++)
				{
					
					// if new partner comes before current partner then return true
					if(womenpreflist[defaultindex][i].equals(newpartner))
					
						return true;
						
					// otherwise return false
					if(womenpreflist[defaultindex][i].equals(current))
					
						return false;
						
					
				}
				
				
				
				return false;
				
			}
			
	
	
}

// Main class of Algorithm

public class MainAlgo{
	
					private static Scanner o;

					public MainAlgo() throws IOException
					{
												
						
						System.out.println("hey inside mainalgo ");
											
						// Read the inputs from the created Input File
						
						File input = new File("src/input.txt");
						
						// Create an Array List to store all lines from file
						ArrayList<String> lines = new ArrayList<String>();
						
						// Array list to store all men
						ArrayList<String> men = new ArrayList<String>();
						
						// Array List to store all women
						ArrayList<String> women = new ArrayList<String>();
						
						// created temparary store for men preference list
						ArrayList<String> tempmenpreflist = new ArrayList<String>();
						
						// created temparary storage for women preference list
						ArrayList<String> tempwomenpreflist = new ArrayList<String>();
						
						// initially number of men and women o
						
						int numberofmen = 0;
						int numberofwomen = 0;
						
						try {
							
							
							o = new Scanner(input);
							
							// Read each line from file and store into array list
							while(o.hasNextLine())
							{
								
								String eachline = o.nextLine();
								lines.add(eachline);
								
							}
							
						
							//	System.out.println(lines);
							
							
							// seperate two string through ":" using String Tokenizer class
							StringTokenizer st1 = new StringTokenizer(lines.get(0), ":");
							
							while(st1.hasMoreTokens())
							{
								String tempmen = st1.nextToken();
								
								numberofmen = Integer.parseInt(tempmen);
								
								numberofwomen = numberofmen;
							}
							
						//	System.out.println(numberofmen);
							
						//	System.out.println(numberofwomen);
							
							int i;
							
							// functionality to read men and women from file and store into Array List
							
							for(i=1;i<lines.size();i++)
							{
								
								String temp = lines.get(i);
								
							//	System.out.println(temp);
								
								StringTokenizer st = new StringTokenizer(temp, ":");
								
								while(st.hasMoreTokens())
								{
									String temp1 = st.nextToken();
									
									if(i<=numberofmen)
									{
										// add first string into array list
										men.add(temp1);
										
										String tempmenpref = st.nextToken();
										
										// add string after ":" into other array list
										tempmenpreflist.add(tempmenpref);
										
									}
									else
									{
										// same for women
										women.add(temp1);
										
										String tempwomenpref = st.nextToken();
										
										// same for women
										tempwomenpreflist.add(tempwomenpref);
										
										
									}
									
								
									
								
									
								}
								
								
							}
							
							
							//create a Two Dimensional Array to store preference list
							String menpreflist[][] = new String[numberofmen][numberofwomen];
							
						//	System.out.println(men);
							
						//	System.out.println(tempmenpreflist);
							
						//	System.out.println(women);
							
						//	System.out.println(tempwomenpreflist);
							
							int j;
							
							// seeperate men preference list through "," and store into two dimensional array
							
							for(j=0;j<tempmenpreflist.size();j++)
							{
								
								String temp4 = tempmenpreflist.get(j);
						//		System.out.println(temp4);
								
								StringTokenizer st3 = new StringTokenizer(temp4, ",");
								
								while(st3.hasMoreTokens())
								{
									
									for(int k=0;k<numberofmen;k++)
									{
										menpreflist[j][k] = st3.nextToken();
										
						//				System.out.println(menpreflist[j][k]);
									}
									
									
									
									
								}
								
								
								
								
							}
							
							//create a Two Dimensional Array to store preference list
							String womenpreflist[][] = new String[numberofmen][numberofwomen];
							
							// seeperate women preference list through "," and store into two dimensional array
							
							for(int l=0;l<tempwomenpreflist.size();l++)
							{
								
								String temp5 = tempwomenpreflist.get(l);
						//		System.out.println(temp5);
								
								StringTokenizer st4 = new StringTokenizer(temp5, ",");
								
								while(st4.hasMoreTokens())
								{
									
									for(int m=0;m<numberofwomen;m++)
									{
										womenpreflist[l][m] = st4.nextToken();
										
							//			System.out.println(womenpreflist[l][m]);
									}
									
									
									
									
								}
								
								
								
								
							}
							
						// pass required men ,women and their preference list into constructor of constructalgo class
							
						 new constructalgo(men, women, menpreflist, womenpreflist);
						
						
							
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						
						
					}
							

					public MainAlgo(String path) throws IOException
					{
												
						
						System.out.println("hey inside mainalgo ");
											
						// Read the inputs from the created Input File
						
						File input = new File(path);
						
						// Create an Array List to store all lines from file
						ArrayList<String> lines = new ArrayList<String>();
						
						// Array list to store all men
						ArrayList<String> men = new ArrayList<String>();
						
						// Array List to store all women
						ArrayList<String> women = new ArrayList<String>();
						
						// created temparary store for men preference list
						ArrayList<String> tempmenpreflist = new ArrayList<String>();
						
						// created temparary storage for women preference list
						ArrayList<String> tempwomenpreflist = new ArrayList<String>();
						
						// initially number of men and women o
						
						int numberofmen = 0;
						int numberofwomen = 0;
						
						try {
							
							
							o = new Scanner(input);
							
							// Read each line from file and store into array list
							while(o.hasNextLine())
							{
								
								String eachline = o.nextLine();
								lines.add(eachline);
								
							}
							
						
							//	System.out.println(lines);
							
							
							// seperate two string through ":" using String Tokenizer class
							StringTokenizer st1 = new StringTokenizer(lines.get(0), ":");
							
							while(st1.hasMoreTokens())
							{
								String tempmen = st1.nextToken();
								
								numberofmen = Integer.parseInt(tempmen);
								
								numberofwomen = numberofmen;
							}
							
						//	System.out.println(numberofmen);
							
						//	System.out.println(numberofwomen);
							
							int i;
							
							// functionality to read men and women from file and store into Array List
							
							for(i=1;i<lines.size();i++)
							{
								
								String temp = lines.get(i);
								
							//	System.out.println(temp);
								
								StringTokenizer st = new StringTokenizer(temp, ":");
								
								while(st.hasMoreTokens())
								{
									String temp1 = st.nextToken();
									
									if(i<=numberofmen)
									{
										// add first string into array list
										men.add(temp1);
										
										String tempmenpref = st.nextToken();
										
										// add string after ":" into other array list
										tempmenpreflist.add(tempmenpref);
										
									}
									else
									{
										// same for women
										women.add(temp1);
										
										String tempwomenpref = st.nextToken();
										
										// same for women
										tempwomenpreflist.add(tempwomenpref);
										
										
									}
									
								
									
								
									
								}
								
								
							}
							
							
							//create a Two Dimensional Array to store preference list
							String menpreflist[][] = new String[numberofmen][numberofwomen];
							
						//	System.out.println(men);
							
						//	System.out.println(tempmenpreflist);
							
						//	System.out.println(women);
							
						//	System.out.println(tempwomenpreflist);
							
							int j;
							
							// seeperate men preference list through "," and store into two dimensional array
							
							for(j=0;j<tempmenpreflist.size();j++)
							{
								
								String temp4 = tempmenpreflist.get(j);
						//		System.out.println(temp4);
								
								StringTokenizer st3 = new StringTokenizer(temp4, ",");
								
								while(st3.hasMoreTokens())
								{
									
									for(int k=0;k<numberofmen;k++)
									{
										menpreflist[j][k] = st3.nextToken();
										
						//				System.out.println(menpreflist[j][k]);
									}
									
									
									
									
								}
								
								
								
								
							}
							
							//create a Two Dimensional Array to store preference list
							String womenpreflist[][] = new String[numberofmen][numberofwomen];
							
							// seeperate women preference list through "," and store into two dimensional array
							
							for(int l=0;l<tempwomenpreflist.size();l++)
							{
								
								String temp5 = tempwomenpreflist.get(l);
						//		System.out.println(temp5);
								
								StringTokenizer st4 = new StringTokenizer(temp5, ",");
								
								while(st4.hasMoreTokens())
								{
									
									for(int m=0;m<numberofwomen;m++)
									{
										womenpreflist[l][m] = st4.nextToken();
										
							//			System.out.println(womenpreflist[l][m]);
									}
									
									
									
									
								}
								
								
								
								
							}
							
						// pass required men ,women and their preference list into constructor of constructalgo class
							
						 new constructalgo(men, women, menpreflist, womenpreflist);
						
						
							
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						
						
					}
					
}