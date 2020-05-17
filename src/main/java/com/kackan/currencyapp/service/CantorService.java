package com.kackan.currencyapp.service;

import com.kackan.currencyapp.model.DetailsForm;
import java.util.Map;

public interface CantorService {

    Map<String,Double> getCurrency(String base);

    Map<String,String> worthOfCurrencyBasedOnDate(DetailsForm form);

}
