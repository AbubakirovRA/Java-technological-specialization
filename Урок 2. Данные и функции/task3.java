3. Написать функцию, возвращающую истину, если в переданном массиве есть два соседних элемента со значением 0:

public static boolean hasZeroNeighbor(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
        if (nums[i] == 0 && nums[i + 1] == 0) {
            return true;
        }
    }
    return false;
}

int[] array1 = {1, 2, 0, 0, 3};
int[] array2 = {1, 0, 0, 4, 5};
boolean hasZeroNeighbor1 = hasZeroNeighbor(array1);
boolean hasZeroNeighbor2 = hasZeroNeighbor(array2);
System.out.println(hasZeroNeighbor1); // true
System.out.println(hasZeroNeighbor2); // false
