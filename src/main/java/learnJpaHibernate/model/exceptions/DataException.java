package learnJpaHibernate.model.exceptions;

public class DataException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataException(String msg) {
		super(msg);
	}

}
