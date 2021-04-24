package com.dabroname.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultStorage implements Storage {

    private String name;
    // храним <список валют, остатки>
    private Map<Currency, BigDecimal> currencyAmounts = new HashMap<>();
    // храним <список валют>
    private List<Currency> currencyList = new ArrayList<>();

    // get, set для currencyList
    @Override
    public List<Currency> getAvaiblableCurrencies() {
        return currencyList;
    }

    public void setAvaiblableCurrencies(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    // get, set для currencyAmounts
    @Override
    public Map<Currency, BigDecimal> getCurrencyAmounts() {
        return currencyAmounts;
    }

    public void setCurrencyAmounts(Map<Currency, BigDecimal> currencyAmounts) {
        this.currencyAmounts = currencyAmounts;
    }

    // get, set для name
    @Override
    public String getName() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ручное обновление баланса
    public void changeAmount(BigDecimal amount, Currency currency) {
        currencyAmounts.put(currency, amount);
    }

    public BigDecimal getAmount(Currency currency) {
        return currencyAmounts.get(currency);
    }

    //добавление денег в хранилище
    public void addAmount(BigDecimal amount, Currency currency) {
        BigDecimal oldAmount = currencyAmounts.get(currency);
        currencyAmounts.put(currency, oldAmount.add(amount));
    }

    //отнять сумму в валюте
    public void expenseAmount(BigDecimal amount, Currency currency) {
        BigDecimal oldAmount = currencyAmounts.get(currency);
        BigDecimal newValue = oldAmount.subtract(amount);
        currencyAmounts.put(currency, newValue);
    }

    // добавить новую валюту в хранилище
    @Override
    public void addCurrency(Currency currency) {
        currencyList.add(currency);
        currencyAmounts.put(currency, BigDecimal.ZERO);
    }

    // удалить валюту из хранилища
    @Override
    public void deleteCurrency(Currency currency) {
        currencyList.remove(currency);
        currencyAmounts.remove(currency);
    }

    // примерный остаток в переводе всех денег в одну валюту
    // НЕ РЕАЛИЗОВАН!!
    @Override
    public BigDecimal getApproxAmount(Currency currency) {
        //TODO подсчет остатка с приведением в одну валюту
        throw new UnsupportedOperationException("не реализовано");
    }

    //получение валюты по коду
    @Override
    public Currency getCurrency(String code) {
        // количество валют для каждого хранилища будет небольшим, поэтому можно через цикл
        //  можно исп. Apache Commons Collections
        // перебираем всю коллекцию, возвращаем currency
        for (Currency currency : currencyList) {
            if (currency.getCurrencyCode().equals(code)) {
                return currency;
            }
        }

        return null;
    }
}
