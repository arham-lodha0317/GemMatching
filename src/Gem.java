import java.awt.*;
import java.util.Random;

enum GemType {
    GREEN, BLUE, ORANGE; //define the different types of Gems, comma delimited

	public static GemType getRandomColor(){
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}

}

public class Gem 
{
	private GemType type;
	private int points;

	public Gem(){
		Random random = new Random();
		type = GemType.getRandomColor();

		points = random.nextInt(11) * 5;
	}

	public Gem(GemType gemType, int points){
		this.type = gemType;
		this.points = points;
	}

	public GemType getType() {
		return type;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return type.toString() + " " + points;
	}

	void draw(double x, double y){
		if(type.equals(GemType.valueOf("GREEN"))){
			StdDraw.picture(x, y, "gem_green.png");
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.text(x, y, String.valueOf(points));
		}
		else if(type.equals(GemType.valueOf("BLUE"))){
			StdDraw.picture(x, y, "gem_blue.png");
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.text(x, y, String.valueOf(points));
		}
		else if(type.equals(GemType.valueOf("ORANGE"))){
			StdDraw.picture(x, y, "gem_orange.png");
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.text(x, y, String.valueOf(points));
		}
	}

	/** Tester main method */
	public static void main(String [] args) {
		final int maxGems = 16;

		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);

		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}


}
