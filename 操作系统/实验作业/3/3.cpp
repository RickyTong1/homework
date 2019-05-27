#include <stdio.h>
#include <process.h>//_beginthread���� 
#include <windows.h>
#include <time.h>
//srand(time(NULL));
//x=rand()%6;
using namespace std;
#define N 5
//���ӻ����ö��� 
int buff[N];     
 /* ��N�������Ļ�����buf[N]����ʵ��ѭ��������� */
int front=0, rear=0;
/*
ReleaseSemaphore(mutex,1,NULL);//v����
WaitForSingleObject(mutex,INFINITE);//p����

ע�⣺��ʹ��WaitForSingleObject�ȴ��ź������ʱ��
���ź���Ϊsignal״̬����wait�����ź����Զ���1��
ֱ��ʹ��ReleaseSemaphore�ͷ��ź������ź����ſ�����
*/ 
int x;
int p_num=0;
int c_num=0;

HANDLE mutex=CreateSemaphore(NULL,1,1,NULL);
//�ź�����ֵ1�����ֵ1
HANDLE full=CreateSemaphore(NULL,0,5,NULL);
HANDLE empty=CreateSemaphore(NULL,5,5,NULL);
void produce_item()
//������Ʒ 
{
	printf("������%dһ����Ʒ \n",++p_num);
}
void enter_item()
//���뻺���� 
{
	printf("���뻺������[%d]\n",rear);
	buff[rear]=1;
    rear=(rear+1)%N;
    if(rear==front)
    	printf("����������\n");
}
void remove_item()
//�ӻ�������ȡ 
{
	printf("                                        �ӻ�������ȡ[%d] \n",front);
	buff[front]=1;
    front=(front+1)%N;	
} 
void consume_item()
//���Ѳ�Ʒ
{
	printf("                                          ���Ѳ�Ʒ��%d����Ʒ\n",++c_num);
} 
/* ������ */
void producer(PVOID param)
{
    while(1){
    	Sleep(1000);
//    	x=rand()%7+1;printf("\n%d\n",x);
//      Sleep(x*1000);
        produce_item();
        WaitForSingleObject(empty,INFINITE);//p����
        WaitForSingleObject(mutex,INFINITE);//p���� 
        enter_item();   /* ��һ���µ���������뻺���� */
        ReleaseSemaphore(mutex,1,NULL);//v����
        ReleaseSemaphore(full,1,NULL);//v����
    }
}
/* ������ */
void consumer(PVOID param)
{
    while(1){
    	//Sleep(8000);
        	x=rand()%6+1;printf("\n%d\n",x);
            Sleep(x*1000);
            WaitForSingleObject(full,INFINITE);//p���� 
            WaitForSingleObject(mutex,INFINITE);//p���� 
            remove_item(); /* �ӻ�������ȡ��һ�������� */
            ReleaseSemaphore(mutex,1,NULL);//v����
            ReleaseSemaphore(empty,1,NULL);//v����
            consume_item(); /* ����������в��������ѣ�*/
    }/* ����������в��������ѣ�*/
 }
int main(){
	srand((unsigned)time(0)); 
	_beginthread(producer, 0, NULL);
	_beginthread(consumer, 0, NULL);
	Sleep(15000);
    printf("\nend\n") ;
	return 0;
}

///*
//void produce_item(int *item_ptr)
////������Ʒ 
// {
//    /*printf("produce an item\n");*/
//    if(buf[rear]=='m') 
//    {
//    	printf("���������, ֹͣ����\n");
//    	return ;
//	}
//    *item_ptr='m';      /* 'm' is "man��" */
// }
// 
//void enter_item(int x)
////���뻺���� 
// {
// //	if()
//    buf[front]=x;
//    printf("������ %c �������� buf[%d]\n", buf[front], front);
//    front=(front+1)%N;
// }
// 
//void remove_item(int *yy)
////�ӻ�������ȡ 
//{
//	if(buf[front]='k')
//	{
//		printf("�����������Ʒ, ֹͣ����\n");
//		return ; 
//	}
//
//    printf("�ӻ�����  buf[%d] ȡ�� %c", rear,buf[rear]);
// 
//    *yy=buf[rear];
//    buf[rear]='k';      /* 'k' is "kong��" */
//    printf("  ��˻����� buf[%d] ��ɿ��ˣ���%c��ʾ\n", rear, buf[rear]);
//    rear=(rear+1)%N;
//}
//void consume_item(int y)
////���Ѳ�Ʒ 
//{
//    printf("���Ѵӻ�����ȡ���Ĳ�Ʒ %c\n", y);
//}
//*/



