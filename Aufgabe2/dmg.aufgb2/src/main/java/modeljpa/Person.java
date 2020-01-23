package modeljpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Date;

@XmlRootElement
@Entity @Getter @Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int PID;

    @Column(nullable = false)
    @Size(min=1, max=255)
    String vorname;
    
    @Column(nullable = false)
    @Size(min=1, max=255)
    String nachname;
    
    @Column(nullable = false)
    Date geburtsdatum;
    
    @Column(nullable = false)
    String emailadresse;
    
    @Column(nullable = true)
    String telefonnr;

    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", nullable = false)
    Adresse address;

    public Person() { }


	public Person(String vorname, String nachname, Date geburtsdatum, Adresse adress, String emailadresse, String telefonnr) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.address = adress;
		this.emailadresse = emailadresse;
		this.telefonnr = telefonnr;
	}
}
