package cn.watermelon.watermelonbackend.enumeration;


public enum ContestTag {

    TuanDui("团队公开赛"),

    GeRen("个人公开赛"),

    ACM("ACM 赛制"),

    OI("OI 赛制"),

    MianShi("面试考题");

    private String tag;

    public String getTag() {
        return tag;
    }

    ContestTag(String tag) {
        this.tag = tag;
    }

    public static ContestTag getContestTag(String tag) {
        ContestTag[] contestTags = ContestTag.values();
        for (ContestTag contestTag: contestTags) {
            if (contestTag.getTag().equals(tag)) {
                return contestTag;
            }
        }
        return null;
    }

}
