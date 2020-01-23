package modeljpa;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement
@Entity
@Getter @Setter
public class Termin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int TID;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "verkaeuferId", nullable = false)
	Person verkaeufer;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "kaeuferId", nullable = false)
	Person kaeufer;

	@Column(nullable = true)
	String art;

	@Column(nullable = false)
	Date datum;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "adressId", nullable = false)
	Adresse ort;

	@Column(nullable = false)
	String fahrzeug;

	public Termin() {
	}

	public Termin(Person verkaeufer, Person kaeufer, String art, Date datum, Adresse ort, String fahrzeug) {
		super();
		this.verkaeufer = verkaeufer;
		this.kaeufer = kaeufer;
		this.art = art;
		this.datum = datum;
		this.ort = ort;
		this.fahrzeug = fahrzeug;
	}
	
	
}
