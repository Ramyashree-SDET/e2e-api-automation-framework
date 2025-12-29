package pojo;

public class DeleteBookRequest {

    private final String ID;

    public String getId()
    {
        return ID;
    }


    public DeleteBookRequest(String ID) // required
    {
        this.ID=ID;
    }
}
