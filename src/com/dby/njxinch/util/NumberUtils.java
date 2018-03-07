package com.dby.njxinch.util;

import java.util.Random;

/**
 * Created by yanhai on 2015/4/14.
 */
public class NumberUtils {
        private static Random randGen = null;
        private static char[] numbersAndLetters = null;
        private static Random randGen1 = null;
        private static char[] numbersAndLetters1 = null;

        /** 随即生成数字 */
        public static final String randomString() {

            if (randGen == null) {
                randGen = new Random();
                numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
                        + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
                // numbersAndLetters =
                // ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
            }
            char[] randBuffer = new char[6];
            for (int i = 0; i < randBuffer.length; i++) {
                randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
                // randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
            }
            return new String(randBuffer);
        }

        /** 短信验证码 */
        public static final String randomNum() {

            if (randGen1 == null) {
                randGen1 = new Random();
                numbersAndLetters1 = ("0123456789").toCharArray();
                // numbersAndLetters =
                // ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
            }
            char[] randBuffer = new char[6];
            for (int i = 0; i < randBuffer.length; i++) {
                randBuffer[i] = numbersAndLetters1[randGen1.nextInt(10)];
                // randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
            }
            return new String(randBuffer);
        }


}
