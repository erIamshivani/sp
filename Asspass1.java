package assignment1;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.*;
import java.util.Set;


public class ass1 {

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
			int i ,LC=200,k=0,flag=0,j,cnt=1,m=0, lcnt=0,t=1;
			//cnt - TO KEEP COUNT OF LITERALS
			//LCNT- SERIAL NUMBER FOR LITERAL TABLE
			
			Map<String,Integer> sym = new HashMap();
			Map<String,Integer> lit = new HashMap();
			Iterator iterator1 ;
			int srno=1;
			String[] ope = null;
			
			//READING FILE 
			while((i=fr.read() )!=-1)
			{
				char ch=(char)i;
				filecontents =filecontents +ch;
			}
			
			String[] str=filecontents.split("\\s+");	//TOKENIZATION
			
			for(String a :str)
			{
				ar.add(a);
			}
			
			for(i=0;i<ar.size();i++)
			{
				System.out.println(i+" "+ar.get(i));
			}
			
			System.out.println("==============Mneumonic Table===============");
			FileReader fr1=new FileReader("mneumonic.txt");
			ArrayList<String>ar1=new ArrayList<String>();
			 
			String filecontents1 = "";
			
			
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
			
			for(i=0;i<ar.size();i++)
			{
				System.out.println(i+" "+ar1.get(i));
			}
			
			
			//ar CORRESPONDS TO INSTRUCTION.TXT AND ar1 CORRESPONDS INSTRUCTION.TXT
			System.out.println("===================IC.txt====================");
		
			
			
			if(ar.get(k).contains("START"))
			{
				
				System.out.println("("+ar1.get(2)+","+ar1.get(1)+")  "+LC);
				fw.write("("+ar1.get(2)+","+ar1.get(1)+")  "+LC+"\n");
				
			}
			
			ArrayList<String>ar2=new ArrayList<String>();
			String st;
			j=0;
			
			
			for(i=2;i<ar.size();i++)
			{
				//If there is a symbol then add that symbol to symtab.txt file
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
									//IF LITERAL THEN 
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
								            t++;	//to keep count of literal to allocate memory 
											j+=2;	//+2 to consider every 2nd operand
											
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
			//To insert literals after END statement
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
