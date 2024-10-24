import com.example.numplay.GameLogic;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameLogic gamelogic = new GameLogic();
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
            System.out.println("1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");
            int choice = sc.nextInt();
            if (choice == 1) {
                gamelogic.play();
                break;
            }else if (choice == 2) {
                System.out.println("해당 기능을 준비중입니다.");
            }else if (choice == 3) {
                System.out.println("게임을 종료합니다.");
                break;
            }else {
                System.out.println("1, 2, 3 중에 선택하세요.");
            }
        }

        sc.close();
    }
}
