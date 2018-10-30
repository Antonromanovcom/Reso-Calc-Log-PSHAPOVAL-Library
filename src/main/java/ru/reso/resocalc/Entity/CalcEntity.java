package ru.reso.resocalc.Entity;

import java.util.ArrayList;
import java.util.Map;

/**
 * Базовый Интерфейс. То есть в терминологии FabricPattern - ИНТЕРФЕЙС ПРОДУКТА
 *
 * Задает базовый интерфейс для всех остальных "продуктов".
 **/
public interface CalcEntity {

    String getTest();

    void addToHash(String key, String value);

    Map<String, String> getHash();



}
