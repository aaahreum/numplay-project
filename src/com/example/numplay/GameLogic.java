package com.example.numplay;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.*;

/**
 * 숫자 야구 게임의 로직을 처리하는 클래스입니다.
 * 컴퓨터가 생성한 3자리 숫자를 플레이어가 맞추는 게임입니다.
 */
public class GameLogic {
    String number;
    List<String> answerNumbers;

    /**
     * 생성자 메서드이며 게임이 시작될 때 중복되지 않는 3자리의 숫자를 랜덤으로 생성합니다.
     * 중복을 허용하지 않는 숫자를 저장하기 위해 LinkedHashSet을 생성합니다.
     */
    public GameLogic() {
        Random random = new Random();
        Set<String> randomNumber = new LinkedHashSet<>();

        /**
         * 중복되지 않는 3개의 숫자를 생성할 때까지 반복합니다.
         */
        while (randomNumber.size() < 3) {
            number = Integer.toString(1 + random.nextInt(9));
            randomNumber.add(number);
        }

        /**
         * ArrayList로 변환하여 배열 형태로 사용할 수 있도록 합니다.
         */
        answerNumbers = new ArrayList<>(randomNumber);

//        System.out.println("answerNumbers = " + Arrays.deepToString(answerNumbers.toArray()));
    }

    /**
     * 플레이어가 숫자를 입력하고 입력된 숫자와 정답을 비교하여 결과를 출력합니다.
     */
    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("< 게임을 시작합니다. >");

        while (true) {
            List<String> inputNumberList;
            Set<String> inputNumberSet = new LinkedHashSet<>();

            /**
             * 유효한 숫자를 입력 받을 때까지 반복합니다.
             */
            while (true) {
                System.out.println();
                System.out.println("숫자를 입력하세요.");
                inputNumberSet.clear();
                String inputNumbers = sc.next();

                if(inputNumbers.length() != 3){
                    System.out.println("세자리 수만 입력 가능합니다.");
                    continue;
                }

                /**
                 * 입력한 문자열이 숫자로만 구성되어있는지 확인합니다.
                 * 문자를 char 타입 변수에 저장하여 문자를 순차적으로 검사합니다.
                 */
                boolean isDigit = true;

                for (int i = 0; i < inputNumbers.length(); i++) {
                    char inputCharacter = inputNumbers.charAt(i);
                    if(!Character.isDigit(inputCharacter)){
                        // ch가 숫자가 아닌 경우
                        isDigit = false;
                        break;
                    }
                    String number = Character.toString(inputCharacter);
                    inputNumberSet.add(number);
                }

                if(!isDigit){
                    System.out.println("입력값에 숫자만 포함되어야 합니다.");
                    continue;
                }

                if(inputNumberSet.size() < 3){
                    System.out.println("중복된 숫자가 있습니다. 다시 입력하세요.");
                    continue;
                }

                if(inputNumberSet.size() == 3){
                    break;
                }
            }
            inputNumberList = new ArrayList<>(inputNumberSet);

            int strike = 0;
            int ball = 0;
            int out = 0;

            /**
             * 사용자가 입력한 숫자와 정답을 비교하여 스트라이크와 볼의 개수를 계산합니다.
             * 숫자가 정확한 위치에 있을 때는 스트라이크를, 정답에 있지만 위치가 다를 때는 볼을 증가시킵니다.
             * 아무 것도 일치 하지 않을 때는 아웃을 증가시킵니다.
             */
            for (int inputNumberIdx = 0; inputNumberIdx < inputNumberList.size(); inputNumberIdx++) {
                String inputNumber = inputNumberList.get(inputNumberIdx);
                boolean isMatchedNumber = false;
                for (int answerNumberIdx = 0; answerNumberIdx < this.answerNumbers.size(); answerNumberIdx++) {
                    String answerNumber = this.answerNumbers.get(answerNumberIdx);
                    if (Objects.equals(inputNumber, answerNumber)) {
                        isMatchedNumber = true;
                        if (inputNumberIdx == answerNumberIdx) {
                            strike++;
                        } else {
                            ball++;
                        }
                    }
                }
                if (!isMatchedNumber) {
                    out++;
                }
            }

            if (strike > 0 && strike < 3) {
                System.out.print(strike + "스트라이크 ");
            }
            if (ball > 0) {
                System.out.print(ball + "볼");
                System.out.println();
            }
            if (strike == 0 && ball == 0) {
                System.out.println("아웃");
            }

            if (strike == 3) {
                System.out.println("정답입니다!");
                System.out.println();
                break;
            }
        }

    }
}
