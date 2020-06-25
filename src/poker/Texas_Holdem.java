package poker;

import java.util.Arrays;
import java.util.Scanner;

public class Texas_Holdem {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入黑方的手牌数据：（如）");
        System.out.println("2H 3D 5S 9C KD");
        System.out.print("Black:");
        String compareBlack = sc.nextLine();

        System.out.println("请输入白方的手牌数据：（如）");
        System.out.println("2C 3H 4S 8C AH");
        System.out.print("White:");
        String compareWhite = sc.nextLine();

        String results = compare(compareBlack, compareWhite);
        System.out.println("结果是: "+results);
    }

    //四种花色               方片  黑桃  红桃 梅花
    final char[] color = {'D', 'S', 'H', 'C'};

    //13张牌的数字
    final static char[] number = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};

    //比较方法
    public static String compare(String Black, String White){
        String[] BlackSingle = Black.split("\\s+");
        String[] WhiteSingle = White.split("\\s+");
        String result = "";
        int blackLevel = 0;
        int whiteLevel = 0;

        //同花顺及同花判断
        if(sameFlower(BlackSingle)) {
            if (sameFlower(WhiteSingle)) {
                if(isList(BlackSingle)){
                    if(isList(WhiteSingle))//都是同花顺
                        return bigger(BlackSingle, WhiteSingle);
                    else
                        return "Black wins";
                }
                else
                    if(isList(WhiteSingle))
                        return "White wins";
                    else  //都是同花但不是同花顺
                        return bigger(BlackSingle, WhiteSingle);
            }
            else
                return  "Black wins";
        }
        else
            if(sameFlower(WhiteSingle))
                return "White wins";

        //判断是否是顺子
        if(isList(BlackSingle)){
            if(isList(WhiteSingle))//都是顺子
                return bigger(BlackSingle, WhiteSingle);
            else
                return "Black wins";
        }
        else
            if(isList(WhiteSingle))
                return "White wins";

        //判断是否是三条
        if(threeSame(BlackSingle)){
            if(threeSame(WhiteSingle))//都是顺子
                return bigger(BlackSingle, WhiteSingle);
            else
                return "Black wins";
        }
        else
            if(threeSame(WhiteSingle))
                return "White wins";

        return hasPairs(BlackSingle,WhiteSingle);
    }

    //判断是否是同花
    private static boolean sameFlower(String[] cards) {
        char flower = 'D';
        for (int i = 0; i < 5; i++) {
            if (i != 0) {
                if (flower != cards[i].toCharArray()[1])
                    return false;
            }
            flower = cards[i].toCharArray()[1];
        }
        return true;
    }

    //判断是否是顺子
    private static boolean isList(String[] cards){
        int[] number = {0,0,0,0,0};
        //将5张牌的数字装入
        for(int i = 0; i<5; i++){
            for(int l = 0; l<13; l++){
                if(cards[i].toCharArray()[0] == Texas_Holdem.number[l])
                    number[i]=l;
            }
        }
        Arrays.sort(number);
        if(number[0]+5==number[4])
            return true;
        else
            return false;
    }

    //判断是否是三条(包括四张相同的情况)
    private static boolean threeSame(String[] cards){
        int[] number = {0,0,0,0,0};
        for(int i = 0; i<5; i++){
            for(int l = 0; l<13; l++){
                if(cards[i].toCharArray()[0] == Texas_Holdem.number[l])
                    number[i]=l;
            }
        }
        Arrays.sort(number);
        if(number[4]-number[0]<=2)
            return true;
        else
            return false;
    }

    //根据对子及散牌判断大小
    private static String hasPairs(String[] blackCards, String[] whiteCards){
        int[] numberB = {0,0,0,0,0};
        for(int i = 0; i<5; i++){
            for(int l = 0; l<13; l++){
                if(blackCards[i].toCharArray()[0] == Texas_Holdem.number[l])
                    numberB[i]=l;
            }
        }
        Arrays.sort(numberB);
        int n = numberB[0];
        int pairsB = 0;
        int blackP1 = 0;
        int blackP2 = 0;
        for(int i = 1; i<5; i++){
             if(n==numberB[i]) {
                 pairsB++;
                 if(blackP2==0 && blackP1!=0)
                     blackP2 = i;
                 if(blackP1==0)
                     blackP1 = i;
             }
             else
                 n = numberB[i];
        }

        int[] numberW = {0,0,0,0,0};
        for(int i = 0; i<5; i++){
            for(int l = 0; l<13; l++){
                if(whiteCards[i].toCharArray()[0] == Texas_Holdem.number[l])
                    numberW[i]=l;
            }
        }
        Arrays.sort(numberW);
        n = numberW[0];
        int pairsW = 0;
        int whiteP1 = 0;
        int whiteP2 = 0;
        for(int i = 1; i<5; i++){
            if(n == numberW[i]) {
                pairsW++;
                if (whiteP2 == 0 && whiteP1 != 0)
                    whiteP2 = i;
                if (whiteP1 == 0)
                    whiteP1 = i;
            }
            else
                n = numberW[i];
        }
        if(pairsB>pairsW)
            return "Black wins";
        else if (pairsW>pairsB)
            return "White wins";
        else{
            if(pairsB==2){
                if(numberB[blackP2]>numberW[whiteP2])
                    return "Black wins";
                else if(numberB[blackP2]<numberW[whiteP2])
                    return  "White wins";
                else {
                    if(numberB[blackP1]>numberW[whiteP1])
                        return "Black wins";
                    else if(numberB[blackP1]<numberW[whiteP1])
                        return  "White wins";
                    else{
                        int B1 = 0;
                        int W1 = 0;
                        for(int i = 0;i<5;i++){
                            if(i==blackP1-1 || i==blackP1 || i==blackP2-1 || i==blackP2);
                            else
                                B1 = numberB[i];
                        }
                        for(int i = 0;i<5;i++){
                            if(i==whiteP1-1 || i==whiteP1 || i==whiteP2-1 || i==whiteP2);
                            else
                                W1 = numberW[i];
                        }
                        if(B1>W1)
                            return "Black wins";
                        else if(B1<W1)
                            return "White wins";
                        else
                            return "tie";
                    }
                }
            }
            else if (pairsB==1){
                if(numberB[blackP1]>numberW[whiteP1])
                    return "Black wins";
                else if(numberB[blackP1]<numberW[whiteP1])
                    return  "White wins";
                else {
                    int B1 = 0;
                    int W1 = 0;
                    for (int i = 0; i < 5; i++) {
                        if (i == blackP1 - 1 || i == blackP1) ;
                        else
                            B1 = numberB[i];
                    }
                    for (int i = 0; i < 5; i++) {
                        if (i == whiteP1 - 1 || i == whiteP1) ;
                        else
                            W1 = numberW[i];
                    }
                    if (B1 > W1)
                        return "Black wins";
                    else if (B1 < W1)
                        return "White wins";
                    else
                        return "tie";
                }
            }
            else{
                return bigger(blackCards, whiteCards);
            }
        }
    }

    //判断散牌大小
    private static String bigger(String[] blackCards, String[] whiteCards){
        int[] numberB = {0,0,0,0,0};
        for(int i = 0; i<5; i++){
            for(int l = 0; l<13; l++){
                if(blackCards[i].toCharArray()[0] == Texas_Holdem.number[l])
                    numberB[i]=l;
            }
        }
        Arrays.sort(numberB);

        int[] numberW = {0,0,0,0,0};
        for(int i = 0; i<5; i++){
            for(int l = 0; l<13; l++){
                if(whiteCards[i].toCharArray()[0] == Texas_Holdem.number[l])
                    numberW[i]=l;
            }
        }
        Arrays.sort(numberW);

        for(int i = 4; i>=0; i--){
            if(numberB[i]>numberW[i])
                return "Black wins";
            else if(numberB[i]<numberW[i])
                return "White wins";
        }
        return "tie";
    }

    public static String add(int x,int y){
        String z = x+y+"";
        return z;
    }
    public int divide(int x,int y){
        return x/y;
    }
}
