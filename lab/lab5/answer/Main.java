import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

public class Main {

    public static void sort(DeckQueue deckqueue) {
        int n = deckqueue.size();
        //排序完成一共需要n-1轮
        for (int i = 1; i <= n - 1; i++) {
            //第i轮操作需要n-i次换牌，将最小的牌移到top并放到bottom位置
            for (int j = 0; j < n - i; j++) {
                if (deckqueue.second() < deckqueue.first())
                    deckqueue.exchangeTopTwo();
                deckqueue.moveTop2Bottom();
            }
            //一轮结束后做i次将最上面的牌移到最下面的操作
            for (int k = 0; k < i; k++) {
                deckqueue.moveTop2Bottom();
            }
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();

        for (int i = 100; i < 100000; i *= 2) {
            int[] array = new int[i];
            for (int j = 1; j < array.length; ++j)
                array[j] = (rand.nextDouble() < 0.9) ? (array[j - 1] + 1) : (array[j - 1] - 1);

            StdRandom.shuffle(array);
            DeckQueue deck = new DeckQueue(array);

            long t1 = System.currentTimeMillis();
            sort(deck);
            long t2 = System.currentTimeMillis();
            System.out.printf("Data size: %d, time: %d.\n", i, t2 - t1);
            System.out.println(deck.isSorted());
        }


        DeckQueue deck1 = new DeckQueue(new int[]{0, 0, 0, 0, 0, 0, 0});
        sort(deck1);
        System.out.print("All zero array: ");
        System.out.println(deck1.isSorted());

        DeckQueue deck2 = new DeckQueue(new int[]{1, 2, 3, 4, 5, 6, 7});
        sort(deck2);
        System.out.print("Sorted array: ");
        System.out.println(deck2.isSorted());

        DeckQueue deck3 = new DeckQueue(new int[]{7, 6, 5, 4, 3, 2, 1});
        sort(deck3);
        System.out.print("Reverse-sorted array: ");
        System.out.println(deck3.isSorted());

        DeckQueue deck4 = new DeckQueue(new int[]{3, 4, 9, 3, 5, 1, 5, 46, 40});
        sort(deck4);
        System.out.print("Contains repetitive numbers array: ");
        System.out.println(deck4.isSorted());

        DeckQueue deck5 = new DeckQueue(new int[]{3});
        sort(deck5);
        System.out.print("No element ");
        System.out.println(deck5.isSorted());

        DeckQueue deck6 = new DeckQueue(new int[]{});
        sort(deck6);
        System.out.print("Contains repetitive numbers array: ");
        System.out.println(deck6.isSorted());
    }
}
