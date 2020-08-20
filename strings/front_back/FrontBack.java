package com.martimlima.javaprojects.strings.front_back;

class FrontBack {

    public static void main(String[] args) {

        frontBack("heisenberg");

        //receive command line argument if available
        if(args.length>0){
            frontBack(args[0]);
        }
    }

    private static void frontBack(String str) {
        char first = str.charAt(0);
        char last = str.charAt(str.length()-1);
        int index = str.indexOf(0);
        int lastIndex = str.lastIndexOf(str.length()-1);

        String newString = str;
        newString = newString.replace(last, first);
        newString = newString.replace(first, last);

        System.out.println(newString);

    }

}