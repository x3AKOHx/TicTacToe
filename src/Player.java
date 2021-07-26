import java.util.Scanner;

public class Player {

    public void makeMove(boolean startFirst, String[][] field) {

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
    }

    public void aiMove(String level, boolean startFirst, String[][] field) {
        if (level.equals("easy")) {
            aiEasyMove(startFirst, true, field);
        }
        if (level.equals("medium")) {
            aiMediumMove(startFirst, field);
        }
    }

    public void aiEasyMove(boolean startFirst, boolean easy, String[][] field) {
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
            }
        }
    }

    public void aiMediumMove(boolean startFirst, String[][] field) {

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
                y1 = 1;
                for (int z = 3; z > 0; z--) {
                    if (field[y1][z].equals(" ")) {
                        field[y1][z] = pick;
                        check = true;
                    }
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
                    y1 = 1;
                    for (int z = 3; z > 0; z--) {
                        if (field[y1][z].equals(" ")) {
                            field[y1][z] = pick;
                            check2 = true;
                        }
                    }
                }
            }
        }
        if (!check && !check2) {
            aiEasyMove(startFirst, false, field);
        }
    }
}
