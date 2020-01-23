package jpa;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import modeljpa.Adresse;
import modeljpa.Person;
import modeljpa.Termin;

public class TestDataHandler {
	
	Dbhandler db = new Dbhandler();
	
	@SuppressWarnings("deprecation")
	List<Person> personData = Arrays.asList(new Person("Ralf", "Roedel", new Date(1992,03,04), new Adresse("Birnenstrasse", "12a", 23443, "Luebeck"), "ralf@roedel.de", "045122222"),
			new Person("Dieter", "Manno", new Date(1974,10,14), new Adresse("Apfelweg", "10", 23343, "Luebeck"), "dieter.manno@gmx.de", "016235533")
			);
	
	@SuppressWarnings("deprecation")
	List<Termin> terminData = Arrays.asList(new Termin(new Person("Jana", "Murmle", new Date(1975,05,02), new Adresse("Ananasallee", "14", 23223, "Luebeck"), "janamurmle@jana.de", "01704454777"),
			new Person("Heiner", "Nichtauffaellig", new Date(1958,11,04), new Adresse("Pfeffergasse", "87", 12223, "Kiel"), "heiner@gisela.com", "01457754"), "Probefahrt", new Date(2019,5,3),
			new Adresse("Hinterm Rewe", "10", 23343, "Luebeck"), "Polo"));
	
	
	public void insertTestData() {
		for (Person p : personData) {
			db.persistObject(p);
		}
		for (Termin t : terminData) {
			db.persistObject(t);
		}
		
	}

}
