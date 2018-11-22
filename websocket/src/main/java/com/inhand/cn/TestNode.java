package com.inhand.cn;

/**
 *测试单链表
 */
public class TestNode {
    public static void main(String[] args) {
        Node<String> head = null;
        Node<String> tail = null;
        head = new Node("nodedata1");
        tail = head;
        tail.next = new Node("nodedata2");
        tail = tail.next;



        Node<String> current = head;
//        while (current != null) {
//            System.out.println(current.item);
//            current = current.next;
//        }

        printListRev(current);

    }

    static void printListRev(Node<String> head) {
   //倒序遍历链表主要用了递归的思想
        if (head != null) {
            printListRev(head.next);
            System.out.println(head.item);
        }
    }





}
