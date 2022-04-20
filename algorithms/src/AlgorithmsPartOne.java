import java.util.Scanner;

public class AlgorithmsPartOne {
    private int[] list;

    public AlgorithmsPartOne(int range) {
        list = new int[range];
        for (int i = 0; i < range; i++) {
            list[i] = i;
        }
    }

    public void list() {
        for (int i = 0; i < list.length; i++) {
            System.out.print("   " + i);
        }
        System.out.println();
        for (int n : list) {

            System.out.print("   " + n);

        }
    }

    public void unionQuickUnion() {
        Scanner scanner = new Scanner(System.in);
        int numberOne = scanner.nextInt();
        int numberTwo = scanner.nextInt();

        // 9=3

        int numberTwoPointer=list[numberTwo];
        if (numberTwoPointer == numberTwo) {
            list[numberOne] = numberTwoPointer;
        }

        int index=list.length;
        while (numberTwoPointer!=list[numberTwoPointer]) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Number InsideTwo " + numberTwoPointer);
            System.out.println("number Two " + numberTwo);


            numberTwoPointer=list[numberTwoPointer];
            list[numberOne] = numberTwoPointer;
        }


    }

    public void unionQuickFind() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int numberOne = scanner.nextInt();
        int numberTwo = scanner.nextInt();

        int numbersToChange = list.length;
        int numbersToChange2 = list.length;

        for (int i = 0; i < list.length; i++) {

            //THERS IMPLEMATATION
           /* if (list[i] == numbersToChange) {
                list[i] = numbersToChange2;
            }*/
            if (i == numberOne && numbersToChange == list.length) {
                numbersToChange = list[i];
                i = 0;
                continue;
            }
            if (i == numberTwo && numbersToChange2 == list.length) {
                numbersToChange2 = list[i];
                i = 0;
                continue;
            }
            if (list[i] == numbersToChange || list[i] == numbersToChange2) {
                list[i] = numberTwo;
            }
        }

    }


    public boolean isConnected(int numberOne, int numberTwo) {
        return list[numberOne] == list[numberTwo];
    }

}
