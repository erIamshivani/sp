package prac;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class page {

	public static void main(String[] args) {
		
		Queue<Integer> q = new LinkedList();
		Scanner sc = new Scanner(System.in);
		int total;
		
		
		
		//-----------------------------------------------------------
		
		int s;
		do{
			System.out.println("\nChoose one: \n1.FIFO\n2.LRU");
			int ch=sc.nextInt();
			
		switch(ch)
		{
		case 1: 
		
			System.out.print("\nEnter total no. of pages: ");
		total = sc.nextInt();
		int pages[]=new int[total];
		
		System.out.print("\nEnter refernce string:");
		for(int i=0;i<total;i++)
		{
			pages[i]=sc.nextInt();
		}
		
		System.out.println("\nEnter total no. of slots:");
		int slot= sc.nextInt();
		
				int count=0,pf=0,phit=0;
				Queue f =new LinkedList();
				Queue hit =new LinkedList();
				for(int i=0;i<total;i++)
				{
					if(count<slot)
					{
						count++;
						pf++;
						q.add(pages[i]);
						f.add(pages[i]);
					}
					else if(q.contains(pages[i]))
					{
						phit++;
						hit.add(pages[i]);
						System.out.println("\nPage hit at "+pages[i]);
					}
					else
					{
						System.out.println(q.peek() + " is replaced by "+ pages[i]);
						q.remove();
						q.add(pages[i]);
						pf++;
						f.add(pages[i]);
					}
					System.out.println("\nQueue after each iteration:\t"+q +"\n");
				} 
				System.out.println("Page faults for: " +f + "Total page faults : "+ pf);
				System.out.print("Page hits for: " + hit + "Total page hits : "+ phit);
				break;
				
		case 2: 
			System.out.print("\nEnter total no. of pages: ");
			total = sc.nextInt();
			int pages1[]=new int[total];
			
			System.out.print("\nEnter refernce string:");
			for(int i=0;i<total;i++)
			{
				pages1[i]=sc.nextInt();
			}
			
			System.out.println("\nEnter total no. of slots:");
			int slot1= sc.nextInt();
			
			
			
			int pgf=0,fl=0,cnt=0,least=0,hits=0;
				int lst[]=new int[slot1];
				int frames[]= new int[slot1];
				
				for(int i=0;i<total;i++)
				{
					fl=0;
					if(cnt<slot1)		//whiile adding in slots for first time
					{
						frames[i]=pages1[i];
						pgf++;
						least++;
						lst[i]=least;
						
						cnt++;
						fl=1;
					}
					if(fl==0)		//when lot is full, check with frames
					{
						for(int j=0;j<slot1;j++)
						{
							if(pages1[i]==frames[j])		//already existing
							{
								hits++;
								least++;
								lst[j]=least;
								break;
							}
							else
								fl=2;
						}
					}
					if(fl==2)
					{
						int min=lst[0];
						int p=0;
						for(int k=1;k<slot1;k++)		//for replacing check least count
						{
							if(lst[k]<min)
							{
								min=lst[k];
								p=k;
							}
						}
							pgf++;
							least++;
							lst[p]=least;
							frames[p]=pages1[i];
						
					}
					System.out.println("\nQueue after each iteration:");
					for(int m=0;m<slot1;m++)
					{
						System.out.println(frames[m]+ "\t");
					}
					
				}
				System.out.println("Page faults: "+ pgf + "\nPage hits: "+ hits);
				
		}
		System.out.println("Do u want to continue? ");
		s=sc.nextInt();
		}while(s==1);
	}

}


/*
output
Enter total no. of pages: 9

Enter refernce string:4
2
3
1
2
3
4
5
1

Enter total no. of slots:
3

Choose one: 
1.FIFO
1

Queue after each iteration:	[4]


Queue after each iteration:	[4, 2]


Queue after each iteration:	[4, 2, 3]

4 is replaced by 1

Queue after each iteration:	[2, 3, 1]


Page hit at 2

Queue after each iteration:	[2, 3, 1]


Page hit at 3

Queue after each iteration:	[2, 3, 1]

2 is replaced by 4

Queue after each iteration:	[3, 1, 4]

3 is replaced by 5

Queue after each iteration:	[1, 4, 5]


Page hit at 1

Queue after each iteration:	[1, 4, 5]

Page faults for: [4, 2, 3, 1, 4, 5] Total page faults : 6
Page hits for: [2, 3, 1] Total page hits : 3
*/