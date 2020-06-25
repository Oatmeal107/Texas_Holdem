package poker;

import static org.junit.jupiter.api.Assertions.*;

class TexasHoldemTest {

    @org.junit.jupiter.api.Test
    void compare1() {
        //测试Texas_Holdem类中的compare方法
        String result=new Texas_Holdem().compare("2H 3D 5S 9C KD", "2C 3H 4S 8C AH");
        //判断
        assertEquals("White wins", result);
    }

    @org.junit.jupiter.api.Test
    void compare2() {
        //测试Texas_Holdem类中的compare方法
        String result=new Texas_Holdem().compare("2H 4S 4C 2D 4H", "2S 8S AS QS 3S");
        //判断
        assertEquals("White wins", result);
    }

    @org.junit.jupiter.api.Test
    void compare3() {
        //测试Texas_Holdem类中的compare方法
        String result=new Texas_Holdem().compare("2H 3H 5H 9H KH", "2C 3H 4S 5C 6H");
        //判断
        assertEquals("Black wins", result);
    }

    @org.junit.jupiter.api.Test
    void compare4() {
        //测试Texas_Holdem类中的compare方法
        String result=new Texas_Holdem().compare("2H 3D 5S 9C KD", "2D 3H 5C 9S KH");
        //判断
        assertEquals("tie", result);
    }
}