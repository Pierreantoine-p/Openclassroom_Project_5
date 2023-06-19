package com.openclassrooms.safetinet.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.openclassrooms.safetinet.model.FireStations;

public class DataFireStation {

	private static List<FireStations> firestations = new ArrayList<FireStations>();


		/**
		 * GET ALL FIRE STATION
		 * @return
		 */
		public static List<FireStations> getFireStations() {
			return firestations;
		}

		public void setFirestations(List<FireStations> firestations) {
			this.firestations = firestations;
		}
		
		/**
		 * GET ONE FIRE STATION
		 * @return
		 */
		public static Optional<FireStations> getStationByAdress(String address ) {
			return firestations.stream()
					.filter(p -> p.getAddress().equals(address))
					.findFirst();
		}
		
		/**
		 * DELETE FIRE STATION
		 * @param person
		 * @return
		 */
		public static boolean delete(FireStations fireStation) {
			return firestations.remove(fireStation);
		}
		
		/**
		 * UPDATE PERSON
		 * @param person
		 * @return
		 */
		 public static Optional<FireStations> update (FireStations fireStation) {
			 return firestations.stream()
			  .filter(p -> p.getAddress().equals(fireStation.getAddress()))
			  .findFirst()
			  .map(p ->{
				 	p.setAddress(fireStation.getAddress());
					return p;
			 });
			  
		 }
}
