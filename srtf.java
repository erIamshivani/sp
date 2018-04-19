package Package;

import java.util.Scanner;

public class Practical {
	public static void main(String[] args) {
		Practical s=new Practical();
		s.method();
	}

	private void method() {
		Scanner sc=new Scanner(System.in);
		int numOfProcess;
		System.out.println("Enter the number of process:");
		numOfProcess=sc.nextInt();
		int totaltime=0;
		int[] a=new int[numOfProcess];
		int[] bursttime=new int[numOfProcess];
		System.out.println("Enter the burst time");
		for(int i=0;i<numOfProcess;i++)
		{
			bursttime[i]=sc.nextInt();
			totaltime=totaltime+bursttime[i];
			a[i]=bursttime[i];
		}
		
		int[] arrivaltime=new int[numOfProcess];
		System.out.println("Enter the arrival time");
		for(int i=0;i<numOfProcess;i++)
		{
			arrivaltime[i]=sc.nextInt();
		}
		int[] wt=new int[numOfProcess];
		int[] flag=new int[numOfProcess];
		int[] turnaround=new int[numOfProcess];
		int currenttime=0,index=0,min;
		int[] gnattchart=new int[totaltime];
		int[] completiontime=new int[numOfProcess];
		while(currenttime!=totaltime)
		{
			min=999;
			int[] aa=new int[numOfProcess];
			for(int i=0;i<numOfProcess;i++)
			{
				if(arrivaltime[i]<=currenttime && bursttime[i]!=0)
				{
					aa[i]=bursttime[i];//taking arrived processes in array(Ready queue)
				}
			}
			for(int i=0;i<aa.length;i++)//finding shortest job
			{
				if(min>aa[i] && aa[i]!=0)
				{
					min=aa[i];
					index=i;
				}
			}
			gnattchart[currenttime]=index;		
			bursttime[index]=bursttime[index]-1;
			if(bursttime[index]==0)
			{
				completiontime[index]=currenttime+1;
			}
			
			currenttime++;
		}
		
		for(int i=0;i<numOfProcess;i++)
		{
			turnaround[i]=completiontime[i]-arrivaltime[i];
			wt[i]=turnaround[i]-a[i];
		}
		
		System.out.println("Burst time\tarrival time\twaitingtime\tturnarooundtime\tCompletiontime");
		for(int i=0;i<numOfProcess;i++)
		{
			System.out.println(a[i]+"\t\t"+arrivaltime[i]+"\t\t"+wt[i]+"\t\t"+turnaround[i]+"\t\t"+completiontime[i]);
		}
		
		System.out.println("Gnatt chart");
		for(int i=0;i<totaltime;i++)
		{
			System.out.print("  P"+gnattchart[i]);
		}
	}

}
/*Enter the number of process:
6
Enter the burst time
7
5
3
1
2
1
Enter the arrival time
0
1
2
3
4
5
Burst time	arrival time	waitingtime	turnarooundtime	Completiontime
7		0		12		19		19
5		1		7		12		13
3		2		1		4		6
1		3		0		1		4
2		4		3		5		9
1		5		1		2		7
Gnatt chart
  P0  P1  P2  P3  P2  P2  P5  P4  P4  P1  P1  P1  P1  P0  P0  P0  P0  P0  P0
*/