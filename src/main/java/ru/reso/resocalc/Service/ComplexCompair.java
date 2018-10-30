package ru.reso.resocalc.Service;


import ru.reso.resocalc.Entity.CalcEntity;
import ru.reso.resocalc.Entity.ComparedParam;
import ru.reso.resocalc.Entity.WsCalcLogsNew;
import ru.reso.resocalc.Entity.WsCoeff;
import ru.reso.resocalc.Service.Factories.ConcreteFactories.WsCoeffCalcFactory;
import ru.reso.resocalc.Utils.Factories.ConcreteFactories.BonusFactory;
import ru.reso.resocalc.Utils.Factories.ConcreteFactories.CommonLogsFactory;
import ru.reso.resocalc.Utils.Factories.ConcreteFactories.WsCalcLogFactory;

import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.reso.resocalc.Service.WsCalcLogDao.getLogByCalcID;


public class ComplexCompair {

    // ----------- ОСНОВНОЙ CALC-LOG --------------------------------
    private static WsCalcLogsNew calcLog1st = new WsCalcLogsNew();
    private static WsCalcLogsNew calcLog2d = new WsCalcLogsNew();


    // ----------- КОЭФИЦИЕНТЫ (НО В БУДУЩЕМ ФАБРИЧНОЕ ПОЛУЧЕНИЕ ДАННЫХ ДЛЯ ВСЕГО ОСТАЛЬНОГО)  --------------------------------

    private static WsCoeff coeffCalc1st = new WsCoeff();
    private static WsCoeff coeffCalc2d = new WsCoeff();

    public static LinkedHashMap<String, Integer> getLightCompare(Integer calcIdFirst, Integer calcIdSecond) {

        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();


        // ------------------------------------- ЧАСТЬ I. WS-CALC-LOGS-NEW ---------------------------------------------

        /*WsCalcLogsNew calcLog1st = getLogByCalcID(calcIdFirst.longValue());
        WsCalcLogsNew calcLog2d = getLogByCalcID(calcIdSecond.longValue()); */

        calcLog1st = getLogByCalcID(calcIdFirst.longValue());
        calcLog2d = getLogByCalcID(calcIdSecond.longValue());

        Logger.getLogger("").log(Level.SEVERE, "ЩА БУДЕМ СРАВНИВАТЬ", "!!!");

    /*    for (String name : calcLog1st.getHash().keySet()) {

            String key = name;
            String value = calcLog1st.getHash().get(name);
            System.out.println(key + " -  " + value);
        }

*/
        for (String name : calcLog1st.getHash().keySet()) {

            if ((calcLog1st.getHash().get(name)) != null) {
                if ((calcLog2d.getHash().get(name)) != null) {
                    if ((calcLog1st.getHash().get(name)).equals(calcLog2d.getHash().get(name))) {
                        ///       Logger.getLogger("").log(Level.SEVERE, "СРАВНИВАЕМ [" + name + "] - РЕЗУЛЬТАТ - 1", "!!!");
                        result.put(name, 1);
                    } else {
                        //          Logger.getLogger("").log(Level.SEVERE, "СРАВНИВАЕМ [" + name + "] - РЕЗУЛЬТАТ - 0", "!!!");
                        result.put(name, 0);
                    }

                } else {

                    //         Logger.getLogger("").log(Level.SEVERE, "СРАВНИВАЕМ [" + name + "]. ПЕРВОЕ НЕ НОЛЬ, А ВТОРОЕ НОЛЬ. ЗНАЧИТ НЕ РАВНЫ", "!!!");
                    result.put(name, 0);

                }
            } else {

                //      Logger.getLogger("").log(Level.SEVERE, "СРАВНИВАЕМ [" + name + "] - ОБА NULL", "!!!");
                result.put(name, 1);
            }
        }
        return result;
    }

