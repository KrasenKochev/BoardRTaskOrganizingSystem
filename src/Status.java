public enum Status {
    Open, Todo, InProgress, Done, Verified;

    public Status setNextStatus() {
        if (this == Verified) {
            return Verified;
        }
        return values()[ordinal() + 1];
    }

    public Status setPreviousStatus() {
        if (this == Open) {
            return Open;
        }
        return values()[ordinal() - 1];
    }
}
