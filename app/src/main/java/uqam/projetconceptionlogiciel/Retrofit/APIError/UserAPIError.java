package uqam.projetconceptionlogiciel.Retrofit.APIError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Response;
import uqam.projetconceptionlogiciel.APIError.ErrorUtils;
import uqam.projetconceptionlogiciel.APIError.IUserAPIError;
import uqam.projetconceptionlogiciel.Model.User;

public class UserAPIError implements IUserAPIError {
    private Response<User> apiErrorResponse;

    public UserAPIError(Response<User> apiErrorResponse) {
        this.apiErrorResponse = apiErrorResponse;
    }

    @Override
    public boolean authTokensAreInvalid() {
        boolean authTokensAreInvalid;

        authTokensAreInvalid = apiErrorResponse.code() == 404;

        return authTokensAreInvalid;
    }

    @Override
    public boolean loginAlreadyExist() {
        JSONObject apiErrorBody = ErrorUtils.parseError(apiErrorResponse.errorBody());
        try {
            JSONArray jsonObject = apiErrorBody.getJSONObject("errors")
                    .getJSONObject("children")
                    .getJSONObject("login")
                    .getJSONArray("errors");

            return jsonObject.getString(0).equals("Cet identifiant existe déjà");
        } catch (JSONException e) {
            return false;
        }
    }
}