    public static LinkedHashMap<String, ComparedParam> getFullCompare(Integer calcIdFirst, Integer calcIdSecond) {

        LinkedHashMap<String, ComparedParam> result = new LinkedHashMap<>();

        calcLog1st = getLogByCalcID(calcIdFirst.longValue());
        calcLog2d = getLogByCalcID(calcIdSecond.longValue());


        if (calcLog2d == null) {
            Logger.getLogger("").log(Level.SEVERE, "calcLog2d - ноль", "!!!!!!!!!!!");
        }

        if ((calcLog1st != null) && (calcLog2d != null)) {

            for (String name : calcLog1st.getHash().keySet()) {
                if ((calcLog1st.getHash().get(name)) != null) {
                    if ((calcLog2d.getHash().get(name)) != null) {
                        if ((calcLog1st.getHash().get(name)).equals(calcLog2d.getHash().get(name))) {
                            result.put(name, new ComparedParam(name, calcLog1st.getHash().get(name), calcLog2d.getHash().get(name), true));
                        } else {
                            result.put(name, new ComparedParam(name, calcLog1st.getHash().get(name), calcLog2d.getHash().get(name), false));
                        }
                    } else {
                        result.put(name, new ComparedParam(name, calcLog1st.getHash().get(name), "", false));
                    }
                } else {
                    if ((calcLog2d.getHash().get(name)) != null) {
                        result.put(name, new ComparedParam(name, "", calcLog2d.getHash().get(name), false));
                    } else {
                        result.put(name, new ComparedParam(name, "", "", true));
                    }
                }
            }
        } else if ((calcLog1st == null) && (calcLog2d == null)) {
            result = null;
        } else if ((calcLog1st != null) && (calcLog2d == null)) {
            for (String name : calcLog1st.getHash().keySet()) {
                result.put(name, new ComparedParam(name, calcLog1st.getHash().get(name), "", false));
            }

        } else if ((calcLog1st == null) && (calcLog2d != null)) {
            for (String name : calcLog2d.getHash().keySet()) {
                result.put(name, new ComparedParam(name, "", calcLog2d.getHash().get(name), false));
            }
        }


        return result;
    }

    public static Integer checkCoefs(Integer calcIdFirst, Integer calcIdSecond) {
        WsCoeffCalcFactory wsCoeffCalcFactory = new WsCoeffCalcFactory();

        Integer result = 0;
        Integer coeffRecCount1st = wsCoeffCalcFactory.getReordsCount(calcIdFirst.longValue());
        Integer coeffRecCount2d = wsCoeffCalcFactory.getReordsCount(calcIdSecond.longValue());

        if (coeffRecCount1st >= coeffRecCount2d) {
            result = 1;
        } else {
            result = 2;
        }
        return result;
    }

    public static LinkedHashMap<String, ComparedParam> getFullCompare4Coefs(Integer calcIdFirst, Integer calcIdSecond) {

        LinkedHashMap<String, ComparedParam> result = new LinkedHashMap<>();
        WsCoeffCalcFactory wsCoeffCalcFactory = new WsCoeffCalcFactory();

        coeffCalc1st = wsCoeffCalcFactory.getEntityByCalcId(calcIdFirst.longValue());
        coeffCalc2d = wsCoeffCalcFactory.getEntityByCalcId(calcIdSecond.longValue());


        //Logger.getLogger("").log(Level.SEVERE, "ПРОВЕРИМ ЛОГ 1 - " + (coeffCalc1st.getCoeffCalcList().get(1).getTest()), "!!!!!!!!!!!");
        // Logger.getLogger("").log(Level.SEVERE, "ПРОВЕРИМ ЛОГ 2 - " + (coeffCalc2d.getTest()), "!!!!!!!!!!!");

        for (String name : coeffCalc1st.getHash().keySet()) {

            if ((coeffCalc1st.getHash().get(name)) != null) {
                if ((coeffCalc2d.getHash().get(name)) != null) {

                    //  Logger.getLogger("").log(Level.SEVERE, "КЛАДЕМ - " + (coeffCalc1st.getHash().get(name)) + " : " + (coeffCalc2d.getHash().get(name)), "!!!!!!!!!!!");

                    if ((coeffCalc1st.getHash().get(name)).equals(coeffCalc2d.getHash().get(name))) {

                        result.put(name, new ComparedParam(name, (coeffCalc1st.getHash().get(name)), (coeffCalc2d.getHash().get(name)), true));
                    } else {
                        result.put(name, new ComparedParam(name, (coeffCalc1st.getHash().get(name)), (coeffCalc2d.getHash().get(name)), false));
                    }

                } else {
                    result.put(name, new ComparedParam(name, coeffCalc1st.getHash().get(name), "", false));
                }
            } else {
                if ((calcLog2d.getHash().get(name)) != null) {
                    result.put(name, new ComparedParam(name, "", coeffCalc2d.getHash().get(name), false));
                } else {
                    result.put(name, new ComparedParam(name, "", "", true));
                }

            }
        }
        return result;
    }

