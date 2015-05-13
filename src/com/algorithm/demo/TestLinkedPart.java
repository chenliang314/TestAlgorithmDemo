package com.algorithm.demo;


/**
 * 自定义链表测试
 **/
public class TestLinkedPart {
    
    public static void main(String[] args) {
        CustomLinkedList<String> list = new CustomLinkedList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        list.add(1,"fff");
        list.remove(0);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("i = " + list.get(i));
        }
    }
}
