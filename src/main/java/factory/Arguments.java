package factory;

import com.github.jankroken.commandline.annotations.LongSwitch;
import com.github.jankroken.commandline.annotations.Option;
import com.github.jankroken.commandline.annotations.ShortSwitch;
import com.github.jankroken.commandline.annotations.SingleArgument;

public class Arguments {

    private String filename;
    private String input;
    private String temp;
    private String xml;

    @Option
    @LongSwitch("xsd")
    @ShortSwitch("x")
    @SingleArgument
    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Option
    @LongSwitch("dir")
    @ShortSwitch("d")
    @SingleArgument
    public void setDirectory(String input) {
        this.input = input;
    }

    @Option
    @LongSwitch("temp")
    @ShortSwitch("t")
    @SingleArgument
    public void setTempporal(String temp) {
        this.temp = temp;
    }

    @Option
    @LongSwitch("xml")
    @ShortSwitch("l")
    @SingleArgument
    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getFilename() {
        return filename;
    }

    public String getDirectory() {
        return input;
    }

    public String getTempporal() {
        return temp;
    }

    public String getXml() {
        return xml;
    }


}
