package uqam.projetconceptionlogiciel.APIError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import okio.BufferedSource;

public class ErrorUtils {
    public static JSONObject parseError(ResponseBody errorBody) {
        JSONObject JSONErrorBody;

        try {
            JSONErrorBody = convertAPIMessageToJSON(errorBody);
        } catch (Exception e) {
            JSONErrorBody = new JSONObject();
        }

        return JSONErrorBody;
    }

    private static JSONObject convertAPIMessageToJSON(ResponseBody errorBody) throws IOException, JSONException {
        BufferedSource source = errorBody.source();

        //BufferedSource --> String --> JSONObject
        String response = source.readUtf8();
        JSONObject jsonResponse = new JSONObject(response);

        return jsonResponse;
    }
}
