package com.mycompany.collatz;

import java.util.ArrayList;

import static spark.Spark.*;

public class Collatz {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://";

    public static void main(String... args){
        port(getPort());

        staticFiles.location("/public");


        get("/collatz", (req, res ) -> {
            String s = req.queryParams("value");
            return getResponse(Integer.parseInt(s));
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }


    private static String collatzSequence(int k){
        ArrayList <Integer> seq = new ArrayList<>();
        seq.add(k);
        while(k != 1){
            if(par(k)){
                k = k/2;
                seq.add(k);
            }
            else{
                k = (3*k)+1;
                seq.add(k);

            }
        }
        return seq.toString();
    }

    private static boolean par(int i){
        return i % 2 == 0;
    }


    private static String getResponse(int n) {
        return "{ \"operation\" : \"collatzsequence\", \n" +
                " \"input\" : " + n + ", \n" +
                " \"output\" : \"" + collatzSequence(n) + "\" \n" +
                "}";
    }




}




