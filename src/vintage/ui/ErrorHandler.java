package vintage.ui;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

import vintage.utils.ErrorCode;

public class ErrorHandler {
    public static void handleError(WindowBasedTextGUI gui, ErrorCode errorCode) {
        switch (errorCode) {
            case ARTIGO_EXPEDIDO:
                MessageDialog.showMessageDialog(gui, "Erro", "O artigo já foi expedido.");
                break;
            case SEM_ESPACO:
                MessageDialog.showMessageDialog(gui, "Erro", "A encomenda está cheia.");
                break;
            case ENCOMENDA_VAZIA:
                MessageDialog.showMessageDialog(gui, "Erro", "A encomenda está vazia.");
                break;
            case EM_ENCOMENDA:
                MessageDialog.showMessageDialog(gui, "Erro", "O artigo já está noutra encomenda.");
                break;
            case EM_EXPEDICAO:
                MessageDialog.showMessageDialog(gui, "Erro", "A encomenda já foi expedida.");
                break;
            case SEM_REEMBOLSO:
                MessageDialog.showMessageDialog(gui, "Erro", "O prazo de reembolso já ultrapassou.");
                break;
            case PARAMETRO_ERRADO:
                MessageDialog.showMessageDialog(gui, "Erro", "Erro em parâmetros obrigatórios.");
                break;
            case NO_ERRORS:
                break;
            default:
                break;
        }
    }
}
