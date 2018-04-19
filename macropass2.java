package macroPass2;

import java.io.*;
import java.util.*;

class  PNTAB{
	String para_name;
	int cnt=0;
}

class EVNTAB{
	String para_name;
	int cnt=0;
}

class SSNTAB{
	String para_name;
	int cnt=0;
}

class SSTAB{
	int para_line;
	int cnt=0;
}

class KPDTAB{
	String para_name;
	String default_value;
	int cnt=0;
}

class EVTAB{
	int default_value;
	static int ev_cnt=0;
}

class APTAB{
	String value;
	int cnt=0;
}

class MNT{
	String macro_name;
	int no_pp;
	int no_kp;
	int no_ev;
	int mdt_ptr;
	int kpd_ptr;
	int ss_ptr;
	
}

class MDT{
	String label=null;
	String opcode=null;
	String operands=null;
	int cnt=0;
}

class Out{
	String label=null;
	String opcode=null;
	String operands=null;
	int cnt=0;
}

public class pass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{

			ArrayList<PNTAB> pnt_array=new ArrayList<PNTAB>();		//ArrayList to store the PNTAB
			ArrayList<EVNTAB> evn_array=new ArrayList<EVNTAB>();
			ArrayList<SSNTAB> ssn_array=new ArrayList<SSNTAB>();
			ArrayList<SSTAB> ss_array=new ArrayList<SSTAB>();
			ArrayList<KPDTAB> kpd_array=new ArrayList<KPDTAB>();
			ArrayList<EVTAB> ev_array=new ArrayList<EVTAB>();
			ArrayList<MNT> mnt_array=new ArrayList<MNT>();
			ArrayList<MDT> mdt_array=new ArrayList<MDT>();
			ArrayList<APTAB> apt_array=new ArrayList<APTAB>();
			ArrayList<Out> out_array=new ArrayList<Out>();
			
			int s;
			String str=new String("");
			String str_pnt[]=new String[100];
			String str_kpd[]=new String[100];
			String str_kpd_sub[]=new String[100];
			String str_sample[]=new String[100];
			String str_sample_operands[]=new String[100];
			String str_mnt[]=new String[100];
			String str_mnt_sub[]=new String[100];
			String str_mdt[]=new String[100];
			String formal_para[]=new String[100];
			String token[]=new String[100];
			String token_sub[]=new String[100];
			String str_evn[]=new String[100];
			String str_ev[]=new String[100];
			String str_ss[]=new String[100];
			
			int macro_name_pos[]=new int[50];
			int pnt_cnt=0;
			int kpd_cnt=0;
			int formal_cnt=0;
			int evn_cnt=0;
			int ev_cnt=0;
			int ss_cnt=0;
			
			FileInputStream fin_pnt=new FileInputStream("/home/rheamadhusuthan/workspace/SPOS/macroPass2/src/macroPass2/PNTAB.txt");	
			FileInputStream fin_kpd=new FileInputStream("/home/rheamadhusuthan/workspace/SPOS/macroPass2/src/macroPass2/KPDTAB.txt");
			FileInputStream fin_sample=new FileInputStream("/home/rheamadhusuthan/workspace/SPOS/macroPass2/src/macroPass2/sample.txt");
			FileInputStream fin_mnt=new FileInputStream("/home/rheamadhusuthan/workspace/SPOS/macroPass2/src/macroPass2/MNT.txt");
			FileInputStream fin_mdt=new FileInputStream("/home/rheamadhusuthan/workspace/SPOS/macroPass2/src/macroPass2/MDTAB.txt");
			FileInputStream fin_evn=new FileInputStream("/home/rheamadhusuthan/workspace/SPOS/macroPass2/src/macroPass2/EVNTAB.txt");
			FileInputStream fin_ev=new FileInputStream("/home/rheamadhusuthan/workspace/SPOS/macroPass2/src/macroPass2/EVTAB.txt");
			FileInputStream fin_ss=new FileInputStream("/home/rheamadhusuthan/workspace/SPOS/macroPass2/src/macroPass2/SSTAB.txt");
			
			
			
