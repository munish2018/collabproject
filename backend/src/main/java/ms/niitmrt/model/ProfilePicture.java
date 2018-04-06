package ms.niitmrt.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table
public class ProfilePicture {
@Id
@Column(length=50)
String loginname;
@Lob
private byte[] image;
public String getLoginname() {
	return loginname;
}
public void setLoginname(String loginname) {
	this.loginname = loginname;
}
public byte[] getImage() {
	return image;
}
public void setImage(byte[] image) {
	this.image = image;
}
@Override
public String toString() {
	return "ProfilePicture [loginname=" + loginname + ", image=" + Arrays.toString(image) + "]";
}
}
