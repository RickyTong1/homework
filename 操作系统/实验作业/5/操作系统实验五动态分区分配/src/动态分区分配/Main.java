package ��̬��������;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Memory memory = null;
        Scanner in = new Scanner(System.in);
        System.out.print("���ʼ���ڴ��С:");
        int size = in.nextInt();
        memory = new Memory(size);
        memory.showZones();
        while (true){
            System.out.println("1.����ռ�  2.���տռ�  3.��ʾ����״��");
            System.out.print("��ѡ��ָ��:");
            size = in.nextInt();
            switch (size) {
                case 1:
                    System.out.print("��������Ҫ����Ŀռ��С:");
                    size = in.nextInt();
                    memory.allocation(size);
                    break;
                case 2:
                    System.out.print("��������Ҫ���յķ�����:");
                    size = in.nextInt();
                    memory.collection(size);
                    break;
                case 3:
                    memory.showZones();
                    break;
                default:
                    System.out.println("������ѡ��!");
            }
        }
    }

}