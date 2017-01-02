package Interfaces;

/**
 * Created by Xain on 15/10/2016.
 */

public interface NetworkResponse {

    void apiResult(String json, int pTag);
    void apiError (String msg, int pTag);
}
