package kadeykin.example.bookmanagement.exceptions;

public class ResourceNotFoundException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String massage) {

    super(massage);
  }
}
