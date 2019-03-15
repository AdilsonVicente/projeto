package analiseskillus.com.br.service.exception;

public class ImpossivelApagarUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImpossivelApagarUsuarioException(String msg) {
		super(msg);
	}
}
