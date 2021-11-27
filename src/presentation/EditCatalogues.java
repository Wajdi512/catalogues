package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.CatalogueDao;
import metier.Catalogue;

public class EditCatalogues extends JFrame {

	private JPanel contentPane;
	private JTextField nomTxt;
	private CatalogueDao dao = new CatalogueDao();
	private JTable cataloguesJTable;
	private Catalogue selectedCatalogue = null;

	/**
	 * Launch the application.
	 */
	public EditCatalogues() {
		setTitle("G\u00E9rer les cat\u00E9gories");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("G\u00E9rer les cat\u00E9gories:");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(10, 0, 233, 68);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel_1);

		JLabel lblNom = new JLabel("Nom:");
		lblNom.setBounds(32, 73, 104, 32);
		contentPane.add(lblNom);

		nomTxt = new JTextField();
		nomTxt.setBounds(112, 79, 241, 20);
		nomTxt.setColumns(10);
		contentPane.add(nomTxt);

		JButton btnNewButton = new JButton("Enregistrer");
		btnNewButton.setBounds(379, 78, 143, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addCatalogue();
				} catch (Exception e1) {
					e1.printStackTrace();
					JDialog dialog = new InfoDialog("Exception", e1.getMessage());
					dialog.setVisible(true);

				}
			}

		});
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 154, 698, 311);
		contentPane.add(scrollPane);

		List<Catalogue> lesCatalogues = new ArrayList<>();
		try {
			lesCatalogues = dao.findAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cataloguesJTable = new JTable();
		cataloguesJTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DELETE && selectedCatalogue != null) {
					try {
						dao.delete(selectedCatalogue.getCode());
						JDialog dialog = new InfoDialog("Catalogue supprimé", String.format("le catalogue %s a été supprimé !", selectedCatalogue.getNom()));
						dialog.setVisible(true);
						updateTable();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JDialog dialog = new InfoDialog("Exception", e1.getMessage());
						dialog.setVisible(true);
						e1.printStackTrace();
					}
				}
			}
		});
		updateTable();
		scrollPane.setViewportView(cataloguesJTable);
		cataloguesJTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateCatalogueInputs();
			}

		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void updateTable() {
		CatalogueTableModel catalogueTableModel = new CatalogueTableModel();
		try {
			catalogueTableModel.chargerTable(dao.findAll());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cataloguesJTable.setModel(catalogueTableModel);
	}

	private void addCatalogue() throws SQLException {
		if(this.selectedCatalogue != null) {
			selectedCatalogue.setNom(nomTxt.getText());
			dao.update(selectedCatalogue);
			selectedCatalogue = null;
			JDialog dialog = new InfoDialog("Catalogue modifié", "Done");
			dialog.setVisible(true);
			nomTxt.setText("");
			updateTable();
			return;
		}
		Catalogue catalogue = new Catalogue(null, nomTxt.getText(), null);
		dao.add(catalogue);
		JDialog dialog = new InfoDialog("Catalogue ajouté", "Done");
		dialog.setVisible(true);
		nomTxt.setText("");
		updateTable();
	}

	private void updateCatalogueInputs() {
		int[] selectedRow = cataloguesJTable.getSelectedRows();
		if (selectedRow.length == 0)
			return;
		Integer code = Integer.valueOf(String.valueOf(cataloguesJTable.getValueAt(selectedRow[0], 0)));
		String nom = String.valueOf(cataloguesJTable.getValueAt(selectedRow[0], 1));
		this.selectedCatalogue = new Catalogue(code, nom, null);
		nomTxt.setText(nom);
	}

}
