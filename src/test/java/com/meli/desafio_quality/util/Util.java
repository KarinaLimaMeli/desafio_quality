package com.meli.desafio_quality.util;

import com.meli.desafio_quality.model.District;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Util {
    public static ArrayList<District> allDistricts () {
        ArrayList<District> allDistricts = new ArrayList<>();

        allDistricts.add(new District("Barra da lagoa", new BigDecimal(20)));
        allDistricts.add(new District("São Geraldo", new BigDecimal(15)));
        allDistricts.add(new District("El Pajonal", new BigDecimal(30)));
        allDistricts.add(new District("Corrego Grande", new BigDecimal(40)));
        allDistricts.add(new District("Laranjeiras", new BigDecimal(18)));

        return allDistricts;
    }
}
