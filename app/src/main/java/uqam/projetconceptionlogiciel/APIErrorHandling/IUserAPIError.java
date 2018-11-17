package uqam.projetconceptionlogiciel.APIErrorHandling;

public interface IUserAPIError {

    boolean authTokensAreInvalid();

    boolean loginAlreadyExist();
}
