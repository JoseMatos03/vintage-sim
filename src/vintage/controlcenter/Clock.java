package vintage.controlcenter;

import static vintage.utils.vintage.Utils.FORMATTER;

import java.util.Timer;
import java.util.TimerTask;

import com.googlecode.lanterna.gui2.Label;

import vintage.Vintage;

public class Clock {
    public static void run(Timer timer, Vintage loja) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                loja.setTempoAtual(loja.getTempoAtual().plusSeconds(1));
            }
        }, 0, 1000);
    }

    public static void update(Timer timer, Vintage loja, Label data) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                data.setText(loja.getTempoAtual().format(FORMATTER));
                loja.entregarEncomendas();
            }
        }, 0, 1000);
    }
}
