
#include <stdio.h>
 
void HanNuoTa(int n, char a, char b, char c)
{
	if( n < 1 )
		return ;	
	HanNuoTa(n-1, a, c, b);
	printf("把第%d盘子通过%c移到%c\n", n, a, c);
	HanNuoTa(n-1, b, a, c);
}
 
int main()
{
	int num;
 
	printf("Input The Num you want to create:");
	scanf("%d", &num);
 
	HanNuoTa(num, 'A', 'B', 'C');
 
	getchar();
	getchar();
	return 0;

}
