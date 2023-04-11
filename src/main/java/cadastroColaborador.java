import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cadastroColaborador extends JFrame{
    private JPanel cadastroColaborador;
    private JLabel lblCadastroColaborador;
    private JTextField txtNome;
    private JLabel lblNome;
    private JPanel pnlCadastroColaborador;
    private JLabel lblSetor;
    private JLabel lblCargo;
    private JLabel lblHorasTrabalhadas;
    private JTextField txtCargo;
    private JTextField txtHorasTrab;
    private JTextField txtSetor;
    private JButton btnSalvar;
    private JLabel lblCpf;
    private JTextField txtCPF;
    final String URL = "jdbc:mysql://localhost:3306/folhaPagamento";
    final String USER = "root";
    final String PASSWORD = "root";
    final String INSERIR = "INSERT INTO colaborador (cpf, nome, setor, cargo, horas_trabalhadas) VALUES(?,?,?,?,?)";

public cadastroColaborador() {
    iniciarComponentes();
    conectar();

}
    public void iniciarComponentes(){
        setTitle("Folha de Pagamento");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(pnlCadastroColaborador);
        setVisible(true);
    }

    public void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado!");

            final PreparedStatement stmtInserir;

            stmtInserir = con.prepareStatement(INSERIR);
            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cpf = txtCPF.getText();
                    String nome = txtNome.getText();
                    String setor = txtSetor.getText();
                    String cargo = txtCargo.getText();
                    String horas_trabalhadas1 = txtHorasTrab.getText();
                    if (txtCPF.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Insira o CPF corretamente!");
                    }
                    else if (txtNome.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Insira o Nome corretamente!");
                    }
                    else if (txtSetor.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Insira o Setor corretamente!");
                    }
                    else if (txtCargo.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Insira o Cargo corretamente!");
                    }else {
                        try {
                            Double horas_trabalhadas = Double.parseDouble(horas_trabalhadas1);
                            stmtInserir.setString(1, cpf);
                            stmtInserir.setString(2, nome);
                            stmtInserir.setString(3, setor);
                            stmtInserir.setString(4, cargo);
                            stmtInserir.setDouble(5, horas_trabalhadas);
                            stmtInserir.executeUpdate();
                            System.out.println("Dados Inseridos!");
                            CalculoFolha cal = new CalculoFolha();
                            cal.calcularINSS(horas_trabalhadas * 35);
                            cal.calcularIRPF(horas_trabalhadas * 35);
                            Double liquido = (horas_trabalhadas * 35) - (cal.aliqINSS + cal.aliqIRPF);
                            String folhaPagamento = "CPF: " + txtCPF.getText() + "\n"
                                    + "Nome: " + txtNome.getText() + "\n"
                                    + "Setor: " + txtSetor.getText() + "\n"
                                    + "Cargo: " + txtCargo.getText() + "\n"
                                    + "Horas Trabalhadas: " + txtHorasTrab.getText() + "\n"
                                    + "Salário Base: " + horas_trabalhadas * 35 + "\n"
                                    + "INSS: " + cal.aliqINSS + "\n"
                                    + "IRPF: " + cal.aliqIRPF + "\n"
                                    + "Salário Liquido: " + liquido;
                            JOptionPane.showMessageDialog(btnSalvar, folhaPagamento);
                            txtCPF.setText("");
                            txtNome.setText("");
                            txtSetor.setText("");
                            txtCargo.setText("");
                            txtHorasTrab.setText("");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Dado das horas trabalhadas incorretos!");
                        } catch (SQLException ex) {
                            System.out.println("Este CPF já possui cadastro!");
                        }
                    }
                }
            });
        }catch (Exception ex) {
            System.out.println("Erro ao conectar o banco de dados !");
        }
    }

    public static void main(String[] args) {
        cadastroColaborador cad = new cadastroColaborador();
    }
}