			//==============================SSTAB===============================
			
			while((s=fin_ss.read())!=-1)
			{
				str=str+(char)s;
			}
			str_ss=str.split("\\n");
			System.out.println("======================================================");
			System.out.println("The SSTAB : ");
			for(int i=0;i<str_ss.length;i++)
			{
				
				SSTAB ss=new SSTAB();				
				ss.para_line=Integer.parseInt(str_ss[i]);
				System.out.println(ss.para_line);
				ss.cnt=pnt_cnt;
				ss_array.add(ss);
				
				ss_cnt++;
			}
			
			
			
			//==============================PNTAB===============================
			
			str="";
			s=0;
			while((s=fin_pnt.read())!=-1)
			{
				str=str+(char)s;
			}
			str_pnt=str.split("\\n");
			System.out.println("======================================================");
			System.out.println("The PNTAB : ");
			for(int i=0;i<str_pnt.length;i++)
			{
				
				PNTAB pnt=new PNTAB();				
				pnt.para_name=str_pnt[i];
				System.out.println(pnt.para_name);
				pnt.cnt=pnt_cnt;
				pnt_array.add(pnt);
				
				pnt_cnt++;
			}

			//==============================EVTAB===============================
//			s=0;
//			str="";
//			while((s=fin_ev.read())!=-1)
//			{
//				str=str+(char)s;
//			}
//			str_ev=str.split("\\n");
//			System.out.println("======================================================");
//			System.out.println("The EVTAB : ");
//			for(int i=0;i<str_ev.length;i++)
//			{
//				
//				EVTAB ev=new EVTAB();				
//				ev.default_value=str_ev[i];
//				System.out.println(ev.default_value);
//				ev.cnt=ev_cnt;
//				ev_array.add(ev);
//				ev_cnt++;
//			}
			
			//==============================EVNTAB===============================
			s=0;
			str="";
			while((s=fin_evn.read())!=-1)
			{
				str=str+(char)s;
			}
			str_evn=str.split("\\n");
			System.out.println("======================================================");
			System.out.println("The EVNTAB : ");
			for(int i=0;i<str_evn.length;i++)
			{
				EVNTAB evn=new EVNTAB();				
				evn.para_name=str_evn[i];
				evn.cnt=evn_cnt;
				System.out.println(evn.para_name);
				evn_array.add(evn);
				evn_cnt++;
			}
			
			
			//==============================KPDTAB===============================
			str="";
			s=0;
			while((s=fin_kpd.read())!=-1)
			{
				str=str+(char)s;
			}
			str_kpd=str.split("\\n");
			
			System.out.println("\n======================================================");
			System.out.println("The KPDTAB : ");
			for(int i=0;i<str_kpd.length;i++)
			{
				str_kpd_sub=str_kpd[i].split("\\s");
				KPDTAB kpd=new KPDTAB();
				kpd.para_name=str_kpd_sub[0];
				kpd.default_value=str_kpd_sub[1];
				System.out.println(kpd.para_name+" "+kpd.default_value);
				kpd.cnt=kpd_cnt;
				kpd_array.add(kpd);
				kpd_cnt++;
			}

			//==============================MNTAB===============================
			str="";
			s=0;
			while((s=fin_mnt.read())!=-1)
			{
				str=str+(char)s;
			}
			str_mnt=str.split("\\n");
			
