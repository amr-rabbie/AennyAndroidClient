package design.swira.aennyapp.pojo.aenny.normaltrips;

import com.google.gson.annotations.SerializedName;

public class AddNormalTrip{

	@SerializedName("Trip_Notes")
	private String tripNotes;

	@SerializedName("Trip_Normal_Distance")
	private double tripNormalDistance;

	@SerializedName("Trip_Cost")
	private double tripCost;

	@SerializedName("IsAdditionalFees")
	private boolean isAdditionalFees;

	@SerializedName("Trip_Type_Id")
	private int tripTypeId;

	@SerializedName("Healthy_Number")
	private int healthyNumber;

	@SerializedName("Trip_Pickup")
	private String tripPickup;

	@SerializedName("Trip_Date")
	private String tripDate;

	@SerializedName("Vehicle_Id")
	private int vehicleId;

	@SerializedName("Trip_Destination_Latt")
	private double tripDestinationLatt;

	@SerializedName("Trip_Destination")
	private String tripDestination;

	@SerializedName("Trip_Destination_Long")
	private double tripDestinationLong;

	@SerializedName("Payment_Type")
	private int paymentType;

	@SerializedName("Total_Waiting_Minutes")
	private double totalWaitingMinutes;

	@SerializedName("Trip_Time")
	private String tripTime;

	@SerializedName("Trip_Pickup_Long")
	private double tripPickupLong;

	@SerializedName("Driver_Id")
	private int driverId;

	@SerializedName("Trip_Pickup_Latt")
	private double tripPickupLatt;

	@SerializedName("Trip_Distance")
	private double tripDistance;

	@SerializedName("Visa_Number")
	private String visaNumber;

	@SerializedName("Trip_Rash_Distance")
	private double tripRashDistance;

	@SerializedName("Trip_Status")
	private int tripStatus;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Handicapped_Number")
	private int handicappedNumber;

	public String getTripNotes() {
		return tripNotes;
	}

	public void setTripNotes(String tripNotes) {
		this.tripNotes = tripNotes;
	}

	public double getTripNormalDistance() {
		return tripNormalDistance;
	}

	public void setTripNormalDistance(double tripNormalDistance) {
		this.tripNormalDistance = tripNormalDistance;
	}

	public double getTripCost() {
		return tripCost;
	}

	public void setTripCost(double tripCost) {
		this.tripCost = tripCost;
	}

	public boolean isAdditionalFees() {
		return isAdditionalFees;
	}

	public void setAdditionalFees(boolean additionalFees) {
		isAdditionalFees = additionalFees;
	}

	public int getTripTypeId() {
		return tripTypeId;
	}

	public void setTripTypeId(int tripTypeId) {
		this.tripTypeId = tripTypeId;
	}

	public int getHealthyNumber() {
		return healthyNumber;
	}

	public void setHealthyNumber(int healthyNumber) {
		this.healthyNumber = healthyNumber;
	}

	public String getTripPickup() {
		return tripPickup;
	}

	public void setTripPickup(String tripPickup) {
		this.tripPickup = tripPickup;
	}

	public String getTripDate() {
		return tripDate;
	}

	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public double getTripDestinationLatt() {
		return tripDestinationLatt;
	}

	public void setTripDestinationLatt(double tripDestinationLatt) {
		this.tripDestinationLatt = tripDestinationLatt;
	}

	public String getTripDestination() {
		return tripDestination;
	}

	public void setTripDestination(String tripDestination) {
		this.tripDestination = tripDestination;
	}

	public double getTripDestinationLong() {
		return tripDestinationLong;
	}

