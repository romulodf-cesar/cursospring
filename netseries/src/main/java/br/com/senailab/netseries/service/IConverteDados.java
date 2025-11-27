package br.com.senailab.netseries.service;

public interface IConverteDados {
    //você sabe  que é o retorno
    //Não sabemos qual entidade será devolvida
    <T> T obterDados(String json,Class<T> classe);
}
