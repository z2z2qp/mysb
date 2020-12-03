package me.will.sb.model.resp;

/**
 * me.will.sb.model.resp.VersionInfo
 *
 * @author Zhuang Jiabin
 * @version V1.0.0
 * @since 2020/12/3 15:45
 */
public final class VersionInfo {

   private final String buildVersion;
   private final String buildDate;

    public VersionInfo(String buildVersion, String buildDate) {
        this.buildVersion = buildVersion;
        this.buildDate = buildDate;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public String getBuildDate() {
        return buildDate;
    }
}
