package restExample;

public class TestDTO {
    private int number;
    private String text;

    public TestDTO(int number, String text) {
        this.number = number;
        this.text = text;
    }

    public TestDTO() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
