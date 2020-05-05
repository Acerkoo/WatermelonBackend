package cn.watermelon.watermelonbackend.enumeration;

public enum  ProblemStatus {

    Wating(0),

    Accepted(1),

    Expectant(2);

    private int status;

    public int getStatus() {
        return status;
    }

    ProblemStatus(int status) {
        this.status = status;
    }

    public static ProblemStatus getProblemStatus(int value) {
        ProblemStatus[] problemStatuses = ProblemStatus.values();
        for (ProblemStatus problemStatus: problemStatuses) {
            if (problemStatus.getStatus() == value) {
                return problemStatus;
            }
        }
        return null;
    }
}
