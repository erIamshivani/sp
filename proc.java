package prac;

import java.util.ArrayList;
import java.util.Scanner;

public class proc {
	static Scanner sc =new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		int ch;
		System.out.println("1.FCFS 2.RR 3.SRTF 4.Prio");
		ch=sc.nextInt();
		switch(ch)
		{
		case 1: fcfs();
				break;
				
		case 2: rr(); break;
		
		case 3: srtf(); break;
		
		case 4: prio(); break;
		}
	}
	
	public static void fcfs()
	{
		System.out.println("total process:");
		int total = sc.nextInt();
		Proc[] process = new Proc[total];
		System.out.println("Enter arrival and burst:");
		for(int i=0;i<total;i++)
		{
			int a =sc.nextInt();
			int b= sc.nextInt();
			process[i] =new Proc(a,b);
		}
		int x=0, sumtt=0,sumwt=0;
		System.out.println("Process\tArrival\tBurst\tTurn Ar\tCompletion\tWaiting");
		for(int i=0;i<process.length;i++)
		{
			x= x+process[i].bur;
			process[i].comp = x;
			process[i].tt = process[i].comp - process[i].arr;
			process[i].wt =  process[i].tt -  process[i].bur;
			
			sumtt = sumtt + process[i].tt;
			sumwt = sumwt + process[i].wt;
			
			
			System.out.println(i+"\t"+process[i].arr+"\t"+process[i].bur+"\t"+process[i].tt+"\t"+
			process[i].comp+"\t"+process[i].wt);
			
			float w,t;
			w=(sumwt /total);
			t=(sumtt /total);
			System.out.println("Avg Turn Around: "+ t);
			System.out.println("Avg WT: "+ w);
		}
	}
	public static void rr()
	{
		System.out.println("total process:");
		int total = sc.nextInt();
		Proc[] process = new Proc[total];
		System.out.println("Enter arrival and burst:");
		
		for(int i=0;i<total;i++)
		{
			int a =sc.nextInt();
			int b= sc.nextInt();
			process[i] =new Proc(a,b);
		}
		System.out.println("enter time slice:");
		int time= sc.nextInt();
		
		int x=0, sumtt=0,sumwt=0;
		//System.out.println("Process\tArrival\tBurst\tTurn Ar\tCompletion\tWaiting");
		
		int pro=total,i=0,CT=0;
		ArrayList exec = new ArrayList();
		while(pro!=0)
		{
			if(process[i].bur > time)
			{
				process[i].bur = process[i].bur-time;
				CT=CT + time;
				process[i].comp=CT;
				process[i].tt = process[i].comp - process[i].arr;			//--check this 
				process[i].wt = process[i].tt - process[i].bur;				//and this
				
				sumtt = sumtt + process[i].tt;
				sumwt = sumwt + process[i].wt;
				exec.add(" (P"+(i+1)+") "+ CT);
			}
			
			else if(process[i].bur<=time && process[i].bur >0)
			{
				CT=CT + process[i].bur;
				process[i].bur= process[i].bur - process[i].bur;
				
				process[i].comp=CT;
				process[i].tt = process[i].comp - process[i].arr;			//--check this 
				process[i].wt = process[i].tt - process[i].bur;				//--check this 
				
				sumtt = sumtt + process[i].tt;
				sumwt = sumwt + process[i].wt;
				pro--;
				exec.add(" (P"+(i+1)+") "+ CT );
			}
			i++;
			if(i==total)	
			{
				i=0;
			}
		}
		
		System.out.print("0");
		for(int k=0;k<exec.size();k++)
		{
			System.out.print(exec.get(k) + " ");
		}
		
		float w,t;
		w=(sumwt /total);
		t=(sumtt /total);
		System.out.println("Avg Turn Around: "+ t);
		System.out.println("Avg WT: "+ w);
	}
	public static void srtf()
	{
		System.out.println("total process:");
		int total = sc.nextInt();
		Proc[] process = new Proc[total];
		
		for(int q=0;q<total;q++)
		{
			System.out.println("Enter arrival and burst:");
			int a =sc.nextInt();
			int b= sc.nextInt();
			process[q] =new Proc(a,b);
		}
		
		int x=0;
		ArrayList exe =new ArrayList();
		
		//-----------logic----------------------

		int count=0;
		
		while(count!=total)
		{
			int min=999;		//to get smallest burst every time
			int pos=0;
			
			for(int i=0;i<total;i++)
			{
				if(process[i].flag!=1)
				{
					if(process[i].bur < min)
					{
						min=process[i].bur;
						pos=i;
					}
				}
			}
			
			process[pos].start =x;
			process[pos].finish = process[pos].start + process[pos].bur;
			process[pos].flag=1;
			x=x+ process[pos].bur;
			exe.add(process[pos].start + " (P" + pos+ ") " + process[pos].finish + " | ");
			
			count++;
		}
		
		for(int k=0;k<exe.size();k++)
		{
			System.out.print(exe.get(k) + " ");
		}
		
		
	}
	public static void prio()
	{
		System.out.println("total process:");
		int total = sc.nextInt();
		Proc[] process = new Proc[total];
		System.out.println("Enter burst and priorities:");
		for(int i=0;i<total;i++)
		{
			int a =sc.nextInt();
			int b= sc.nextInt();
			process[i] =new Proc((i+1), a,b);
		}
		
		int temp,x=0, sumtt=0,sumwt=0;
		for(int i=0; i<total;i++)
		{
			for(int j=0;j<total-i-1;j++)
			{
				
				if(process[j].pri > process[j+1].pri)
				{
					temp=process[j].pri;
					process[j].pri = process[j+1].pri;
					process[j+1].pri = temp;
					
					temp=process[j].bur;
					process[j].bur = process[j+1].bur;
					process[j+1].bur = temp;
					
					temp=process[j].arr;
					process[j].arr = process[j+1].arr;
					process[j+1].arr = temp;
				}
			}
		}
		System.out.println("After Sorting:");
		System.out.println("Process\tBurst\tPriority");
		for(int i=0;i<total ; i++)
		{
			System.out.println(process[i].arr + "\t" + process[i].bur + "\t" + process[i].pri);
		}
		
		System.out.println("Process\tArrival\tBurst\tTurn Ar\tCompletion\tWaiting");
		 ArrayList arr= new ArrayList();
		 
		for(int i=0;i<process.length;i++)
		{
			x= x+process[i].bur;
			process[i].comp = x;
			process[i].tt = process[i].comp - process[i].arr;
			process[i].wt =  process[i].tt -  process[i].bur;
			
			sumtt = sumtt + process[i].tt;
			sumwt = sumwt + process[i].wt;
			
			arr.add(" (P" + (i+1) + ") "+process[i].comp);
			System.out.println(i+"\t"+process[i].arr+"\t"+process[i].bur+"\t"+process[i].tt+"\t"+
			process[i].comp+"\t"+process[i].wt);
			
			
		}
		System.out.println("0");
		for(int k=0;k<arr.size();k++)
		{
			System.out.print(arr.get(k) + " ");
		}
		float w,t;
		w=(sumwt /total);
		t=(sumtt /total);
		System.out.println("Avg Turn Around: "+ t);
		System.out.println("Avg WT: "+ w);
		
	}

}

class Proc
{
	int comp=0,bur=0,arr=0,tt=0,wt=0,pri=0;
	int start=0,finish=0,flag=0;
	Proc(int a,int b)
	{
		arr=a;
		bur=b;
	}
	Proc(int a,int b ,int p)
	{
		arr=a;
		bur=b;
		pri=p;
	}
}
