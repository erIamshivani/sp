package bankers;

import java.util.Scanner;

public class bankers {
	
	static  int aval[];
	static int need[][];
	static int res,pid;
	static int tot[];
	static int inst[];
	static int alloc[][];
	static int max[][];
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		
		
		System.out.println("Enter number of resources and processes");
		res=sc.nextInt();
		pid=sc.nextInt();
		
		 inst=new int[res];//Max instances
		 tot=new int[res];//Total
		max=new int[pid][res];//Max requirement array
		alloc=new int[pid][res];//allocated resources 
		need=new int[pid][res];
		aval=new int[res];
		
		System.out.println("Enter MAX instances ");
		for(int i=0;i<res;i++)
		{
			inst[i]=sc.nextInt();
			tot[i]=0;	//Initialize tot array
		}
		
		//Allocated Resources
		for(int i=0;i<pid;i++)
		{
			System.out.println("Enter Allocated resources for P"+i);
			for(int j=0;j<res;j++)
			{
				alloc[i][j]=sc.nextInt();
				tot[j]=tot[j]+alloc[i][j];
			}
			
			System.out.println("Max requirement for P "+i);
			
			for(int j=0;j<res;j++)
			{
				max[i][j]=sc.nextInt();
			}
		}
		
		input();
		 
		safe_seq();
		
		
		
		System.out.println("=============================================================");
		
		int p;
		int res1[]=new int[res];
		System.out.println("Enter process number");
		p=sc.nextInt();
		System.out.println("Enter requesting resources");
		for(int i=0;i<res;i++)
		{
			res1[i]=sc.nextInt();
		}
		
		for(int i=0;i<res;i++)
		{
			alloc[p][i]=alloc[p][i]+res1[i];
			tot[i]=0;
		}
		
		for(int i=0;i<pid;i++)
		{
			for(int j=0;j<res;j++)
			{
				tot[j]=tot[j]+alloc[i][j];
			}
		}
		
		input();
		
		safe_seq();
		
		
		
		System.out.println("Completed================================");
		
	}
	

	
	public static void input()
	{

		//printing tot matrix 
		System.out.println("Total resources");
		for(int i=0;i<res;i++)
		{
			System.out.println(tot[i]);
		}
		//calculation of available resources
		
		
		for(int i=0;i<res;i++)
		{
			aval[i]=inst[i]-tot[i];
			
		}
		
		//Calculation of need matrix
		
		for(int i=0;i<pid;i++)
		{
			
			for(int j=0;j<res;j++)
			{
				need[i][j]=max[i][j]-alloc[i][j];
				
			}
			
			
		}
		
		//Printing Need Matrix
		for(int i=0;i<pid;i++)
		{
			for(int j=0;j<res;j++)
			{
				System.out.print(need[i][j]+"\t");
			}
			System.out.println("\n");
		}
		
		

	}

	public static void safe_seq()
	{
		
		//Calculation of Safe sequence
	
		boolean[] done=new boolean[pid];
		int j=0;
		
		while(j<pid)
		{
			
			boolean allocated=false;
			
			for(int i=0;i<pid;i++)
			{
				
				if(!done[i] && check(i))
				{
					
					for(int k=0;k<res;k++)
					{
						aval[k]=aval[k]+max[i][k]-need[i][k];
						System.out.println(aval[k]);
					}
					
					System.out.println("Allocated process :P "+i);
					j++;
					allocated=done[i]=true;
				}
			}
			
			if(!allocated)
				break;
			
		}
	}
	
	
	public static boolean check(int i)
	{
		for(int j=0;j<res;j++) 
		       if(aval[j]<need[i][j])
		          return false;
		   
		    return true;
	
		
	}
}
