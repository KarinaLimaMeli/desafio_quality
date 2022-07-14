package com.meli.desafio_quality.util;

import com.meli.desafio_quality.model.District;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Util {
    /**
     * Este método retorna um novo arrayList de District.
     * @return allDistricts
     */
    public static ArrayList<District> allDistricts () {
        ArrayList<District> allDistricts = new ArrayList<>();

        allDistricts.add(new District("Barra da lagoa", new BigDecimal(2000)));
        allDistricts.add(new District("São Geraldo", new BigDecimal(1500)));
        allDistricts.add(new District("El Pajonal", new BigDecimal(3000)));
        allDistricts.add(new District("Corrego Grande", new BigDecimal(4000)));
        allDistricts.add(new District("Laranjeiras", new BigDecimal(1800)));

        return allDistricts;
    }
}
