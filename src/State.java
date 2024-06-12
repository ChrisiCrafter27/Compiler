public enum State {
    q0,
    q1,
    q2,
    q3,
    q4,
    q5,
    fail;

    public static State start() {
        return q0;
    }
    public static boolean ends(State state) {
        return state == q1 || state == q2 || state == q4 || state == q5;
    }
}
