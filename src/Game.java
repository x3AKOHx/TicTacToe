import java.util.Scanner;

class Game {

    static Field field = new Field();

    public void gameStart() {
        Player player1 = new Player();
        Player player2 = new Player();

        while (true) {
            System.out.println("The new game is starting...");
            System.out.println();
            System.out.println("Who will go first?");
            System.out.println("1 - user");
            System.out.println("2 - easy difficulty PC");
            System.out.println("3 - medium difficulty PC");
            System.out.println("4 - hard difficulty PC");
            System.out.println("5 - exit");
            Scanner sc = new Scanner(System.in);
            String firstPlayer = sc.nextLine();
            if (firstPlayer.equals("5")) {
                break;
            }
            System.out.println();
            System.out.println("Who will go second?");
            System.out.println("1 - user");
            System.out.println("2 - easy difficulty PC");
            System.out.println("3 - medium difficulty PC");
            System.out.println("4 - hard difficulty PC");
            System.out.println("5 - exit");
            String secondPlayer = sc.nextLine();
            if (secondPlayer.equals("5")) {
                break;
            }
            if (firstPlayer.equals("1") && secondPlayer.equals("1")) {
                field.createTheField();
                field.showField();
                while (gameResult()) {
                    player1.makeMove(true, field.field);
                    field.showField();
                    if (!gameResult()) {
                        break;
                    }
                    player2.makeMove(false, field.field);
                    field.showField();
                }
            } else if (firstPlayer.equals("1") &&
                      (secondPlayer.equals("2") || secondPlayer.equals("3") || secondPlayer.equals("4"))) {
                field.createTheField();
                field.showField();
                while (gameResult()) {
                    player1.makeMove(true, field.field);
                    field.showField();
                    if (!gameResult()) {
                        break;
                    }
                    player2.aiMove(secondPlayer, false, field.field);
                    field.showField();
                }
            } else if (secondPlayer.equals("1") &&
                      (firstPlayer.equals("2") || firstPlayer.equals("3") || firstPlayer.equals("4"))) {
                field.createTheField();
                field.showField();
                while (gameResult()) {
                    player1.aiMove(firstPlayer, true, field.field);
                    field.showField();
                    if (!gameResult()) {
                        break;
                    }
                    player2.makeMove(false, field.field);
                    field.showField();
                }
            } else if ((firstPlayer.equals("2") || firstPlayer.equals("3") || firstPlayer.equals("4"))  &&
                      (secondPlayer.equals("2") || secondPlayer.equals("3") || secondPlayer.equals("4"))) {
                field.createTheField();
                field.showField();
                while (gameResult()) {
                    player1.aiMove(firstPlayer, true, field.field);
                    field.showField();
                    if (!gameResult()) {
                        break;
                    }
                    player2.aiMove(secondPlayer, false, field.field);
                    field.showField();
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    public static boolean xWin() {
        int temp = 0;

        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (!field.field[x][y].equals("X"))
                    temp++;
            }
            if (temp == 0) {
                return true;
            }
            temp = 0;
        }
        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (!field.field[y][x].equals("X"))
                    temp++;
            }
            if (temp == 0) {
                return true;
            }
            temp = 0;
        }
        if (field.field[1][1].equals("X") && field.field[2][2].equals("X") && field.field[3][3].equals("X")) {
            return true;
        }
        if (field.field[1][3].equals("X") && field.field[2][2].equals("X") && field.field[3][1].equals("X")) {
            return true;
        }
        return false;
    }

    public static boolean oWin() {
        int temp = 0;

        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (!field.field[x][y].equals("O"))
                    temp++;
            }
            if (temp == 0) {
                return true;
            }
            temp = 0;
        }
        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (!field.field[y][x].equals("O"))
                    temp++;
            }
            if (temp == 0) {
                return true;
            }
            temp = 0;
        }
        if (field.field[1][1].equals("O") && field.field[2][2].equals("O") && field.field[3][3].equals("O")) {
            return true;
        }
        if (field.field[1][3].equals("O") && field.field[2][2].equals("O") && field.field[3][1].equals("O")) {
            return true;
        }
        return false;
    }

    public static boolean draw() {
        int temp = 0;

        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (field.field[x][y].equals(" "))
                    temp++;
            }
        }
        return temp == 0;
    }

    public static boolean gameResult() {

        if (xWin()) {
            System.out.println("X wins");
            return false;
        } else if (oWin()) {
            System.out.println("O wins");
            return false;
        } else if (draw()) {
            System.out.println("Draw");
            return false;
        } else {
            return true;
        }
    }
}