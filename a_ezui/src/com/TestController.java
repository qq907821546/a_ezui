package com;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gk666.util.HttpUtil;

@Controller
@RequestMapping("/test")
public class TestController {

	public static void main(String[] args) {
	/*Random random = new Random();
	int s = (int)(Math.random()*10);
	System.out.println(s);*/
		//冒泡排序
		/*int[] arg = new int[]{5,9,1,0,6,3,4,8};
		for(int m=0;m<arg.length;m++){
		System.out.print(arg[m]);
		}
		System.out.print("\n");

		for(int j=0;j<arg.length-1;j++){
			for(int k=j+1;k<arg.length;k++){
				int tem;
				if(arg[j]>arg[k]){
					tem = arg[j];
					arg[j] = arg[k];
					arg[k] = tem;
				}
			}
		}
		for(int m=0;m<arg.length;m++){
			System.out.print(arg[m]);
			}*/
		ming(18,16);
		}
	
     /**
      * 定义一个int型的一维数组，包含10个元素，分别赋一些随机整数，然后求出所有元素的最大值，最小值，平均值，和值，并输出出来。
      */
	public static void arryInt(){
		int[] arry;
		arry = new int[10];
		for(int i=0;i<10;i++){
			arry[i] = (int)(Math.random()*100);
			System.out.print(arry[i]+" ");
		}
		System.out.print("\n");
		int max = arry[0];
		int min = arry[0];
		int sum = 0;
		for(int j=0;j<arry.length;j++){
			if(max<arry[j])
				max = arry[j];
			if(min>arry[j])
				min = arry[j];
			sum += arry[j];
		}
		System.out.println("最大值："+max+" 最小值："+min+" 和值："+sum+" 平均值："+sum/10);
	}
	
	/**
	 * 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第四个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？ 
	 */
	public static int tuzi(int i){
		if(i == 1||i == 2){
			return 1;
		}else{
			return tuzi(i-1)+tuzi(i-2);
		}
	}
	
	/**
	 * 判断101-200之间有多少个素数，并输出所有素数。 
	 *
	 */
	public static void issushu(){
		for(int i=101;i<=200;i++){
			for(int j=2;j<(i/2);j++){
				if(i % j == 0)
					break;
				else
					if(j == (i/2)-1)
						System.out.print(i+" ");
					else
						continue;
			}
		}
	}
	
	/**
	 * 打印出所有的 “水仙花数 “，所谓 “水仙花数 “是指一个三位数，其各位数字立方和等于该数本身。例如：153是一个 “水仙花数 “，因为153=1的三次方＋5的三次方＋3的三次方。
	 */
	public static void shuixiahua(){
		for(int i=100;i<=999;i++){
			int b = i/100;
			int s = (i%100)/10;
			int g = (i%100)%10;
			int sum = b*b*b+s*s*s+g*g*g;
			if(sum == i)
			  System.out.print(sum+" ");
		}
	}
	
	/**
	 * 将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
	 */
	public static void fenjie(int num){
		String result = "";
		for(int i=2;i<=num;i++){
			if(num % i == 0){
				result += i+"*";
				num = num/i;
				i=2;
			}
		}
		result = result.substring(0, result.length()-1);
		System.out.println(result);
	}
	
    /**
     * 输入两个正整数m和n，求其最大公约数和最小公倍数。 
     */
	public static int maxg(int max,int min){
		int temp = 0;
		while(max%min!=0){
			temp = max % min;
			max = min;
			min = temp;
		}
		return temp;
	}
	public static void ming(int max,int min){
		System.out.println("最大公约数："+maxg(max,min));
		System.out.println("最小公倍数："+(max*min)/maxg(max,min));
	}
	
}
