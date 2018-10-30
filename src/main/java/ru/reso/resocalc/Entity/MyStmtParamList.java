package ru.reso.resocalc.Entity;

import ru.reso.wp.srv.db.models.StmtParam;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Это какой-то класс SHAPP для передачи в БД для SQL-запроса параметров. В данном случае это список параметров
 */
public class MyStmtParamList extends ArrayList<MyStmtParam> implements Serializable {

    public MyStmtParamList() {
    }

    public MyStmtParamList(int typeForParamList, Object o, String s, String s1) {
    }


}
