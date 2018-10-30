package ru.reso.resocalc.Service;


import ru.reso.resocalc.Entity.Interfaces.CalcEntity;
import ru.reso.resocalc.Entity.ComparedParam;
import ru.reso.resocalc.Entity.WsCoeff;
import ru.reso.resocalc.Factories.ConcreteFactories.BonusFactory;
import ru.reso.resocalc.Factories.ConcreteFactories.CommonLogsFactory;
import ru.reso.resocalc.Factories.ConcreteFactories.WsCalcLogFactory;
import ru.reso.resocalc.Factories.ConcreteFactories.WsCoeffCalcFactory;
import ru.reso.resocalc.Utils.sqlLogging;
import java.util.LinkedHashMap;


/**
 * Основной сервисный класс, осуществляющий собственно сверку.
 */
public class ComplexCompair {

    private static WsCoeff coeffCalc1st = new WsCoeff();
    private static WsCoeff coeffCalc2d = new WsCoeff();

    /** Метод, проверяющий если при сравнии коэфициентов у calcid 2 больше записей в таблице по коэфициентам чем у сalcid 1, то меняем их местами. Но
     * меняем уже на клиенте.
      * @param calcIdFirst
     * @param calcIdSecond
     * @return
     */
    public static Integer checkCoefs(Integer calcIdFirst, Integer calcIdSecond) {
        WsCoeffCalcFactory wsCoeffCalcFactory = new WsCoeffCalcFactory();

        Integer result = 0;
        Integer coeffRecCount1st = wsCoeffCalcFactory.getReordsCount(sqlLogging.SQL_GET_COEFFS_COUNT_BY_ID, calcIdFirst.longValue());
        Integer coeffRecCount2d = wsCoeffCalcFactory.getReordsCount(sqlLogging.SQL_GET_COEFFS_COUNT_BY_ID, calcIdSecond.longValue());

        if (coeffRecCount1st >= coeffRecCount2d) {
            result = 1;
        } else {
            result = 2;
        }
        return result;
    }

    /**
     * Запрашиваем названия полей для Коэфициентов
     * @param id
     * @return
     */
    public static String getDescriptionByCoefID(int id) {
        String result;

        WsCoeffCalcFactory wsCoeffCalcFactory = new WsCoeffCalcFactory();
        result = wsCoeffCalcFactory.getCoeffDescription(id);


        return result;
    }

    /**
     * Получаем полноценные дискрипшены для Премий.
     * @param id
     * @return
     */
    public static String getDescriptionByPremiumID(int id) {
        String result;

        BonusFactory bonusFactory = new BonusFactory();
        result = bonusFactory.getBonusDescription(id);


        return result;
    }

    /**
     * Получаем дескрипшены для WsCalcLog
     * @param columnName
     * @return
     */
    public static String getDescription4WsCoeffCalc(String columnName) {
        String result;

        WsCalcLogFactory wsCalcLogFactory = new WsCalcLogFactory();
        result = wsCalcLogFactory.getDescription4Column(columnName);


        return result;
    }

    /**
     * Запрашиваем нормальные дискрипшены для CommonLogs
     * @param columnName
     * @return
     */
    public static String getDescription4WsCommonLogs(String columnName) {
        String result;

        CommonLogsFactory commonLogsFactory = new CommonLogsFactory();
        result = commonLogsFactory.getDescription4Column(columnName);

        return result;
    }


    /**
     * Основной метод сравнения calcId. Работает для всех типов Энтити и Фабрик
     * @param calcEntity1st
     * @param calcEntity2d
     * @return
     */
    public static LinkedHashMap<String, ComparedParam> getFullCompareNew(CalcEntity calcEntity1st, CalcEntity calcEntity2d) {

        LinkedHashMap<String, ComparedParam> result = new LinkedHashMap<>();

        if ((calcEntity1st != null) && (calcEntity2d != null)) { //проверка, что оба энтити не нулевые

            for (String name : calcEntity1st.getHash().keySet()) { // циклим по первому энтити (по ключам)
                if ((calcEntity1st.getHash().get(name)) != null) { //проверяем, чтобы запись по ключу в цикле у хэша первого энтити была не ноль
                    if ((calcEntity2d.getHash().get(name)) != null) { //проверяем, чтобы запись по ключу в цикле у хэша второго энтити была не ноль
                        if ((calcEntity1st.getHash().get(name)).equals(calcEntity2d.getHash().get(name))) { //если записи в хэшах обоих энтити совпали....
                            result.put(name, new ComparedParam(name, (calcEntity1st.getHash().get(name)), (calcEntity2d.getHash().get(name)), true)); //кладем в новый хэш
                        } else {
                           // System.out.println(name + " - " + (calcEntity1st.getHash().get(name)) + ":" + (calcEntity2d.getHash().get(name)));
                            result.put(name, new ComparedParam(name, (calcEntity1st.getHash().get(name)), (calcEntity2d.getHash().get(name)), false)); //тоже кладем, но ставим флаг - ложь
                        }
                    } else {
                        result.put(name, new ComparedParam(name, calcEntity1st.getHash().get(name), "", false)); //calcEntity2d - нулевой хэш. Пишем Ложь и "" вместо второго параметра
                    }
                } else { // Хэш calcEntity1st дал НОЛЬ
                    if ((calcEntity2d.getHash().get(name)) != null) { // если второй хэш таки не ноль
                        result.put(name, new ComparedParam(name, "", calcEntity2d.getHash().get(name), false)); // первый параметр - ноль, результат сравнения - ложь
                    } else {
                        result.put(name, new ComparedParam(name, "", "", true)); // все ноль. результат сравнения - тру
                    }
                }
            }
        } else if ((calcEntity1st == null) && (calcEntity2d == null)) { // если оба энтити вообще ноль.
            result = null; // в хэш просто пишем ноль
        } else if ((calcEntity1st != null) && (calcEntity2d == null)) { // если первый энтити не ноль, а второй ноль
            for (String name : calcEntity1st.getHash().keySet()) { // циклим по первому хэшу чтобы собрать поля
                result.put(name, new ComparedParam(name, calcEntity1st.getHash().get(name), "", false)); //везде пишем первый хэш, второй параметр - "", булеан - ноль.
            }
        } else if ((calcEntity1st == null) && (calcEntity2d != null)) { // если первый энтити ноль, а втлорой не ноль....
            for (String name : calcEntity2d.getHash().keySet()) { // циклим по второму энтити, чтобы собрать имена
                result.put(name, new ComparedParam(name, "", calcEntity2d.getHash().get(name), false)); // пишем "", значения со второго хэша, булеан - ложь.
            }
        }


        return result;
    }

}
