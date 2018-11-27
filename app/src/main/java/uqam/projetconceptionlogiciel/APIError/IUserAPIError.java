package uqam.projetconceptionlogiciel.APIError;

public interface IUserAPIError {

    boolean authTokensAreInvalid();

    boolean loginAlreadyExist();
}
