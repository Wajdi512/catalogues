package metier;

public class Produit {

	private Integer reference;

	private String nom;

	private Double prix;

	private Integer quantite;

	private Categorie catalogue;

	public Produit() {
		super();
	}

	public Produit(Integer reference, String nom, Double prix, Integer quantite, Categorie catalogue) {
		super();
		this.reference = reference;
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
		this.catalogue = catalogue;
	}

	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Categorie getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(Categorie catalogue) {
		this.catalogue = catalogue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produit other = (Produit) obj;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}

}
