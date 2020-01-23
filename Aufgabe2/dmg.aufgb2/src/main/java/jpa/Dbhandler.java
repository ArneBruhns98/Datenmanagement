package jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modeljpa.Adresse;
import modeljpa.Person;
import modeljpa.Termin;

public class Dbhandler {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb-localhost");
	EntityManager em = emf.createEntityManager();

	public <T> boolean persistObject(T t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		return true;
	}

	@SuppressWarnings("unchecked")
	public <T> Collection<T> getObjects(String type) {
		String jpql = "";
		switch (type) {
		case "person":
			jpql = "SELECT p FROM Person p";
			break;
		case "adresses":
			jpql = "SELECT a FROM Adresse a";
			break;
		case "termin":
			jpql = "SELECT t FROM Termin t";
			break;
		}
		Query q1 = em.createQuery(jpql);
		Collection<T> res1 = q1.getResultList();
		return res1;
	}

	
	public Person getPersonById(int id) {
		return em.find(modeljpa.Person.class, id);
	}
	
	public Adresse getAdresseById(int id) {
		return em.find(modeljpa.Adresse.class, id);
	}
	
	public Termin getTerminById(int id) {
		return em.find(modeljpa.Termin.class, id);
	}
	
	public boolean deletePersonById(int id) {
		em.getTransaction().begin();
		Person person = em.find(Person.class, id);
		if (person != null) {
			em.remove(person);
			em.getTransaction().commit();
			em.close();
			return true;
		}
		em.getTransaction().commit();
		em.close();
		return false;
	}
	
	public boolean deleteAdresseById(int id) {
		em.getTransaction().begin();
		Adresse adresse = em.find(Adresse.class, id);
		if (adresse != null) {
			em.remove(adresse);
			em.getTransaction().commit();
			em.close();
			return true;
		}
		em.getTransaction().commit();
		em.close();
		return false;
	}

	public boolean deleteTerminById(int id) {
		em.getTransaction().begin();
		Termin termin = em.find(Termin.class, id);
		if (termin != null) {
			em.remove(termin);
			em.getTransaction().commit();
			em.close();
			return true;
		}
		em.getTransaction().commit();
		em.close();
		return false;
	}

	public boolean updatePerson(Person person) {
		em.getTransaction().begin();
		Person toUpdate = em.find(modeljpa.Person.class, person.getPID());
		if (toUpdate != null) {
			toUpdate.setVorname(person.getVorname());
			toUpdate.setNachname(person.getNachname());
			toUpdate.setGeburtsdatum(person.getGeburtsdatum());
			toUpdate.setAddress(person.getAddress());
			toUpdate.setEmailadresse(person.getEmailadresse());
			toUpdate.setTelefonnr(person.getTelefonnr());
			em.getTransaction().commit();
			em.close();
			return true;
		}
		em.getTransaction().commit();
		em.close();
		return false;
	}
	
	public boolean updateAdresse(Adresse adresse) {
		em.getTransaction().begin();
		Adresse toUpdate = em.find(modeljpa.Adresse.class, adresse.getAID());
		if (toUpdate != null) {
			toUpdate.setStrasse(adresse.getStrasse());
			toUpdate.setHausnummer(adresse.getHausnummer());
			toUpdate.setOrt(adresse.getOrt());
			toUpdate.setPlz(adresse.getPlz());
			em.getTransaction().commit();
			em.close();
			return true;
		}
		em.getTransaction().commit();
		em.close();
		return false;
	}
	
	public boolean updateTermin(Termin termin) {
		em.getTransaction().begin();
		Termin toUpdate = em.find(modeljpa.Termin.class, termin.getTID());
		if (toUpdate != null) {
			toUpdate.setArt(termin.getArt());
			toUpdate.setDatum(termin.getDatum());
			toUpdate.setFahrzeug(termin.getFahrzeug());
			toUpdate.setKaeufer(termin.getKaeufer());
			toUpdate.setOrt(termin.getOrt());
			toUpdate.setVerkaeufer(termin.getVerkaeufer());
			em.getTransaction().commit();
			em.close();
			return true;
		}
		em.getTransaction().commit();
		em.close();
		return false;
	}
}
