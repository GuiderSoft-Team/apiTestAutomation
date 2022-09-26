package POJO;


/*
{
    "version": "1.0.0",
    "major": 1,
    "minor": 0,
    "patch": 0
}
 */
public class Version {

    private String version;
    private int major;
    private int minor;
    private int patch;

    public Version(String version, int major, int minor, int patch) {
        this.version = version;
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public Version() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getPatch() {
        return patch;
    }

    public void setPatch(int patch) {
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Version{" +
                "version='" + version + '\'' +
                ", major=" + major +
                ", minor=" + minor +
                ", patch=" + patch +
                '}';
    }
}
