package projeto.util;

import projeto.model.Cliente;

public class Email extends Thread {
    private Cliente cliente;
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Enviando email para " + cliente.getEmail());
    }
}
