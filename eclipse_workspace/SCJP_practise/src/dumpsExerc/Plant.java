package dumpsExerc;

public class Plant {
	private String name;
//	public Plant(){}
//	public Plant() { Tree(); } 
	public Plant(String name) { this.name = name; }
	public Plant() { this("fern"); }
	public String getName() { return name; }
}

