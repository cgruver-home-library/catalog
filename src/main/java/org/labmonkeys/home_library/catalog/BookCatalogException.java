package org.labmonkeys.home_library.catalog;


public class BookCatalogException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public BookCatalogException() {
	}

	public BookCatalogException(String message) {
		super(message);
	}

	public BookCatalogException(Throwable cause) {
		super(cause);
	}

	public BookCatalogException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookCatalogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}