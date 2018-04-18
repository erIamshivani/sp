package assign1;

import java.io.BufferedReader;
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


public class error {

	public static void main( String args[])
	{
		try{
			FileReader fr=new FileReader("error.txt");
				        
			 ArrayList<String>ar=new ArrayList<String>();
			 
			 
			String filecontents = "";
			int i;
			
			while((i=fr.read() )!=-1)
			{
				char ch=(char)i;
				filecontents =filecontents +ch;
				
			}
			
			String[] str=filecontents.split(System.lineSeparator());
			
			for(String a :str)
			{
				if(!a.isEmpty())
				ar.add(a);
			}
			
			String AD[]={"START","END","LTORG","EQU","ORIGIN","STOP"};
			Iterator<String>itr=ar.iterator();
		
			FileReader fr1=new FileReader("menm.txt");
			ArrayList<String>ar1=new ArrayList<String>();
			 
			String filecontents1 = "";
			int j;
			
			while((j=fr1.read() )!=-1)
			{
				char ch=(char)j;
				filecontents1 =filecontents1 +ch;
			}
			
			String[] str1=filecontents1.split("\n");
			
			for(String a :str1)
			{
				ar1.add(a);
			}
			Iterator<String>itr1=ar1.iterator();
			
			int flag=0;
			
			//End of File exception
			flag=0;
			for( i=0;i<ar.size();i++)
			{
				
				if(ar.get(i).contains("END"))
				{
					
					flag=1;
					break;
				}
				else
					continue;
					
			}
			
			if(flag==0)
			{
				throw new Exception("Error: End of file");
				
			}
			
			j=0;
			String[] opc = null;
			//Incomplete Instruction
			for(i=0;i<ar.size();i++)
			{
				
				flag=0;
				if(!ar.get(i).contains(","))		
				{
					for(j=0;j<AD.length;j++)
					{
						flag=0;
						if(ar.get(i).contains(AD[j]))
						{
							break;
						}
						else
							flag=1;
					}
					
					if(flag==1)
					{
						throw new Exception(" Error: Instruction is Incomplete  ");
						
					}
				}
				
				//Mnemonic used as label
				if(ar.get(i).contains(":"))
				{
					String[] op=ar.get(i).split("\\:");
					
					for(j=0;j<ar1.size();j++)
					{
						opc=ar1.get(j).split("\\s+");
						
						if(op[0].equals(opc[0]))
						{
							throw new Exception("Mneumonic used as a label or symbol");
						}
						
					
					}
					
					
				}
			}
				flag=0;
				//Instruction not defined(DIV)
				for(i=0;i<ar.size();i++)
				{
					String op[]=ar.get(i).split("\\s+");
					
					for(j=0;j<ar1.size();j++)
					{
						String[] op1=ar1.get(j).split("\\s+");
						flag=0;
						if(op1[0].equalsIgnoreCase(op[1]))
						{
							
							flag=1;
							break;
						}
						
					}
					if(flag==0)
					{
						throw new Exception("INSTRUCTION NOT DEFINED!");
					}
				}
			
				
				//Undefined Symbol
			ArrayList<String>err=new ArrayList<String>();
			ArrayList<String>err1=new ArrayList<String>();
				for(i=0;i<ar.size();i++)
				{
					
					
					if(ar.get(i).contains(",")  )
					{
						String[] op=ar.get(i).split(",");
						if(op[1].matches("[A-Z]+"))
						{
							err.add(op[1]);
						}
						
					
						if(ar.get(i).contains(":"))
						{
							
							String[] s=ar.get(i).split(":");
							
							err1.add(s[0]);
							
						}
						
						
					}	
				}
				int flag1=0;
				
				for(j=0;j<err.size();j++)
				{
					
					for(i=0;i<err1.size();i++)
					{
						flag1=0;
						
						if(err.get(j).equals(err1.get(i)))
						{
							
							flag1=1;
							break;
						}
					}
					if(flag1==0)
					{
						
						throw new Exception("Symbol Not Defined ");
					}
						
				}
			
				
				
			
			System.out.println("Success");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
