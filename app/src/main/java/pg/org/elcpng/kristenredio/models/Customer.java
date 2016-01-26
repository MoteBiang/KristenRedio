package pg.org.elcpng.kristenredio.models;

import android.os.Bundle;

public class Customer {

	private String customerID;
	private String customerPass;
	private String customerMDN;
	private String customerName;  
	private String customerSurname;
	private String fatherName;  
	private String fatherSurname;
	private String customerDOB;
	private String customerGender;	
	private String customerMaritalStatus;
	private int customerTotDependents;
	private String customerDependentsAges;	
	private String curPostAddress;
	private String curResAddress;
	private String curSuburb;
	private String curTownCity;
	private String curResideYearStart;
	private String curResideYearEnd;
	private String prevResAddress;
	private String prevSuburb;
	private String prevTownCity;
	private String prevResideYearStart;
	private String prevResideYearEnd;
	private long timestamp;
	
	public Customer()
	{
		super();
	}
	
	public Customer(Bundle bundle)
	{
		super();
		
		customerID = bundle.getString("customerID");
		customerPass = bundle.getString("customerPass");
		customerMDN = bundle.getString("customerMDN");
		customerName   = bundle.getString("customerName");
		customerSurname = bundle.getString("customerSurname");
		fatherName   = bundle.getString("fatherName");
		fatherSurname = bundle.getString("fatherSurname");
		customerDOB = bundle.getString("customerDOB");
		customerGender = bundle.getString("customerGender");
		customerMaritalStatus = bundle.getString("customerMaritalStatus");
		customerTotDependents = bundle.getInt("customerTotDependents");
		customerDependentsAges = bundle.getString("customerDependentsAges");
		curPostAddress = bundle.getString("curPostAddress");
		curResAddress = bundle.getString("curResAddress");
		curSuburb = bundle.getString("curSuburb");
		curTownCity = bundle.getString("curTownCity");
		curResideYearStart = bundle.getString("curResideYearStart");
		curResideYearEnd = bundle.getString("curResideYearEnd");
		prevResAddress = bundle.getString("prevResAddress");
		prevSuburb = bundle.getString("prevSuburb");
		prevTownCity = bundle.getString("prevTownCity");
		prevResideYearStart = bundle.getString("prevResideYearStart");
		prevResideYearEnd = bundle.getString("prevResideYearEnd");
		timestamp = bundle.getLong("timestamp");
		
	}
	
	public Bundle toBundle()
	{
		Bundle bundle = new Bundle();
		
		bundle.putString("customerID", this.customerID);
		bundle.putString("customerPass", this.customerPass);
		bundle.putString("customerMDN", this.customerMDN);
		bundle.putString("customerName  ", this.customerName  );
		bundle.putString("customerSurname", this.customerSurname);
		bundle.putString("fatherName  ", this.fatherName  );
		bundle.putString("fatherSurname", this.fatherSurname);
		bundle.putString("customerDOB", this.customerDOB);
		bundle.putString("customerGender", this.customerGender);
		bundle.putString("customerMaritalStatus", this.customerMaritalStatus);
		bundle.putInt("customerTotDependents", this.customerTotDependents);
		bundle.putString("customerDependentsAges", this.customerDependentsAges);
		bundle.putString("curPostAddress", this.curPostAddress);
		bundle.putString("curResAddress", this.curResAddress);
		bundle.putString("curSuburb", this.curSuburb);
		bundle.putString("curTownCity", this.curTownCity);
		bundle.putString("curResideYearStart", this.curResideYearStart);
		bundle.putString("curResideYearEnd", this.curResideYearEnd);
		bundle.putString("prevResAddress", this.prevResAddress);
		bundle.putString("prevSuburb", this.prevSuburb);
		bundle.putString("prevTownCity", this.prevTownCity);
		bundle.putString("prevResideYearStart", this.prevResideYearStart);
		bundle.putString("prevResideYearEnd", this.prevResideYearEnd);
		bundle.putLong("timestamp", this.timestamp);
		
		return bundle;
	}
	
