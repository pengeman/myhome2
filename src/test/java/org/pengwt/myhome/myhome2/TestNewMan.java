package org.pengwt.myhome.myhome2;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;


public class TestNewMan {

    @Test
    public void emulate_new_env() {
        new File(NewMan.getDBFilePath()).delete();
    }

    @SneakyThrows
    @Test
    public void test_createDB() {
        new File(NewMan.getDBFilePath()).delete();
        new NewMan().createDB(1);
    }
}
