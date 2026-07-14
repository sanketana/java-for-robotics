public class ObjectBugHunt {

    public static void main(String[] args) {
        Robot atlas = new Robot("Atlas");
        atlas.addScore(50);

        Robot nova = null;
        nova.addScore(20);

        atlas.printStatus();
        nova.printStatus();
    }
}
