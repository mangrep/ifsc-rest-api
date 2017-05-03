package in.co.techm.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "ifsc_dtl")
@XmlRootElement(name="bank")
public class Bank implements Serializable {
	@Id
	private String _id;
	@JsonProperty("STATE")
	private String state;
	@JsonProperty("BANK")
	private String bank;
	@JsonProperty("IFSC")
	private String ifsc;
	@JsonProperty("BRANCH")
	private String branch;
	@JsonProperty("ADDRESS")
	private String address;
	@JsonProperty("CONTACT")
	private String contact;
	@JsonProperty("CITY")
	private String city;
	@JsonProperty("DISTRICT")
	private String district;
	@JsonProperty("MICRCODE")
	private String micrcode;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMicrcode() {
		return micrcode;
	}
	public void setMicrcode(String micrcode) {
		this.micrcode = micrcode;
	}
	@Override
	public String toString() {
		return "Bank [_id=" + _id + ", state=" + state + ", bank=" + bank + ", ifsc=" + ifsc + ", branch=" + branch
				+ ", address=" + address + ", contact=" + contact + ", city=" + city + ", district=" + district
				+ ", micrcode=" + micrcode + "]";
	}

}
