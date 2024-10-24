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
    }

    public void play() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("숫자를 입력하세요.");
            List<String> inputNumberList;
            Set<String> inputNumberSet = new LinkedHashSet<>();

            while (inputNumberSet.size() < 3) {
                inputNumberSet.clear();
                String inputNumbers = sc.next();
                for (int i = 0; i < inputNumbers.length(); i++) {
                    char ch = inputNumbers.charAt(i);
                    String number = Character.toString(ch);
                    inputNumberSet.add(number);
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
                break;
            }
        }
        sc.close();
    }
}
