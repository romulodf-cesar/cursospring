package br.com.senailab.netseries.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}