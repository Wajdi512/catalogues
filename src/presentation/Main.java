package presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.CategorieDao;
import dao.IDAOCatalogue;
import dao.IDAOCatalogueImpl;
import dao.IDAOCategorie;
import dao.IDAOProduit;
import dao.ProduitDao;

public class Main {

	private static final int ALL_CATALOGUE = 0;
	private JFrame frame;
	private JTable table;
	ProduitTableModel produitTableModel = new ProduitTableModel();
	private IDAOCatalogue catalogueDao = new IDAOCatalogueImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 618, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Rechercher des produits");
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 602, 472);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("S\u00E9lectionner une cat\u00E9gorie");
		lblNewLabel.setBounds(200, 179, 212, 34);
		panel.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer catalogueId = ALL_CATALOGUE;
				if(comboBox.getSelectedItem() != null) {
					catalogueId = Integer.valueOf(((ComboItem) comboBox.getSelectedItem()).getKey());
				}
				loadProduitsByCategorieId(catalogueId);
			}

		});
		comboBox.setBounds(422, 185, 108, 22);
		panel.add(comboBox);
		loadCatalogueOnComboBox(comboBox);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 602, 22);
		panel.add(menuBar);

		JMenu mnNewMenu = new JMenu("Actions");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Edition catalogue");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditCatalogues edit = new EditCatalogues();
				edit.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						Integer catalogueId = Integer.valueOf(((ComboItem) comboBox.getSelectedItem()).getKey());
						loadCatalogueOnComboBox(comboBox);
					}
				});
				Integer catalogueId = Integer.valueOf(((ComboItem) comboBox.getSelectedItem()).getKey());
				loadProduitsByCategorieId(catalogueId);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ajout produit");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProduit addPrduit = new AddProduit();
				addPrduit.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						Integer catalogueId = Integer.valueOf(((ComboItem) comboBox.getSelectedItem()).getKey());
						loadProduitsByCategorieId(catalogueId);
					}
				});

				try {
					produitTableModel.chargerTable(catalogueDao.findAllProduits());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 224, 582, 237);
		panel.add(scrollPane);
		try {
			produitTableModel.chargerTable(catalogueDao.findAllProduits());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table = new JTable();
		table.setModel(produitTableModel);
		scrollPane.setViewportView(table);
	}

	private void loadCatalogueOnComboBox(final JComboBox catalogueSelect) {
		catalogueSelect.removeAllItems();
		catalogueSelect.addItem(new ComboItem("0", "ALL"));
		try {
			catalogueDao.findAllCategories().forEach(catalogue -> catalogueSelect
					.addItem(new ComboItem(String.valueOf(catalogue.getCode()), catalogue.getNom())));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadProduitsByCategorieId(Integer catalogueId) {
		try {
			if (catalogueId != ALL_CATALOGUE) {
				produitTableModel.chargerTable(catalogueDao.findProduitsByCategorie(catalogueId));
			} else {
				produitTableModel.chargerTable(catalogueDao.findAllProduits());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
