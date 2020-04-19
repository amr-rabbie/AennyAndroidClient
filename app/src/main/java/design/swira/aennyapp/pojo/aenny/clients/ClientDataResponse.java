package design.swira.aennyapp.pojo.aenny.clients;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ClientDataResponse{

	@SerializedName("Client_Name")
	private String clientName;

	@SerializedName("Client_Mobile")
	private String clientMobile;

	@SerializedName("Client_Notes")
	private Object clientNotes;

	@SerializedName("Gender_Name")
	private String genderName;

	@SerializedName("editmsg")
	private Object editmsg;

	@SerializedName("Client_DisabilityType")
	private Object clientDisabilityType;

	@SerializedName("Client_Relative_Name")
	private Object clientRelativeName;

	@SerializedName("Area_Name")
	private String areaName;

	@SerializedName("Client_GovermentsId")
	private int clientGovermentsId;

	@SerializedName("Client_Email")
	private String clientEmail;

	@SerializedName("IsDeleted")
	private Object isDeleted;

	@SerializedName("Client_JoinDate")
	private Object clientJoinDate;

	@SerializedName("Client_City")
	private int clientCity;

	@SerializedName("Client_NoOfTrips")
	private int clientNoOfTrips;

	@SerializedName("Client_BirthDate")
	private String clientBirthDate;

	@SerializedName("Client_Relative_Mobile")
	private String clientRelativeMobile;

	@SerializedName("City_Name")
	private String cityName;

	@SerializedName("Client_AvgRate")
	private int clientAvgRate;

	@SerializedName("addmsg")
	private Object addmsg;

	@SerializedName("Client_Area")
	private int clientArea;

	@SerializedName("ClientDisabilities")
	private List<Object> clientDisabilities;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Client_Gender")
	private int clientGender;

	@SerializedName("Use_App_Disabled")
	private int useAppDisabled;

	@SerializedName("Goverments_Name")
	private String govermentsName;

	public void setClientName(String clientName){
		this.clientName = clientName;
	}

	public String getClientName(){
		return clientName;
	}

	public void setClientMobile(String clientMobile){
		this.clientMobile = clientMobile;
	}

	public String getClientMobile(){
		return clientMobile;
	}

	public void setClientNotes(Object clientNotes){
		this.clientNotes = clientNotes;
	}

	public Object getClientNotes(){
		return clientNotes;
	}

	public void setGenderName(String genderName){
		this.genderName = genderName;
	}

	public String getGenderName(){
		return genderName;
	}

	public void setEditmsg(Object editmsg){
		this.editmsg = editmsg;
	}

	public Object getEditmsg(){
		return editmsg;
	}

	public void setClientDisabilityType(Object clientDisabilityType){
		this.clientDisabilityType = clientDisabilityType;
	}

	public Object getClientDisabilityType(){
		return clientDisabilityType;
	}

	public void setClientRelativeName(Object clientRelativeName){
		this.clientRelativeName = clientRelativeName;
	}

	public Object getClientRelativeName(){
		return clientRelativeName;
	}

	public void setAreaName(String areaName){
		this.areaName = areaName;
	}

	public String getAreaName(){
		return areaName;
	}

	public void setClientGovermentsId(int clientGovermentsId){
		this.clientGovermentsId = clientGovermentsId;
	}

	public int getClientGovermentsId(){
		return clientGovermentsId;
	}

	public void setClientEmail(String clientEmail){
		this.clientEmail = clientEmail;
	}

	public String getClientEmail(){
		return clientEmail;
	}

	public void setIsDeleted(Object isDeleted){
		this.isDeleted = isDeleted;
	}

	public Object getIsDeleted(){
		return isDeleted;
	}

	public void setClientJoinDate(Object clientJoinDate){
		this.clientJoinDate = clientJoinDate;
	}

	public Object getClientJoinDate(){
		return clientJoinDate;
	}

	public void setClientCity(int clientCity){
		this.clientCity = clientCity;
	}

	public int getClientCity(){
		return clientCity;
	}

	public void setClientNoOfTrips(int clientNoOfTrips){
		this.clientNoOfTrips = clientNoOfTrips;
	}

	public int getClientNoOfTrips(){
		return clientNoOfTrips;
	}

	public void setClientBirthDate(String clientBirthDate){
		this.clientBirthDate = clientBirthDate;
	}

	public String getClientBirthDate(){
		return clientBirthDate;
	}

	public void setClientRelativeMobile(String clientRelativeMobile){
		this.clientRelativeMobile = clientRelativeMobile;
	}

	public String getClientRelativeMobile(){
		return clientRelativeMobile;
	}

	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	public String getCityName(){
		return cityName;
	}

	public void setClientAvgRate(int clientAvgRate){
		this.clientAvgRate = clientAvgRate;
	}

	public int getClientAvgRate(){
		return clientAvgRate;
	}

	public void setAddmsg(Object addmsg){
		this.addmsg = addmsg;
	}

	public Object getAddmsg(){
		return addmsg;
	}

	public void setClientArea(int clientArea){
		this.clientArea = clientArea;
	}

	public int getClientArea(){
		return clientArea;
	}

	public void setClientDisabilities(List<Object> clientDisabilities){
		this.clientDisabilities = clientDisabilities;
	}

	public List<Object> getClientDisabilities(){
		return clientDisabilities;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	public void setClientGender(int clientGender){
		this.clientGender = clientGender;
	}

	public int getClientGender(){
		return clientGender;
	}

	public void setUseAppDisabled(int useAppDisabled){
		this.useAppDisabled = useAppDisabled;
	}

	public int getUseAppDisabled(){
		return useAppDisabled;
	}

	public void setGovermentsName(String govermentsName){
		this.govermentsName = govermentsName;
	}

	public String getGovermentsName(){
		return govermentsName;
	}

	@Override
 	public String toString(){
		return 
			"ClientDataResponse{" + 
			"client_Name = '" + clientName + '\'' + 
			",client_Mobile = '" + clientMobile + '\'' + 
			",client_Notes = '" + clientNotes + '\'' + 
			",gender_Name = '" + genderName + '\'' + 
			",editmsg = '" + editmsg + '\'' + 
			",client_DisabilityType = '" + clientDisabilityType + '\'' + 
			",client_Relative_Name = '" + clientRelativeName + '\'' + 
			",area_Name = '" + areaName + '\'' + 
			",client_GovermentsId = '" + clientGovermentsId + '\'' + 
			",client_Email = '" + clientEmail + '\'' + 
			",isDeleted = '" + isDeleted + '\'' + 
			",client_JoinDate = '" + clientJoinDate + '\'' + 
			",client_City = '" + clientCity + '\'' + 
			",client_NoOfTrips = '" + clientNoOfTrips + '\'' + 
			",client_BirthDate = '" + clientBirthDate + '\'' + 
			",client_Relative_Mobile = '" + clientRelativeMobile + '\'' + 
			",city_Name = '" + cityName + '\'' + 
			",client_AvgRate = '" + clientAvgRate + '\'' + 
			",addmsg = '" + addmsg + '\'' + 
			",client_Area = '" + clientArea + '\'' + 
			",clientDisabilities = '" + clientDisabilities + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",client_Gender = '" + clientGender + '\'' + 
			",use_App_Disabled = '" + useAppDisabled + '\'' + 
			",goverments_Name = '" + govermentsName + '\'' + 
			"}";
		}
}