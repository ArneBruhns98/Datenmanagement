package modeljpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement
@Entity @Getter @Setter
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int AID;
	
    @Column(nullable = false)
    @Size(min=1, max=255)
    String strasse;
    
    @Column(nullable = false)
    String hausnummer;
    
    @Column(nullable = false)
    int plz;

    
    @Column(nullable = false)
    @Size(min=1, max=255)
    String ort;
    
    public Adresse() {}

	public Adresse(String strasse, String hausnummer, int plz, String ort) {
		super();
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.plz = plz;
		this.ort = ort;
	}
    
    
}
