package ru.reso.resocalc.Entity.Interfaces;

import java.util.ArrayList;
import java.util.Map;

/**
 * Базовый Интерфейс. То есть в терминологии FabricPattern - ИНТЕРФЕЙС ПРОДУКТА
 *
 * Задает базовый интерфейс для всех остальных "продуктов", то есть классов (Энтити) логирования. Каждый класс представляет отдельную таблицу,
 * представляющую отдельную группу отчетов.
 **/
public interface CalcEntity {

    void addToHash(String key, String value); // в каждом классе есть хэшМэп, куда мы добавляем сравниваемые значения для передачи на клиента.

    Map<String, String> getHash(); // геттер для Хэша

}
