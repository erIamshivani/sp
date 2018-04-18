package assignment2;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class pass2 {
	
	
	static ArrayList<String>ar=new ArrayList<String>();
	static ArrayList<String>ar1=new ArrayList<String>();
	static ArrayList<String>apt=new ArrayList<String>();
	static ArrayList<String>pnt=new ArrayList<String>();
	
	public static void main(String args[])
	{
		try{
			FileReader fr=new FileReader("macrocall.txt");
		 FileReader fr1=new FileReader("mnt.txt");
			FileReader fr2=new FileReader("kpdttab.txt");
			FileReader fr3=new FileReader("pnt");
			FileReader fr4=new FileReader("mdt.txt");
		ArrayList<String>pnt2=new ArrayList<String>();
		/*ArrayList<String>SSNT=new ArrayList<String>();
		ArrayList<String>MDT=new ArrayList<String>();
		*/
		Map<String,Integer>formalpara = new HashMap();
		Map<String,String>KPDT = new HashMap();
		int sstp=1;
		
		System.out.println("====Reading Macrocall.txt file======");
			String filecontents = "";
			int j;
			
		
			while((j=fr.read() )!=-1)
			{
				char ch=(char)j;
				filecontents =filecontents +ch;
			}
			
			String[] str=filecontents.split("\\n|\t");
			
			for(String a :str)
			{
				if(!a.isEmpty())
				{	
				ar.add(a);
				}
				
			}
			
			for(int i=0;i<ar.size();i++)
			{
				System.out.println(i+" "+ar.get(i));
			}
			
			//====Reading MNT.txt file======

			String filecontents1 = "";
			
			
		
			while((j=fr1.read() )!=-1)
			{
				char ch=(char)j;
				filecontents1 =filecontents1 +ch;
			}
			
			
			
			 str=filecontents1.split("\\n|\t");
			
			for(String a :str)
			{
				if(!a.isEmpty())
				{	
				ar1.add(a);
				}
				
			}
			
			System.out.println("====Reading KPDTTB.txt file======");

			String filecontents2 = "";
			
			
		
			while((j=fr2.read() )!=-1)
			{
				char ch=(char)j;
				filecontents2 =filecontents2 +ch;
			}
			
			
			
			
			
			System.out.println("====Reading pnt.txt file======");

			String filecontents3 = "";
			
			
		
			while((j=fr3.read() )!=-1)
			{
				char ch=(char)j;
				filecontents3 =filecontents3 +ch;
			}
			
			System.out.println("====Reading MDT.txt file======");

			String filecontents4 = "";
			
			
		
			while((j=fr4.read() )!=-1)
			{
				char ch=(char)j;
				filecontents4 =filecontents4 +ch;
			}
			
			
			
		
			for(int i=0;i<ar1.size();i++)
			{
				System.out.println(i+" "+ar1.get(i));
			}
			
			System.out.println("====================");
			
			j=1;
			str=filecontents2.split("\\n");
			String[] st1=ar1.get(j).split("\\s+");
				
				for(int i=0;i<ar.size();i++)
				{
					String[] st=ar.get(i).split("\\s+");
					
					
					if(st1[0].equals(st[0]))
					{
						
						String[] call=st[1].split(",");
						for(String a:call)
						{
							apt.add(a);
						}
						if(st1[0].equals("CLEAR"))
						{
						for(String a:str)
						 {
							 if(!a.contains("#"))
							 {
								String[] kp=a.split("\\s+");
								 apt.add(kp[1]);
							 }
						 }
						}
					 st1=ar1.get(j+1).split("\\s+");
						
						
						
					}
					
				}
			
				
				 
				
			 
			 
			 String[] par=filecontents3.split("\\n+");
			 int mdt_ptr[]=new int[2];
			 String name[]=new String[2];
			 for(int i=1;i<ar1.size();i++)
			 {
				 
				 String[] st=ar1.get(i).split("\\s+");
				name[i-1]=st[0];
				 String s1=st[1].replaceAll("[^0-9+]", "");
				 String s2=st[2].replaceAll("[^0-9+]", "");
				 String s3=st[4].replaceAll("[^0-9+]", "");
				
				 int pp=Integer.parseInt(s1)+Integer.parseInt(s2);
				mdt_ptr[i-1]=Integer.parseInt(s3)-1;
				
				 for(int k=0;k<pp;k++)
				 {
					 pnt.add(par[k]);
				 }
				 pnt.add("Break\n");
				 
			 }
			 
			System.out.println("========Actual Parameter Table=========");
			
			for(int i=0;i<apt.size();i++)
			{
				System.out.println(apt.get(i));
				
			}
			
			System.out.println("========Macro call=====");
			int i=0;
			while(i<apt.size()&&(!pnt.get(i).contains("Break")))
			{
				pnt.set(i, apt.get(i));
				i++;
			}
		
			System.out.println("=====MDT Tokenazation=====");
			String[] mdt=filecontents4.split("\\n+");
			int l=0;
			for(String a:mdt)
			{
				System.out.println(l+"  "+a);
				l++;
			}
			
			
			j=1;
			int k=mdt_ptr[0];
			int m=mdt_ptr[1];
			int pnt_ptr=0,ev=0;
		st1=ar1.get(j).split("\\s+");
				
			for(i=0;i<ar.size();i++)
			{
				String[] st=ar.get(i).split("\\s+");
				
				if(st1[0].equals(st[0]))
				{
					if( st1[0].contains(name[0]))
					{
						
						while(k<m)
						{
							String m1=mdt[k];
							String concat="";
						if((!m1.contains("SET")) && (!m1.contains("LCL")))
						{
							
							if(m1.matches(".*\\(.*"))
							{
								String[] sp=m1.split("\\(+|\\)+");
								
								
								if(sp[1].contains("P"))
								{
									String index=sp[1].replaceAll("[^0-9+]", "");
									concat=concat+sp[0];
									while(!pnt.get(pnt_ptr).contains("Break"))
									{
										int ind=Integer.parseInt(index);
										
										if((pnt_ptr+1)==ind)
										{
											//System.out.println(pnt.get(pnt_ptr));
											concat=concat+pnt.get(pnt_ptr);
											pnt_ptr=0;
											break;
										}
										pnt_ptr++;
									}
									concat=concat+sp[2];
									System.out.println(concat);
								}
							
								if(sp[1].contains("S"))
								{
									ev=0;
									while(ev<=2)
									{
									String index=sp[3].replaceAll("[^0-9+]", "");
									concat=concat+sp[2];
									while(!pnt.get(pnt_ptr).contains("Break"))
									{
										int ind=Integer.parseInt(index);
										
										if((pnt_ptr+1)==ind)
										{
											//System.out.println(pnt.get(pnt_ptr));
											concat=concat+pnt.get(pnt_ptr)+" ";
											concat=concat+sp[4];
											pnt_ptr=0;
											
											index=sp[5].replaceAll("[^0-9+]", "");
											while(!pnt.get(pnt_ptr).contains("Break"))
											{
												ind=Integer.parseInt(index);
												
												if((pnt_ptr+1)==ind)
												{
													//System.out.println(pnt.get(pnt_ptr));
													concat=concat+pnt.get(pnt_ptr)+" ";
													pnt_ptr=0;
													concat=concat+sp[6]+" ";
													concat=concat+ev;
													System.out.println(concat);
													ev++;
													
												
													
													
													break;
												}
												
												
												pnt_ptr++;
												break;
											}
											
											break;
										}
										
										pnt_ptr++;
										
									}
									}
									
									
								
							}

								
										
							}
						}
						k++;
						}
					}
					//else if for cube
					
					/*else if(st1[0].contains(name[1]))
					{
						
						
							
							while(m<ar.size())
							{
								String m1=mdt[m];
								String concat="";
							if((!m1.contains("SET")) && (!m1.contains("LCL")))
							{
								
								if(m1.matches(".*\\(.*"))
								{
									String[] sp=m1.split("\\(+|\\)+");

									
									if(sp[1].contains("P"))
									{
										String index=sp[1].replaceAll("[^0-9+]", "");
										concat=concat+sp[0];
										while(!pnt.get(pnt_ptr).contains("Break"))
										{
											int ind=Integer.parseInt(index);
											
											if((pnt_ptr+1)==ind)
											{
												//System.out.println(pnt.get(pnt_ptr));
												concat=concat+pnt.get(pnt_ptr);
												pnt_ptr=0;
												break;
											}
											pnt_ptr++;
										}

										System.out.println(concat);
									}
								
									

									
											
								}
							}
							k++;
							}
						}*/
					 st1=ar1.get(j+1).split("\\s+");
					
				}
				
				
				
				else
				{
					System.out.println(ar.get(i));
					
					
				}
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
}
	
	public static void macroexpansion()
	{
		
	}
}