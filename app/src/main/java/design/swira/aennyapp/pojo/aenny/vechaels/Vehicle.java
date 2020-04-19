package design.swira.aennyapp.pojo.aenny.vechaels;

import com.google.gson.annotations.SerializedName;

public class Vehicle{

	@SerializedName("$ref")
	private String ref;

	public void setRef(String ref){
		this.ref = ref;
	}

	public String getRef(){
		return ref;
	}

	@Override
 	public String toString(){
		return 
			"Vehicle{" + 
			"$ref = '" + ref + '\'' + 
			"}";
		}
}