package br.com.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.dao.CRUDChamado;
import br.com.dominio.Chamado;

public class Atendente extends JFrame {
	private JTextField txtId;
	private JTextField txtStatus;
	private JTextField txtResolucao;
	private JTextField txtResponsavel;
	Long id;
	CRUDChamado cc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Atendente frame = new Atendente();
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
	public Atendente() {
		setTitle("Sistema de suporte - Atendente");
		setBounds(100, 100, 619, 504);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID - Chamado");
		lblNewLabel.setBounds(42, 23, 107, 28);
		getContentPane().add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(31, 57, 86, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Status Chamado");
		lblNewLabel_1.setBounds(384, 30, 126, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtStatus = new JTextField();
		txtStatus.setBounds(361, 57, 126, 20);
		getContentPane().add(txtStatus);
		txtStatus.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Data de resolução do chamado");
		lblNewLabel_2.setBounds(10, 127, 205, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtResolucao = new JTextField();
		txtResolucao.setBounds(31, 156, 118, 20);
		getContentPane().add(txtResolucao);
		txtResolucao.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Responsável do chamado");
		lblNewLabel_3.setBounds(361, 127, 165, 14);
		getContentPane().add(lblNewLabel_3);
		
		txtResponsavel = new JTextField();
		txtResponsavel.setBounds(361, 156, 126, 20);
		getContentPane().add(txtResponsavel);
		txtResponsavel.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Observações");
		lblNewLabel_4.setBounds(121, 227, 126, 14);
		getContentPane().add(lblNewLabel_4);
		
		JTextArea txtObservacoes = new JTextArea();
		txtObservacoes.setBounds(31, 252, 264, 181);
		getContentPane().add(txtObservacoes);
		
		JButton btnAtualizar = new JButton("Atualizar Chamados");
		btnAtualizar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Chamado cr = new Chamado();
				CRUDChamado cc = new CRUDChamado();
				
				if(txtResponsavel.getText().trim().equals("") || txtStatus.getText().trim().equals("") || txtId.getText().trim().equals("") || txtResolucao.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Os campos Responsável Chamado, Id do Chamado, Status do Chamado e Data de Resolução devem ser preenchidos",
							"Erro 4000765x" , JOptionPane.ERROR_MESSAGE); 
				}
				else {
					cr.setDataResolucao(txtResolucao.getText());
					cr.setStatusChamado(txtStatus.getText());
					cr.setAtendente(txtResponsavel.getText()); 
					cr.setObservacoes(txtObservacoes.getText());
					cr.setIdChamado(Long.parseLong(txtId.getText())); 
					JOptionPane.showMessageDialog(null, cc.atualizar(cr)); 

				}
			}
		});
		
		btnAtualizar.setBounds(381, 252, 165, 42);
		getContentPane().add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir Chamados");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtId.equals(null)) {
					JOptionPane.showMessageDialog(null, "Selecione o chamado a ser excluido.","erro 4000770x",JOptionPane.ERROR_MESSAGE);
				}else {
						if(JOptionPane.showConfirmDialog(null, "Você tem certeza desta ação ? \nEstá ação é Permanente"+"e não pode ser desfeita","ATEÇÃO",JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE)== 0) {
				Chamado cr = new Chamado();
				CRUDChamado cc = new CRUDChamado();
				cr.setIdChamado(Long.parseLong(txtId.getText())); 
				JOptionPane.showConfirmDialog(null, cc.apagar(cr));
					}
				}
				
			}
		});
		btnExcluir.setBounds(381, 321, 165, 42);
		getContentPane().add(btnExcluir);
	
	}

}
