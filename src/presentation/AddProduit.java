package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import constants.Constants;
import dao.CatalogueDao;
import dao.IDAOCatalogue;
import dao.IDAOProduit;
import dao.ProduitDao;
import metier.Produit;

public class AddProduit extends JFrame {

	private JPanel contentPane;
	private JTextField referenceTxt;
	private JTextField nomTxt;
	private JTextField prixTxt;
	private JTextField qteTxt;

	private IDAOProduit produitDao = new ProduitDao();
	private IDAOCatalogue catalogueDao = new CatalogueDao();

	/**
	 * Create the frame.
	 */
	public AddProduit() {
		setTitle("Ajout produit");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(Constants.PRODUIT_AJOUT_LABLEL);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(219, 23, 125, 61);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(Constants.PRODUIT_REFERENCE_LABLEL);
		lblNewLabel_1.setBounds(22, 109, 63, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel(Constants.PRODUIT_NOM_LABLEL);
		lblNewLabel_1_1.setBounds(22, 158, 63, 25);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel(Constants.PRODUIT_PRIX_LABLEL);
		lblNewLabel_1_1_1.setBounds(22, 213, 63, 25);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel(Constants.PRODUIT_QUANTITE_LABLEL);
		lblNewLabel_1_1_1_1.setBounds(22, 281, 63, 25);
		contentPane.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel(Constants.PRODUIT_CATATLOGUE_LABLEL);
		lblNewLabel_1_1_1_1_1.setBounds(22, 344, 63, 25);
		contentPane.add(lblNewLabel_1_1_1_1_1);

		referenceTxt = new JTextField();
		referenceTxt.setBounds(132, 111, 175, 20);
		contentPane.add(referenceTxt);
		referenceTxt.setColumns(10);

		nomTxt = new JTextField();
		nomTxt.setColumns(10);
		nomTxt.setBounds(132, 160, 175, 20);
		contentPane.add(nomTxt);

		prixTxt = new JTextField();
		prixTxt.setColumns(10);
		prixTxt.setBounds(132, 215, 175, 20);
		contentPane.add(prixTxt);

		qteTxt = new JTextField();
		qteTxt.setColumns(10);
		qteTxt.setBounds(132, 281, 175, 20);
		contentPane.add(qteTxt);

		JComboBox catalogueSelect = new JComboBox();
		catalogueSelect.setBounds(132, 345, 175, 22);

		contentPane.add(catalogueSelect);
		loadCatalogueOnComboBox(catalogueSelect);
		JButton btnNewButton = new JButton(Constants.ENREGISTRER);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer codeCatalogue = Integer.valueOf(((ComboItem) catalogueSelect.getSelectedItem()).getKey());
				saveProduit(codeCatalogue);
			}
		});
		btnNewButton.setBounds(251, 431, 154, 23);
		contentPane.add(btnNewButton);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void loadCatalogueOnComboBox(final JComboBox catalogueSelect) {
		try {
			catalogueDao.findAll().forEach(catalogue -> catalogueSelect
					.addItem(new ComboItem(String.valueOf(catalogue.getCode()), catalogue.getNom())));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveProduit(Integer codeCatalogue) {
		Produit newProduit = new Produit(Integer.valueOf(referenceTxt.getText()), nomTxt.getText(),
				Double.valueOf(prixTxt.getText()), Integer.valueOf(qteTxt.getText()), null);
		try {
			newProduit.setCatalogue(catalogueDao.findById(codeCatalogue));
			produitDao.add(newProduit);
			JDialog dialog = new InfoDialog(Constants.PRODUIT_AJOUTE, Constants.PRODUIT_AJOUTE_INF);
			dialog.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
			JDialog dialog = new InfoDialog(Constants.ERROR, e1.getMessage());
			dialog.setVisible(true);

		}
	}

}
