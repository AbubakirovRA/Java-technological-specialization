//Написать функцию, возвращающую разницу между самым большим и самым маленьким элементами переданного не пустого массива:

public static int getDifference(int[] nums) {
    int min = nums[0];
    int max = nums[0];

    for (int i = 1; i < nums.length; i++) {
        if (nums[i] < min) {
            min = nums[i];
        }
        if (nums[i] > max) {
            max = nums[i];
        }
    }

    return max - min;
}

int[] array = {7, 2, 9, 10, 1};
int difference = getDifference(array);
System.out.println(difference); // 9
