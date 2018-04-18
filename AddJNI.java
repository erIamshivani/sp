import java.util.*;
import java.math.*;
public class AddJNI
{
static {
 System.load("/home/vishwaja/Documents/mynativelib.so");
//System.load("/home/ccoew/myjava/cal.so");
		// Load native library at runtime
		// cal.dll (Windows) or libcal.so (Unix)
}
		// Declare a native method add() that receives nothing and returns void
private native int add(int n1,int n2);

private native int sub(int n1,int n2);

private native int mul(int n1,int n2);

private native int div(int n1,int n2);

		// Test Driver

public static void main(String[] args)
{		// invoke the native method
Scanner sc= new Scanner(System.in);
System.out.println("\nEnter the first number:");
int num1=sc.nextInt();
System.out.println("\nEnter the second number:");
int num2=sc.nextInt();
System.out.println("\nAddition is=" +new AddJNI().add(num1,num2)); 
System.out.println("\nSubtraction is=" +new AddJNI().sub(num1,num2));
System.out.println("\nMultiplication is=" +new AddJNI().mul(num1,num2));
System.out.println("\nDivision is=" +new AddJNI().div(num1,num2));

}

}

/*root@Vishwaja:/home/vishwaja/Documents# javac AddJNI.java
root@Vishwaja:/home/vishwaja/Documents# javah AddJNI
root@Vishwaja:/home/vishwaja/Documents# g++ -fPIC -shared -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include  -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include/linux -I/home/vishwaja/Documents/ -o /home/vishwaja/Documents/mynativelib.so /home/vishwaja/Documents/jnitest.cpp
root@Vishwaja:/home/vishwaja/Documents# java -Djava.library.path=home/vishwaja/Documents AddJNI
Enter the first number:
4

Enter the second number:
2

Addition is=6

Subtraction is=2

Multiplication is=8

Division is=2
root@Vishwaja:/home/vishwaja/Documents#


*/
