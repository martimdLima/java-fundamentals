package com.martimlima.javaprojects.strings.not_string;

class NotString {

    public static void main(String[] args) {

        notString("semicolon");
        notString("not semicolon");

        //receive command line argument if available
        if(args.length>0){
            notString(args[0]);
        }

    }

    private static void notString(String str) {
        String not = "not ";
        String newS;
        if(!str.contains("not")) {
            newS = not + str;
        } else {
            newS = str.replace("not ", "");
        }

        System.out.println(newS);

    }
}