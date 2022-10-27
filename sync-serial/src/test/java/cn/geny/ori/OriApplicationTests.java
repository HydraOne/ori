package cn.geny.ori;

import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class OriApplicationTests {

    @AllArgsConstructor
    public static class CompleteFlag{
        int time;
        int flag;
    }

    @Test
    void contextLoads() {
        int[] startTime = {1, 2, 3};
        int[] endTime = {3, 2, 7};
        int queryTime = 4;
    }


    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        return 0;
    }
}