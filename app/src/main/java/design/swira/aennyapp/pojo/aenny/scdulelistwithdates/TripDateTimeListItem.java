package design.swira.aennyapp.pojo.aenny.scdulelistwithdates;


import com.google.gson.annotations.SerializedName;


public class TripDateTimeListItem{

	@SerializedName("Trip_Date")
	private String tripDate;

	@SerializedName("Trip_Time")
	private String tripTime;

	@SerializedName("$id")
	private String id;

	public void setTripDate(String tripDate){
		this.tripDate = tripDate;
	}

	public String getTripDate(){
		return tripDate;
	}

	public void setTripTime(String tripTime){
		this.tripTime = tripTime;
	}

	public String getTripTime(){
		return tripTime;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"TripDateTimeListItem{" + 
			"trip_Date = '" + tripDate + '\'' + 
			",trip_Time = '" + tripTime + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}