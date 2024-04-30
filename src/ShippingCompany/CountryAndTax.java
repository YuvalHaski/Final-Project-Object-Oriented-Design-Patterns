package ShippingCompany;

import java.util.Objects;

import main.Main;

public class CountryAndTax implements Comparable<CountryAndTax> {
	private String destinationCountry;
	private double expressTax = 20;
	private double standardTax = 0;
	private String currency = Main.USD;
	
	public CountryAndTax(String destinationCountry, double expressTax, double standardTax) {
		this.destinationCountry = destinationCountry;
		this.expressTax = expressTax;
		this.standardTax = standardTax; 
	}
	
	public CountryAndTax(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}
	
	public String getDestinationCountry() {
		return destinationCountry;
	}
	
	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public double getExpressTax() {
		return expressTax;
	}

	public void setExpressTax(double expressTax) {
		this.expressTax = expressTax;
	}

	public double getStandardTax() {
		return standardTax;
	}

	public void setStandardTax(double standardTax) {
		this.standardTax = standardTax;
	}
	
	public String getCurrency() {
		return currency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(destinationCountry); // removed the tax because each object differs by the destinationCountry field 
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryAndTax other = (CountryAndTax) obj;
		return Objects.equals(destinationCountry, other.destinationCountry); // removed the tax because each object differs by the destinationCountry field
	}

	@Override
	public int compareTo(CountryAndTax o) {
		return this.destinationCountry.compareTo(o.getDestinationCountry());
	}
}
