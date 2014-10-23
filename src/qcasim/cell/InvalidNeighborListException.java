package qcasim.cell;

public class InvalidNeighborListException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public InvalidNeighborListException()
    {
    }

    public InvalidNeighborListException(String message)
    {
	super(message);
    }

    public InvalidNeighborListException(Throwable cause)
    {
	super(cause);
    }

    public InvalidNeighborListException(String message, Throwable cause)
    {
	super(message, cause);
    }
}
