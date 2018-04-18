package assign1;

import java.io.BufferedReader;
import java.io.File;
import java.io.File.*;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.*;
import java.util.Scanner;
import java.util.Set;


public class pass2 {

	public static void main( String args[])
	{
		try{
			FileReader fr=new FileReader("in.txt");
	
	        FileWriter fw=new FileWriter("IC.txt");    
	        FileWriter fw1=new FileWriter("littab.txt"); 
	        FileWriter fw2=new FileWriter("symtab.txt"); 
	        FileWriter fw3=new FileWriter("pooltab.txt");
	      
	        fw3.write("# "+1+"\n");
			FileOutputStream fout2=new FileOutputStream("littab.txt");
			 ArrayList<String>ar=new ArrayList<String>();
			 
			String filecontents = "";
			int i;
			
			while((i=fr.read() )!=-1)
			{
				char ch=(char)i;
				filecontents =filecontents +ch;
			}
			
			String[] str=filecontents.split("\\s+");
			
			for(String a :str)
			{
				ar.add(a);
			}
			
			Iterator<String>itr=ar.iterator();
			
			/*for(i=0 ;i<ar.size();i++)
			{
				System.out.println(i+" "+ar.get(i));
			}
			*/
			System.out.println("==============Mneumonic Table===============");
			FileReader fr1=new FileReader("mneumonic.txt");
			ArrayList<String>ar1=new ArrayList<String>();
			 
			String filecontents1 = "";
			int j;
			
			while((j=fr1.read() )!=-1)
			{
				char ch=(char)j;
				filecontents1 =filecontents1 +ch;
			}
			
			String[] str1=filecontents1.split("\\s+");
			
			for(String a :str1)
			{
				ar1.add(a);
			}
			Iterator<String>itr1=ar1.iterator();
			/*for(j=0 ;j<ar1.size();j++)
			{
				System.out.println(j+" "+ar1.get(j));
			}*/
			System.out.println("===================IC.txt====================");
		
			int LC=200;
			int k=0,flag=0;
			//End of File exception
			for( i=0;i<ar.size();i++)
			{
				if(ar.get(i).equalsIgnoreCase("END"))
				{
					
					flag=1;
					break;
				}
				else
					continue;
					
			}
			
			if(flag==0)
			{
				throw new Exception("End of file");
				
			}
			
			if(ar.get(k).contains("START"))
			{
				
				System.out.println("("+ar1.get(2)+","+ar1.get(1)+")  "+LC);
				fw.write("("+ar1.get(2)+","+ar1.get(1)+")  "+LC+"\n");
				
			}
			ArrayList<String>ar2=new ArrayList<String>();
			ArrayList<String>ar3=new ArrayList<String>();
			String st;
			
			j=0;
			int cnt=1,m=0, lcnt=0,t=1;
			Map<String,Integer> sym = new HashMap();
			Map<String,Integer> lit = new HashMap();
			Iterator iterator1 ;
			int srno=1;
			String[] ope = null;
			
			
			for(i=2;i<ar.size();i++)
			{
					if(ar.get(i).contains(":"))
					{
						String sp[]=ar.get(i).split(":");
						sym.put(sp[0], LC);
						
					}
					
					if(!ar.get(i).equalsIgnoreCase("END"))
					{
						for(k=0;k<ar1.size();k++)
						{
								
								if(ar.get(i).equalsIgnoreCase(ar1.get(k)) )
								{
									
									if(ar.get(i+1).contains(",") && ar.get(i+1).contains("="))
									{			
											st=ar.get(i+1);
											ope=st.split(",");
											
											for(String a:ope)
												{
													ar2.add(a);
												}
											
											if(lcnt>0)
											{
												lit.put(ope[1], 0);
												cnt=1;
											}
											
											System.out.println(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+")"+" "+ar2.get(j)+"  (L,"+(t)+") ");
											fw.write(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+")"+" "+ar2.get(j)+"  (L,"+t+") "+"\n");
											LC=LC+Integer.parseInt(ar1.get(k+3));
								            cnt++;
								            t++;
											j+=2;
											
									}
										
									else if(ar.get(i+1).contains(","))
									{
											st=ar.get(i+1);
											String[] op=st.split(",");
											for(String a:op)
												{
													ar2.add(a);
												}
											
											sym.put(op[1], LC);
											System.out.println(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+")"+" "+ar2.get(j)+", "+ar2.get(j+1));
											fw.write(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+")"+" "+ar2.get(j)+", "+ar2.get(j+1)+"\n");
											LC=LC+Integer.parseInt(ar1.get(k+3));
											j+=2;
											
									}
										
									else
									{
										if(ar.get(i).equals("LTORG"))
											{
												fw3.write("# "+t+"\n");
													m=i+1;
													lcnt++;
													if(ar.get(i+1).contains("="))
													{
												
															for(int l=1;l<cnt;l++)
															{
																	System.out.println(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+")"+" literal "+l );
																	fw.write(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+") "+ar.get(m)+"\n");				//IC.txt 
																	fw1.write(srno+"\t"+ar.get(m)+"\t"+LC+"\n");	//Literal table
																	srno++;
																	
																	LC=LC+1;
																	m++;
															}
															
															
													
													}
														
														
											}
													
											else
											{
												if(ar.get(i+1).contains("+"))
												{
														System.out.println(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+")"+ar.get(i+1));
														fw.write(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+") "+ar.get(i+1)+"\n");
														String x[]=ar.get(i+1).split("\\+");
														
														Iterator iterator = sym.keySet().iterator();
														while (iterator.hasNext()) 
														{
															String key=iterator.next().toString();
															if(key.equalsIgnoreCase(x[0]))
															{
																LC=Integer.parseInt(sym.get(key).toString())+Integer.parseInt(x[1]);
															}
														
													   
														}
												}
														
												else if(ar.get(i).contains("EQU"))
												{
														System.out.println(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+")");
														fw.write(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+")"+"\n");
														
														Iterator iterator = sym.keySet().iterator();
														while (iterator.hasNext()) 
														{
															String key=iterator.next().toString();
															
															if(key.equalsIgnoreCase((ar.get(i+1))))
															{
																sym.put(ar.get(i-1), sym.get(key));
																
															}
														
														}
															
												}
														
												else
												{
													if(ar.get(i).contains("DS"))
													{
														
														String key=ar.get(i-1);
														fw.write(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+")"+" 1\n");
														sym.put(key, LC);
														if(!ar.get(i).equals("END"))
															LC=LC+1;
													}
													else{
													System.out.println(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+")");
													fw.write(LC+" "+"("+ar1.get(k+2)+","+ar1.get(k+1)+")"+"\n");
													if(!ar.get(i).equals("END"))
													LC=LC+1;
													}
														
												}
														
											}
									}	
							}
							
					}
			}	
			
			else
			{
				System.out.println(LC+" "+"("+ar1.get(i+2)+","+ar1.get(i+1)+")");
				fw.write(LC+" "+"("+ar1.get(i+2)+","+ar1.get(i+1)+")");
				System.out.println("END");
			}
						
			
		}
		
			Iterator iterator = sym.keySet().iterator();
			while (iterator.hasNext()) {
		   String key = iterator.next().toString();
		   String value = sym.get(key).toString();
		  fw2.write(key + " " + value+"\n");
		   
		}
			
			if(t>srno)
			{
				
				iterator1 = lit.keySet().iterator();
				while (iterator1.hasNext()) {
			   String key = iterator1.next().toString();
			   int value = lit.get(key);
			   if(value==0){
				  
				   value=LC;
			   }
			  
			   fw1.write(srno+"\t"+key +"\t"+value+"\n");
			  
				}
			}
			fw.close();
			fw1.close();
			fw2.close();
			fw3.close();
			
		System.out.println("=======================Pass 2==============================");
		FileReader fr2=new FileReader("IC.txt");
		 ArrayList<String>arp=new ArrayList<String>();
		 
		String filecontentsp = "";
		
		
		while((i=fr2.read() )!=-1)
		{
			
			char ch=(char)i;
			filecontentsp =filecontentsp +ch;
			
		}
		
		String[] strp=filecontentsp.split(System.lineSeparator());
		
		for(String a :strp)
		{
			
			arp.add(a);
		}
		
		  FileWriter fw4=new FileWriter("pass2.txt");
		
		String line;
		for(i=1;i<arp.size();i++)
		{
			String op[]=arp.get(i).split("\\s+");
			
			fw4.write(op[0]+"\t  ");
			String numberOnly= op[1].replaceAll("[^0-9]", "");
			
			if(op.length>2||op[1].contains("00"))
			{
				
				if(op.length==3)
				{
					if(op[2].contains("+"))
					fw4.write("");
					if(op[2].contains("="))
					{
						String equ=op[2].replaceAll("[^0-9]", "");
						fw4.write(equ);
					}
			
				}
				else
				{
				fw4.write("+\t  "+numberOnly+"\t");
				
				}
			}
			if(op.length==3)
			{
				
				if(op[2].matches("[0-9]+"))
				{
					fw4.write("1\t");
				}
				else if(op[2].contains("+"))
				{
					
					String[] aa=op[2].split("\\+");
					
					Scanner inputFile1 = new Scanner(new File("symtab.txt"));
				    while (inputFile1.hasNext()) {
				        String unknown = inputFile1.next();
				       
				        if (aa[0].equals(unknown)) {
				        	int val=Integer.parseInt(inputFile1.next())+Integer.parseInt(aa[1]);
				        	
				        	String vals=Integer.toString(val);
					         fw4.write(vals);
				           


				        }
				    }
				    inputFile1.close();

					
				}
			}
			if(op.length>3)
			{
				
				if(op[2].contains("AREG"))
					fw4.write(1+"\t");
				else if(op[2].contains("BREG"))
					fw4.write(2+"\t");
				else if(op[2].contains("CREG"))
					fw4.write(3+"\t");
				
	
				
			}
			
			if(op.length==4)
			{
				
				
				if(op[3].contains("(L,"))
				{
					String litst= op[3].replaceAll("[^0-9]", "");
				
			    Scanner inputFile2 = new Scanner(new File("littab.txt"));
			    while (inputFile2.hasNext()) {
			        String unknown = inputFile2.next();
			     
			        if (unknown.equals(litst)) {
			        	
			        	inputFile2.next();
			            
			           fw4.write(inputFile2.next());
			        }
			    }
			    inputFile2.close();
			    
			  
				}
				else
				{
					Scanner inputFile1 = new Scanner(new File("symtab.txt"));
				    while (inputFile1.hasNext()) {
				        String unknown = inputFile1.next();
				       
				        if (op[3].equals(unknown)) {
					           fw4.write(inputFile1.next());
				            

				        }
				    }
				    inputFile1.close();
				}
			    

			}
			fw4.write("\n");
			
			
			
		}
		
		
		fw4.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
