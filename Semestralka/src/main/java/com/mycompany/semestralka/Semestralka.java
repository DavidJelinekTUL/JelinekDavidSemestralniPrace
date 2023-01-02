package com.mycompany.semestralka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * 26. Program má za úkol zjistit, zda uživatelem zadaná matice je takzvaně
 * magická (součet jednotlivých sloupců, řádků a diagonál je u všech stejný).
 *
 * @author david
 */
public class Semestralka {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Integer> ans = new ArrayList<>();

    /**
     * Metoda main je rozdělena na 3 části: Input, Calculate a Output. Input: V
     * části input se ověřuje pomocí metody isNumber (níže popsaná), zda vložená
     * data mají chtěný formát. Veškerý vstup je v while-loopu, který
     * zapříčiňuje libovolný počet vstupů, dokud uživatel nezadá nekladné číslo.
     * Calculate: Část calculate pouze volá metodu zodpovědnou pro vešekeré
     * výpočty isMagic (popsanou níže) Output: Část output tlumočí z uživatelsky
     * neznámého stavu do stavu lepšího (tj.: false-> Nejedná se.... a true->
     * Jedná se....)
     *
     * @param args
     */
    static Christmas ch = new Christmas();

    public static void printMenu() {
        System.out.println("%-------------------------------------%");
        System.out.println("       Co si přejete spustit?");
        System.out.println("    Zadejte 1 pro Vánoční úlohy");
        System.out.println("Zadejte 2 pro Semestrální práci č.26)");
        System.out.println("%-------------------------------------%");
    }

    public static void main(String[] args) {
        int size = 2;
        while (size > 0) {
            printMenu();
            if (isNumberMenu(sc.nextLine()) == 1) {
                ch.summonMenu();
            } else if(isNumberMenu(sc.nextLine()) == 2) {
                //INPUT
                System.out.println("Zadej rozmer matice: ");
                size = isNumberMenu(sc.nextLine());
                if (size < 1) {
                    break;
                }
                int[][] list = fill(size);
                //int[][] list = testerFalse(size);  //Vzorový špatný vstup  TEST
                //int[][] list = testerTrue(size);   //Vzorový správný vstup TEST
                isMagic(list);

                //OUTPUT
                prnitMatrix(list);
                if (result(list)) {
                    System.out.println("Jedná se o magický čtverec");
                } else {
                    System.out.println("Nejedná se o magický čtverec");
                }
            }
        }

    }

    /**
     * Metoda pro načtení Špatných testovacích hodnot
     *
     * @param size
     * @return
     */
    public static int[][] testerFalse(int size) {
        System.out.println("Prvni matice");
        int[][] list = new int[size][size];
        list[0][0] = 1;
        list[1][0] = 2;
        list[2][0] = 3;

        list[0][1] = 4;
        list[1][1] = 5;
        list[2][1] = 6;

        list[0][2] = 7;
        list[1][2] = 8;
        list[2][2] = 9;

        return list;
    }

    /**
     * Metoda pro načtení Správných testovacích hodnot
     *
     * @param size
     * @return
     */
    public static int[][] testerTrue(int size) {
        System.out.println("Prvni matice");
        int[][] list = new int[size][size];
        list[0][0] = 2;
        list[1][0] = 7;
        list[2][0] = 6;

        list[0][1] = 9;
        list[1][1] = 5;
        list[2][1] = 1;

        list[0][2] = 4;
        list[1][2] = 3;
        list[2][2] = 8;

        return list;
    }

    /**
     * Metoda použitá k naplnění hodnotami zadanými uživatelem
     *
     * @param size
     * @return
     */
    public static int[][] fill(int size) {
        System.out.println("Prvni matice");
        int[][] list = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                list[j][i] = isNumber(sc.nextLine());
            }
            System.out.println(" ");
        }

        return list;
    }

    /**
     * Tato metoda slouží k kontrole vstupu. Je schopná vyfiltrovat prázdný
     * vstup a i nechtěné znaky Použil jsem funkci !input.matches("[0-9-]+") kde
     * limituji vstup na veškerá kladná i záporná čísla Pokud tato podmínka
     * nevyjde, tak se metoda spustí znovu, vytvářejíc tak nekonečný cyklus,
     * dokud uživatel nezadá chtěnou hodnotu.
     *
     * @param input
     * @return
     */
    public static int isNumber(String input) {
        if (!input.matches("[1-9]+")) {
            return isNumber(sc.nextLine());
        } else {
            return Integer.parseInt(input);
        }
    }

    public static int isNumberMenu(String input) {
        if (!input.matches("[1-9]+")) {
            if (!input.matches("T(-?[0-9]+)")) {
                System.exit(0);
            }
            return isNumber(sc.nextLine());
        } else {
            return Integer.parseInt(input);
        }
    }

    /**
     * Metoda "isMagic" řídí celou funkčnost programu, vstupují do ní data skrze
     * dvourozměrné pole list. Tyto data postupně vyhodnocuje, zda odpovídají
     * stanoveným podmínkám. Tyto vyhodnocená data poté vloží do ArrayListu,
     * který se postupně naplní výsledky. Metoda funguje postupně a to tak, že
     * si nejprve ověřuje sloupce, řádky a poté diagonály.
     *
     * @param size
     * @param list
     */
    static public void isMagic(int[][] list) {
        int temp = 0;
        int size = list.length;
        //Top to down
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp += list[i][j];
            }
            ans.add(temp);
            temp = 0;
        }

        //Left to Right
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp += list[j][i];
            }
            ans.add(temp);
            temp = 0;
        }

        //Diagonals
        for (int i = 0; i < size; i++) {
            temp += list[i][i];
        }
        ans.add(temp);
        temp = 0;
        for (int i = 0; i < size; i++) {
            temp += list[i][(size - 1) - i];
        }
        ans.add(temp);
        temp = 0;
        /**
         * Metoda poté vypíše celou matici, aby uživatel věděl, s jakými
         * hodnotami pracoval.
         */

    }

    public static void prnitMatrix(int[][] list) {
        System.out.println("Print: ");
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                System.out.print(list[j][i] + " ");
            }
            System.out.println(" ");
        }

    }

    /**
     * Tato metoda slouží k ověření výsledných dat a usoudění celkového stavu
     * výsledku. Vrací buďto true, za předpokladu, že podmínka byla pravdivá a
     * nebo false naopak. Ověřování funguje na principu, že uložená data(sečtené
     * hodnoty z jednotlivých řádků, sloupců a diagonál) se seřadí od nejmenší
     * po největší a pokud první a poslední hodnota jsou stejné, tak je podmínka
     * splněna. Zároveň také kontroluje, zda matice obsahuje 1,2,3,....n^2
     *
     * @param size
     * @param list
     * @return
     */
    static boolean result(int list[][]) {
        int size = list.length;
        boolean isAbsent = false;

        for (int i = 0; i < ans.size() - 1; i++) {
            if (!(ans.get(i).equals(ans.get(i + 1)))) {
                ans.clear();
                return false;

            }
        }
        
        for (int i = 0; i < Math.pow(size, 2); i++) {
            inner:
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (!(list[j][k] == i+1)) {
                        isAbsent = true;
                    } else {
                        isAbsent = false;
                        break inner;
                    }
                }

            }
            if (isAbsent) {
                return false;
            }
            isAbsent = false;
            
        }

        
        ans.clear();
        return true;

    }
}
