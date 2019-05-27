#include <stdio.h>
#include <process.h>//_beginthread函数 
#include <windows.h>
#include <time.h>
//srand(time(NULL));
//x=rand()%6;
using namespace std;
#define N 5
//可视化设置队列 
int buff[N];     
 /* 有N个槽数的缓冲区buf[N]，并实现循环缓冲队列 */
int front=0, rear=0;
/*
ReleaseSemaphore(mutex,1,NULL);//v操作
WaitForSingleObject(mutex,INFINITE);//p操作

注意：在使用WaitForSingleObject等待信号量句柄时，
若信号量为signal状态，则wait过后信号量自动减1，
直到使用ReleaseSemaphore释放信号量，信号量才可增加
*/ 
int x;
int p_num=0;
int c_num=0;

HANDLE mutex=CreateSemaphore(NULL,1,1,NULL);
//信号量初值1，最大值1
HANDLE full=CreateSemaphore(NULL,0,5,NULL);
HANDLE empty=CreateSemaphore(NULL,5,5,NULL);
void produce_item()
//生产产品 
{
	printf("生产第%d一个产品 \n",++p_num);
}
void enter_item()
//放入缓存区 
{
	printf("放入缓存区第[%d]\n",rear);
	buff[rear]=1;
    rear=(rear+1)%N;
    if(rear==front)
    	printf("缓存区已满\n");
}
void remove_item()
//从缓存区中取 
{
	printf("                                        从缓存区中取[%d] \n",front);
	buff[front]=1;
    front=(front+1)%N;	
} 
void consume_item()
//消费产品
{
	printf("                                          消费产品第%d个商品\n",++c_num);
} 
/* 生产者 */
void producer(PVOID param)
{
    while(1){
    	Sleep(1000);
//    	x=rand()%7+1;printf("\n%d\n",x);
//      Sleep(x*1000);
        produce_item();
        WaitForSingleObject(empty,INFINITE);//p操作
        WaitForSingleObject(mutex,INFINITE);//p操作 
        enter_item();   /* 将一个新的数据项放入缓冲区 */
        ReleaseSemaphore(mutex,1,NULL);//v操作
        ReleaseSemaphore(full,1,NULL);//v操作
    }
}
/* 消费者 */
void consumer(PVOID param)
{
    while(1){
    	//Sleep(8000);
        	x=rand()%6+1;printf("\n%d\n",x);
            Sleep(x*1000);
            WaitForSingleObject(full,INFINITE);//p操作 
            WaitForSingleObject(mutex,INFINITE);//p操作 
            remove_item(); /* 从缓冲区中取走一个数据项 */
            ReleaseSemaphore(mutex,1,NULL);//v操作
            ReleaseSemaphore(empty,1,NULL);//v操作
            consume_item(); /* 对数据项进行操作（消费）*/
    }/* 对数据项进行操作（消费）*/
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
////生产产品 
// {
//    /*printf("produce an item\n");*/
//    if(buf[rear]=='m') 
//    {
//    	printf("缓存池已满, 停止生产\n");
//    	return ;
//	}
//    *item_ptr='m';      /* 'm' is "man满" */
// }
// 
//void enter_item(int x)
////放入缓存区 
// {
// //	if()
//    buf[front]=x;
//    printf("输入了 %c 到缓冲区 buf[%d]\n", buf[front], front);
//    front=(front+1)%N;
// }
// 
//void remove_item(int *yy)
////从缓存区中取 
//{
//	if(buf[front]='k')
//	{
//		printf("缓存池暂无商品, 停止消费\n");
//		return ; 
//	}
//
//    printf("从缓冲区  buf[%d] 取走 %c", rear,buf[rear]);
// 
//    *yy=buf[rear];
//    buf[rear]='k';      /* 'k' is "kong空" */
//    printf("  因此缓冲区 buf[%d] 变成空了，用%c表示\n", rear, buf[rear]);
//    rear=(rear+1)%N;
//}
//void consume_item(int y)
////消费产品 
//{
//    printf("消费从缓冲区取出的产品 %c\n", y);
//}
//*/



