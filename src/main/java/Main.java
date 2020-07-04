import Field.Field;
import Field.FieldIsFull;
import Player.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    private Main(){
        Field.createField();
        createUsers();
        run();
    }

    private void run(){
        boolean run = true;
        boolean win;
        while(run){
            for(User user : User.getUsers()){
                try {
                    win = user.step();
                    if (win) {
                        run = false;
                        break;
                    }
                } catch (FieldIsFull FFEx){
                    run = false;
                    System.out.println("Поле заполнено");
                    break;
                }
            }
        }
    }

    private void createUsers(){
        int userNum = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите с кем вы хотите играть.");
        System.out.println("1: человек, 2: компьютер");
        do{
            try {
                System.out.print("{You} -> ");
                userNum =Integer.parseInt(br.readLine());
            }catch (IOException IOEx){}
            catch (NumberFormatException NFEx){
                System.out.println("вы ввели не число");
                continue;
            }
            if(userNum>0 && userNum < 3) break;
            System.out.println("Неверный ввод");
        }while (true);

        new User("You", 'X', User.UsersType.HUMAN);

        switch (userNum){
            case 1:
                new User("Player1", '0', User.UsersType.HUMAN);
                break;
            case 2:
                new User("Computer1", '0', User.UsersType.COMPUTER);
                break;
        }
    }
}
