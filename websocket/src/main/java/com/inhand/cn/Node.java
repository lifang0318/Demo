package com.inhand.cn;

/**
 * 单链表数据节点
 *
 * @param <E>
 */
public class Node<E> {

    E item;
    Node<E> next;

    public Node(E item) {
        this.item = item;
        this.next = null;
    }

    public E getItem() {
        return item;
    }

    public void setItem(E item) {
        this.item = item;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
