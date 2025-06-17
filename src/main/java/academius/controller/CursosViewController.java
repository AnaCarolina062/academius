package academius.controller;

import academius.dao.CursoDAO; // Importe o DAO se ainda não estiver lá
import academius.model.Curso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador unificado que gerencia tanto a interface gráfica (FXML)
 * quanto a lógica de negócio para a tela de Cursos.
 */
public class CursosViewController implements Initializable {

    // --- Parte FXML: Injeção dos componentes da tela ---
    @FXML
    private TableView<Curso> tabelaCursos;
    @FXML
    private TableColumn<Curso, Long> colId;
    @FXML
    private TableColumn<Curso, String> colNome;
    @FXML
    private TableColumn<Curso, Integer> colCargaHoraria;

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCargaHoraria;

    // --- Parte Lógica: Acesso ao Banco de Dados ---
    // A linha com erro foi removida e substituída pela instância do DAO.
    private CursoDAO cursoDAO = new CursoDAO();
    private ObservableList<Curso> observableListCursos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTableColumns();
        loadCursosTable();

        tabelaCursos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> preencherFormulario(newValue));
    }

    // ✅ MÉTODO QUE ESTAVA FALTANDO
    private void setupTableColumns() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCargaHoraria.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
    }

    // ✅ MÉTODO QUE ESTAVA FALTANDO
    private void loadCursosTable() {
        observableListCursos = FXCollections.observableArrayList(cursoDAO.findAll());
        tabelaCursos.setItems(observableListCursos);
    }

    @FXML
    private void handleAdicionar() {
        if (isInputValid()) {
            Curso novoCurso = new Curso();
            novoCurso.setNome(txtNome.getText());
            novoCurso.setCargaHoraria(Integer.parseInt(txtCargaHoraria.getText()));
            cursoDAO.create(novoCurso);
            loadCursosTable();
            handleLimpar();
        }
    }

    @FXML
    private void handleAtualizar() {
        Curso selectedCurso = tabelaCursos.getSelectionModel().getSelectedItem();
        if (selectedCurso != null && isInputValid()) {
            selectedCurso.setNome(txtNome.getText());
            selectedCurso.setCargaHoraria(Integer.parseInt(txtCargaHoraria.getText()));
            cursoDAO.update(selectedCurso);
            loadCursosTable();
            handleLimpar();
        } else {
            showAlert("Nenhum curso selecionado", "Por favor, selecione um curso na tabela para atualizar.");
        }
    }

    @FXML
    private void handleDeletar() {
        Curso selectedCurso = tabelaCursos.getSelectionModel().getSelectedItem();
        if (selectedCurso != null) {
            cursoDAO.delete(selectedCurso.getId());
            loadCursosTable();
            handleLimpar();
        } else {
            showAlert("Nenhum curso selecionado", "Por favor, selecione um curso na tabela para deletar.");
        }
    }

    @FXML
    private void handleLimpar() {
        tabelaCursos.getSelectionModel().clearSelection();
        txtNome.clear();
        txtCargaHoraria.clear();
        txtNome.requestFocus();
    }

    private void preencherFormulario(Curso curso) {
        if (curso != null) {
            txtNome.setText(curso.getNome());
            txtCargaHoraria.setText(String.valueOf(curso.getCargaHoraria()));
        }
    }

    // ✅ MÉTODO QUE ESTAVA FALTANDO
    private boolean isInputValid() {
        String errorMessage = "";
        if (txtNome.getText() == null || txtNome.getText().trim().isEmpty()) {
            errorMessage += "Nome inválido!\n";
        }
        if (txtCargaHoraria.getText() == null || txtCargaHoraria.getText().trim().isEmpty()) {
            errorMessage += "Carga horária inválida!\n";
        } else {
            try {
                Integer.parseInt(txtCargaHoraria.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Carga horária deve ser um número inteiro!\n";
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            showAlert("Campos Inválidos", errorMessage);
            return false;
        }
    }

    // ✅ MÉTODO QUE ESTAVA FALTANDO
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}