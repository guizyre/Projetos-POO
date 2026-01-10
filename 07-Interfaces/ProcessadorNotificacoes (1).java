package academy.guih.poo.javacore.classes.Interface.Dominio;

import java.util.List;

public class ProcessadorNotificacoes {
    public void enviarTodas(List<Notificavel> notificaveis) {
        for (Notificavel n : notificaveis) {
            n.enviarNotificacao();
        }
    }
}
