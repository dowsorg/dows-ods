package org.dows.ods.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoBizTest {
    @Autowired
    private DemoBiz demoBiz;
    @Test
    public void testDemo(){
        String param = "";
        demoBiz.completeExp(param);
        System.out.println();
    }


}
