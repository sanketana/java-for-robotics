public class Robot {

    // Each robot carries its own copy of these — its own "sprite variables".
    String name;
    double topSpeed;
    int score;

    // Sets up a brand-new robot when you write: new Robot("Titan", 1.5)
    Robot(String name, double topSpeed) {
        this.name = name;
        this.topSpeed = topSpeed;
        this.score = 0;
    }

    void addScore(int points) {
        this.score = this.score + points;
    }

    void printStatus() {
        System.out.println(name + " | top speed " + topSpeed + " | score " + score);
    }
}