    public static String[] getValuesFrom2LogsByKey(String name) {

        //String value1 = String.valueOf(WsCalcLogDao.searchInClassFieldsAndGet(calcLog1st, name));
        //String value1 = (WsCalcLogDao.searchInClassFieldsAndGet(calcLog1st, name)).toString();

        String value1 = calcLog1st.getHash().get(name.trim());
        String value2 = calcLog2d.getHash().get(name.trim());

        Logger.getLogger("").log(Level.SEVERE, "Name = " + name, "!!!");
        Logger.getLogger("").log(Level.SEVERE, "Value1 = " + value1, "!!!");


        String[] stringArray4Return = new String[2];

        stringArray4Return[0] = value1;
        stringArray4Return[1] = value2;


        return stringArray4Return;
    }

    public static String getDescriptionByCoefID(int id) {
        String result;

        WsCoeffCalcFactory wsCoeffCalcFactory = new WsCoeffCalcFactory();
        result = wsCoeffCalcFactory.getCoeffDescription(id);


        return result;
    }

    public static String getDescriptionByPremiumID(int id) {
        String result;

        BonusFactory bonusFactory = new BonusFactory();
        result = bonusFactory.getBonusDescription(id);


        return result;
    }

    public static String getDescription4WsCoeffCalc(String columnName) {
        String result;

        WsCalcLogFactory wsCalcLogFactory = new WsCalcLogFactory();
        result = wsCalcLogFactory.getDescription4Column(columnName);


        return result;
    }

    public static String getDescription4WsCommonLogs(String columnName) {
        String result;

        CommonLogsFactory commonLogsFactory = new CommonLogsFactory();
        result = commonLogsFactory.getDescription4Column(columnName);

        return result;
    }



    public static LinkedHashMap<String, ComparedParam> getFullCompareNew(CalcEntity calcEntity1st, CalcEntity calcEntity2d) {

        LinkedHashMap<String, ComparedParam> result = new LinkedHashMap<>();

        if ((calcEntity1st != null) && (calcEntity2d != null)) {

            for (String name : calcEntity1st.getHash().keySet()) {
                if ((calcEntity1st.getHash().get(name)) != null) {
                    if ((calcEntity2d.getHash().get(name)) != null) {
                        if ((calcEntity1st.getHash().get(name)).equals(calcEntity2d.getHash().get(name))) {
                            result.put(name, new ComparedParam(name, (calcEntity1st.getHash().get(name)), (calcEntity2d.getHash().get(name)), true));
                        } else {
                            result.put(name, new ComparedParam(name, (calcEntity1st.getHash().get(name)), (calcEntity2d.getHash().get(name)), false));
                        }
                    } else {
                        result.put(name, new ComparedParam(name, calcEntity1st.getHash().get(name), "", false));
                    }
                } else {
                    if ((calcEntity2d.getHash().get(name)) != null) {
                        result.put(name, new ComparedParam(name, "", calcEntity2d.getHash().get(name), false));
                    } else {
                        result.put(name, new ComparedParam(name, "", "", true));
                    }
                }
            }
        } else if ((calcEntity1st == null) && (calcEntity2d == null)) {
            result = null;
        } else if ((calcEntity1st != null) && (calcEntity2d == null)) {
            for (String name : calcEntity1st.getHash().keySet()) {
                result.put(name, new ComparedParam(name, calcEntity1st.getHash().get(name), "", false));
            }
        } else if ((calcEntity1st == null) && (calcEntity2d != null)) {
            for (String name : calcEntity2d.getHash().keySet()) {
                result.put(name, new ComparedParam(name, "", calcEntity2d.getHash().get(name), false));
            }
        }


        return result;
    }

}
