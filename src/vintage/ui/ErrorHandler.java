package vintage.ui;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

import vintage.utils.ErrorCode;

public class ErrorHandler {
    public static void handleError(WindowBasedTextGUI gui, ErrorCode errorCode) {
        switch (errorCode) {
            case SEM_ESPACO:
                MessageDialog.showMessageDialog(gui, "Erro", "A encomenda já está cheia");
                break;
            case EM_ENCOMENDA:
                MessageDialog.showMessageDialog(gui, "Erro", "O artigo já está noutra encomenda");
                break;
            case EM_EXPEDICAO:
                MessageDialog.showMessageDialog(gui, "Erro", "A encomenda já foi expedida.");
                break;
            case NO_ERRORS:
                break;
            default:
                break;
        }
    }
}
