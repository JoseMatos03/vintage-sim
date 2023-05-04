package vintage;

import java.util.Timer;
import java.util.TimerTask;

import com.googlecode.lanterna.gui2.Label;

import static vintage.utils.vintage.Utils.FORMATTER;

public class Clock {
    public static void run(Vintage loja) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                loja.setTempoAtual(loja.getTempoAtual().plusSeconds(1));
            }
        }, 0, 1000);
    }

    public static void update(Vintage loja, Label data) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                data.setText(loja.getTempoAtual().format(FORMATTER));
            }
        }, 0, 1000);
    }
}
