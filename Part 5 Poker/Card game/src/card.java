public class card extends deck {

	private String num;
	private String suite;

	// Constructor
	public card(String num, String suite) {
		super(num, suite);
		this.num = num;
		this.suite = suite;
	}

	public String getnum() {
		return num;
	}

	public String getsuite() {
		return suite;
	}

}
