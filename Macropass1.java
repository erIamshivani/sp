package assignment2;
//imcomplete macro type
//missing mend
//missing macro definition
//incomplete model statement
import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.SpringLayout.Constraints;

public class pass1 {
	static ArrayList<String>ar=new ArrayList<String>();
	
	static Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
	public static void main(String args[])
	{
		try{
		FileReader fr=new FileReader("MACRO");
		
		ArrayList<String>pnt=new ArrayList<String>();
		ArrayList<String>EVNT=new ArrayList<String>();
		ArrayList<String>SSNT=new ArrayList<String>();
		ArrayList<String>MDT=new ArrayList<String>();
		Map<Integer,Integer>SST = new HashMap();
		Map<String,String>KPDT = new HashMap();
		int sstp=1;
		
			String filecontents = "";
			int i,sr;
			int aa[]=new int[10];
			
			int mdtp=1,cnt=0;
			String name = null;
			int pp=0,pp_ptr=1,ev = 0,kpdtp = 0,kp=0,ssp=0;
			
			
		
			while((i=fr.read() )!=-1)
			{
				char ch=(char)i;
				filecontents =filecontents +ch;
			}
			
			String[] str=filecontents.split(System.lineSeparator());
			
			for(String a :str)
			{
				if(!a.isEmpty())
				{	
				ar.add(a);
				}
				
			}
		
			String st,st1;
			String[] op = null;
			int j=0;
			System.out.println("=======================================================================");
			for(i=0;i<ar.size();i++)
			{
				 List<Integer> para = new ArrayList<Integer>();
				if(ar.get(i).contains("MACRO"))
				{
					System.out.println("===Macro=="+ar.get(i)+ar.get(i+1));
							st=ar.get(i+1);
							op=st.split("\\s+");
						
						st=op[2];
						name=op[1];
									
					String[] op1=st.split(",");
					for(String a:op1)
					{
							if((!a.contains("=")))
							{
								System.out.println("For Pnt "+a);
							 op1=a.split("&");
							 pnt.add(op1[1]);
							 pp++;
							 pp_ptr++;
							 
							}
							else
							{
								op1=a.split("&|=");
								pnt.add(op1[1]);
								KPDT.put(op1[1], op1[2]);
								kp++;
								kpdtp++;
								pp_ptr++;
							}
						
							
					}
				
					i=i+1;
					
					
				}
				else if(!ar.get(i).contains("MEND"))
				{
				if(ar.get(i).contains("LCL")||ar.get(i).contains("GCL"))
				{
					
					String[] s=ar.get(i).split("&");
					EVNT.add(s[1]);
					ev++;
					
					for(j=0;j<EVNT.size();j++)
					{
						
						if(EVNT.get(j).equals(s[1]))
						{
							MDT.add("  "+s[0]+"  "+"(E,"+(j+1)+")");
						}
					}
				}
				else if(ar.get(i).matches("\\..*\\s.*"))
				{
					System.out.println("hhhh"+i);
					String s=ar.get(i).replaceAll("\\.*", "");
					
					String[] ss=s.split("\\s");
					String concat="";
					SSNT.add(ss[0]);
					ssp++;
					String[] s2 =ar.get(i).split("\\s+");
					int index=s2[2].indexOf(",");
					//int index2=s2[2].indexOf("+");
					String spechar=s2[2].replaceAll("[A-Za-z0-9]","").replaceAll("[&,]", "");
					
					//String[] 
					
					String[] s1 =s2[2].split("\\,|\\"+spechar);
					
					for(j=0;j<SSNT.size();j++)
					{
						if(ss[0].equals(SSNT.get(j)))
						{
							
							concat=concat+"(S,"+(j+1)+")";
							break;
						}
					}
					
					concat=concat+"\t"+s2[1]+"  ";
					for(int k=0;k<s1.length;k++)
					{
					if(s1[k].contains("&"))
					{
						
						for(j=0;j<EVNT.size();j++)
						{
							if(s1[k].contains(EVNT.get(j)))
							{
								concat=concat+"(E,"+(j+1)+")";
								if(k==0)
									concat=concat+",";
								if(k<(s1.length-1))
									concat=concat+spechar;
								break;
							}
						}
						for(j=0;j<pnt.size();j++)
						{
							if(s1[k].contains(pnt.get(j)))
							{
								concat=concat+"(P,"+(j+1)+")";
								if(k==0)
									concat=concat+",";
								else
									concat=concat+spechar;
								break;
							}
						}
						
						
						
					}
					}
					System.out.println("yyyyy"+concat);
					MDT.add(concat);
					
					
					
				}
				
				else if(ar.get(i).contains("SET"))
				{
					
					String[] s=ar.get(i).split("\\s+");
				
					
					for(j=0;j<EVNT.size();j++)
					{
						
						if(s[0].contains(EVNT.get(j)))
						{
							
							if(s[2].matches("[0-9]+"))
							{
							MDT.add("(E,"+(j+1)+")"+"   "+s[1]+"  "+s[2]);
							break;
							}
							if(s[2].contains(s[0]))
							{
								
								MDT.add("(E,"+(j+1)+")"+"   "+s[1]+"  "+"(E,"+(j+1)+")"+s[2].replaceAll("[A-Z]+","").replaceAll("&",""));
								break;
							}
						}
					}
				}
				
				else if(ar.get(i).contains("AIF"))
				{
					
					String[] s=ar.get(i).split("\\(|\\)");
					int counter;
					
					String[] s1=s[1].split("\\s+");
					String str1="  "+s[0];
					//MDT.add();
					//System.out.println(MDT.indexOf("  "+s[0]));
					for(int k=0;k<s1.length;k++)
					{
						if(s1[k].contains("&"))
						{
						String s2=s1[k].replaceAll("[^A-Z]", "");
						for(j=0;j<EVNT.size();j++)
						{
							
								
							if(s2.equals(EVNT.get(j)))
							{
								
								str1=str1+"  "+"(E,"+(j+1)+")"+"  ";
							}
							
							}
							
							 
						
						for(j=0;j<pnt.size();j++)
						{
							if(s2.equals(pnt.get(j)))
							{
								str1=str1+"  "+"(P,"+(j+1)+")"+"  ";
							}
						}
					}
					else
					{
						//MDT.add(s1[k]);
						
						str1=str1+s1[k];
						
						
						

					}
					}
					for( int t=0;t<SSNT.size();t++)
					{
						
						if(s[2].contains(SSNT.get(t)))
						{
							
							str1=str1+"  (S,"+(t+1)+") ";
						}
						
					}
					
					MDT.add(str1);
					
					
				}
				
				else 
				{
					System.out.println("Inside else...."+ar.get(i));
					String[] s =ar.get(i).split("\\s+");
					String[] s1 =s[2].split(",");
					//System.out.println(s1[0]+s1[1]);
					String concat="\t"+s[1]+"    ";
					for(int k=0;k<s1.length;k++)
					{
					if(s1[k].contains("&"))
					{
						for(j=0;j<EVNT.size();j++)
						{
							if(s1[k].contains(EVNT.get(j)))
							{
								//MDT.add("\t"+s[1]+"  "+"(E,"+(j+1)+"),"+s1[1]);
								concat=concat+"(E,"+(j+1)+")";
								if(k==0)
									concat=concat+",";
							}
						}
						for(j=0;j<pnt.size();j++)
						{
							if(s1[k].contains(pnt.get(j)))
							{
								
								//MDT.add("\t"+s[1]+"  "+"(P,"+(j+1)+"),"+s1[1]);
								concat=concat+"(P,"+(j+1)+")";
								if(k==0)
									concat=concat+",";

							}
						}
						
						System.out.println(s1[k]);
						
					}
					
					else
					{
						concat=concat+s1[k]+" ";
						if(k==0)
							concat=concat+",";
					}
					
					
					}
					MDT.add(concat);
					
					
				}
				
				}
				
				else
				{
					MDT.add("\tMEND\n");
					
					para.add(pp);
					para.add(ev);
					para.add(kp);
					para.add(mdtp);
					para.add(kpdtp);
					para.add(ssp);
					map.put(name, para);
					pp=0;ev=0;kp=0;mdtp=i;kpdtp=0;ssp=0;
					
				}
				
				
			}
			
			
			
		
			System.out.println("======PNT=========");
			for(i=0;i<pnt.size();i++)
			{
				System.out.println(i+" "+pnt.get(i));
				
			}
			
			
			for(i=2;i<ar.size();i++)
			{
				
			}
			
			System.out.println("======EVNTAB=========");
			for(i=0;i<EVNT.size();i++)
			{
				System.out.println(EVNT.get(i));
				
			}
			System.out.println("======SSNTAB=========");
			
			for(i=0;i<SSNT.size();i++)
			{
				System.out.println(SSNT.get(i));
				//ssp++;
				
			}
			
			System.out.println("======MDT=========");
			
			for(i=0;i<MDT.size();i++)
			{
				//if(ar.get(i).matches("\\..*\\s.*")
				System.out.println(MDT.get(i));
				if(MDT.get(i).matches("\\(S,.*"))
						{
							SST.put(sstp, (i+1));
							//System.out.println("Contents ====="+sstp+" "+(i+1));
							sstp++;
						}
				
				
			}
			
			System.out.println("======SSTAB=========");
			System.out.println("#key #value");
			Iterator iterator = SST.keySet().iterator();
			while (iterator.hasNext()) 
			{
				   int key = (int) iterator.next();
				  int  value = SST.get(key);
				  System.out.println(key + "\t" + value+"\n");
			
			}
			
			System.out.println("======KPDTAB=========");
			System.out.println("#key  #value");
			 iterator = KPDT.keySet().iterator();
			while (iterator.hasNext()) 
			{
				   String key = iterator.next().toString();
				   String value = KPDT.get(key).toString();
				  System.out.println(key + "\t" + value+"\n");
			
			}
			
			
			
			
			
			
			
			
			System.out.println("NAME  #pp #kp #EV #mdtr #kpdtr #sstab ");
			
			 for (Entry<String, List<Integer>> entry : map.entrySet()) {
		            String key = entry.getKey();
		            List<Integer> values = entry.getValue();
		           System.out.println(key+"   "+values);
		            //System.out.println("Values = " + values + "n");
		        }
			 
			
						
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
		

}