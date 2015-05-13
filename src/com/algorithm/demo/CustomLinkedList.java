package com.algorithm.demo;

import java.util.NoSuchElementException;



public class CustomLinkedList<T> {

    private int size = 0;

    private Node<T> voidLink;
    
    private static final class Node<T>{
        T mObject;
        Node<T> mPrevious;
        Node<T> mNext;
        
        Node(T object,Node<T> previous,Node<T> next) {
            mObject = object;
            mPrevious = previous;
            mNext = next;
        }
    }
    
    public CustomLinkedList() {
        voidLink = new Node<T>(null, null, null);
        voidLink.mPrevious = voidLink;
        voidLink.mNext = voidLink;
    }
    
    //
    public int size(){
        return size;
    }
    
    /**
     * 添加一个对象到链表，默认放在最后
     **/
    public void add(T object){
        //最后一个节点（非voidLink默认节点）
        Node<T> oldLast = voidLink.mPrevious;
        Node<T> newNode = new Node<T>(object,oldLast, voidLink);
        //新创建的节点设置成最后一个节点，于是成为oldLast的下一个节点。
        oldLast.mNext = newNode;
        //将默认节点前一个节点为新创建的节点
        voidLink.mPrevious = newNode;
        size ++;
    }
    
    /**
     * 将对象添加链表的到指定位置
     **/
    public void add(int location,T object){
        Node<T> node = voidLink;
        if (location < (size / 2)) {
            for (int i = 0; i <= location; i++) {
                node = node.mNext;
            }
        } else {
            for (int i = size; i > location; i--) {
                node = node.mPrevious;
            }
        }
        Node<T> previous = node.mPrevious;
        //将id插入到前面
        Node<T> newTicket = new Node<T>(object, previous, node);
        previous.mNext = newTicket;
        node.mPrevious = newTicket;
        size++;
    }
    
    /**
     * 返回链表中指定位置的对象
     **/
    public T get(int location) {
        if (location < 0 || location > size) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(location).mObject;
    };

    /**
     * 返回链表中指定位置的节点
     **/
    private Node<T> getNode(int location){
        Node<T> node = voidLink;
        if (location < (size / 2)) {
            for (int i = 0; i <= location; i++) {
                node = node.mNext;
            }
        } else {
            for (int i = size; i > location; i--) {
                node = node.mPrevious;
            }
        }
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node;
    }
    
    /**
     * 返回链表中顶端对象
     **/
    public T getFirst() {
        Node<T> first = voidLink.mNext;
        if (first != voidLink) {
            return first.mObject;
        }
        throw new NoSuchElementException();
    }

    /**
     * 返回链表中低端对象
     **/
    public T getLast(){
        Node<T> last = voidLink.mPrevious;
        if (last != voidLink) {
            return last.mObject;
        }
        throw new NoSuchElementException();
    }
    
    /**
     * 根据下标删除链表中指定的对象
     **/
    public T remove(int location){
        Node<T> node = getNode(location);
        Node<T> previous = node.mPrevious;
        Node<T> next = node.mNext;
        previous.mNext = next;
        next.mPrevious = previous;
        size--;
        return node.mObject;
    }

    /**
     * 删除链表中的对象
     **/
    public T remove(T object) {
        if (object == null) {
            return null;
        }
        Node<T> node = voidLink.mNext;
        for (int i = 0; i <= size; i++) {
            if (node == voidLink) {
                return null;
            }
            if (object == node.mObject) {
                break;
            }
            node = node.mNext;
        }
        Node<T> previous = node.mPrevious;
        Node<T> next = node.mNext;
        previous.mNext = next;
        next.mPrevious = previous;
        size--;
        return node.mObject;
    }
}
