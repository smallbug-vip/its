package edu.hpc.its.center.exception;

/**
 * 队列异常
 * 
 * @timestamp Feb 23, 2016 3:50:19 PM
 * @author smallbug
 */
public class QueueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3156992864706426492L;

	public QueueException() {

		this(null, null);

	}

	public QueueException(String message) {

		this(message, null);

	}

	public QueueException(Throwable throwable) {

		this(null, throwable);

	}

	public QueueException(String message, Throwable throwable) {

		super();
		this.message = message;
		this.throwable = throwable;

	}

	protected String message = null;

	protected Throwable throwable = null;

	public String getMessage() {

		return (message);

	}

	public Throwable getThrowable() {

		return (throwable);

	}

	public String toString() {

		StringBuilder sb = new StringBuilder("QueryException:  ");
		if (message != null) {
			sb.append(message);
			if (throwable != null) {
				sb.append(":  ");
			}
		}
		if (throwable != null) {
			sb.append(throwable.toString());
		}
		return (sb.toString());

	}

}
