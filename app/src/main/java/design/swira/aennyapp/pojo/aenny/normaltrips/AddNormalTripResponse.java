package design.swira.aennyapp.pojo.aenny.normaltrips;

import com.google.gson.annotations.SerializedName;

public class AddNormalTripResponse{

	@SerializedName("Trip_Normal_Distance")
	private int tripNormalDistance;

	@SerializedName("Trip_Cost")
	private int tripCost;

	@SerializedName("IsAdditionalFees")
	private boolean isAdditionalFees;

	@SerializedName("Trip_Destination_Latt")
	private int tripDestinationLatt;

	@SerializedName("Trip_Destination")
	private String tripDestination;

	@SerializedName("Trip_Destination_Long")
	private int tripDestinationLong;

	@SerializedName("Payment_Type")
	private int paymentType;

	@SerializedName("Total_Waiting_Minutes")
	private int totalWaitingMinutes;

	@SerializedName("Trip_Time")
	private String tripTime;

	@SerializedName("Trip_Pickup_Long")
	private int tripPickupLong;

	@SerializedName("Driver_Id")
	private int driverId;

	@SerializedName("Trip_Notes")
	private String tripNotes;

	@SerializedName("Trip_Type_Id")
	private int tripTypeId;

	@SerializedName("Healthy_Number")
	private int healthyNumber;

	@SerializedName("Trip_Pickup")
	private String tripPickup;

	@SerializedName("Trip_Key")
	private String tripKey;

	@SerializedName("Trip_Date")
	private String tripDate;

	@SerializedName("Vehicle_Id")
	private int vehicleId;

	@SerializedName("Trip_Pickup_Latt")
	private int tripPickupLatt;

	@SerializedName("Trip_Distance")
	private int tripDistance;

	@SerializedName("Visa_Number")
	private String visaNumber;

	@SerializedName("Trip_Rash_Distance")
	private int tripRashDistance;

	@SerializedName("Trip_Id")
	private int tripId;

	@SerializedName("Trip_Status")
	private int tripStatus;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Handicapped_Number")
	private int handicappedNumber;

	public void setTripNormalDistance(int tripNormalDistance){
		this.tripNormalDistance = tripNormalDistance;
	}

	public int getTripNormalDistance(){
		return tripNormalDistance;
	}

	public void setTripCost(int tripCost){
		this.tripCost = tripCost;
	}

	public int getTripCost(){
		return tripCost;
	}

	public void setIsAdditionalFees(boolean isAdditionalFees){
		this.isAdditionalFees = isAdditionalFees;
	}

	public boolean isIsAdditionalFees(){
		return isAdditionalFees;
	}

	public void setTripDestinationLatt(int tripDestinationLatt){
		this.tripDestinationLatt = tripDestinationLatt;
	}

	public int getTripDestinationLatt(){
		return tripDestinationLatt;
	}

	public void setTripDestination(String tripDestination){
		this.tripDestination = tripDestination;
	}

	public String getTripDestination(){
		return tripDestination;
	}

	public void setTripDestinationLong(int tripDestinationLong){
		this.tripDestinationLong = tripDestinationLong;
	}

	public int getTripDestinationLong(){
		return tripDestinationLong;
	}

	public void setPaymentType(int paymentType){
		this.paymentType = paymentType;
	}

	public int getPaymentType(){
		return paymentType;
	}

	public void setTotalWaitingMinutes(int totalWaitingMinutes){
		this.totalWaitingMinutes = totalWaitingMinutes;
	}

	public int getTotalWaitingMinutes(){
		return totalWaitingMinutes;
	}

	public void setTripTime(String tripTime){
		this.tripTime = tripTime;
	}

	public String getTripTime(){
		return tripTime;
	}

	public void setTripPickupLong(int tripPickupLong){
		this.tripPickupLong = tripPickupLong;
	}

	public int getTripPickupLong(){
		return tripPickupLong;
	}

	public void setDriverId(int driverId){
		this.driverId = driverId;
	}

	public int getDriverId(){
		return driverId;
	}

	public void setTripNotes(String tripNotes){
		this.tripNotes = tripNotes;
	}

	public String getTripNotes(){
		return tripNotes;
	}

	public void setTripTypeId(int tripTypeId){
		this.tripTypeId = tripTypeId;
	}

	public int getTripTypeId(){
		return tripTypeId;
	}

	public void setHealthyNumber(int healthyNumber){
		this.healthyNumber = healthyNumber;
	}

	public int getHealthyNumber(){
		return healthyNumber;
	}

	public void setTripPickup(String tripPickup){
		this.tripPickup = tripPickup;
	}

	public String getTripPickup(){
		return tripPickup;
	}

	public void setTripKey(String tripKey){
		this.tripKey = tripKey;
	}

	public String getTripKey(){
		return tripKey;
	}

	public void setTripDate(String tripDate){
		this.tripDate = tripDate;
	}

