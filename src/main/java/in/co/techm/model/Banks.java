package in.co.techm.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by livquik on 01/06/17.
 */
@XmlRootElement(name = "banks")
@XmlAccessorType(XmlAccessType.FIELD)
public class Banks
{
    @XmlElement(name = "bank")
    private List<Bank> banks = null;

    public Banks(List<Bank> banks) {
        this.banks = banks;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }
}
