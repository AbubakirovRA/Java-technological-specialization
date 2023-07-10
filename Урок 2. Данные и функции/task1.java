//Написать метод, возвращающий количество чётных элементов массива:

public static int countEvens(int[] nums) {
    int count = 0;
    for (int num : nums) {
        if (num % 2 == 0) {
            count++;
        }
    }
    return count;
}

int[] array1 = {2, 1, 2, 3, 4};
int[] array2 = {2, 2, 0};
int[] array3 = {1, 3, 5};

int count1 = countEvens(array1);
int count2 = countEvens(array2);
int count3 = countEvens(array3);

System.out.println(count1); // 3
System.out.println(count2); // 3
System.out.println(count3); // 0
