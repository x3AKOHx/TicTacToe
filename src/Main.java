import java.util.Scanner;

class Game {

    String[][] field = new String[5][5];

    public void gameStart() {
        while(true) {
            System.out.println("Input command: ");
            Scanner sc = new Scanner(System.in);
            String gameMode = sc.nextLine();
            if (gameMode.equals("exit")) {
                break;
            }
            String[] words = gameMode.split(" ");
            if (words[1].equals("user") && words[2].equals("user")) {
                createTheField();
                showField();
                while (gameResult()) {
                    makeMove(true);
                    if (!gameResult()) {
                        break;
                    }
                    makeMove(false);
                }
            } else if (words[1].equals("user")) {
                createTheField();
                showField();
                while (gameResult()) {
                    makeMove(true);
                    if (!gameResult()) {
                        break;
                    }
                    aiMove(words[2], false);
                }
            } else if (words[2].equals("user")) {
                createTheField();
                showField();
                while (gameResult()) {
                    aiMove(words[1], true);
                    if (!gameResult()) {
                        break;
                    }
                    makeMove(false);
                }
            } else {
                createTheField();
                showField();
                while (gameResult()) {
                    aiMove(words[1], true);
                    if (!gameResult()) {
                        break;
                    }
                    aiMove(words[2], false);
                }
            }
        }
    }

