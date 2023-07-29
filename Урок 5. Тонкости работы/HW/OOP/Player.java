package OOP;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private int sign;

    public Player(int sign) {
        this.sign = sign;
    }

    public int getSign() {
        return sign;
    }

    public Player getOpponent() {
        return new Player(3 - sign); // Возвращаем игрока с противоположным знаком (1 -> 2, 2 -> 1)
    }
}
