package il.bigid.interviewtest.processing;

class Offsets {
    private Integer lineOffset;
    private Integer charOffset;

    public Offsets(Integer lineOffset, Integer charOffset) {
        this.lineOffset = lineOffset;
        this.charOffset = charOffset;
    }

    public Integer getLineOffset() {
        return lineOffset;
    }

    public Integer getCharOffset() {
        return charOffset;
    }

    @Override
    public String toString() {
        return
               "[" + "lineOffset=" + lineOffset +
                ", charOffset=" + charOffset + "]" ;
    }
}