	public Customer getBundle(Bundle bundle)
	{		
		Customer customer = new Customer();
		
		customer.setCustomerID(bundle.getString("customerID"));
		customer.setCustomerPass(bundle.getString("customerPass"));
		customer.setCustomerMDN(bundle.getString("customerMDN"));
		customer.setCustomerName(bundle.getString("customerName"));
		customer.setCustomerSurname(bundle.getString("customerSurname"));
		customer.setFatherName(bundle.getString("fatherName"));
		customer.setFatherSurname(bundle.getString("fatherSurname"));
		customer.setCustomerDOB(bundle.getString("customerDOB"));
		customer.setCustomerGender(bundle.getString("customerGender"));
		customer.setCustomerMaritalStatus(bundle.getString("customerMaritalStatus"));
		customer.setCustomerTotDependents(bundle.getInt("customerTotDependents"));
		customer.setCustomerDependentsAges(bundle.getString("customerDependentsAges"));
		customer.setCurPostAddress(bundle.getString("curPostAddress"));
		customer.setCurResAddress(bundle.getString("curResAddress"));
		customer.setCurSuburb(bundle.getString("curSuburb"));
		customer.setCurTownCity(bundle.getString("curTownCity"));
		customer.setCurResideYearStart(bundle.getString("curResideYearStart"));
		customer.setCurResideYearEnd(bundle.getString("curResideYearEnd"));
		customer.setPrevResAddress(bundle.getString("prevResAddress"));
		customer.setPrevSuburb(bundle.getString("prevSuburb"));
		customer.setPrevTownCity(bundle.getString("prevTownCity"));
		customer.setPrevResideYearStart(bundle.getString("prevResideYearStart"));
		customer.setPrevResideYearEnd(bundle.getString("prevResideYearEnd"));
		customer.setTimestamp(bundle.getLong("timestamp"));
		
		return customer;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerPass() {
		return customerPass;
	}

	public void setCustomerPass(String customerPass) {
		this.customerPass = customerPass;
	}

	public String getcustomerMDN() {
		return customerMDN;
	}

	public void setCustomerMDN(String customerMDN) {
		this.customerMDN = customerMDN;
	}

	public String getcustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getcustomerSurname() {
		return customerSurname;
	}

	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherSurname() {
		return fatherSurname;
	}

	public void setFatherSurname(String fatherSurname) {
		this.fatherSurname = fatherSurname;
	}

	public String getcustomerDOB() {
		return customerDOB;
	}

	public void setCustomerDOB(String customerDOB) {
		this.customerDOB = customerDOB;
	}

	public String getcustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public String getcustomerMaritalStatus() {
		return customerMaritalStatus;
	}

	public void setCustomerMaritalStatus(String customerMaritalStatus) {
		this.customerMaritalStatus = customerMaritalStatus;
	}

	public int getcustomerTotDependents() {
		return customerTotDependents;
	}

	public void setCustomerTotDependents(int customerTotDependents) {
		this.customerTotDependents = customerTotDependents;
	}

	public String getcustomerDependentsAges() {
		return customerDependentsAges;
	}

	public void setCustomerDependentsAges(String customerDependentsAges) {
		this.customerDependentsAges = customerDependentsAges;
	}

	public String getCurPostAddress() {
		return curPostAddress;
	}

	public void setCurPostAddress(String curPostAddress) {
		this.curPostAddress = curPostAddress;
	}

	public String getCurResAddress() {
		return curResAddress;
	}

	public void setCurResAddress(String curResAddress) {
		this.curResAddress = curResAddress;
	}

	public String getCurSuburb() {
		return curSuburb;
	}

	public void setCurSuburb(String curSuburb) {
		this.curSuburb = curSuburb;
	}

	public String getCurTownCity() {
		return curTownCity;
	}

	public void setCurTownCity(String curTownCity) {
		this.curTownCity = curTownCity;
	}

	public String getCurResideYearStart() {
		return curResideYearStart;
	}

	public void setCurResideYearStart(String curResideYearStart) {
		this.curResideYearStart = curResideYearStart;
	}

	public String getCurResideYearEnd() {
		return curResideYearEnd;
	}

	public void setCurResideYearEnd(String curResideYearEnd) {
		this.curResideYearEnd = curResideYearEnd;
	}

	public String getPrevResAddress() {
		return prevResAddress;
	}

	public void setPrevResAddress(String prevResAddress) {
		this.prevResAddress = prevResAddress;
	}

	public String getPrevSuburb() {
		return prevSuburb;
	}

	public void setPrevSuburb(String prevSuburb) {
		this.prevSuburb = prevSuburb;
	}

	public String getPrevTownCity() {
		return prevTownCity;
	}

	public void setPrevTownCity(String prevTownCity) {
		this.prevTownCity = prevTownCity;
	}

	public String getPrevResideYearStart() {
		return prevResideYearStart;
	}

	public void setPrevResideYearStart(String prevResideYearStart) {
		this.prevResideYearStart = prevResideYearStart;
	}

	public String getPrevResideYearEnd() {
		return prevResideYearEnd;
	}

	public void setPrevResideYearEnd(String prevResideYearEnd) {
		this.prevResideYearEnd = prevResideYearEnd;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}	
}
