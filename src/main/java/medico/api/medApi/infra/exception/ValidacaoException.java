package medico.api.medApi.infra.exception;

public class ValidacaoException extends RuntimeException{

    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