	public void setTripDestinationLong(double tripDestinationLong) {
		this.tripDestinationLong = tripDestinationLong;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public double getTotalWaitingMinutes() {
		return totalWaitingMinutes;
	}

	public void setTotalWaitingMinutes(double totalWaitingMinutes) {
		this.totalWaitingMinutes = totalWaitingMinutes;
	}

	public String getTripTime() {
		return tripTime;
	}

	public void setTripTime(String tripTime) {
		this.tripTime = tripTime;
	}

	public double getTripPickupLong() {
		return tripPickupLong;
	}

	public void setTripPickupLong(double tripPickupLong) {
		this.tripPickupLong = tripPickupLong;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public double getTripPickupLatt() {
		return tripPickupLatt;
	}

	public void setTripPickupLatt(double tripPickupLatt) {
		this.tripPickupLatt = tripPickupLatt;
	}

	public double getTripDistance() {
		return tripDistance;
	}

	public void setTripDistance(double tripDistance) {
		this.tripDistance = tripDistance;
	}

	public String getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	public double getTripRashDistance() {
		return tripRashDistance;
	}

	public void setTripRashDistance(double tripRashDistance) {
		this.tripRashDistance = tripRashDistance;
	}

	public int getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(int tripStatus) {
		this.tripStatus = tripStatus;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getHandicappedNumber() {
		return handicappedNumber;
	}

	public void setHandicappedNumber(int handicappedNumber) {
		this.handicappedNumber = handicappedNumber;
	}

	@Override
 	public String toString(){
		return 
			"AddNormalTrip{" + 
			"trip_Notes = '" + tripNotes + '\'' + 
			",trip_Normal_Distance = '" + tripNormalDistance + '\'' + 
			",trip_Cost = '" + tripCost + '\'' + 
			",isAdditionalFees = '" + isAdditionalFees + '\'' + 
			",trip_Type_Id = '" + tripTypeId + '\'' + 
			",healthy_Number = '" + healthyNumber + '\'' + 
			",trip_Pickup = '" + tripPickup + '\'' + 
			",trip_Date = '" + tripDate + '\'' + 
			",vehicle_Id = '" + vehicleId + '\'' + 
			",trip_Destination_Latt = '" + tripDestinationLatt + '\'' + 
			",trip_Destination = '" + tripDestination + '\'' + 
			",trip_Destination_Long = '" + tripDestinationLong + '\'' + 
			",payment_Type = '" + paymentType + '\'' + 
			",total_Waiting_Minutes = '" + totalWaitingMinutes + '\'' + 
			",trip_Time = '" + tripTime + '\'' + 
			",trip_Pickup_Long = '" + tripPickupLong + '\'' + 
			",driver_Id = '" + driverId + '\'' + 
			",trip_Pickup_Latt = '" + tripPickupLatt + '\'' + 
			",trip_Distance = '" + tripDistance + '\'' + 
			",visa_Number = '" + visaNumber + '\'' + 
			",trip_Rash_Distance = '" + tripRashDistance + '\'' + 
			",trip_Status = '" + tripStatus + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",handicapped_Number = '" + handicappedNumber + '\'' + 
			"}";
		}

	public AddNormalTrip(String tripNotes, double tripNormalDistance, double tripCost, boolean isAdditionalFees, int tripTypeId, int healthyNumber, String tripPickup, String tripDate, int vehicleId, double tripDestinationLatt, String tripDestination, double tripDestinationLong, int paymentType, double totalWaitingMinutes, String tripTime, double tripPickupLong, int driverId, double tripPickupLatt, double tripDistance, String visaNumber, double tripRashDistance, int tripStatus, int clientId, int handicappedNumber) {
		this.tripNotes = tripNotes;
		this.tripNormalDistance = tripNormalDistance;
		this.tripCost = tripCost;
		this.isAdditionalFees = isAdditionalFees;
		this.tripTypeId = tripTypeId;
		this.healthyNumber = healthyNumber;
		this.tripPickup = tripPickup;
		this.tripDate = tripDate;
		this.vehicleId = vehicleId;
		this.tripDestinationLatt = tripDestinationLatt;
		this.tripDestination = tripDestination;
		this.tripDestinationLong = tripDestinationLong;
		this.paymentType = paymentType;
		this.totalWaitingMinutes = totalWaitingMinutes;
		this.tripTime = tripTime;
		this.tripPickupLong = tripPickupLong;
		this.driverId = driverId;
		this.tripPickupLatt = tripPickupLatt;
		this.tripDistance = tripDistance;
		this.visaNumber = visaNumber;
		this.tripRashDistance = tripRashDistance;
		this.tripStatus = tripStatus;
		this.clientId = clientId;
		this.handicappedNumber = handicappedNumber;
	}
}