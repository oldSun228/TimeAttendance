package com.dby.njxinch.util;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.dby.njxinch.common.MatrixToImageWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


public class MakeTwoImg {
   public static void makeTwoImg(String contents,File file){
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.QR_CODE, 250, 250, hints);
        } catch (WriterException e) {

            e.printStackTrace();
        }
        try {

            MatrixToImageWriter.writeToFile(matrix, "png", file);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}