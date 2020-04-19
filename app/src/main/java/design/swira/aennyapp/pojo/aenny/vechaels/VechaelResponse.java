package design.swira.aennyapp.pojo.aenny.vechaels;

import com.google.gson.annotations.SerializedName;

public class VechaelResponse{

	@SerializedName("Vehicle_Description")
	private Object vehicleDescription;

	@SerializedName("Vehicle_Manfactory_Year")
	private Object vehicleManfactoryYear;

	@SerializedName("IsDeleted")
	private Object isDeleted;

	@SerializedName("Brand_Id")
	private int brandId;

	@SerializedName("Model_Id")
	private int modelId;

	@SerializedName("Vehicle_Healty_number")
	private int vehicleHealtyNumber;

	@SerializedName("Vehicle_Notes")
	private Object vehicleNotes;

	@SerializedName("Color_Id")
	private int colorId;

	@SerializedName("Vehicle_Number")
	private String vehicleNumber;

	@SerializedName("Vehicle_Owned")
	private int vehicleOwned;

	@SerializedName("Driver")
	private Driver driver;

	@SerializedName("Vehicle_Id")
	private int vehicleId;

	@SerializedName("Vehicle_Model")
	private String vehicleModel;

	@SerializedName("Driver_Name")
	private String driverName;

	@SerializedName("Vehicle_Color_Hexa")
	private String vehicleColorHexa;

	@SerializedName("Vehicle_AvgRate")
	private int vehicleAvgRate;

	@SerializedName("Vehicle_Brand")
	private String vehicleBrand;

	@SerializedName("Vehicle_JoinDate")
	private Object vehicleJoinDate;

	@SerializedName("Vehicle_Color")
	private String vehicleColor;

	@SerializedName("$id")
	private String id;

	@SerializedName("Vehicle_HandiCappied_number")
	private int vehicleHandiCappiedNumber;

	public void setVehicleDescription(Object vehicleDescription){
		this.vehicleDescription = vehicleDescription;
	}

	public Object getVehicleDescription(){
		return vehicleDescription;
	}

	public void setVehicleManfactoryYear(Object vehicleManfactoryYear){
		this.vehicleManfactoryYear = vehicleManfactoryYear;
	}

	public Object getVehicleManfactoryYear(){
		return vehicleManfactoryYear;
	}

	public void setIsDeleted(Object isDeleted){
		this.isDeleted = isDeleted;
	}

	public Object getIsDeleted(){
		return isDeleted;
	}

	public void setBrandId(int brandId){
		this.brandId = brandId;
	}

	public int getBrandId(){
		return brandId;
	}

	public void setModelId(int modelId){
		this.modelId = modelId;
	}

	public int getModelId(){
		return modelId;
	}

	public void setVehicleHealtyNumber(int vehicleHealtyNumber){
		this.vehicleHealtyNumber = vehicleHealtyNumber;
	}

	public int getVehicleHealtyNumber(){
		return vehicleHealtyNumber;
	}

	public void setVehicleNotes(Object vehicleNotes){
		this.vehicleNotes = vehicleNotes;
	}

	public Object getVehicleNotes(){
		return vehicleNotes;
	}

	public void setColorId(int colorId){
		this.colorId = colorId;
	}

	public int getColorId(){
		return colorId;
	}

	public void setVehicleNumber(String vehicleNumber){
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleNumber(){
		return vehicleNumber;
	}

	public void setVehicleOwned(int vehicleOwned){
		this.vehicleOwned = vehicleOwned;
	}

	public int getVehicleOwned(){
		return vehicleOwned;
	}

	public void setDriver(Driver driver){
		this.driver = driver;
	}

	public Driver getDriver(){
		return driver;
	}

	public void setVehicleId(int vehicleId){
		this.vehicleId = vehicleId;
	}

	public int getVehicleId(){
		return vehicleId;
	}

	public void setVehicleModel(String vehicleModel){
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleModel(){
		return vehicleModel;
	}

	public void setDriverName(String driverName){
		this.driverName = driverName;
	}

	public String getDriverName(){
		return driverName;
	}

	public void setVehicleColorHexa(String vehicleColorHexa){
		this.vehicleColorHexa = vehicleColorHexa;
	}

	public String getVehicleColorHexa(){
		return vehicleColorHexa;
	}

	public void setVehicleAvgRate(int vehicleAvgRate){
		this.vehicleAvgRate = vehicleAvgRate;
	}

	public int getVehicleAvgRate(){
		return vehicleAvgRate;
	}

	public void setVehicleBrand(String vehicleBrand){
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehicleBrand(){
		return vehicleBrand;
	}

	public void setVehicleJoinDate(Object vehicleJoinDate){
		this.vehicleJoinDate = vehicleJoinDate;
	}

	public Object getVehicleJoinDate(){
		return vehicleJoinDate;
	}

	public void setVehicleColor(String vehicleColor){
		this.vehicleColor = vehicleColor;
	}

	public String getVehicleColor(){
		return vehicleColor;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setVehicleHandiCappiedNumber(int vehicleHandiCappiedNumber){
		this.vehicleHandiCappiedNumber = vehicleHandiCappiedNumber;
	}

	public int getVehicleHandiCappiedNumber(){
		return vehicleHandiCappiedNumber;
	}

	@Override
 	public String toString(){
		return 
			"VechaelResponse{" + 
			"vehicle_Description = '" + vehicleDescription + '\'' + 
			",vehicle_Manfactory_Year = '" + vehicleManfactoryYear + '\'' + 
			",isDeleted = '" + isDeleted + '\'' + 
			",brand_Id = '" + brandId + '\'' + 
			",model_Id = '" + modelId + '\'' + 
			",vehicle_Healty_number = '" + vehicleHealtyNumber + '\'' + 
			",vehicle_Notes = '" + vehicleNotes + '\'' + 
			",color_Id = '" + colorId + '\'' + 
			",vehicle_Number = '" + vehicleNumber + '\'' + 
			",vehicle_Owned = '" + vehicleOwned + '\'' + 
			",driver = '" + driver + '\'' + 
			",vehicle_Id = '" + vehicleId + '\'' + 
			",vehicle_Model = '" + vehicleModel + '\'' + 
			",driver_Name = '" + driverName + '\'' + 
			",vehicle_Color_Hexa = '" + vehicleColorHexa + '\'' + 
			",vehicle_AvgRate = '" + vehicleAvgRate + '\'' + 
			",vehicle_Brand = '" + vehicleBrand + '\'' + 
			",vehicle_JoinDate = '" + vehicleJoinDate + '\'' + 
			",vehicle_Color = '" + vehicleColor + '\'' + 
			",$id = '" + id + '\'' + 
			",vehicle_HandiCappied_number = '" + vehicleHandiCappiedNumber + '\'' + 
			"}";
		}
}