package it.polito.tdp.poweroutages.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class PowerOutage {
	
	private int id;
	private Nerc nerc;
	private int customersAffected;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	
	public PowerOutage(int id, Nerc nerc, int customersAffected, LocalDateTime startDate, LocalDateTime endDate) {
		this.id = id;
		this.nerc = nerc;
		this.customersAffected = customersAffected;
		this.startDate = startDate;
		this.endDate = endDate;

	}

	public int getId() {
		return id;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}


	@Override
	public int hashCode() {
		return Objects.hash(customersAffected, endDate, id, nerc, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		return customersAffected == other.customersAffected && Objects.equals(endDate, other.endDate)
				&& id == other.id && Objects.equals(nerc, other.nerc)
				&& Objects.equals(startDate, other.startDate);
	}

	@Override
	public String toString() {
		return "PowerOutage [id=" + id + ", nerc=" + nerc + ", customersAffected=" + customersAffected + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
	

}
