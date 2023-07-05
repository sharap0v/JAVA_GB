package hw3;

import java.util.ArrayList;
import java.util.Random;

public class hw3 {
    /**
     * Формат сдачи: ссылка на подписанный git-проект.
     *
     * Задание
     *
     * Пусть дан произвольный список целых чисел.
     *
     * 1) Нужно удалить из него чётные числа
     * 2) Найти минимальное значение
     * 3) Найти максимальное значение
     * 4) Найти среднее значение
     */
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < r.nextInt(100); i++) {
            array.add(r.nextInt(100));
        }
        //рандомно заполняем список
        printArray(array);
        printArray(deleteEvenNumbers(array));
        System.out.println("Min " + findMin(array));
        System.out.println("Max " + findMax(array));
        System.out.println("Average " + findAverage(array));
    }

    public static void printArray(ArrayList<Integer> array){
        for (Integer i: array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static ArrayList deleteEvenNumbers (ArrayList<Integer> array){
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i)%2==0){
                array.remove(i);
            }
        }
        return array;
    }
    public static Integer findMin(ArrayList<Integer> array){
        if (array.size()>1){
            Integer min = array.get(0);
            for (int i = 0; i < array.size(); i++) {
                if (min>array.get(i)){
                    min = array.get(i);
                }
            }
            return min;
        }
        else System.out.println("Список пуст");return null;
    }
    public static Integer findMax(ArrayList<Integer> array){
        if (array.size()>1){
            Integer max = array.get(0);
            for (int i = 0; i < array.size(); i++) {
                if (max<array.get(i)){
                    max = array.get(i);
                }
            }
            return max;
        }
        else System.out.println("Список пуст");return null;
    }

    public static Double findAverage(ArrayList<Integer> array){
        if (array.size()>1){
            Integer sum = 0;
            for (int i = 0; i < array.size(); i++) {
                sum = sum + array.get(i);
            }
            return (double) sum/array.size();
        }
        else System.out.println("Список пуст");return null;
    }

}
