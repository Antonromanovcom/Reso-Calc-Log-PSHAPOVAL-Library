package ru.reso.resocalc.Entity;

import ru.reso.wp.srv.db.models.StmtParam;


/**
 * Это какой-то класс SHAPP для передачи в БД для SQL-запроса параметров
  */
public class MyStmtParam extends StmtParam {

    private String fieldName;
    private String fieldType;
    private String logValue;


    public MyStmtParam(int type, Object value) {
        super(type, value);
    }
    public MyStmtParam(int type, Object value, String fieldName, String fieldType) {
        super(type, value);
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }



    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getLogValue() {
        return logValue;
    }

    public void setLogValue(String logValue) {
        this.logValue = logValue;
    }
}
