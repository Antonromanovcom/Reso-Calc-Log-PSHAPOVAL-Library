package ru.reso.resocalc.Factories;

import ru.reso.resocalc.Entity.Interfaces.CalcEntity;
import ru.reso.resocalc.Entity.ComparedParam;

import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.reso.resocalc.Service.ComplexCompair.getFullCompareNew;


/**
 * Основной класс, то есть фабричный билдер/продюсер. Именно по сути отвечает за производство объектов (в нашем случае - энтити)
 * с помощью фабрик, подбирая ту или иную фабрику.
 */
public  class MainFactory {

    private static CalcEntity entity1st; //Энтити для calcid I.
    private static CalcEntity entity2d; //Энтити для calcid II.

    /**
     *  Это дополнительный энтити, который нужен для тех случаев, если в calcid 1 меньше значений и как следствие полей относительно calcid 2 и как следствие,
     *  эти поля не попадают в отчет/сравнение. Поэтому нам обязательно нужно узнать об этом и недостающие поля запихнуть как бы в отдельный энтити.
     */
    private static CalcEntity additionalEntity;

    public MainFactory() {
    }

    /**
     * Собственно, главный метод класса, который и возвращает хэшмеп с результатами сравнения.
     * @param factory - фабрику передаем из клиента. В зависимости от типа отчета.
     * @param calcidFirst
     * @param calcidSecond
     * @return
     */
    public static LinkedHashMap<String, ComparedParam>  getResult(EntitiesUtils factory, long calcidFirst, long calcidSecond){

        LinkedHashMap<String, ComparedParam> result = new LinkedHashMap<>();

        /**
         * Вот собственно зачем нам и нужны фабрики. Тут нам вообще по хер какой энтити, какой у нее дао. Я могу добавлять бесконечные количество энтити и отчетов,
         * главное - чтобы все это соответствовало интерфейсу.
         */
        entity1st = factory.getEntityByCalcId(calcidFirst);
        entity2d = factory.getEntityByCalcId(calcidSecond);

        // А тут мы вызываем соответствующий метод из ComplexCompair
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
                    result.put(name, new ComparedParam(name, (""), (additionalEntity.getHash().get(name)), false));
                }
            }
        }

        return result;
    }
}
