package dumpsExerc;

public class Persons1 {
	private String name;
	public Persons1(String name) { this.name = name; }
	public boolean equals(Persons1 p) {
		return p.name.equals(this.name);
	}
}
