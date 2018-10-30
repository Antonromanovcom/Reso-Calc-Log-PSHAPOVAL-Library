package ru.reso.resocalc.Service.Factories;

import ru.reso.resocalc.Entity.CalcEntity;
import ru.reso.resocalc.Entity.ComparedParam;
import ru.reso.resocalc.Utils.Factories.EntitiesUtils;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.reso.resocalc.Service.ComplexCompair.getFullCompareNew;


public  class MainFactory {

    private static CalcEntity entity1st;
    private static CalcEntity entity2d;
    private static CalcEntity additionalEntity;

    public MainFactory() {
    }

    public static LinkedHashMap<String, ComparedParam>  getResult(EntitiesUtils factory, long calcidFirst, long calcidSecond){

        LinkedHashMap<String, ComparedParam> result = new LinkedHashMap<>();

        entity1st = factory.getEntityByCalcId(calcidFirst);
        entity2d = factory.getEntityByCalcId(calcidSecond);

        result = getFullCompareNew(entity1st, entity2d);

        /** Совершенно случайно выскочила такая тема: бывает, что в таблице А есть премии, который не повторяются в таблице Б (ни разу)
         *  а в таблице Б бывают премии, который не отразились в таблице А. Их надо все выудить. В основном это касается премий...
         *
         *  Что решил делать тогда:
         *
         *  1) запросом вытащить не соответствующие записи в таблице Б по отношению к таблице А
         *  2) Если кол-во записей больше 0, тогда вызываем уже заданный метод парсинга WebRowSet в класс и получаем новый Энтити
         *  3) Теперь у нас есть еще один энтити, естественно с новым хэшом
         *  4) Нужно взять его хэш (на самом деле хэш - это только название. В реале это  Мапа) и скопировать, точнее добавить все его значения в конец базового хэша
         *
         */

        if (calcidFirst != calcidSecond) {

            additionalEntity = factory.checkNonMatching(calcidSecond, calcidFirst);
            if (additionalEntity != null) {

                for (String name : additionalEntity.getHash().keySet()) {
                    Logger.getLogger("").log(Level.SEVERE, "КЛАДЕМ ДОПЫ - " + name + " - " + (additionalEntity.getHash().get(name)), "!!!!!!!!!!!");
                    result.put(name, new ComparedParam(name, (""), (additionalEntity.getHash().get(name))));
                }
            }
        }

        return result;
    }
}