			System.out.println("\n======================================================");
			System.out.println("The MNTAB : ");
			for(int i=0;i<str_mnt.length;i++)
			{
				str_mnt_sub=str_mnt[i].split("\\s");
				MNT mnt=new MNT();
				mnt.macro_name=str_mnt_sub[0];
				mnt.no_pp=Integer.parseInt(str_mnt_sub[1]);
				mnt.no_kp=Integer.parseInt(str_mnt_sub[2]);
				mnt.no_ev=Integer.parseInt(str_mnt_sub[3]);
				mnt.mdt_ptr=Integer.parseInt(str_mnt_sub[4]);
				mnt.kpd_ptr=Integer.parseInt(str_mnt_sub[5]);
				mnt.ss_ptr=Integer.parseInt(str_mnt_sub[6]);
				System.out.println(mnt.macro_name+" "+mnt.no_pp+" "+mnt.no_kp+" "+mnt.no_ev+" "+mnt.mdt_ptr+" "+mnt.kpd_ptr+" "+mnt.ss_ptr);
				mnt_array.add(mnt);
			}

			
			//==============================MDTAB===============================
			str="";
			s=0;
			while((s=fin_mdt.read())!=-1)
			{
				str=str+(char)s;
			}
			str_mdt=str.split("\\n");
			
			System.out.println("\n======================================================");
			System.out.println("The MDTAB : ");
			
			//==============================APTAB===============================
			s=0;
			str="";
			while((s=fin_sample.read())!=-1)
			{
				str=str+(char)s;
			}
			str_sample=str.split("\\n");
			
			for(int i=0;i<str_sample.length;i++)
			{
				if(str_sample[i].contains(mnt_array.get(0).macro_name))
				{
					str_sample_operands=str_sample[i].split("\\s|,");
					break;
				}
			}
			System.out.println("\n======================================================");
			System.out.println("Formal Parameters : ");
			for(int i=0,j=0;i<str_sample_operands.length;i++)
			{
				if(i!=0)
				{
					formal_para[j++]=str_sample_operands[i];
					System.out.println(str_sample_operands[i]);
					formal_cnt++;
				}
			}
			
			for(int i=0;i<formal_cnt;i++)
			{
				APTAB apt=new APTAB();
				apt.value=formal_para[i];
				//apt.cnt++;
				apt_array.add(apt);
			}
			
			if(formal_cnt<(pnt_cnt))
			{
				for(int i=0;i<(pnt_cnt-formal_cnt);i++)
				{
					for(int j=0;j<pnt_cnt;j++)
					{
						if(kpd_array.get(i).para_name.equals(pnt_array.get(j).para_name))
						{
							APTAB apt=new APTAB();
							apt.value=kpd_array.get(i).default_value;
							//apt.cnt++;
							apt_array.add(apt);
						}
					}
				}
			}
			
			System.out.println("\n======================================================");
			System.out.println("The APTAB :");
			for(APTAB ap:apt_array)
			{
				System.out.println(ap.value);
			}
			
			//=========================Macro Expansion===================================

			System.out.println("\n======================================================");
			System.out.println("The Final Expansion :");

