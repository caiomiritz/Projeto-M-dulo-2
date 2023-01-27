package br.com.ada.crud.controller.estado;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.model.estado.dao.EstadoDAO;
import br.com.ada.crud.model.estado.dao.impl.EstadoBinariaDAO;
import br.com.ada.crud.model.estado.dao.impl.EstadoXmlDAO;
import br.com.ada.crud.persistencia.PersistenciaTipo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EstadoDAOFactory {

    private static final EstadoDAOFactory INSTANCE = new EstadoDAOFactory();
    private static final String PERSISTENCIA_TIPO = "estado.persistencia.tipo";

    private PersistenciaTipo tipo;

    private EstadoDAOFactory() {
    }

    public static EstadoDAOFactory getInstance() {
        return INSTANCE;
    }

    public EstadoDAO criar() {

        if (tipo == null) {
            carregarTipoPersistencia();
        }
        EstadoDAO estadoDAO = null;

        if (tipo == PersistenciaTipo.BINARIO) {
            estadoDAO = new EstadoBinariaDAO();
        }
        else if (tipo == PersistenciaTipo.XML) {
            estadoDAO = new EstadoXmlDAO();
        }
        return estadoDAO;
    }

    private void carregarTipoPersistencia() {

        try {

            Properties properties = new Properties();
            properties.load(new FileInputStream(Constantes.ARQUIVO_PROPRIEDADES));

            String valorNoArquivo = properties.getProperty(PERSISTENCIA_TIPO);
            tipo = PersistenciaTipo.valueOf(valorNoArquivo);
        }
        catch (IOException ex) {

            throw new RuntimeException("Não foi possível ler o arquivo de configurações", ex);
        }
    }
}
