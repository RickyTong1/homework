
#include<stdio.h>
 
int pageTable[10]={3,4,6,8,1,2,5,7,9,10};
 
//页面大小 
#define PageSize 4096
 
//传入参数：逻辑地址(假设输入合法) 
int formulaMethod(int logicalAddress) {
	//页号 
	int P = logicalAddress/PageSize;
	if(P>9)
	{
		printf("ERROR!");
		return -1;
	}
	//页内地址
	int d = logicalAddress%PageSize;
	//块号 
	int Fno = pageTable[P]; 
	//返回计算所得的物理地址 
	return Fno*PageSize + d; 	 
}
 
int main() {
	int logicalAddress;
	printf("请输入逻辑地址：");
	scanf("%d",&logicalAddress); 
	printf("物理地址为：%d\n",formulaMethod(logicalAddress));
	getchar();
	getchar();
	return 0;
}
