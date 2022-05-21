package com.company;

import java.io.*;
import java.util.Scanner;

class Example implements Serializable {
    double x;
    double y;
    Example(){
        x=0;
        y=0;
    }
   double sinus (double x){
        this.x = x;
        y = this.x - Math.sin(this.x);
        return (y);
    }
}

public class Main {
    public static void main(String[] args) {
        String txt;
        Scanner in = new Scanner(System.in);
        Example ex = new Example();
        System.out.print("Введите значение х: ");
        while (true) {
            txt = in.nextLine();
            try {
                double x = Double.parseDouble(txt);
                ex.x = x;
                ex.sinus(x);
                System.out.println("Введите команду: save, upload, check");
            } catch (Exception IOe) {
                if (txt.equalsIgnoreCase("save")) {
                    try (ObjectOutputStream wr = new ObjectOutputStream(new FileOutputStream("sin.txt"))) {
                        wr.writeObject(ex);
                        System.out.println("Данные сохранены");
                    } catch (IOException exc) {
                        exc.getMessage();
                    }
                } else if (txt.equalsIgnoreCase("upload")) {
                    try (ObjectInputStream rd = new ObjectInputStream(new FileInputStream("sin.txt"))) {
                        ex = (Example) rd.readObject();
                        System.out.println("Успешно");
                    } catch (Exception exc) {
                        exc.getMessage();
                    }
                } else if (txt.equalsIgnoreCase("check")) {
                    System.out.printf("x: %s\ny: %s\n", ex.x, ex.y);
                } else {
                    System.out.println("Такой команды нет");
                }
                System.out.println("Введите команду: save, upload, check");
            }
        }
    }
}