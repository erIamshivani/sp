import java.util.*;
import java.io.*;



public class Main {
	
	public static boolean safeSeq(int resNo,int procNo,boolean[] fin, int[] avail,int[][] need,int[][] alloc){
		int check =0;
		int flag=0;
		int[] work = new int[resNo];
		
		for(int i=0;i<resNo;i++){
			work[i] = avail[i];
		}
		
		System.out.println("\nSafe Sequence: \n");
		while(check!=procNo)
		{
			for(int i=0;i<procNo;i++)
			{
				if(fin[i]!=true)
				{
					for(int j=0;j<resNo;j++)
					{
						if(need[i][j]<work[j])
						{
							flag =0;
						}
						else
						{
							flag=1;
							break;
						}
					}
					
					if(flag==0)
					{
						for(int j=0;j<resNo;j++)
						{
							work[j] = work[j] + alloc[i][j];
							
						}
						fin[i] = true;
						check++;
						System.out.print("\tP"+i);
					}
				}
			}
		}
		if(check==procNo){
			return true;
		}
		else 
			return false;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nEnter the number of resources");
		int resNo = sc.nextInt();
		
		System.out.println("\nEnter the number of processes");
		int procNo = sc.nextInt();
		
		int[][] alloc = new int[procNo][resNo];
		int[][] max = new int[procNo][resNo];
		int[][] need = new int[procNo][resNo];
		
		int[] avail = new int[resNo];
		boolean[] fin = new boolean[procNo];
		
		
	//-------------------------------	res
		System.out.println("\nEnter the available resources");
		for(int i=0;i<resNo;i++){
			avail[i] = sc.nextInt();
		}
	//-------------------------------
		
	//-------------------------------alloc
		
		System.out.println("\nEnter the allocation matrix");
		for(int i=0;i<procNo;i++){
			for(int j=0;j<resNo;j++){
				alloc[i][j] = sc.nextInt();
			}
		}
	//-------------------------------
		
	//-------------------------------max
		
		System.out.println("\nEnter the max matrix");
		for(int i=0;i<procNo;i++){
			for(int j=0;j<resNo;j++){
				max[i][j] = sc.nextInt();
			}
		}
	//-------------------------------
		
		
	//-------------------------------need
		
	
		for(int i=0;i<procNo;i++){
			for(int j=0;j<resNo;j++){
				need[i][j] = max[i][j] - alloc[i][j];
			}
		}
	//-------------------------------
		
	//-------------------------------	res dis
			System.out.println("\nAvailable resources");
			for(int i=0;i<resNo;i++){
				System.out.println("\tR"+i+" : "+avail[i]);
			}
		//-------------------------------


	//------------------------------- alloc dis
		
		System.out.println("\nThe allocation matrix is: ");
		for(int i=0;i<procNo;i++){
			System.out.println("\n");
			for(int j=0;j<resNo;j++){
				System.out.print("\t"+alloc[i][j]);
			}
		}
	//--------------------------------
		
	//------------------------------- max dis
		
		System.out.println("\nThe max matrix is: ");
		for(int i=0;i<procNo;i++){
			System.out.println("\n");
			for(int j=0;j<resNo;j++){
				System.out.print("\t"+max[i][j]);
			}
		}
	//--------------------------------
		
	//-------------------------------need dis
		
		System.out.println("\nThe need matrix is: ");
		for(int i=0;i<procNo;i++){
			System.out.println("\n");
			for(int j=0;j<resNo;j++){
				System.out.print("\t"+need[i][j]);
			}
		}
	//--------------------------------
		
		

		
		safeSeq(resNo,procNo,fin,avail,need,alloc);
		
		int[] req = new int[resNo];
		System.out.println("\nEnter the process number for request: ");
		int pro = sc.nextInt();
		
		System.out.println("\nEnter the request for resources: ");
		for(int i=0;i<resNo;i++){
			req[i] = sc.nextInt();
		}
		
		int flag = 0;
		
		for(int i=0;i<resNo;i++)
		{
			if((req[i]<=need[pro][i]) && (req[i]<=avail[i])){
				flag=0;
			}
			else{
				flag=1;
				break;
			}
		}
		if(flag==0)
		{
			for(int i=0;i<procNo;i++)
			{
				fin[i] = false;
			}
			
			for(int i=0;i<resNo;i++)
			{
				avail[i] = avail[i] - req[i];
				alloc[pro][i] = alloc[pro][i] + req[i];
				need[pro][i] = need[pro][i] - req[i];
			}
			
			boolean grant = safeSeq(resNo,procNo,fin,avail,need,alloc);
			if(grant==true){
				System.out.println("\nYes the request can be granted");
			}
			else
			{
				System.out.println("\nNo the request cannot be granted");
			}
		}
		
		
	}

}


/*OUTPUT
 * 
 * 
Enter the number of resources
3

Enter the number of processes
5

Enter the available resources
10
5
7

Enter the allocation matrix
0
1
0
2
0
0
3
0
2
2
1
1
0
0
2

Enter the max matrix
7
5
3
3
2
2
9
0
2
2
2
2
4
3
3

Available resources
	R0 : 10
	R1 : 5
	R2 : 7

The allocation matrix is: 


	0	1	0

	2	0	0

	3	0	2

	2	1	1

	0	0	2
The max matrix is: 


	7	5	3

	3	2	2

	9	0	2

	2	2	2

	4	3	3
The need matrix is: 


	7	4	3

	1	2	2

	6	0	0

	0	1	1

	4	3	1
Safe Sequence: 

	P0	P1	P2	P3	P4
	
Enter the process number for request: 
1

Enter the request for resources: 
1
0
2

Safe Sequence: 

	P0	P1	P2	P3	P4
Yes the request can be granted

 * */
