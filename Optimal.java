import java.util.*;
public class Optimal {

	public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
		
		//input
		System.out.println("Enter the length of reference string : ");
		int nPages = sc.nextInt();
		System.out.println("Enter the string");
		int pages[] = new int[nPages];
		for(int i = 0; i<nPages; i++){
			pages[i] = sc.nextInt();
		}
		System.out.println("Enter no of. slots/frames : ");
		int nFrames = sc.nextInt();
		int frames[] = new int[nFrames];
		//algo
		for(int i = 0 ; i<nFrames; i++){
			frames[i] = -1; //initializing with -1
		}
		int pFaults = 0;
		int hits = 0;
		int count = 0; 
		int flag;
		int temp[]=new int[nFrames]; //for storing distance of pages
		int pos, flag1;
		for(int i = 0; i<nPages; i++){
			flag = 0;
			if(count<nFrames){
				frames[i] = pages[i];
				count++;
				flag =1;
				pFaults++;
			}
			if(flag == 0){
				for(int j = 0; j<nFrames; j++){
					if(pages[i]==frames[j]){
						hits++;
						break;
					}
					else
						flag =2;
				}
			}
			if(flag == 2){
				pFaults++;
				 for(int k=0; k < nFrames; k++){  
					   temp[k]=-1;
		               for(int l=i+1; l < nPages-1; l++){ //creating the distance array
		                     if(frames[k] == pages[l]){
		                           temp[k] = l;
		                           break;
		                       }
		                 }
		           }
				  pos = -1;
			      flag1 = 0;
			        for(int l=0; l<nFrames; l++){
			            if(temp[l] == -1){
			                  pos = l;
			                  flag1 = 1;
			                  break;
			              }
			        }
			         
			        if(flag1 == 1){
			        	frames[pos] = pages[i];
			        }
			        else{
			        	int max = temp[0];
			        	pos = 0;
			           
			        	for(int k=1; k<nFrames; k++){
			              if(max<temp[k]){
			                  pos = k;
			                  max=temp[k];			              
			              }
			          }			           
			          frames[pos] = pages[i];			          
			     }
			}
			for(int l = 0; l<nFrames; l++){ //displaying after every iteration
				System.out.print(frames[l] + " ");
			}
			System.out.println();
		}
		 System.out.println("Hits: "+hits+ " Page faults "+ pFaults);
		 sc.close();
	}
}
/*
Enter the length of reference string : 
10
Enter the string
2
3
1
2
4
5
2
3
6
1
Enter no of. slots/frames : 
3
2 -1 -1 
2 3 -1 
2 3 1 
2 3 1 
2 3 4 
2 3 5 
2 3 5 
3 3 5 
6 3 5 
1 3 5 
Hits: 3Page faults 8
*/
