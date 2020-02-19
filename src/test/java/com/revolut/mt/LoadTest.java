package com.revolut.mt;

import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TestMapping;
import org.jsmart.zerocode.core.domain.TestMappings;
import org.jsmart.zerocode.jupiter.extension.ParallelLoadExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ParallelLoadExtension.class})
public class LoadTest {

    @Test
    @LoadWith("load-config.properties")
    @TestMappings({@TestMapping(testClass = AppLoadTest.class, testMethod = "transfer"),
    })
    public void testLoad() {
    }

}
