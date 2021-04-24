package com.dabroname.core;

import java.math.BigDecimal;
// готовый системный класс для работы с валютами, ИСО 4217
import java.util.Currency;
import java.util.List;
import java.util.Map;

/*
описываем поведение для всех типов хранилищ
 */

// TODO изменить тип BigDecimal на готовый класс по работе с деньгами Money

public interface Storage {

    String getName();

    // получение баланса (остаток)
    Map<Currency, BigDecimal> getCurrencyAmounts(); // остаток по каждой доступной валюте в хранилище
    BigDecimal getAmount(Currency currency); // остаток по определенной валюте
    BigDecimal getApproxAmount(Currency currency); // примерный остаток в переводе всех денег в одну валюту

    // изменение баланса
    /*
    BigDecimal amount - сумма для изменения
    Currency currency - в какой валюте мы меняем
     */
    void changeAmount(BigDecimal amount, Currency currency); // изменение баланса по определенной валюте
    void addAmount(BigDecimal amount, Currency currency); // добавить сумму в валюте
    void expenseAmount(BigDecimal amount, Currency currency); // отнять сумму в валюте

    // работа с валютой
    void addCurrency(Currency currency); // добавить новую валюту в хранилище
    void deleteCurrency(Currency currency); // удалить валюту из хранилища
    Currency getCurrency(String code); // получить валюту по коду
    List<Currency> getAvaiblableCurrencies(); // получить все доступные валюты хранилища в отдельной коллекции

}
