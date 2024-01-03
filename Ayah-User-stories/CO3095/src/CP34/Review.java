package CP34;

class Review {
    private String content;
    private boolean isAbusive;
    private boolean isRemoved;

    public Review(String content) {
        this.content = content;
        this.isAbusive = false;
        this.isRemoved = false;
    }

    public String getContent() {
        return content;
    }

    public boolean isAbusive() {
        return isAbusive;
    }

    public void markAsAbusive() {
        this.isAbusive = true;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void markAsRemoved() {
        this.isRemoved = true;
    }
}
