import java.util.Random;
import java.util.Scanner;

public class HomeWorkXO {

    private static char[][] map;
    private static final char defaultValue = '•';
    private static final char userValue = 'X';
    private static final char compValue = '0';
    private static int mapSize = 3;
    private static int countFigure = 3;
    private static int step = 0;
    private static final Scanner SC = new Scanner(System.in);


    public static void main(String[] args) {

        System.out.println("Введите 1 для выбора поля 3х3 и кол-во фишек 3");
        System.out.println("Введите 2 для выбора поля 5х5 и кол-во фишек 4");
        int flag = 0;
        if (SC.hasNextInt()) {
            flag = SC.nextInt();
        }
        if (flag == 2){
            mapSize = 5;
            countFigure = 4;
        }

        initMap();
        printMap();

        while (true) {
            userStep();
            printMap();
            if ((step > 1) && isWin(userValue)) {
                System.out.println("Вы выиграли!");
                break;
            }
            if ((step > 1) && Draw()) {
                break;
            }
            compStep();
            printMap();
            if ((step > 1) && isWin(compValue)) {
                System.out.println("Вы проиграли!");
                break;
            }
            if ((step > 1) && Draw()) {
                break;
            }
            step+=1;
        }

    }


    private static boolean isWin(char dot) {

        for (int i = 0; i < mapSize; i++) {
            int horizon = 0;
            int vertical = 0;
            int LeftDiagonal = 0;
            int rightDiagonal  = countFigure-1;

            for (int j = 0; j < mapSize; j++) {
                if (map[i][j] == dot) {
                    horizon += 1;
                }
                if (map[j][i] == dot) {
                    vertical += 1;
                }
                if (map[j][j] == dot) {
                    LeftDiagonal += 1;
                }
                if (map[j][rightDiagonal] == dot) {
                    rightDiagonal -= 1;
                }
            }
            if ((horizon == countFigure) || (vertical == countFigure) || (LeftDiagonal == countFigure) || (rightDiagonal == -1)) {
                return true;
            }

        }

        return false;
    }


    private static boolean Draw() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == defaultValue) {
                    return false;
                }

            }

        }
        System.out.println("Ничья");
        return true;
    }


    private static void userStep() {

        int xCoordinate;
        int yCoordinate;
        System.out.println("Введите координаты в формате \"x пробел y\"");
        do {
            xCoordinate = -1;
            yCoordinate = -1;

            if (SC.hasNextInt()) {
                xCoordinate = SC.nextInt();
            }
            if (SC.hasNextInt()) {
                yCoordinate = SC.nextInt();
            }
            SC.nextLine();
        } while (!userValid(xCoordinate, yCoordinate));
    }

    public static boolean userValid(int xCoordinate, int yCoordinate) {
        if (xCoordinate < 1 || yCoordinate < 1 || xCoordinate > mapSize || yCoordinate > mapSize) {
            System.out.println("Вы ввели неправильные координаты. Введите координаты в формате \"x пробел y\"");
            return false;
        }
        if (map[xCoordinate - 1][yCoordinate - 1] == defaultValue) {
            map[xCoordinate - 1][yCoordinate - 1] = userValue;
            return true;
        }
        System.out.println("Вы ввели неправильные координаты. Введите координаты в формате \"x пробел y\"");
        return false;
    }


    private static void compStep() {
        Random rand = new Random();
        int xCoordinate;
        int yCoordinate;
        do {
            xCoordinate = rand.nextInt(mapSize);
            yCoordinate = rand.nextInt(mapSize);
        } while (!compValid(xCoordinate, yCoordinate));
    }

    public static boolean compValid(int xCoordinate, int yCoordinate) {
        if (map[xCoordinate][yCoordinate] == defaultValue) {
            map[xCoordinate][yCoordinate] = compValue;
            return true;
        }
        return false;
    }


    private static void printMap() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();

        }
        System.out.println();
    }

    public static void initMap() {
        map = new char[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = defaultValue;
            }
        }
    }


}
