/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class IServidorImpl extends UnicastRemoteObject implements IServidor {

    private ArrayList<Icliente> cli = new ArrayList<>();
    private ArrayList<String> nomes = new ArrayList<>();

    public IServidorImpl() throws RemoteException {
        super();
    }

    @Override
    public void enviaMensagem(String mensagem, String dest) throws RemoteException {
        System.out.println("Recebeu: " + mensagem);
        if (dest.equals("Todos")) {
            encTodos(mensagem);
        } else {
            envInd(mensagem,dest);
        }

    }

    public void encTodos(String mensagem) throws RemoteException {
        for (Icliente c : cli) {
            c.notificaRecebimento(mensagem);
        }
    }

    public void envInd(String msg, String dest) throws RemoteException {
        for(Icliente ic: cli){
            if(ic.getNome().equals(dest))
                ic.notificaRecebimento(msg);
        }
    }

    @Override
    public void contatoCliente(Icliente cli) throws RemoteException {
        this.cli.add(cli);
    }

    @Override
    public ArrayList<String> enviaClientes() throws RemoteException {

        for (int i = 0; i < cli.size(); i++) {
            nomes.add(cli.get(i).retornaNome());
        }
        return nomes;
    }

    @Override
    public boolean Atualizar() throws RemoteException {
        return true;
    }
}
