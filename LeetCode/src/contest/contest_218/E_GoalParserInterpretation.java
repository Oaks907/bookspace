package contest.contest_218;

/**
 * Create by haifei on 6/12/2020 10:51 AM.
 */
public class E_GoalParserInterpretation {

    public String interpret(String command) {
        if (null == command || command.isEmpty()) {
            return command;
        }

        command = command.replace("()", "o");
        command = command.replace("(al)", "al");
        return command;
    }
}
