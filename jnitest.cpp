#include <jni.h>
#include <stdio.h>
#include "AddJNI.h"

// Implementation of native method add() of TestJNI class

//JNIEXPORT void JNICALL Java_JniExample_justSayHello(JNIEnv *env, jobject obj)
JNIEXPORT jint JNICALL Java_AddJNI_add(JNIEnv *env, jobject thisObj,jint n1,jint n2)

{
	jint res1;
	res1=n1+n2;
	return res1;
}
JNIEXPORT jint JNICALL Java_AddJNI_sub(JNIEnv *env, jobject thisObj,jint n1,jint n2)

{
	jint res2;
	res2=n1-n2;
	return res2;
}
JNIEXPORT jint JNICALL Java_AddJNI_mul(JNIEnv *env, jobject thisObj,jint n1,jint n2)

{
	jint res3;
	res3=n1*n2;
	return res3;
}
JNIEXPORT jint JNICALL Java_AddJNI_div(JNIEnv *env, jobject thisObj,jint n1,jint n2)

{
	jint res4;
	res4=n1/n2;
	return res4;
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
