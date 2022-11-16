package br.com.sistemalima.pagamentos.constant;

import java.util.ArrayList;

public final class ApiConstantRequestId {

    public final static String requestIdAluraFood = "x-alura-food";
    public final static ArrayList<String> listRequestId = new ArrayList<String>();


    public static void initialization() {
        listRequestId.add(requestIdAluraFood);
    }

}
