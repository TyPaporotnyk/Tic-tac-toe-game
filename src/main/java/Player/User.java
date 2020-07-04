package Player;

import Field.Field;
import Field.FieldIsFull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class User {
    private static List<User> users;
    private int steps;
    private String name;
    private char symbol;
    private UsersType user;
    private Random random;
    public enum UsersType{
        COMPUTER,
        HUMAN
    }

    public User(String name, char simble, UsersType user){
        this.name = name;
        this.symbol = simble;
        this.user = user;

        steps = 0;
        if(user == UsersType.COMPUTER){
            random = new Random();
        }
        addUser(this);
    }

    // Create and add user to list
    private static void addUser(User user){
        if(users == null){
            users = new ArrayList<>();
        }

        users.add(user);
    }

    public boolean step() throws FieldIsFull{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean run = true;

        System.out.println("Сейчах ходит {"+name+"}");

        switch (user) {
            case HUMAN:
                do {
                    int x;
                    int y;
                    try {
                        System.out.println("Введите позицию по Y: ");
                        y = Integer.parseInt(br.readLine())-1;
                        System.out.println("Введите позицию по X: ");
                        x = Integer.parseInt(br.readLine())-1;

                        if(Field.getGameField()[y][x] != Field.EMPTY_POSITION){
                            System.out.println("Это поле занято");
                            continue;
                        }
                        Field.setSimbleByPosition(y, x, symbol);
                        run = false;
                    }catch (IOException e){ }
                     catch (NumberFormatException ex) {
                        System.out.println("Вы ввели не число");
                    } catch (IndexOutOfBoundsException ex1) {
                        System.out.println("Вы вышли за границу игрового поля");
                    }
                } while (run);
                steps++;
                Field.paint();

                return isVin();

            case COMPUTER:
                do {
                    int x;
                    int y;
                        y = randInt(0, Field.getWidth()-1);
                        x = randInt(0, Field.getHeight()-1);

                        if(Field.getGameField()[y][x] != Field.EMPTY_POSITION) continue;
                        Field.setSimbleByPosition(y, x, symbol);
                        run = false;
                } while (run);
                steps++;
                Field.paint();
                if(isVin()) {
                    System.out.println(name + " победил");
                    return true;
                }
                return false;
        }
        return false;
    }

    private int randInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    private boolean checkDiagonal(int block) {
        boolean toright, toleft;
        toright = true;
        toleft = true;
        for (int i=0; i<block; i++) {
            toright &= (Field.getGameField()[i][i] == symbol);
            toleft &= (Field.getGameField()[block-i-1][i] == symbol);
        }

        if (toright || toleft) return true;

        return false;
    }

    // Проверяем горизонтальные и вертикальные линии
    private boolean checkLanes(int block) {
        boolean cols, rows;

        for (int col=0; col<block; col++) {
            cols = true;
            rows = true;
            for (int row=0; row<block; row++) {
                cols &= (Field.getGameField()[col][row] == symbol);
                rows &= (Field.getGameField()[row][col] == symbol);
            }

            // Это условие после каждой проверки колонки и столбца
            // позволяет остановить дальнейшее выполнение, без проверки
            // всех остальных столбцов и строк.
            if (cols || rows) return true;
        }

        return false;
    }

    // Преверяем победу
    public boolean isVin(){
        if(steps >= 3){
            if(checkLanes(Field.getBlock())){
                System.out.println(name + " победил");
                return true;
            }
            else if(checkDiagonal(3)){
                System.out.println(name + " победил");
                return true;
            }
        }

        return false;
    }

    public static List<User> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public char getSimble() {
        return symbol;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", simble=" + symbol +
                ", user=" + user +
                '}';
    }
}