    public void createTheField() {

        for(int i = 0; i < 4; i++) {
            field[0][i] = "--";
            field[4][i] = "--";
        }
        field[0][4] = "-";
        field[4][4] = "-";

        for(int i = 1; i < 4; i++) {
            field[i][0] = "|";
            field[i][4] = "|";
        }
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                field[i][j] = " ";
            }
        }
    }

    public void showField() {
        for (int a = 0; a < 5; a++) {
            System.out.print(field[0][a]);
        }
        System.out.println();
        for(int i = 1; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.print(field[i][4]);
            System.out.println();
        }
        for (int a = 0; a < 5; a++) {
            System.out.print(field[4][a]);
        }
        System.out.println();
    }

    public void makeMove(boolean startFirst) {
        String pick;
        if (startFirst) {
            pick = "X";
        } else {
            pick = "O";
        }
        boolean isTrue = true;
        int x;
        int y;
        while (isTrue) {
            System.out.println("Enter the coordinates: ");
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            char[] ch = line.toCharArray();
            if (ch.length > 3 || ch[1] != ' ') {
                System.out.println("Enter coordinates in \"x + space + y\" form!");
            } else if ((ch[0] == 48 || (ch[0] > 51 && ch[0] < 58)) || (ch[2] == 48 || (ch[2] > 51 && ch[2] < 58))) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (ch[0] < 52 && ch[0] > 48 && ch[2] < 52 && ch[2] > 48) {
                x = ch[0] - '0';
                y = ch[2] - '0';
                if (field[x][y].equals(" ")) {
                    field[x][y] = pick;
                    isTrue = false;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } else System.out.println("You should enter numbers!");
        }
        showField();
    }

    public void aiMove(String level, boolean startFirst) {
        if (level.equals("easy")) {
            aiEasyMove(startFirst, true);
        }
        if (level.equals("medium")) {
            aiMediumMove(startFirst);
        }
    }

    public void aiEasyMove(boolean startFirst, boolean easy) {
        String pick;
        if (startFirst) {
            pick = "X";
        } else {
            pick = "O";
        }
        if (easy) {
            System.out.println("Making move level \"easy\"");
        }

        boolean isTrue = true;

        while (isTrue) {
            int x = (int) (Math.random() * 3) + 1;
            int y = (int) (Math.random() * 3) + 1;
            if (field[x][y].equals(" ")) {
                field[x][y] = pick;
                isTrue = false;
                showField();
            }
        }
    }

    public void aiMediumMove(boolean startFirst) {

        boolean check = false;
        boolean check2 = false;
        int y1 = 3;
        int temp = 0;

        String pick;
        String opPick;
        if (startFirst) {
            pick = "X";
            opPick = "O";
        } else {
            pick = "O";
            opPick = "X";
        }
        System.out.println("Making move level \"medium\"");

        //Can ai win in a string?
        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (field[x][y].equals(pick)) {
                    temp++;
                }
                if (temp == 2) {
                    for (int z = 1; z < 4; z++) {
                        if (field[x][z].equals(" ")) {
                            field[x][z] = pick;
                            check = true;
                            showField();
                        }
                    }
                }
            }
            temp = 0;
            if (check) {
                break;
            }
        }
        //Can ai win in a column?
        for (int x = 1; x < 4; x++) {
            if (check) {
                break;
            }
            for (int y = 1; y < 4; y++) {
                if (field[y][x].equals(pick)) {
                    temp++;
                }
                if (temp == 2) {
                    for (int z = 1; z < 4; z++) {
                        if (field[z][x].equals(" ")) {
                            field[z][x] = pick;
                            check = true;
                            showField();
                        }
                    }
                }
            }
            temp = 0;
        }
        //Can ai win in a diagonal?
        for (int x = 1; x < 4; x++) {
            if (check) {
                break;
            }
            if (field[x][x].equals(pick)) {
                temp++;
            }
            if (temp == 2) {
                for (int z = 1; z < 4; z++) {
                    if (field[z][z].equals(" ")) {
                        field[z][z] = pick;
                        check = true;
                        showField();
                    }
                }
            }
        }
        temp = 0;
        for (int x = 1; x < 4; x++) {
            if (check) {
                break;
            }
            if (field[x][y1].equals(pick)) {
                temp++;
            }
            if (temp == 2) {
                for (int z = 3; z > 0; z--) {
                    if (field[y1][z].equals(" ")) {
                        field[y1][z] = pick;
                        check = true;
                        showField();
                    }
                    y1++;
                }
            }
            y1--;
        }
        temp = 0;
        y1 = 3;

        if (!check) {
            //Can opponent win in a string?
            for (int x = 1; x < 4; x++) {
                for (int y = 1; y < 4; y++) {
                    if (field[x][y].equals(opPick)) {
                        temp++;
                    }
                    if (temp == 2) {
                        for (int z = 1; z < 4; z++) {
                            if (field[x][z].equals(" ")) {
                                field[x][z] = pick;
                                check2 = true;
                                showField();
                            }
                        }
                    }
                }
                temp = 0;
                if (check2) {
                    break;
                }
            }
            //Can opponent win in a column?
            for (int x = 1; x < 4; x++) {
                if (check2) {
                    break;
                }
                for (int y = 1; y < 4; y++) {
                    if (field[y][x].equals(opPick)) {
                        temp++;
                    }
                    if (temp == 2) {
                        for (int z = 1; z < 4; z++) {
                            if (field[z][x].equals(" ")) {
                                field[z][x] = pick;
                                check2 = true;
                                showField();
                            }
                        }
                    }
                }
                temp = 0;
            }
            //Can opponent win in a diagonal?
            for (int x = 1; x < 4; x++) {
                if (check2) {
                    break;
                }
                if (field[x][x].equals(opPick)) {
                    temp++;
                }
                if (temp == 2) {
                    for (int z = 1; z < 4; z++) {
                        if (field[z][z].equals(" ")) {
                            field[z][z] = pick;
                            check2 = true;
                            showField();
                        }
                    }
                }
            }
            temp = 0;
            for (int x = 1; x < 4; x++) {
                if (check2) {
                    break;
                }
                if (field[x][y1].equals(opPick)) {
                    temp++;
                }
                if (temp == 2) {
                    for (int z = 3; z > 0; z--) {
                        if (field[y1][z].equals(" ")) {
                            field[y1][z] = pick;
                            check2 = true;
                            showField();
                        }
                        y1++;
                    }
                }
                y1--;
            }
        }
        if (!check && !check2) {
            aiEasyMove(startFirst, false);
        }
    }

    public boolean gameResult() {
        boolean xWin = false;
        boolean oWin = false;
        boolean draw = false;
        int temp = 0;

        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (!field[x][y].equals("X"))
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
                if (!field[y][x].equals("X"))
                    temp++;
            }
            if (temp == 0) {
                xWin = true;
                temp = 0;
                break;
            }
            temp = 0;
        }
        if (field[1][1].equals("X") && field[2][2].equals("X") && field[3][3].equals("X")) {
            xWin = true;
        }
        if (field[1][3].equals("X") && field[2][2].equals("X") && field[3][1].equals("X")) {
            xWin = true;
        }

        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (!field[x][y].equals("O"))
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
                if (!field[y][x].equals("O"))
                    temp++;
            }
            if (temp == 0) {
                oWin = true;
                temp = 0;
                break;
            }
            temp = 0;
        }
        if (field[1][1].equals("O") && field[2][2].equals("O") && field[3][3].equals("O")) {
            oWin = true;
        }
        if (field[1][3].equals("O") && field[2][2].equals("O") && field[3][1].equals("O")) {
            oWin = true;
        }

        if (!xWin && !oWin) {
            for (int x = 1; x < 4; x++) {
                for (int y = 1; y < 4; y++) {
                    if (field[x][y].equals(" "))
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

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.gameStart();
    }
}