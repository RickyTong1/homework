
#include<stdio.h>
 
int pageTable[10]={3,4,6,8,1,2,5,7,9,10};
 
//ҳ���С 
#define PageSize 4096
 
//����������߼���ַ(��������Ϸ�) 
int formulaMethod(int logicalAddress) {
	//ҳ�� 
	int P = logicalAddress/PageSize;
	if(P>9)
	{
		printf("ERROR!");
		return -1;
	}
	//ҳ�ڵ�ַ
	int d = logicalAddress%PageSize;
	//��� 
	int Fno = pageTable[P]; 
	//���ؼ������õ������ַ 
	return Fno*PageSize + d; 	 
}
 
int main() {
	int logicalAddress;
	printf("�������߼���ַ��");
	scanf("%d",&logicalAddress); 
	printf("�����ַΪ��%d\n",formulaMethod(logicalAddress));
	getchar();
	getchar();
	return 0;
}
