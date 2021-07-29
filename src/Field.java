public class Field {
    String[][] field = new String[5][5];

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
}
