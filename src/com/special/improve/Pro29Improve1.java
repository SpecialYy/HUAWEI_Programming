package com.special.improve;

import java.util.Scanner;

/** 
*
* @author special
* @date 2017年9月27日 下午6:16:46
*/
public class Pro29Improve1 {

	/**
	 * 数组元素交换
	 * @param ch
	 * @param i
	 * @param j
	 */
	private static void swap(char[] ch, int i, int j){
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
	}
	/**
	 * 希尔排序，其实就是步长为2的插入排序
	 * @param ch
	 * @param step
	 */
	private static void shell(char[] ch, int step){
		int length = ch.length;
		/**
		 * 注意i的初始值，i的增长
		 * 注意j的初始值，为什么j取i,而不是i + 1呢？
		 * 1.因为i的判断条件确保了他不会越界，所以我们j取i值一定不会越界
		 * 2.如果我们j取i+1,我们就会错过i的正确排序！
		 * 同时注意j的判断条件和步长
		 * j>=step 是为了防止数组下界越界！
		 */
		for(int i = step; i < length; i++){
			for(int j = i; j >= step; j -= step){
				if(ch[j] < ch[j - step]) swap(ch,j,j - step);
			}
		}
	}
	/**
	 * 插入排序的另一个版本，可以减少交换的次数
	 * 但要注意i，j的取值，同时注意内循环比较的条件！
	 * @param ch
	 * @param step
	 */
	private static void improveShell(char[] ch, int step){
		int length = ch.length;
		for(int i = step; i < length; i++){
			char temp = ch[i];
			int j = i - step;
			for(; j >= 0; j -= step){
				if(ch[j] > temp) ch[j + step] = ch[j];
				else                     break;
			}
			ch[j + step] = temp;
		}
	}
	/**
	 * 对单个字符进行处理
	 * @param ch
	 * @return
	 */
	private static char solve(char ch){
		StringBuilder binary = new StringBuilder();
		int result = 0;
		if(ch >= '0' && ch <= '9')
			result = ch - '0';
		else if(ch >= 'A' && ch <= 'F')
			result = ch - 'A' + 10;
		else if(ch >= 'a' && ch<= 'f')
			result = ch - 'a' + 10;
		else
			return ch;
		while(result != 0){
			int temp = result & 1;
			binary.append(temp);
			result >>= 1;
		}
		binary.append("0000");
		String binaryStr = binary.substring(0, 4);
		int ans = 0;
		for(int i = 0; i < 4; i++)
			ans = ans * 2 + (binaryStr.charAt(i) - '0');
		if(ans >= 10)
			ch = (char) ('A' + (ans - 10));
		else
			ch = (char) ('0' + (ans - 0));
		return ch;
	}
	public static String ProcessString(String str1,String str2){
		String mergeString = str1 + str2;
		int length = mergeString.length();
		char[] mergeArr    = mergeString.toCharArray();
		improveShell(mergeArr,2);
		System.out.println(mergeArr);
		for(int i = 0; i < length; i++){
			mergeArr[i] = solve(mergeArr[i]);
		}
		return new String(mergeArr);
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()){
			String str1 = input.next();
			String str2 = input.next();
			String result = ProcessString(str1,str2);
			System.out.println(result);
		}

	}

}
