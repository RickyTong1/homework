package ques6;

import java.util.Scanner;

public class Main {

	public static void main(String... strings) {
		Scanner scan = new Scanner(System.in);
		String str1 = scan.nextLine();
		String str2 = scan.nextLine();
		int total = 0;
		for (String tmp = str1; tmp != null&&tmp.length()>=str2.length();){
		  if(tmp.indexOf(str2) == 0){
		    total ++;
		  }
		  tmp = tmp.substring(1);
		}
		System.out.println(str1+"ÖÐº¬ÓÐ"+total+"¸ö"+str2);
	}

}
