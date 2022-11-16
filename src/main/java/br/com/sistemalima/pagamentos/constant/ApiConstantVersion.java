package br.com.sistemalima.pagamentos.constant;

import java.util.ArrayList;

public final class ApiConstantVersion {


    public final static String versionApiOne = "v1";

    public final static ArrayList<String> listVersion = new ArrayList<String>();


    public static void initialization() {
        listVersion.add(versionApiOne);
    }

}
