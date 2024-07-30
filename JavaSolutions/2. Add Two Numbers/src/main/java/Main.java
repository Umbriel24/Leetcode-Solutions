import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {

    private static void testcase1() {
        ListNode a3 = new ListNode(3);
        ListNode a2 = new ListNode(4, a3);
        ListNode a1 = new ListNode(2, a2);

        ListNode b3 = new ListNode(4);
        ListNode b2 = new ListNode(6, b3);
        ListNode b1 = new ListNode(5, b2);

        Solution sol = new Solution();

        ListNode solListnode = sol.addTwoNumbers(a1, b1);
        printTestCase(solListnode);
    }

    private static void testcase2() {
        ListNode a1 = new ListNode(0);
        ListNode b1 = new ListNode(0);

        Solution sol = new Solution();
        ListNode solListnode = sol.addTwoNumbers(a1, b1);
        printTestCase(solListnode);
    }

    private static void testcase3() {
        ListNode a3 = new ListNode(9);
        ListNode a2 = new ListNode(4, a3);
        ListNode a1 = new ListNode(2, a2);


        ListNode b4 = new ListNode(9);
        ListNode b3 = new ListNode(4, b4);
        ListNode b2 = new ListNode(6, b3);
        ListNode b1 = new ListNode(5, b2);

        Solution solution = new Solution();
        ListNode solListnode = solution.addTwoNumbers(a1, b1);
        printTestCase(solListnode);
    }

    private static void testcase4() {
        ListNode a7 = new ListNode(9);
        ListNode a6 = new ListNode(9, a7);
        ListNode a5 = new ListNode(9, a6);
        ListNode a4 = new ListNode(9, a5);
        ListNode a3 = new ListNode(9, a4);
        ListNode a2 = new ListNode(9, a3);
        ListNode a1 = new ListNode(9, a2);

        ListNode b4 = new ListNode(9);
        ListNode b3 = new ListNode(9, b4);
        ListNode b2 = new ListNode(9, b3);
        ListNode b1 = new ListNode(9, b2);

        Solution solution = new Solution();
        ListNode solListnode = solution.addTwoNumbers(a1, b1);
        printTestCase(solListnode);
    }

    private static void printTestCase(ListNode listNode) {
        System.out.print(listNode.val + " ");
        try {
            if (listNode.next != null) {
                printTestCase(listNode.next);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        /*

         */
        System.out.println("Test case 1: ");
        testcase1();
        System.out.println("Test case 2: ");
        testcase2();
        System.out.println("Test case 3: ");
        testcase3();
        System.out.println("Test case 4: ");
        testcase4();

    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ArrayList arraySomma = createArrayFromListnode(l1, l2);
        System.out.println("Programma finito");
        return ListNodeCreator(arraySomma);


    }


    //Metodi
    private ArrayList createArrayFromListnode(ListNode listnode1, ListNode listnode2) {
        ArrayList arrayListA = NextListnode(listnode1);
        ArrayList arrayListB = NextListnode(listnode2);

        ArrayList arraySomma = fromArrayToSumArray(arrayListA, arrayListB);

        //Debug
        for (int i = 0; i < arraySomma.size(); i++) {
            System.out.println(arraySomma.get(i));
        }

        return arraySomma;

    }

    private ArrayList NextListnode(ListNode tempListNode) {
        ArrayList arraylistTemp = new ArrayList();

        while (tempListNode.next != null) {
            arraylistTemp.add(tempListNode.val);
            tempListNode = tempListNode.next;
        }

        //Aggiungi l'ultimo
        arraylistTemp.add(tempListNode.val);

        return arraylistTemp;
    }

    private ArrayList fromArrayToSumArray(ArrayList arrayList1, ArrayList arrayList2) {
        ArrayList arraySomma = new ArrayList();
        int riporto = 0;
        int maxArray = Math.max(arrayList1.size(), arrayList2.size());

        for (int i = 0; i < maxArray; i++) {
            int temp1;
            int temp2;

            if (arrayList1.size() > i) {
                temp1 = (int) arrayList1.get(i);
            } else temp1 = 0;

            if (arrayList2.size() > i) {
                temp2 = (int) arrayList2.get(i);
            } else temp2 = 0;

            int tempSomma = temp1 + temp2 + riporto;
            riporto = 0;

                if (tempSomma < 10) {
                    arraySomma.add(tempSomma);

                } else if (tempSomma >= 10 && tempSomma < 100) {
                    riporto = tempSomma / 10;
                    arraySomma.add(tempSomma % 10);
                } else {
                    //Riporto di 100
                    riporto = tempSomma / 100;
                    arraySomma.add(tempSomma % 100);

            }
        }

        //Controlla se l'ultimo è un n >= 10
        if ((int)arraySomma.getLast() >= 10 && (int)arraySomma.getLast() < 100){
            int temp = (int)arraySomma.getLast() ;
            int checkRiporto = temp / 10;
            temp = temp % 10;

            arraySomma.removeLast();
            arraySomma.add(temp);
            arraySomma.add(checkRiporto);
        }
        //Controlla se l'ultimo è un n >= 100
        if ((int)arraySomma.getLast() >= 100){
            int temp = (int)arraySomma.getLast();
            int checkRiporto = temp / 100;
            temp = temp % 100;

            arraySomma.removeLast();
            arraySomma.add(temp);
            arraySomma.add(checkRiporto);
        }

        if(riporto != 0){
            arraySomma.add(riporto);
        }
        return arraySomma;
    }

    private ListNode ListNodeCreator(ArrayList arraylist) {
        ListNode tempListNode = new ListNode();
        tempListNode.val = (int) arraylist.getFirst();

        if (arraylist.size() > 1) {
            tempListNode.next = ListnodeFactory(arraylist, 1);
        }
        return tempListNode;
    }

    private ListNode ListnodeFactory(ArrayList arraylist, int contatore) {
        ListNode listnode = new ListNode();

        listnode.val = (int) arraylist.get(contatore);

        if (arraylist.size() - 1 > contatore) {
            contatore++;
            listnode.next = ListnodeFactory(arraylist, contatore);
        }
        return listnode;
    }
}
