package fbReact;

public class Comment {

	public String author;
	public String text;

	public Comment(String author, String text){
		this.author = author;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getAuthor() {
		return author;
	}

	@Override
    public String toString() {
        return String.format(
                "Comment[text='%s', author='%s']",
                 text, author);
    }
}