			int pos;
			int i=0;
			ev_cnt=0;
			//for(int i=0;i<str_mdt.length;i++)
			while(i<str_mdt.length)	
			{
				
				if(str_mdt[i].contains("SET"))
				{
					String temp="";
					pos=0;
					//System.out.println("Contains SET");
					String[]token_set=str_mdt[i].split("\\s");
					if(token_set.length==3)
					{
						int pos_p=token_set[0].indexOf("E");
						pos=pos_p+2;
						//System.out.println("Hello :"+pos);
						temp=Character.toString(token_set[0].charAt(3));
						int value=Integer.parseInt(temp);
						
						EVTAB ev=new EVTAB();
						if(token_set[2].contains("("))
						{
							pos_p=token_set[2].indexOf("E");
							pos=pos_p+2;
							//System.out.println("Hello :"+pos);
							temp=Character.toString(token_set[0].charAt(3));
							value=Integer.parseInt(temp);
							int m=EVTAB.ev_cnt;
							//temp=ev_array.get(m).default_value;
							int temp_value=ev_array.get(m).default_value;
							//System.out.println("HELLO "+value);
							
							pos_p=token_set[2].indexOf("+");
							pos=pos_p+1;
							String intermeiate_value=Character.toString(token_set[2].charAt(pos));
							int intermediate_value1=Integer.parseInt(intermeiate_value);
							temp_value=temp_value+intermediate_value1;
							ev.default_value=temp_value;
						}
						else
						{
							ev.default_value=Integer.parseInt(token_set[2]);
						}
						
						EVTAB.ev_cnt=ev_cnt;
						ev_array.add(ev);
						ev_cnt++;

						
					}
					else
					{
						System.out.println("Syntax wrong for token statement");
					}
					
					
				}
				else if(str_mdt[i].contains("AGO"))
				{
					//System.out.println("Contains AGO");
				}
				else if(str_mdt[i].contains("AIF"))
				{
				//	System.out.println("Contains AIF");
					
					String temp="";
					int value_first=0,value_second=0,flag=0;;
					
					String[] token_aif=str_mdt[i].split("\\s");
					if(token_aif[1].contains("E"))
					{
						int pos_p=token_aif[1].indexOf("E");
						pos=pos_p+2;
						
						temp=Character.toString(token_aif[1].charAt(4));
						value_first=Integer.parseInt(temp);
						value_first=ev_array.get(EVTAB.ev_cnt).default_value;
						

					}
					if(token_aif[3].contains("P"))
					{
						int pos_p=token_aif[3].indexOf("P");
						pos=pos_p+2;
						
						int temp1=Integer.parseInt(Character.toString(token_aif[3].charAt(3)));
						APTAB ap=new APTAB();
						temp=apt_array.get(temp1-1).value;
						value_second=Integer.parseInt(temp);
						//System.out.println("HELLO "+temp);
					}
					
					if(token_aif[2].contains("NE"))
					{
						if (value_second!=value_first)
						{
							//continue;
							//System.out.println("HELLO "+ss_array.size());
							i=ss_array.get(0).para_line;
							continue;
						}
					}
					else
					{
						System.out.println("END OF MACRO");
					}
					
				}
				else if(str_mdt[i].contains("MOVER") ||str_mdt[i].contains("MOVEM")||str_mdt[i].contains("ADD")||str_mdt[i].contains("MULT"))
				{
					pos=0;
					String final_stmt=new String();
					String new_token=new String();
					char temp;
					int pos_p=-1;
					int mec=-1;
					token=str_mdt[i].split("\\s");
					//System.out.println("Length of "+token.length);
					
					if(token.length==2)
					{
						//for(int j=0;j<token.length;j++)
						//{
							
							while(token[1].contains("(P"))
							{
								pos_p=token[1].indexOf("P");
								pos=pos_p+2;

								temp=token[1].charAt(pos);
								mec=Character.getNumericValue(temp);
								new_token=apt_array.get(mec-1).value;
								//System.out.println("Rhea : "+new_token);
								
								int startIndex = token[1].indexOf("(");
								int endIndex = token[1].indexOf(")");
								String toBeReplaced = token[1].substring(startIndex , endIndex+1);
								token[1]=(token[1].replace(toBeReplaced, new_token));
													
								
							}
							//System.out.println(token[1]);
//							final_stmt=token[0]+" "+token[1];
//							System.out.println(final_stmt);
							if(token[1].contains("(E"))
							{
								
								pos_p=token[1].indexOf("E");
								pos=pos_p+2;
								//new_token=ev_array.get(pos-1).default_value;
								
								temp=token[1].charAt(pos);
								//mec=Character.getNumericValue(temp);
								mec=EVTAB.ev_cnt;
								new_token=Integer.toString(ev_array.get(mec).default_value);
								
								int startIndex = token[1].indexOf("(");
								int endIndex = token[1].indexOf(")");
								String toBeReplaced = token[1].substring(startIndex , endIndex+1);
								token[1]=(token[1].replace(toBeReplaced, new_token));
								//System.out.println(token[1]);
							}
							final_stmt=token[0]+" "+token[1];
							System.out.println(final_stmt);

						}
					//}
				}
				
			i++;	
			}
			
			
			System.out.println("\n=======================================================================");
			System.out.println("EVTAB after set");
			for(EVTAB evt:ev_array)
			{
				System.out.println(evt.default_value);
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}