	public String getTripDate(){
		return tripDate;
	}

	public void setVehicleId(int vehicleId){
		this.vehicleId = vehicleId;
	}

	public int getVehicleId(){
		return vehicleId;
	}

	public void setTripPickupLatt(int tripPickupLatt){
		this.tripPickupLatt = tripPickupLatt;
	}

	public int getTripPickupLatt(){
		return tripPickupLatt;
	}

	public void setTripDistance(int tripDistance){
		this.tripDistance = tripDistance;
	}

	public int getTripDistance(){
		return tripDistance;
	}

	public void setVisaNumber(String visaNumber){
		this.visaNumber = visaNumber;
	}

	public String getVisaNumber(){
		return visaNumber;
	}

	public void setTripRashDistance(int tripRashDistance){
		this.tripRashDistance = tripRashDistance;
	}

	public int getTripRashDistance(){
		return tripRashDistance;
	}

	public void setTripId(int tripId){
		this.tripId = tripId;
	}

	public int getTripId(){
		return tripId;
	}

	public void setTripStatus(int tripStatus){
		this.tripStatus = tripStatus;
	}

	public int getTripStatus(){
		return tripStatus;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	public void setHandicappedNumber(int handicappedNumber){
		this.handicappedNumber = handicappedNumber;
	}

	public int getHandicappedNumber(){
		return handicappedNumber;
	}

	@Override
 	public String toString(){
		return 
			"AddNormalTripResponse{" + 
			"trip_Normal_Distance = '" + tripNormalDistance + '\'' + 
			",trip_Cost = '" + tripCost + '\'' + 
			",isAdditionalFees = '" + isAdditionalFees + '\'' + 
			",trip_Destination_Latt = '" + tripDestinationLatt + '\'' + 
			",trip_Destination = '" + tripDestination + '\'' + 
			",trip_Destination_Long = '" + tripDestinationLong + '\'' + 
			",payment_Type = '" + paymentType + '\'' + 
			",total_Waiting_Minutes = '" + totalWaitingMinutes + '\'' + 
			",trip_Time = '" + tripTime + '\'' + 
			",trip_Pickup_Long = '" + tripPickupLong + '\'' + 
			",driver_Id = '" + driverId + '\'' + 
			",trip_Notes = '" + tripNotes + '\'' + 
			",trip_Type_Id = '" + tripTypeId + '\'' + 
			",healthy_Number = '" + healthyNumber + '\'' + 
			",trip_Pickup = '" + tripPickup + '\'' + 
			",trip_Key = '" + tripKey + '\'' + 
			",trip_Date = '" + tripDate + '\'' + 
			",vehicle_Id = '" + vehicleId + '\'' + 
			",trip_Pickup_Latt = '" + tripPickupLatt + '\'' + 
			",trip_Distance = '" + tripDistance + '\'' + 
			",visa_Number = '" + visaNumber + '\'' + 
			",trip_Rash_Distance = '" + tripRashDistance + '\'' + 
			",trip_Id = '" + tripId + '\'' + 
			",trip_Status = '" + tripStatus + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",handicapped_Number = '" + handicappedNumber + '\'' + 
			"}";
		}

	public AddNormalTripResponse(int tripNormalDistance, int tripCost, boolean isAdditionalFees, int tripDestinationLatt, String tripDestination, int tripDestinationLong, int paymentType, int totalWaitingMinutes, String tripTime, int tripPickupLong, int driverId, String tripNotes, int tripTypeId, int healthyNumber, String tripPickup, String tripKey, String tripDate, int vehicleId, int tripPickupLatt, int tripDistance, String visaNumber, int tripRashDistance, int tripId, int tripStatus, int clientId, int handicappedNumber) {
		this.tripNormalDistance = tripNormalDistance;
		this.tripCost = tripCost;
		this.isAdditionalFees = isAdditionalFees;
		this.tripDestinationLatt = tripDestinationLatt;
		this.tripDestination = tripDestination;
		this.tripDestinationLong = tripDestinationLong;
		this.paymentType = paymentType;
		this.totalWaitingMinutes = totalWaitingMinutes;
		this.tripTime = tripTime;
		this.tripPickupLong = tripPickupLong;
		this.driverId = driverId;
		this.tripNotes = tripNotes;
		this.tripTypeId = tripTypeId;
		this.healthyNumber = healthyNumber;
		this.tripPickup = tripPickup;
		this.tripKey = tripKey;
		this.tripDate = tripDate;
		this.vehicleId = vehicleId;
		this.tripPickupLatt = tripPickupLatt;
		this.tripDistance = tripDistance;
		this.visaNumber = visaNumber;
		this.tripRashDistance = tripRashDistance;
		this.tripId = tripId;
		this.tripStatus = tripStatus;
		this.clientId = clientId;
		this.handicappedNumber = handicappedNumber;
	}
}