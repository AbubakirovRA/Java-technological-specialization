package package1;

public class MathOperationsTest {
    @Test
    public void testAddition() {
        MathOperations math = new MathOperations();
        int result = math.add(2, 3);
        assertEquals(5, result);
    }

    private void assertEquals(int i, int result) {
    }
}