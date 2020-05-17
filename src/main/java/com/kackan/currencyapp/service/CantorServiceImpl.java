package com.kackan.currencyapp.service;

import com.google.gson.Gson;
import com.kackan.currencyapp.model.Cantor;
import com.kackan.currencyapp.model.CurrencyName;
import com.kackan.currencyapp.model.DetailsForm;
import com.kackan.currencyapp.model.Rate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class CantorServiceImpl implements CantorService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.exchangeratesapi.io/latest?base=";

    public CantorServiceImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public Map<String, Double> getCurrency(String base) {
        Cantor cantor = restTemplate.getForObject(URL + base.toUpperCase(), Cantor.class);
        Rate rates = cantor != null ? cantor.getRates() : null;
        Map<String, Double> currencies = new HashMap<>();

        if (rates != null) {
            currencies.put(CurrencyName.AUD.name(), rates.getAUD());
            currencies.put(CurrencyName.BGN.name(), rates.getBGN());
            currencies.put(CurrencyName.BRL.name(), rates.getBRL());
            currencies.put(CurrencyName.CAD.name(), rates.getCAD());
            currencies.put(CurrencyName.CHF.name(), rates.getCHF());
            currencies.put(CurrencyName.CNY.name(), rates.getCNY());
            currencies.put(CurrencyName.CZK.name(), rates.getCZK());
            currencies.put(CurrencyName.DKK.name(), rates.getDKK());
            currencies.put(CurrencyName.GBP.name(), rates.getGBP());
            currencies.put(CurrencyName.HKD.name(), rates.getHKD());
            currencies.put(CurrencyName.HRK.name(), rates.getHRK());
            currencies.put(CurrencyName.HUF.name(), rates.getHUF());
            currencies.put(CurrencyName.IDR.name(), rates.getIDR());
            currencies.put(CurrencyName.ILS.name(), rates.getILS());
            currencies.put(CurrencyName.INR.name(), rates.getINR());
            currencies.put(CurrencyName.ISK.name(), rates.getISK());
            currencies.put(CurrencyName.JPY.name(), rates.getJPY());
            currencies.put(CurrencyName.KRW.name(), rates.getKRW());
            currencies.put(CurrencyName.MXN.name(), rates.getMXN());
            currencies.put(CurrencyName.MYR.name(), rates.getMYR());
            currencies.put(CurrencyName.NOK.name(), rates.getNOK());
            currencies.put(CurrencyName.NZD.name(), rates.getNZD());
            currencies.put(CurrencyName.PHP.name(), rates.getPHP());
            currencies.put(CurrencyName.PLN.name(), rates.getPLN());
            currencies.put(CurrencyName.RON.name(), rates.getRON());
            currencies.put(CurrencyName.RUB.name(), rates.getRUB());
            currencies.put(CurrencyName.SEK.name(), rates.getSEK());
            currencies.put(CurrencyName.SGD.name(), rates.getSGD());
            currencies.put(CurrencyName.THB.name(), rates.getTHB());
            currencies.put(CurrencyName.TRY.name(), rates.getTRY());
            currencies.put(CurrencyName.USD.name(), rates.getUSD());
            currencies.put(CurrencyName.ZAR.name(), rates.getZAR());
            currencies.put(CurrencyName.EUR.name(), rates.getEUR());
        }

        return currencies;
    }

    @Override
    public Map<String, String> worthOfCurrencyBasedOnDate(DetailsForm form) {
        Cantor cantor = restTemplate.getForObject("https://api.exchangeratesapi.io/history?start_at=" + form.getStart() + "&end_at=" + form.getEnd() + "&symbols=" + form.getSymbol().toUpperCase() + "&base=" + form.getBase().toUpperCase(), Cantor.class);

        Map<String, Object> worthOfCurrency;
        Map<String, String> adjustedMap = new TreeMap<>();
        Gson gs = new Gson();
        Rate rates = cantor != null ? cantor.getRates() : null;

        if (rates != null) {
            worthOfCurrency = rates.getAdditionalProperties();
            worthOfCurrency.forEach((k, v) -> {

                String s = gs.toJson(v);
                String[] split = s.replace("{", "").replace("}", "").split(":");
                adjustedMap.put(k, split[1]);
            });

        }
        return adjustedMap;
    }
}
