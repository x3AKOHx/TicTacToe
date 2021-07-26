import java.util.Scanner;

class Game {

    Field field = new Field();

    public void gameStart() {
        Player player1 = new Player();
        Player player2 = new Player();
        while(true) {
            System.out.println("Input command: ");
            Scanner sc = new Scanner(System.in);
            String gameMode = sc.nextLine();
            if (gameMode.equals("exit")) {
                break;
            }
            String[] words = gameMode.split(" ");
            if (words.length < 3) {
                System.out.println("Bad parameters!");
            } else if (words[1].equals("user") && words[2].equals("user")) {
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
            } else if (words[1].equals("user")) {
                field.createTheField();
                field.showField();
                while (gameResult()) {
                    player1.makeMove(true, field.field);
                    field.showField();
                    if (!gameResult()) {
                        break;
                    }
                    player2.aiMove(words[2], false, field.field);
                    field.showField();
                }
            } else if (words[2].equals("user")) {
                field.createTheField();
                field.showField();
                while (gameResult()) {
                    player1.aiMove(words[1], true, field.field);
                    field.showField();
                    if (!gameResult()) {
                        break;
                    }
                    player2.makeMove(false, field.field);
                    field.showField();
                }
            } else if ((words[1].equals("easy") || words[1].equals("medium")) &&
                       (words[2].equals("easy") || words[2].equals("medium"))) {
                field.createTheField();
                field.showField();
                while (gameResult()) {
                    player1.aiMove(words[1], true, field.field);
                    field.showField();
                    if (!gameResult()) {
                        break;
                    }
                    player2.aiMove(words[2], false, field.field);
                    field.showField();
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    public boolean gameResult() {
        boolean xWin = false;
        boolean oWin = false;
        boolean draw = false;
        int temp = 0;

        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (!field.field[x][y].equals("X"))
                    temp++;
            }
            if (temp == 0) {
                xWin = true;
                temp = 0;
                break;
            }
            temp = 0;
        }
        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (!field.field[y][x].equals("X"))
                    temp++;
            }
            if (temp == 0) {
                xWin = true;
                temp = 0;
                break;
            }
            temp = 0;
        }
        if (field.field[1][1].equals("X") && field.field[2][2].equals("X") && field.field[3][3].equals("X")) {
            xWin = true;
        }
        if (field.field[1][3].equals("X") && field.field[2][2].equals("X") && field.field[3][1].equals("X")) {
            xWin = true;
        }

        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (!field.field[x][y].equals("O"))
                    temp++;
            }
            if (temp == 0) {
                oWin = true;
                temp = 0;
                break;
            }
            temp = 0;
        }
        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (!field.field[y][x].equals("O"))
                    temp++;
            }
            if (temp == 0) {
                oWin = true;
                temp = 0;
                break;
            }
            temp = 0;
        }
        if (field.field[1][1].equals("O") && field.field[2][2].equals("O") && field.field[3][3].equals("O")) {
            oWin = true;
        }
        if (field.field[1][3].equals("O") && field.field[2][2].equals("O") && field.field[3][1].equals("O")) {
            oWin = true;
        }

        if (!xWin && !oWin) {
            for (int x = 1; x < 4; x++) {
                for (int y = 1; y < 4; y++) {
                    if (field.field[x][y].equals(" "))
                        temp++;
                }
            }
            if (temp == 0) {
                draw = true;
            }
        }

        if (xWin) {
            System.out.println("X wins");
            return false;
        } else if (oWin) {
            System.out.println("O wins");
            return false;
        } else if (draw) {
            System.out.println("Draw");
            return false;
        } else {
            return true;
        }
    }
}