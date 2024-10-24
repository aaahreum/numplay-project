package com.example.numplay;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.*;

public class GameLogic {
    String number;
    List<String> answerNumbers;

    public GameLogic() {
        Random random = new Random();
        Set<String> randomNumber = new LinkedHashSet<>();

        // 중복된 숫자를 피하고 3개를 넣을 수 있도록 while문 사용
        while (randomNumber.size() < 3) {
            number = Integer.toString(1 + random.nextInt(9));
            randomNumber.add(number);
        }

        answerNumbers = new ArrayList<>(randomNumber);

//        System.out.println("answerNumbers = " + Arrays.deepToString(answerNumbers.toArray()));
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("< 게임을 시작합니다. >");

        while (true) {
//            System.out.println();
//            System.out.println("숫자를 입력하세요.");
            List<String> inputNumberList;
            Set<String> inputNumberSet = new LinkedHashSet<>();

            while (true) {
                System.out.println();
                System.out.println("숫자를 입력하세요.");
                inputNumberSet.clear();
                String inputNumbers = sc.next();
                if(inputNumbers.length() != 3){
                    System.out.println("세자리 수만 입력 가능합니다.");
                    continue;
                }
                boolean isDigit = true;
                for (int i = 0; i < inputNumbers.length(); i++) {
                    char ch = inputNumbers.charAt(i);
                    if(!Character.isDigit(ch)){
                        // ch가 숫자가 아닌 경우
                        isDigit = false;
                        break;
                    }
                    String number = Character.toString(ch);
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
