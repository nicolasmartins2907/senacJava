package br.com.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.dao.CRUDChamado;
import br.com.dominio.Chamado;

public class SistemaDeSuporte extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtDepartamento;
	private JTextArea txtDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SistemaDeSuporte frame = new SistemaDeSuporte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SistemaDeSuporte() {
		setTitle("Sistema de Suporte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Preencha todos os campos para efetuar um cadastro");
		lblNewLabel.setBounds(35, 11, 331, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Insira seu nome:");
		lblNewLabel_1.setBounds(35, 50, 102, 14);
		contentPane.add(lblNewLabel_1);
		
		txtNome = new JTextField();
		txtNome.setBounds(35, 75, 175, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Insira com qual departamento deseja falar:");
		lblNewLabel_2.setBounds(35, 110, 258, 14);
		contentPane.add(lblNewLabel_2);
		
		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(35, 137, 188, 20);
		contentPane.add(txtDepartamento);
		txtDepartamento.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Conte-nos mais sobre o problema:");
		lblNewLabel_3.setBounds(35, 182, 219, 14);
		contentPane.add(lblNewLabel_3);
		
		JTextArea txtDescricao = new JTextArea();
		txtDescricao.setBounds(35, 210, 284, 202);
		contentPane.add(txtDescricao);
		
		JButton btnRegistrar = new JButton("Registrar Chamado");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CRUDChamado cc = new CRUDChamado();

				
				Chamado soliChamado = new Chamado(); 
				
				 
				if(txtNome.getText().trim().equals("") || 
				txtDepartamento.getText().trim().equals("") || 
				txtDescricao.getText().trim().equals("")) { 
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Erro 4000765x" , JOptionPane.ERROR_MESSAGE); 
				} 
				else { 
				 
				soliChamado.setSolicitacao(txtNome.getText()); 
				 
				soliChamado.setDepSolicitado(txtDepartamento.getText()); 
				 
				soliChamado.setDescChamado(txtDescricao.getText()); 
				 
				JOptionPane.showMessageDialog(null, cc.registrar(soliChamado)); 
				 
				} 
			}
			
		});
		btnRegistrar.setBounds(354, 268, 125, 43);
		contentPane.add(btnRegistrar);
		
			
		}
	
	
	
	}
	
