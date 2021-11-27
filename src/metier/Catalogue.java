package metier;

import java.util.List;

public class Catalogue {

	private Integer code;

	private String nom;

	private List<Produit> produits;

	public Catalogue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Catalogue(Integer code, String nom, List<Produit> produits) {
		super();
		this.code = code;
		this.nom = nom;
		this.produits = produits;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Catalogue other = (Catalogue) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
