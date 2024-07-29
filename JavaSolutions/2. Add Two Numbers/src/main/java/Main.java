import java.util.ArrayList;
import java.util.Scanner;

class Main {


    public static void main(String[] args) {
        Solution solution = new Solution();



        ListNode a3 = new ListNode(3);
        ListNode a2 = new ListNode(4, a3);
        ListNode a1 = new ListNode(2, a2);



        ListNode b3 = new ListNode(4);
        ListNode b2 = new ListNode(6, b3);
        ListNode b1 = new ListNode(5, b2);

        ListNode test = solution.addTwoNumbers(a1, b1);
        System.out.println(test.next.val);
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
        //Dobbiamo partire dall'ultimo e andare a retroso


        boolean conditionTest = true;

        ListNode list1, list2;
        list1 = l1;
        list2 = l2;

        int conteggiolist1 = 0;
        int conteggiolist2 = 0;

        //Contatore di ListNode oltre l'iniziale
        while (list1.next != null) {
            conteggiolist1++;
            list1 = list1.next;
        }

        while (list2.next != null) {
            conteggiolist2++;
            list2 = list2.next;
        }
        //Fine contatore

        //Creazione array con grandezza della ListNode
        int[] array1 = new int[conteggiolist1 + 1];
        int[] array2 = new int[conteggiolist2 + 1];


        list1 = l1;
        list2 = l2;

        //allocazione valori nell'array
        //Array1
        for (int i = 0; i <= conteggiolist1; i++) {
            array1[i] = list1.val;

            if (list1.next != null) {
                list1 = list1.next;
            }
        }

        //Array2
        for (int i = 0; i <= conteggiolist2; i++) {
            array2[i] = list2.val;

            if (list2.next != null) {
                list2 = list2.next;
            }
        }

        //Check Array più grande
        int arrayMaggiore = CheckBiggerArray(array1.length, array2.length);

        //Crea array con somma e riporto
        int[] arraySomma = arraySomma(array1, array2, arrayMaggiore);

        //Crea Listnode
        return CreaListnode(arraySomma, new ListNode(), 0);


    }


    private int CheckBiggerArray(int n1, int n2) {
        return Math.max(n1, n2);
    }

    private int[] arraySomma(int[] n1, int[] n2, int conteggio) {
        int[] tempInt;

        if(n1[n1.length - 1] == 9 || n2[n2.length - 1] == 9) {
            tempInt = new int[conteggio + 1];
        } else {
            tempInt = new int[conteggio];
        }

        int riporto = 0;


        if(n1[n1.length - 1] != 9 || n2[n2.length - 1] != 9) {
            conteggio--;
        }
        for (int i = 0; i <= conteggio; i++) {

            //check se esiste solo uno degli array
            if (n1.length - 1 < i) {
                //vuol dire che n1 ha meno numeri di n2
                if(n2.length - 1 < i && riporto != 0) {
                    tempInt[i] = riporto;
                } else {
                    if(n2.length - 1 < i) {
                        tempInt[i] = riporto;
                        riporto = 0;
                    } else {
                        tempInt[i] = n2[i] + riporto;
                        riporto = 0;
                    }
                }

            } else if (n2.length - 1 < i) {
                tempInt[i] = (n1[i] + riporto) % 10;
                riporto = (n1[i] + riporto)/ 10;

            } else {
                if (riporto != 0) {
                    n1[i] += riporto;
                    riporto = 0;
                }
                if (n1[i] + n2[i] >= 10 && n1[i] + n2[i] < 100) { //27
                    tempInt[i] = ((n1[i] + n2[i]) % 10);
                    riporto = (n1[i] + n2[i]) / 10;
                } else if (n1[i] + n2[i] >= 100) {
                    tempInt[i] = ((n1[i] + n2[i]) % 100);
                    riporto = (n1[i] + n2[i]) / 100;
                } else if (n1[i] + n2[i] < 10) {
                    tempInt[i] = n1[i] + n2[i];
                }
            }
        }
        return tempInt;
    }

    private ListNode CreaListnode(int[] arraySomma, ListNode tempListnode, int conteggio) {
        tempListnode.val = arraySomma[conteggio];

        if (arraySomma.length == 1) {
            return tempListnode;
        }
        tempListnode.next = creaNextListnode(conteggio + 1, arraySomma, 0);

        //

        return tempListnode;
    }

    private ListNode creaNextListnode(int conteggio, int[] arraySomma, int riporto) {
        ListNode tempListnode = new ListNode();

        if(riporto != 0){
            arraySomma[conteggio] += riporto;
        }

        if (arraySomma[conteggio] >= 10 && riporto == 0) {
            riporto = arraySomma[conteggio] / 10;
            arraySomma[conteggio] %= 10;
            tempListnode.val = arraySomma[conteggio];
        } else if (riporto == 0) {
            tempListnode.val = arraySomma[conteggio];
            conteggio++;
        } else {
            tempListnode.val = arraySomma[conteggio];
            conteggio++;
        }


        if (conteggio < arraySomma.length) {
            tempListnode.next = creaNextListnode(conteggio, arraySomma, riporto);
        }

        return tempListnode;
    }

}
