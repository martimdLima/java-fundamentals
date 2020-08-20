package com.martimlima.javaprojects.strings.missing_char;

public class Main {

    public static void main(String[] args) {
        missingChar("Robocop", 3);

        //receive command line argument if available
        if (args.length >= 2) {
            missingChar(args[0], Integer.parseInt(args[1]));
        }
    }

    private static void missingChar(String str, int n) {
        String beg = str.substring(0,n);
        String end = str.substring(n+1);
        System.out.print(beg+end);
    }
}
